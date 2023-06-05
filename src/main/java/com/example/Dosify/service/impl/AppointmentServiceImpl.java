package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Exception.*;
import com.example.Dosify.Transformers.AppointmentTransformer;
import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.model.*;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.service.Dose2Service;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose1Service dose1Service;

    @Autowired
    Dose2Service dose2Service;


    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws DifferentVaccineTypeException,UserNotPresentException, DoctorNotPresentException, Dose1NotTakenException, DoseAlreadyTakenException {

        //Check whether the User exist or not
        Optional<User> userOptional = this.userRepository.findById(appointmentRequestDto.getUserId());
        if( userOptional.isEmpty() ){
            throw new UserNotPresentException("User does not exist");
        }

        //Check whether the Doctor exist or not
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if( doctorOptional.isEmpty() ){
            throw new DoctorNotPresentException("Doctor does not exist");
        }

        //Get Entities:
        User user = userOptional.get();
        Doctor doctor = doctorOptional.get();
        //Create Appointment Entity:
        Appointment appointment = AppointmentTransformer.AppointmentRequestDtoToAppointment(appointmentRequestDto, user, doctor);

        //Different appointment booking procedure for different doses:
        // CALL METHODS OF Dose1Service & Dose2Service to get dose1 and dose2 objects.
        if( appointmentRequestDto.getDoseNo() == DoseNo.DOSE_1 ){
            //check if dose already taken:
            if( user.isDose1Taken() ){
                throw new DoseAlreadyTakenException("Dose is already taken!!");
            }

            //assigning dose1 to user:
            Dose1 dose1 = this.dose1Service.bookDose1Appointment(user, appointmentRequestDto.getVaccineType());
            user.setDose1(dose1);
            user.setDose1Taken(true);
        }
        else{
            //check isDose1Taken == true? before giving 2nd dose:
            if( !user.isDose1Taken() ){
                throw new Dose1NotTakenException("Dose1 is not taken! Take Dose1 before Dose2");
            }
            //check if dose2 already taken:
            if( user.isDose2Taken() ){
                throw new DoseAlreadyTakenException("Dose is already taken!!");
            }

            //check for the vaccineType of both the doses:
            String dose1Type = String.valueOf(user.getDose1().getVaccineType());
            String dose2Type = String.valueOf(appointmentRequestDto.getVaccineType());
            if( !dose1Type.equals(dose2Type) ){
                throw new DifferentVaccineTypeException("Different vaccine type than DOSE_1");
            }

            //assigning dose2 to user.
            Dose2 dose2 = this.dose2Service.bookDose2Appointment(user, appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }

        //we need to save -> dose1/dose2, appointment
        //save common parent to save the child classes/entities (here parent = USER)
        user.getAppointments().add(appointment);
        User savedUser = userRepository.save(user);

        //update and save doctor entity
        Appointment savedAppointment = savedUser.getAppointments().get(savedUser.getAppointments().size()-1);

        //savedAppointment is added to doctor to don't get duplicate entries:
        // since until the appointment is not saved it does not get any primary key,
        // if we add the appointments without P.K
        // while saving the parent due to bi-directional mapping/relation, it'll get saved multiple times.
        // will get assigned with auto-generated primary keys.

        //Therefore, add to user.....save the user......so that the appointment gets a PK and gets stored in the DB
        // add that particular savedAppointment to all other references.
        doctor.getAppointments().add(savedAppointment);

        //saving the doctor is optional, since it'll not make any difference:
        Doctor savedDoctor = doctorRepository.save(doctor);

        //Prepare AppointmentResponseDto:
        AppointmentResponseDto appointmentResponseDto = AppointmentTransformer.AppointmentToAppointmentResponseDto(savedAppointment, savedUser, savedDoctor);

        //Send real-time mails for dose booking;
        String text = "Congratulations Your booking for "
                + appointmentRequestDto.getDoseNo()
                + " have been successfully booked."
                + "\nDetails: "
                + "\nAppointment number : " + savedAppointment.getAppointmentNo()
                + "\nAppointment date : " + savedAppointment.getAppointmentDate()
                + "\nVaccination Center : " + savedDoctor.getVaccinationCenter()
                + "\nDoctor : " + savedDoctor.getName() + "gender: " + savedDoctor.getGender() + " mobile : " + savedDoctor.getContactNo()
                + "\n\n Please make sure to get yourself vaccinated!!";


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dosifydosify@gmail.com");
        message.setTo(savedUser.getEmail());
        message.setSubject("Appointment Booked Successfully!!");
        message.setText(text);
        mailSender.send(message);

        return appointmentResponseDto;
    }
}

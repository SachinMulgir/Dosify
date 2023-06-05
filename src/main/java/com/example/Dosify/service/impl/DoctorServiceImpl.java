package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.CenterNotPresentException;
import com.example.Dosify.Transformers.DoctorTransformer;
import com.example.Dosify.Transformers.UserTransformer;
import com.example.Dosify.Transformers.VaccinationCenterTransformer;
import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = this.vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
        if( optionalCenter.isEmpty() ){
            throw new CenterNotPresentException("Center Does Not Exist!!");
        }

        // doctorRequestDto -> Entity
        Doctor doctor = DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);

        // setting center for doctor
        VaccinationCenter vaccinationCenter = optionalCenter.get();
        doctor.setVaccinationCenter(vaccinationCenter);

        // adding doctor to list<doctor> in VaccinationCenter
        vaccinationCenter.getDoctors().add(doctor);

        // save doctor to the database
        Doctor savedDoctor = this.doctorRepository.save(doctor);

        // Entity -> DoctorResponseDto
        return DoctorTransformer.DoctorToDoctorResponseDto(savedDoctor);
    }

    @Override
    public List<GetPersonEntityResponseDto> getDoctorsWithNAppointments(Integer num) {
        List<Doctor> doctors = this.doctorRepository.findAll();
        List<GetPersonEntityResponseDto> entityResponseDtoList = new ArrayList<>();
        for( Doctor doctor : doctors ){
            int noOfAppointments = doctor.getAppointments().size();
            if( noOfAppointments >= num ){
                GetPersonEntityResponseDto personEntityResponseDto = DoctorTransformer.UserToGetPersonEntityResponseDto(doctor);
                entityResponseDtoList.add(personEntityResponseDto);
            }
        }
        return entityResponseDtoList;
    }

//    @Override
//    public List<GetPersonEntityResponseDto> getDoctorsOfGenderAboveAge(Gender gender, int age) {
//        List<GetPersonEntityResponseDto> getPersonEntityResponseDtos = new ArrayList<>();
//        List<Doctor> doctors = this.doctorRepository.findOfGenderAboveAge(gender, age);
//         for( Doctor d : doctors ){
//                GetPersonEntityResponseDto getPersonEntityResponseDto = DoctorTransformer.UserToGetPersonEntityResponseDto(d);
//                getPersonEntityResponseDtos.add(getPersonEntityResponseDto);
//         }
//        return getPersonEntityResponseDtos;
//    }
}

package com.example.Dosify.Transformers;

import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.User;

import java.util.UUID;

public class AppointmentTransformer {

    public static Appointment AppointmentRequestDtoToAppointment(AppointmentRequestDto appointmentRequestDto, User user, Doctor doctor) {

        return Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .build();
    }

    public static AppointmentResponseDto AppointmentToAppointmentResponseDto(Appointment appointment, User user, Doctor doctor) {

        DoctorResponseDto doctorResponseDto = DoctorTransformer.DoctorToDoctorResponseDto(doctor);

        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .dateOfAppointment(appointment.getAppointmentDate())
                .doseNo(appointment.getDoseNo())
                .appointmentNo(appointment.getAppointmentNo())
                .doctorResponseDto(doctorResponseDto)
                .vaccineType(user.getDose1().getVaccineType())
                .build();
    }
}

package com.example.Dosify.Transformers;


import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.Dosify.model.Doctor;

public class DoctorTransformer {

    public static Doctor DoctorRequestDtoToDoctor (DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .email(doctorRequestDto.getEmail())
                .contactNo(doctorRequestDto.getContactNo())
                .gender(doctorRequestDto.getGender())
                .build();
    }

    public static DoctorResponseDto DoctorToDoctorResponseDto(Doctor doctor){

        VaccinationCenterResponseDto vaccinationCenterResponseDto = VaccinationCenterTransformer.VaccinationCenterToVaccinationCenterResponseDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .contactNo(doctor.getContactNo())
                .email(doctor.getEmail())
                .vaccinationCenterResponseDto(vaccinationCenterResponseDto)
                .build();
    }

    public static GetPersonEntityResponseDto UserToGetPersonEntityResponseDto(Doctor doctor) {
        return GetPersonEntityResponseDto.builder()
                .name(doctor.getName())
                .age(doctor.getAge())
                .email(doctor.getEmail())
                .contactNo(doctor.getContactNo())
                .gender(doctor.getGender())
                .build();
    }
}

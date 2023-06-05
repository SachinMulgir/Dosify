package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.CenterNotPresentException;
import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;

import java.util.List;

public interface DoctorService {
    DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;

    List<GetPersonEntityResponseDto> getDoctorsWithNAppointments(Integer num);

   // List<GetPersonEntityResponseDto> getDoctorsOfGenderAboveAge(Gender gender, int age);
}

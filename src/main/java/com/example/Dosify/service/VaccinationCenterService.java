package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;

public interface VaccinationCenterService {
    VaccinationCenterResponseDto addVaccinationCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto);
}

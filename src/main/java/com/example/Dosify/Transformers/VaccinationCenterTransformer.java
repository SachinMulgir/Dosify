package com.example.Dosify.Transformers;

import com.example.Dosify.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;

public class VaccinationCenterTransformer {

    public static VaccinationCenter VaccinationCenterRequestDtoToVaccinationCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto){
        return VaccinationCenter.builder()
                .name(vaccinationCenterRequestDto.getName())
                .location(vaccinationCenterRequestDto.getLocation())
                .centerType(vaccinationCenterRequestDto.getCenterType())
                .build();
    }

    public static VaccinationCenterResponseDto VaccinationCenterToVaccinationCenterResponseDto(VaccinationCenter vaccinationCenter){
        return VaccinationCenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }
}

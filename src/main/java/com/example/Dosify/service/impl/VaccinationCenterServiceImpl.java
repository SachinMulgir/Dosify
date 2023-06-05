package com.example.Dosify.service.impl;

import com.example.Dosify.Transformers.VaccinationCenterTransformer;
import com.example.Dosify.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public VaccinationCenterResponseDto addVaccinationCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto) {

        //convert VaccinationCenterRequestDto -> Entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.VaccinationCenterRequestDtoToVaccinationCenter(vaccinationCenterRequestDto);

        //save the entity
        VaccinationCenter savedVaccinationCenter = this.vaccinationCenterRepository.save(vaccinationCenter);

        // entity -> VaccinationCenterResponseDto
        return VaccinationCenterTransformer.VaccinationCenterToVaccinationCenterResponseDto(savedVaccinationCenter);
    }
}

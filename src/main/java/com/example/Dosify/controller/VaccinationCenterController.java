package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDto.VaccinationCenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dosify/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public ResponseEntity addVaccinationCenter(@RequestBody VaccinationCenterRequestDto vaccinationCenterRequestDto){
        VaccinationCenterResponseDto vaccinationCenterResponseDto = this.vaccinationCenterService.addVaccinationCenter(vaccinationCenterRequestDto);
        return new ResponseEntity(vaccinationCenterResponseDto, HttpStatus.CREATED);
    }
}

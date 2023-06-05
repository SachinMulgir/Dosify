package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.CenterNotPresentException;
import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dosify/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    //POST APIs:

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try{
            DoctorResponseDto doctorResponseDto = this.doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);

        }catch (CenterNotPresentException ex){

            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //GET apis:
    @GetMapping("/appointment/{num}")
    public List<GetPersonEntityResponseDto> getDoctorWithNAppointments(@PathVariable int num){
        return this.doctorService.getDoctorsWithNAppointments(num);
    }

//    @GetMapping("/gender/above-age")
//    public List<GetPersonEntityResponseDto> getDoctorsOfGenderAboveAge(@RequestParam("gender") Gender gender, @RequestParam("age") int age){
//        return this.doctorService.getDoctorsOfGenderAboveAge(gender, age);
//    }


}

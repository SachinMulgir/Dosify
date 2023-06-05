package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dosify/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){

        try{
            AppointmentResponseDto appointmentResponseDto = this.appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity(appointmentResponseDto, HttpStatus.CREATED);

        }catch (Exception ex){

            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}

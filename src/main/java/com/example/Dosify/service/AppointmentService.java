package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;

public interface AppointmentService {

    AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto);

}

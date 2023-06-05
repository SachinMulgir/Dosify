package com.example.Dosify.dto.ResponseDto;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Doctor;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {

    String userName;

    String appointmentNo;

    Date dateOfAppointment;

    DoseNo doseNo;

    VaccineType vaccineType;

    DoctorResponseDto doctorResponseDto;

}

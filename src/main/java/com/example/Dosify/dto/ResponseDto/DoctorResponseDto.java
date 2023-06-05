package com.example.Dosify.dto.ResponseDto;

import com.example.Dosify.model.VaccinationCenter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {

    String name;

    String contactNo;

    String email;

    VaccinationCenterResponseDto vaccinationCenterResponseDto;

}

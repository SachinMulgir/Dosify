package com.example.Dosify.dto.RequestDto;

import com.example.Dosify.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VaccinationCenterRequestDto {

    String name;

    String location;

    CenterType centerType;
}

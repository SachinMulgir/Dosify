package com.example.Dosify.dto.ResponseDto;

import com.example.Dosify.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPersonEntityResponseDto {

    String name;

    int age;

    String email;

    String contactNo;

    Gender gender;
}

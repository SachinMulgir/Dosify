package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;

import java.util.List;

public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);

    GetPersonEntityResponseDto findByEmail(String email);

    List<GetPersonEntityResponseDto> noDoseUsers();

    List<GetPersonEntityResponseDto> allDoseUsers();

    List<GetPersonEntityResponseDto> oneDoseUsers();

    List<GetPersonEntityResponseDto> noDoseUsersByGender(Gender gender);
}

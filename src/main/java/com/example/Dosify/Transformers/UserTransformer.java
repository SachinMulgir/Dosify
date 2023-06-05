package com.example.Dosify.Transformers;

import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.model.User;

public class UserTransformer {

    public static User UserRequestDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .email(userRequestDto.getEmail())
                .contactNo(userRequestDto.getContactNo())
                .gender(userRequestDto.getGender())
                .build();
    }

    public static UserResponseDto UserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats!! You've Registered To Dosify")
                .build();
    }

    public static GetPersonEntityResponseDto UserToGetPersonEntityResponseDto(User user){
        return GetPersonEntityResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .contactNo(user.getContactNo())
                .gender(user.getGender())
                .build();
    }
}

package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.UserNotPresentException;
import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dosify/user")
public class UserController {

    @Autowired
    UserService userService;

    // Post Mapping:

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
         UserResponseDto userResponseDto = this.userService.addUser(userRequestDto);
         return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }


    // Get Mapping:
    @GetMapping("/find-by-email")
    public ResponseEntity findByEmail(@RequestParam("email") String email){
        try{
            GetPersonEntityResponseDto getPersonEntityResponseDto = this.userService.findByEmail(email);
            return new ResponseEntity(getPersonEntityResponseDto, HttpStatus.FOUND);
        }catch (UserNotPresentException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/no-dose")
    public List<GetPersonEntityResponseDto> noDoseUsers(){
        return this.userService.noDoseUsers();
    }

    @GetMapping("/all-dose")
    public List<GetPersonEntityResponseDto> allDoseUsers(){
        return this.userService.allDoseUsers();
    }

    @GetMapping("/one-dose")
    public List<GetPersonEntityResponseDto> oneDoseUsers(){
        return this.userService.oneDoseUsers();
    }

    @GetMapping("/no-dose/gender")
    public List<GetPersonEntityResponseDto> noDoseUsersByGender(@RequestParam("gender") Gender gender){
        return this.userService.noDoseUsersByGender(gender);
    }
}

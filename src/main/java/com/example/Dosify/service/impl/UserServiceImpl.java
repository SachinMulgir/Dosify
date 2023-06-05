package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.UserNotPresentException;
import com.example.Dosify.Transformers.UserTransformer;
import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.dto.ResponseDto.GetPersonEntityResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        //Convert UserRequestDto -> User Entity:
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);

        //Save user:
        User savedUser = this.userRepository.save(user);

        //Convert savedUser -> UserResponseDto
        return UserTransformer.UserToUserResponseDto(savedUser);
    }

    @Override
    public GetPersonEntityResponseDto findByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        if( user == null ){
            throw new UserNotPresentException("User not present");
        }
        return UserTransformer.UserToGetPersonEntityResponseDto(user);
    }

    @Override
    public List<GetPersonEntityResponseDto> noDoseUsers() {
        List<GetPersonEntityResponseDto> listNoDoseUsers = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for( User user : users ){
            if( user.isDose1Taken() == false ){
                GetPersonEntityResponseDto getPersonEntityResponseDto = UserTransformer.UserToGetPersonEntityResponseDto(user);
                listNoDoseUsers.add(getPersonEntityResponseDto);
            }
        }
        return listNoDoseUsers;
    }

    @Override
    public List<GetPersonEntityResponseDto> allDoseUsers() {
        List<GetPersonEntityResponseDto> listAllDoseUsers = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for( User user : users ){
            if( user.isDose2Taken() == true ){
                GetPersonEntityResponseDto getPersonEntityResponseDto = UserTransformer.UserToGetPersonEntityResponseDto(user);
                listAllDoseUsers.add(getPersonEntityResponseDto);
            }
        }
        return listAllDoseUsers;
    }

    @Override
    public List<GetPersonEntityResponseDto> oneDoseUsers() {
        List<GetPersonEntityResponseDto> listNoDoseUsers = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for( User user : users ){
            if( user.isDose1Taken() == true && user.isDose2Taken() == false ){
                GetPersonEntityResponseDto getPersonEntityResponseDto = UserTransformer.UserToGetPersonEntityResponseDto(user);
                listNoDoseUsers.add(getPersonEntityResponseDto);
            }
        }
        return listNoDoseUsers;
    }

    @Override
    public List<GetPersonEntityResponseDto> noDoseUsersByGender(Gender gender) {
        List<GetPersonEntityResponseDto> noDoseList = noDoseUsers();
        List<GetPersonEntityResponseDto> genderNoDoseList = new ArrayList<>();
        for( GetPersonEntityResponseDto dto : noDoseList ){
            if( dto.getGender() == gender ){
                genderNoDoseList.add(dto);
            }
        }
        return genderNoDoseList;
    }
}

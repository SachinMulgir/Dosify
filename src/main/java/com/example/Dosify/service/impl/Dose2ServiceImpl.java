package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.Dose2Repository;
import com.example.Dosify.service.Dose2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose2ServiceImpl implements Dose2Service {

    @Autowired
    Dose2Repository dose2Repository;

    @Override
    public Dose2 bookDose2Appointment(User user, VaccineType vaccineType) {

        Dose2 dose2 = Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();

        return dose2;
    }
}

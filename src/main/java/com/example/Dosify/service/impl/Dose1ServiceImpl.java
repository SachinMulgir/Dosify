package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.Dose1Repository;
import com.example.Dosify.service.Dose1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose1ServiceImpl implements Dose1Service {

    @Autowired
    Dose1Repository dose1Repository;

    @Override
    public Dose1 bookDose1Appointment(User user, VaccineType vaccineType) {

        Dose1 dose1 = Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();

        return dose1;
    }
}

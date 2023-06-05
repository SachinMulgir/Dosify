package com.example.Dosify.controller;

import com.example.Dosify.service.Dose1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose1")
public class Dose1Controller {

    @Autowired
    Dose1Service dose1Service;
}

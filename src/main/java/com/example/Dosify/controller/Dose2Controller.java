package com.example.Dosify.controller;

import com.example.Dosify.service.Dose2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose2")
public class Dose2Controller {

    @Autowired
    Dose2Service dose2Service;
}

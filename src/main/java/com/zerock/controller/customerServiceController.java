package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class customerServiceController {

    @GetMapping(value = "/notic")
    public  String notic(){

        return "Custom/notic";
    }

    @GetMapping(value = "/inquity")
    public  String inquity(){

        return "Custom/inquity";
    }

}

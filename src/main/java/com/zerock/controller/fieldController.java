package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class fieldController {

    @GetMapping(value = "/video")
    public String fieldVideo() {

        return "field/video";
    }

}

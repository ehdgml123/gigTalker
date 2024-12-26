package com.zerock.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginMainController {

    @GetMapping(value = "/loginMain")
    public String loginMain(Model model) {


        return "login/loginMain";
    }


    @GetMapping(value = "EmailLogin.html")
    public String EmailLogin(Model model) {

        return "login/emailLogin";
    }


}
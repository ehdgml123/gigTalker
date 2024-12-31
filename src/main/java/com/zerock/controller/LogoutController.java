package com.zerock.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

 @GetMapping(value = "logout")
    public String logout(HttpSession session) {

     session.invalidate();

     return "redirect:/";
 }

}

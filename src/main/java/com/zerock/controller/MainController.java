package com.zerock.controller;

import com.zerock.Entity.Users;
import com.zerock.service.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class MainController {

    @Autowired
    private  UsersService usersService;

     @GetMapping(value = "/")
    public String main(Model model, HttpSession session) {

         Users loggedInuser = (Users) session.getAttribute("user");

         if (loggedInuser != null) {
             model.addAttribute("userName", loggedInuser.getName());
             log.info("Logged in user:" + loggedInuser.getName());
         }else {
             model.addAttribute("userName", "Guest");
             log.info("No user found in session.");
         }

         return "Main";
     }

}

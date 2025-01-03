package com.zerock.controller;

import com.zerock.Entity.Users;
import com.zerock.service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/EditInformation")
    public String editInformation(Model model, HttpSession session) {

         Users loggedInUser = (Users) session.getAttribute("user");

         if(loggedInUser != null) {
             model.addAttribute("user", loggedInUser);
         }else {
             return "redirect:/";
         }

        return "member/editInformation";
    }




    @GetMapping(value = "/UserMyPage")
    public String myPage(Model model, HttpSession session) {

        Users loggedInUser = (Users) session.getAttribute("user");

        if(loggedInUser != null) {
            model.addAttribute("userName", loggedInUser.getName());
        } else {
             model.addAttribute("userName", "Guest");
        }

        return "member/UserMyPage";
    }

    @GetMapping(value = "/order-management")
    public String orderManagement(Model model) {
        return "member/orderManagement";
    }

    @GetMapping(value = "/my-review")
    public String myReview(Model model) {

        return "member/my-review";
    }

    @GetMapping(value = "/pay-history")
    public String payHistory(Model model) {

        return "member/payHistory";
    }
}

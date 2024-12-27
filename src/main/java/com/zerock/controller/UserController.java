package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class UserController {

    @GetMapping(value = "/user/EditInformation")
    public String editInformation(Model model) {

        return "member/editInformation";
    }

    @GetMapping(value = "/user/UserMyPage")
    public String myPage(Model model) {


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

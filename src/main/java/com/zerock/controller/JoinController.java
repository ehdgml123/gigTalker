package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/join")
public class JoinController {

    @GetMapping(value = "/joinMain")
    public String joinMain(Model model) {

        return "Join/JoinMain";
    }

    @GetMapping(value = "/EmailJoin.html")
    public String EmailJoin(Model model) {

        return "Join/EmailJoin";
    }


}

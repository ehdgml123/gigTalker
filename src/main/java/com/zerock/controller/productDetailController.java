package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productDetailController {


    @GetMapping(value = "/Detailvideo")
    public String productDetail(Model model) {


        return "field/Detail";
    }

}

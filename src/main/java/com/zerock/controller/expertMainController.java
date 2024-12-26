package com.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/expert")
public class expertMainController {

    @GetMapping(value = "/main")
    public String expertMain(Model model) {

        return "expert/expertMain";
    }

    @GetMapping(value = "/productnew")
    public String productNew(Model model) {

        return "expert/productNew";
    }

    @GetMapping(value = "ProductionPproduct")
    public String productionProduct(Model model) {

        return "expert/productionProduct";
    }
}

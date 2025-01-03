package com.zerock.controller;

import com.zerock.Entity.ProductionService;
import com.zerock.service.ProductionServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class fieldController {

    private final ProductionServiceService productionServiceService;

    public fieldController(ProductionServiceService productionServiceService) {
        this.productionServiceService = productionServiceService;
    }

    @GetMapping(value = "/video")
    public String fieldVideo(Model model) {

        String category = "영상/사진"; // 조회하려는 카테고리 이름
        List<ProductionService> productList = productionServiceService.getProductsByCategory(category);
        model.addAttribute("productList", productList);

        // 로그 추가
        System.out.println("Fetched productList size: " + productList.size());
        productList.forEach(product -> System.out.println("Product: " + product.getTitle()));

        return "field/video"; // Thymeleaf 템플릿 경로
    }

}

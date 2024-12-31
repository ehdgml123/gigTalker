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


    @PostMapping("/EditInformation")
    public String updateInformation(@ModelAttribute("user") @Valid Users user,
                                    BindingResult result, Model model,
                                    HttpSession session) {
        if(result.hasErrors()) {
            return "member/editInformation";
        }

        Users loggedInUser = (Users) session.getAttribute("user");
        if(loggedInUser == null) { // 수정: 로그인되지 않은 경우 리디렉션
            return "redirect:/";
        }

        // 보안을 위해 클라이언트에서 전송된 이메일을 무시하고 세션에서 가져온 이메일 사용
        user.setEmail(loggedInUser.getEmail());

        try {
            usersService.updateUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "정보 업데이트 중 오류가 발생했습니다.");
            return "member/editInformation";
        }

        // 업데이트된 사용자 정보를 다시 세션에 저장
        Users updatedUser = usersService.(user.getEmail());
        session.setAttribute("user", updatedUser);

        model.addAttribute("successMessage", "정보가 성공적으로 업데이트되었습니다.");

        return "redirect:/UserMyPage"; // 업데이트 후 마이 페이지로 리디렉션
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

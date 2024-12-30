package com.zerock.controller;

import com.zerock.Entity.Users;
import com.zerock.dto.LoginFormDto;
import com.zerock.dto.UserFormDto;
import com.zerock.service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;

@Controller
@RequestMapping(value = "/login")
@Log4j2
public class LoginMainController {

    private final UsersService usersService;



    public LoginMainController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/loginMain")
    public String loginMain(Model model) {


        return "login/loginMain";
    }


    @GetMapping(value = "EmailLogin.html")
    public String EmailLogin(Model model) {

        model.addAttribute("loginFormDto", new LoginFormDto());

        return "login/emailLogin";
    }

    @PostMapping(value = "EmailLogin.html")
    public String EmailLoginPost(
            @Valid @ModelAttribute("loginFormDto") LoginFormDto loginFormDto,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "login/emailLogin";
        }

        try {
            Users users = usersService.authenticateUser(loginFormDto.getEmail(), loginFormDto.getPassword());
            session.setAttribute("user", users);
            model.addAttribute("sessionMessage", "로그인에 성공했습니다.");
            log.info("로그인 성공..");
            return "redirect:/"; // 로그인 성공 시 메인 페이지로 리다이렉션
        } catch (Exception e) {
            bindingResult.rejectValue("email", "error", e.getMessage());
            return "login/emailLogin";
        }
    }
}


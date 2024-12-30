package com.zerock.controller;

import com.zerock.dto.UserFormDto;
import com.zerock.exception.EmailAlreadyExistsException;
import com.zerock.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/join")
public class JoinController {

    private final UsersService usersService;

    public JoinController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/joinMain")
    public String joinMain(Model model) {
        return "Join/JoinMain";
    }

    @GetMapping(value = "/EmailJoin.html")
    public String EmailJoin(Model model) {
        model.addAttribute("user", new UserFormDto());
        return "Join/EmailJoin";
    }

    @PostMapping(value = "/EmailJoin.html")
    public String EmailJoinPost(@Valid @ModelAttribute("user") UserFormDto userFormDto,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Join/EmailJoin";
        }

        try {
            usersService.registerUser(userFormDto);
            model.addAttribute("successMessage", "회원가입이 완료되었습니다.");
            return "redirect:/";
        } catch (EmailAlreadyExistsException e) {
            bindingResult.rejectValue("email", "emailAlreadyExists", e.getMessage());
            return "Join/EmailJoin";
        }
    }
}

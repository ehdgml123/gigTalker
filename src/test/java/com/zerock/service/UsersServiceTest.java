package com.zerock.service;

import com.zerock.Entity.Users;
import com.zerock.Repository.UsersRepository;
import com.zerock.dto.UserFormDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserFormDto createUserFormDto() {

        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("test@test.com");
        userFormDto.setPassword("123456"); // 암호화 전 비밀번호
        userFormDto.setName("홍길동");
        userFormDto.setRole("user");

        return userFormDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUser() {

        UserFormDto userFormDto = createUserFormDto();

        Users savedUser = usersService.registerUser(userFormDto);

        assertEquals(savedUser.getEmail(), userFormDto.getEmail());
        assertEquals(savedUser.getName(), userFormDto.getName());
        assertTrue(passwordEncoder.matches("123456", savedUser.getPassword()));
    }
}
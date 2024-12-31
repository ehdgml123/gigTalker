package com.zerock.service;

import com.zerock.Entity.Users;
import com.zerock.Repository.UsersRepository;
import com.zerock.dto.UserFormDto;
import com.zerock.exception.EmailAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    // SHA-256을 사용한 비밀번호 해싱
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 알고리즘을 지원하지 않습니다.", e);
        }
    }

    @Transactional
    public Users registerUser(UserFormDto userFormDto) {

        // 이메일 중복 확인
        if (usersRepository.findByEmail(userFormDto.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // UserFormDto를 Users 엔티티로 매핑
        Users user = modelMapper.map(userFormDto, Users.class);

        // 비밀번호 암호화 후 저장
        user.setPassword(hashPassword(userFormDto.getPassword()));

        return usersRepository.save(user);
    }

    public Users authenticateUser(String email, String password) throws Exception {

        // 사용자 검색
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("등록되지 않은 이메일입니다.");
        }

        // 암호화된 비밀번호와 비교
        if (!user.getPassword().equals(hashPassword(password))) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    @Transactional
    public void updateUser(Users user) {

        Users existingUser = usersRepository.findById(user.getId())
                .orElseThrow(()->new RuntimeException("사용자를 찾을수 없습니다."));


        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());

        usersRepository.save(existingUser);

    }


}

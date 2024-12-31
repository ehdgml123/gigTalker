package com.zerock.Entity;


import com.zerock.dto.UserFormDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role;


/*    public static Users createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {

        Users user = new Users();
        user.setEmail(userFormDto.getEmail());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setName(userFormDto.getName());
        user.setPhone(userFormDto.getPhone());
        user.setRole(userFormDto.getRole());
        return user;
    }
    */

}

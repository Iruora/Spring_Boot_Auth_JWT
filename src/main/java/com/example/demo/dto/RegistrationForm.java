package com.example.demo.dto;

import lombok.*;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationForm {
    private String username;
    private String password;
    private String confirmPassword;
}

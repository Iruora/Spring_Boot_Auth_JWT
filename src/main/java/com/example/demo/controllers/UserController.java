package com.example.demo.controllers;

import com.example.demo.dto.RegistrationForm;
import com.example.demo.entities.AppUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nidhal on 13/03/2019.
 */
@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    // ==================================
    @PostMapping("/users")
    public AppUser signUp(@RequestBody RegistrationForm data) {
        return accountService.saveUser(data);
    }
}

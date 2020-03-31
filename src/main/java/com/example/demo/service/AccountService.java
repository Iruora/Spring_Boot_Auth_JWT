package com.example.demo.service;

import com.example.demo.dto.RegistrationForm;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Service
@Transactional
public interface AccountService {
    AppUser saveUser(RegistrationForm data);
    AppRole saveRole(AppRole appRole);
    AppUser findUserByUsername(String username);
    void addRoleToUser(String username, String role) throws Exception;
}

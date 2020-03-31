package com.example.demo.service.impl;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.AppUserRepository;
import com.example.demo.dto.RegistrationForm;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AppRoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    // =======================================
    @Override
    public AppUser saveUser(RegistrationForm data) {
        String username = data.getUsername();
        AppUser user = this.findUserByUsername(username);
        if (user != null) throw new RuntimeException("This user already exists");
        String password = data.getPassword();
        String confirmPassword = data.getConfirmPassword();

        if (! password.equals(confirmPassword))
            throw new RuntimeException("Confirm password exception");
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(password);
        u.setPassword(
                bCryptPasswordEncoder.encode(
                        u.getPassword()
                )
        );

        this.userRepository.save(u);

        this.addRoleToUser(username, "USER");

        AppUser ur = userRepository.save(u);
        return ur;
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findAppUserByUsername(username);
        AppRole role = roleRepository.findByRole(roleName);

        user.getRoles().add(role);
    }
}

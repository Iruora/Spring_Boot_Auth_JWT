package com.example.demo.config.service.impl;

import com.example.demo.entities.AppUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    //=============================================================
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = accountService.findUserByUsername(username);
        if (u == null) throw new UsernameNotFoundException("username not found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        u.getRoles().forEach(
                r -> {
                    authorities.add(new SimpleGrantedAuthority(r.getRole()));
                }
        );
        // the password passed in parameter to the User below is encrypted and that's why we have specified the encoder in
        // SecurityConfig.class
        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}

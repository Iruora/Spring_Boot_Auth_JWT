package com.example.demo.config;

import com.example.demo.config.filters.JWTAuthenticationFilter;
import com.example.demo.config.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Nidhal on 13/03/2019.
 */
@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration
//@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
        // userDetainService will verify well get a crypted pass that will be encoded via bCryptPasswordEncoder
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we make csrf disabled to enjoy stateless
        http.csrf().disable()
        // we have no need to create session as we are using a stateless way : JWT
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // here we authorize landing on login page
                .authorizeRequests()
                .antMatchers("/login/**", "/users/**")
                .permitAll()
                // any other request needs to be authenticated
                .anyRequest().authenticated()
                .and()
                // authenticationFilter is the one which is going to attemptAuthentication and with its success will generate a token
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // AuthorizationFilter is the filter which is going to verify whether coming requests are authorized
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

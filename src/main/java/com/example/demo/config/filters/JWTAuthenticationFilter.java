package com.example.demo.config.filters;

import com.example.demo.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.example.demo.config.constants.SecurityConstants.*;

/**
 * Created by Nidhal on 13/03/2019.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    // this method will be run when ever a user attempt to authenticate
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser user = null;
        // the user that we'll get is in JSON format that's why we need jaxon object mapper
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        // give to spring security the user that aims to authenticate in order to verify whether he's able or not
        // on success spring sec will invoke loadUserByUsername() from UserDetailsServiceImpl
        // if it's all good after checking password the successfulAuthentication will be invoked and now we'll generate the token
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact(); // make it a string
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

    }
}

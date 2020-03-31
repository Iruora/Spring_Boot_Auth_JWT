package com.example.demo.config.filters;

import com.example.demo.config.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Nidhal on 14/03/2019.
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    // for each request the doFilterInternal will be executed
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // Enable CORS if the request method is OPTIONS
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        // Which Headers are allowed to be sent with the response
        httpServletResponse.addHeader(
                "Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization"
        );
        // As server authorize client to read these headers
        httpServletResponse.addHeader(
                "Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization"
        );
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.setStatus(httpServletResponse.SC_OK);
        }
        else {
            String jwtToken = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
            if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX))  {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(
                    role -> {
                        authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                    }
            );
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}

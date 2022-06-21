package com.jrp.sma.security;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.jrp.sma.entities.User;
import com.jrp.sma.services.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        int rolesSize = user.getRoles().size();
        for (int i=0; i < rolesSize; i++) {
        authorities.add(new SimpleGrantedAuthority((user.getRoles()).get(i).getDescription())); // description is a string
        }
        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
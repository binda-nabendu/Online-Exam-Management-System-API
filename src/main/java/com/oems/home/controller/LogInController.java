package com.oems.home.controller;

import com.oems.home.model.AuthenticationRequest;
import com.oems.home.model.AuthenticationResponse;
import com.oems.home.security.JwtUtil;
import com.oems.home.security.MyUserDetailsService;
import com.oems.home.security.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    @Autowired
    private AuthenticationManager securityManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authentication) throws Exception {
        securityManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentication.getUsername(),authentication.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

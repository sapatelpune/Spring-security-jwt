package com.ms.security.jwt.jwt.controller;

import com.ms.security.jwt.jwt.beans.AuthRequest;
import com.ms.security.jwt.jwt.beans.AuthResponse;
import com.ms.security.jwt.jwt.util.JwtUtil;
import com.ms.security.jwt.jwt.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailService myUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authReq)  {
        try {
            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(authReq.getUserName(), authReq.getPassword()));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username/Password");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed");
        }

        // If user is authenticated/ Valid User
        // fatch the user details form user Details services
       final UserDetails userDetails= myUserDetailsService.loadUserByUsername(authReq.getUserName());

        // Create JWT Token form user Details Services
        final String jwtToken=jwtUtil.createToken(userDetails);

        System.out.println("JWT Token="+jwtToken);
        // return JWT token in response
        return ResponseEntity.ok((new AuthResponse(jwtToken)));
    }
}
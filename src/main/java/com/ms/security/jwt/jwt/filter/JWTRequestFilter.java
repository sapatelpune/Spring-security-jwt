package com.ms.security.jwt.jwt.filter;

import com.ms.security.jwt.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        /*String authorizationHeader=httpServletRequest.getHeader("Authorization");
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            String jwt=authorizationHeader.substring(7);*/
        System.out.println("****************************="+httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().equals("/login")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            try{
//                String token=jwtUtil.resolveToken(httpServletRequest);
//                System.out.println("token : "+token);
                Claims claims = jwtUtil.resolveClaims(httpServletRequest);

                if(claims != null & jwtUtil.validateClaims(claims)){
                    String userName = claims.getSubject();
                    System.out.println("userName : "+userName);
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userName,"",new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }catch(Exception e){
                throw new JwtException("Invalid Token");
            }
        }

    }

    }


package com.foodcourt.users.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_TOKEN_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTH_HEADER_NAME);
        final String jwtToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(AUTH_TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.replace(AUTH_TOKEN_PREFIX,"");
        userEmail = jwtService.extractUserEmail(jwtToken); //TODO extract th data from JWT token
    }
}

package com.foodcourt.users.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private String secretKey = "mLtJzjsDmKgWwXfdb5OlBe/SC/NBkHyI3nfzSlrgdYgbkbXRH3cycJSDEvnMYpGyvhy0eqgPOQPgvzcRP0fMYw==";

    public String extractUserEmail(String jwtToken) {
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

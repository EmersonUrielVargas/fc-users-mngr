package com.foodcourt.users.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PublicKey;
import java.util.function.Function;

@Service
public class JwtService {

    private String secretKey = "mLtJzjsDmKgWwXfdb5OlBe/SC/NBkHyI3nfzSlrgdYgbkbXRH3cycJSDEvnMYpGyvhy0eqgPOQPgvzcRP0fMYw==";

    public String extractUserEmail(String jwtToken) {
        return null;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

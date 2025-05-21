package com.foodcourt.users.infrastructure.security;

import com.foodcourt.users.infrastructure.security.dto.UserDetailsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long expirationMiliseconds;

    public String extractUserEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(createdDate.getTime() + expirationMiliseconds);
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(createdDate)
                .expiration(expirationDate)
                .signWith(getSigninKey())
                .compact();
    }
    
    public String generateToken(UserDetailsDto userDetails){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userID", userDetails.getIdUser());
        extraClaims.put("role",userDetails.getRole().name());
        return generateToken(extraClaims, userDetails);
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
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

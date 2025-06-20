package com.project.muwbe.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "your-256-bit-secret-key-for-jwt-signing"; // Thay bằng khóa bí mật an toàn
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 giờ

    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).toList());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
//
//    private static final String SECRET_KEY = "your-256-bit-secret-key-for-jwt-signing-1234567890abcdef"; // Must be at least 256 bits
//    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 hours
//
//    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", authorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
}

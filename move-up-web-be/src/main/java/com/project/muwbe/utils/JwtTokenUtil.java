package com.project.muwbe.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

//    private static final String SECRET_KEY = "your-256-bit-secret-key-for-jwt-signing"; // Thay bằng khóa bí mật an toàn
//    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 giờ
//
//    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).toList());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }

    private static final String SECRET_KEY = "mySecretKeyForJWTTokenGenerationAndValidation2024"; // Key đủ dài cho HS256
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // 5 giờ

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Tạo token từ email và authorities
    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        return createToken(claims, email);
    }

    // Tạo token với claims và subject
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Lấy email từ token
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Lấy ngày hết hạn từ token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Lấy claim từ token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Lấy tất cả claims từ token
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Kiểm tra token đã hết hạn chưa
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Validate token
    public Boolean validateToken(String token, String email) {
        final String tokenEmail = getEmailFromToken(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    // Lấy roles từ token
    @SuppressWarnings("unchecked")
    public java.util.List<String> getRolesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (java.util.List<String>) claims.get("roles");
    }

}

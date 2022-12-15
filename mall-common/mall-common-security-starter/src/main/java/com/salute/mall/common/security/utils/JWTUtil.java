package com.salute.mall.common.security.utils;

import com.salute.mall.common.security.dto.AuthUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String generateToken(AuthUserEntity authUserEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user",authUserEntity);
        return doGenerateToken(claims, authUserEntity.getUserName());
    }

    private static String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SECRET.getBytes()))
                .compact();
    }

    public static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    public static AuthUserEntity getUserInfoFromToken(String token) {
        return (AuthUserEntity)getAllClaimsFromToken(token).get("user");
    }
}

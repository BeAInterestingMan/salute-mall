package com.salute.mall.auth.service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String SECRET = "ukc8BDbRigUDasasqs1vvvrfvreO";

    public static String generateToken(Object object) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("permission", object);
        claims.put("user", object);
        return doGenerateToken(claims, "username");
    }

    private static String doGenerateToken(Map<String, Object> claims, String username) {
        final Date createdDate = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SECRET.getBytes()))
                .compact();
    }


    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
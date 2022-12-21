package com.salute.mall.auth.service.utils;

import com.salute.mall.auth.service.dto.AuthUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * @Description 生成token
     * @author liuhu
     * @param authUserEntity
     * @date 2022/12/21 18:06
     * @return java.lang.String
     */
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

    /**
     * @Description 获取用户信息
     * @author liuhu
     * @param token
     * @date 2022/12/21 18:05
     * @return com.salute.mall.auth.service.dto.AuthUserEntity
     */
    public static AuthUserEntity getUserInfoFromToken(String token) {
        return (AuthUserEntity)getAllClaimsFromToken(token).get("user");
    }
}

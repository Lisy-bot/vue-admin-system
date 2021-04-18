package com.lisy.config.security.component;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lisy
 * @Date: 2021/4/1
 * @version: 1.0
 * @Description: ""
 */
@Component
public class JwtTokenUtil {
    public static final String CLAIM_KEY_USERNAME="sub";
    public static final String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secre}")
    private String secre;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token
     * @param userDetails
     * @return
     */
    public String generatorToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generatorToken(claims);
    }

    /**
     * 生成token
     * @param claim
     * @return
     */
    private String generatorToken(Map<String, Object> claim){
        return Jwts.builder()
                .setClaims(claim)
                .setExpiration(getExpirationDate()).signWith(SignatureAlgorithm.HS512, secre).compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFormToken(String token){
        String userName;
        try {
            Claims claims = getClamisFormToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }
        return  userName;
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClamisFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secre).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证token是否过期
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String userName = getUserNameFormToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFormToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token获取荷载
     * @param token
     * @return
     */
    private Date getExpiredDateFormToken(String token) {
        Claims claims = getClamisFormToken(token);
        return  claims.getExpiration();
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean tokenRefresh(String token){
        return !isTokenExpired(token);
    }
    public String refreshToken(String token){
        Claims claims = getClamisFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generatorToken(claims);
    }
}

package com.pdx.radar.common;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 派大星
 * @Date: 2023/02/13 2023/2/13
 * @Description: JWT 工具类
 */
@Component
public class JwtUtils {

    private static final String CLAIM_KEY_USER = "user";
    private static final String CLAIM_KEY_CREATED = "created";

    //失效时间
    @Value("${jwt.expiration}")
    private Long expiration;
    //密钥
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 根据用户信息生成Token
     * @param userDetails
     * @return
     */
    public String generatorToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER,userDetails);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generatorTokenByClaim(claims);
    }

    /**
     * 根据荷载生成Token
     * @param claims
     * @return
     */
    private String generatorTokenByClaim(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exchangeExpiration())
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date exchangeExpiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public UserDetails getUserDetailsByToken(String token){
        UserDetails userDetails = null;
        try {
            Claims claims = genClaimsByToken(token);
            userDetails = (UserDetails) claims.get(CLAIM_KEY_USER);
        } catch (Exception e) {
            userDetails = null;
        }
        return userDetails;
    }

    /**
     * 根据token获取荷载
     * @param token
     * @return
     */
    private Claims genClaimsByToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 校验token是否过期
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        UserDetails details = getUserDetailsByToken(token);
        return details.equals(userDetails) && !isTokenExpired(token);
    }

    /**
     * token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateByToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 获取失效时间
     * @param token
     * @return
     */
    private Date getExpiredDateByToken(String token) {
        Claims claims = genClaimsByToken(token);
        return claims.getExpiration();
    }


}


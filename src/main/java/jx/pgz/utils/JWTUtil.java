package jx.pgz.utils;



import cn.hutool.core.date.DateTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName: JwtHelper
 * @Description: token工具类
 * @author: whq
 * @date: 2018/8/17 9:59
 * @version: V 2.0.0
 * @since: (jdk_1.8)
 */
public class JWTUtil {


    /**
     * 获取token中的参数
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token,String key) {
        if ("".equals(token)) {
            return null;
        }
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public static String createToken(Long userId,String username,String key, int expireMinutes) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        Date date = new Date(System.currentTimeMillis() + Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(expireMinutes)).toMillis());
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId) // 设置载荷信息
                .claim("username",username)
                .claim("expirationTime",date.getTime())
                .setExpiration(date)// 设置超时时间
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }


    public static void main(String[] args) {

        String token = JWTUtil.createToken(1L, "zhangsan","admin", 30);
        System.out.println(token);

//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJhZ2UiOjIzLCJzZXgiOiLnlLciLCJleHAiOjE2MDY3MjYyOTB9.3yrt1Njzy7FTq56oz6u4W40Jv9msh_77tubN10TLTYI";

        Claims claims = JWTUtil.parseToken(token, "admin");

        System.out.println(claims.get("username"));

    }


}
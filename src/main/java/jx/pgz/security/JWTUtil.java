package jx.pgz.security;



import cn.hutool.core.date.DateTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jx.pgz.dao.sys.entity.SysUser;


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
            ex.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param user
     * @param key
     * @param expireMinutes
     * @return
     */
    public static String createToken(SysUser user ,String key, int expireMinutes) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        Date date = new Date(System.currentTimeMillis() + Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(expireMinutes)).toMillis());
        JwtBuilder builder = Jwts.builder()
                .claim("userId",user.getId()) // 设置载荷信息
                .claim("username",user.getUsername())
                .claim("role",user.getRole())
                .claim("expirationTime",date.getTime())
                .setExpiration(date)// 设置超时时间
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }




}
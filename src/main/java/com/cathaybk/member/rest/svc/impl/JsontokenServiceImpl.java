
package com.cathaybk.member.rest.svc.impl;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.svc.JsontokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JsontokenServiceImpl implements JsontokenService {

    @Autowired
    StringRedisTemplate redisTemplate;

    private final SecretKey singingKey = new SecretKeySpec(Base64.decodeBase64("cathaybk"), 0, Base64.decodeBase64("cathaybk").length,
            "AES");
    // private final Key singingKey = new SecretKeySpec(Base64.decodeBase64("cathaybk"), SignatureAlgorithm.HS256.getJcaName());

    public String getJwttoken(String userName) {
        String uuid = UUID.randomUUID().toString();

        // 將UUID/使用者名稱放到Rejis中
        redisTemplate.opsForValue().set(uuid, userName);

        return getJwttokenWithUUID(uuid);
    }

    @Override
    public String getJwttokenWithUUID(String uuid) {
        JwtBuilder jwtBuilder = Jwts.builder();

        long nowMillis = System.currentTimeMillis();
        jwtBuilder.setNotBefore(new Date(nowMillis));
        jwtBuilder.setExpiration(new Date(nowMillis + 1800000000));

        jwtBuilder.claim("key", uuid);

        jwtBuilder.signWith(SignatureAlgorithm.HS256, singingKey);
        String tokenString = jwtBuilder.compact();
        Claims test = Jwts.parser().setSigningKey(singingKey).parseClaimsJws(tokenString).getBody();
        System.out.println(test);
        return tokenString;
    }

    public Boolean validtoken(String token) {
        try {

            // 會自動驗證token是否過期，故不須另外進行驗證
            @SuppressWarnings("unused")
            Jws<Claims> claims = Jwts.parser().setSigningKey(singingKey).parseClaimsJws(token);

            //TODO
            //            String key = redisTemplate.opsForValue().get(test.get("key"));
            //            if (key == null) {
            //                return false;
            //            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getKey(String token) {
        Claims test = Jwts.parser().setSigningKey(singingKey).parseClaimsJws(token).getBody();
        //TODO
        return redisTemplate.opsForValue().get((String) test.get("key"));

    }

}

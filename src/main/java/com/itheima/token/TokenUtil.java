package com.itheima.token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
public class TokenUtil {
    // token过期时间 3个小时
    public static final long EXPIRE_TIME = 3*60*60*1000;
    // 验证token是否正确
    public static boolean verify(String token, int user_id, String password){
        try{
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("user_id", user_id)
                    .build();
            // 校验token
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    // 生成签名 3个小时后过期
    public static String sign(Integer user_id, String password){
        long currentTime = System.currentTimeMillis() + EXPIRE_TIME;//3小时有效时间
        Date date = new Date(currentTime);
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create()
                .withClaim("user_id",user_id)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static Integer getUserIdByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("user_id")
                .asInt();
    }
}

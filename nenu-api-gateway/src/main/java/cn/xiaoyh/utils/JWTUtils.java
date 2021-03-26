package cn.xiaoyh.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;


import java.util.Date;
import java.util.Map;


public class JWTUtils {

    private static final String SECRET = "342bdc63c3c5a45879eb9d03adfe98202bd301ec52239a74fc0c9a9aeccce604743367c9646b";

    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;

    public static String createJWT(String userId) {
        return JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> parseJwt(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

}

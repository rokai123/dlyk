package com.lukai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;

public class JWTUtils {

    private static String secret = "@%&lukai";

    /**
     * 创建JWT令牌
     * 
     * 使用HMAC256算法对指定数据进行签名，生成包含用户信息的JWT令牌。
     * 令牌头部包含算法类型(alg: HS256)和令牌类型(typ: JWT)。
     *
     * @param data 需要编码到令牌中的用户数据，将存储在"user"声明中
     * @return 生成的JWT令牌字符串
     */
    public static String createJwt(String data) {

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ","JWT");


        return JWT.create().withHeader(header)
                .withClaim("user",data)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 校验JWT令牌是否合法有效
     * 
     * 使用HMAC256算法对令牌进行签名验证，检查令牌的完整性和有效性。
     * 如果令牌过期、签名不匹配或格式不正确，将抛出运行时异常。
     *
     * @param token 待验证的JWT令牌字符串
     * @return 如果令牌验证通过返回true
     * @throws RuntimeException 当令牌验证失败时抛出，包装了具体的异常信息：
     *                          - IllegalArgumentException: 参数非法或令牌格式错误
     *                          - JWTVerificationException: JWT签名验证失败或令牌已过期
     */
    public static boolean checkeJwt(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwtVerifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 解析JWT令牌并提取用户信息
     * 
     * 验证JWT令牌的有效性，并从令牌的声明中提取存储的用户数据。
     * 如果令牌无效或已过期，将抛出异常。
     *
     * @param token 待解析的JWT令牌字符串
     * @return 从JWT的"user"声明中提取的用户信息字符串
     */
    public static String parseJwt(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        Claim user = verify.getClaim("user");
//        return  user.toString();
        return  user.asString();
    }
}

package com.gsgy.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    /**
     * header键值
     */
    public static final String AUTH_HEADER_KEY = "Authorization";
    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    //token加密字符
	private static final String SECRET = "gsgy!@#$";
    //资源超时时间，分钟
	public static final short resource_timeout = 1;
    //资源刷新超时时间，分钟
    public static final short refresh_timeout = 2;

    /**
     * 创建token
     * @param userId 用户ID
     * @return token值
     * @throws Exception
     */
    public static String createToken(String userId,short expiresMinute){
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,expiresMinute);//设置过期时间
        Date expiresDate = nowTime.getTime();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg","HS256");
        map.put("type","JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("userId",userId)
                .withExpiresAt(expiresDate)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    
    public static String verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asString();
    }
    
    /*public static void main(String[] a){
    	try {
    		Map<String,String> infoMap = new HashMap<String,String>();
    		infoMap.put("id", "123456789");
    		infoMap.put("name", "全世杰");
    		infoMap.put("sex", "男");
    		String objStr = JSONObject.toJSONString(infoMap);
            //String resourceToken = JwtUtil.createToken("123456789",JwtUtil.resource_timeout);
			//System.out.println(resourceToken);
            String info = verifyToken("eyJ0eXAiOiJKV1QiLCJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE1ODcwODcwMzgsInVzZXJJZCI6IjEyMzQ1Njc4OSIsImlhdCI6MTU4NzA4Njk3OH0.YvCuSEKM_232ECfGRcD0Jc7Xt2W__OBur1pYyyUY6ZU");
            System.out.println(info);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }*/
}

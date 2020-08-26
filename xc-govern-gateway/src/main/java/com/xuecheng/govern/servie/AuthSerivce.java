package com.xuecheng.govern.servie;

import com.xuecheng.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexlou on 2020/7/10.
 */
@Service
public class AuthSerivce {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //从头中取出jwt令牌
    public String getJwtFromHeader(HttpServletRequest request){
        //得到Authorization头
        String authorization = request.getHeader("Authorization");
        //判断头信息
        if (StringUtils.isEmpty(authorization)){
            //拒绝访问
            return null;
        }
        if (!authorization.startsWith("Bearer ")){
            //拒绝访问
            return null;
        }
        //取到jwt令牌
        String jwt = authorization.substring(7);
        return jwt;

    }
    //从cookie中取出身份令牌（token）
    public String getTokenFromCookie(HttpServletRequest request){
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        String access_token = cookieMap.get("uid");
        if (StringUtils.isEmpty(access_token)){
            return null;
        }
        return access_token;
    }

    //从Redis中查询有效期
    public long getExpire(String access_token){
        //token在Redis中的key
        String key = "user_token:" + access_token;
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire;

    }
}

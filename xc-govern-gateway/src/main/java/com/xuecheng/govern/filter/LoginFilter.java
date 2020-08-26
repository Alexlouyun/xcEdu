package com.xuecheng.govern.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.govern.servie.AuthSerivce;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexlou on 2020/7/10.
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Autowired
    AuthSerivce authSerivce;
    //过滤器类型
    @Override
    public String filterType() {
        /*
        * pre：请求在被路由之前执行
        * routing：在路由请求时调用
        * post：在routing和errror过滤器之后调用
        * error：处理请求时发生错误调用
        * */
        return "pre";
    }
    //过滤器序号，越小越先被执行
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //返回true表示需要执行此过滤器
        return true;
    }

    @Override
    //测试需求：过虑所有请求，判断头部信息是否有Authorization，如果没有则拒绝访问，否则转发到微服务
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = currentContext.getRequest();
        //从cookie中取出身份令牌
        String access_token = authSerivce.getTokenFromCookie(request);

        if(StringUtils.isEmpty(access_token)){
            this.accessDenied();
            return null;
        }
        //从Redis中校验身份令牌是否过期
        long expire = authSerivce.getExpire(access_token);
        if (expire<0){
            accessDenied();
            return null;
        }
        //从头中取出jwt令牌
        String jwt = authSerivce.getJwtFromHeader(request);
        if (StringUtils.isEmpty(jwt)){
            accessDenied();
            return null;
        }
        return null;
    }

    //拒绝访问
    private void accessDenied(){
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到response
        HttpServletResponse response = currentContext.getResponse();
        //拒绝访问
        currentContext.setSendZuulResponse(false);
        //设置响应代码
        currentContext.setResponseStatusCode(200);
        //构建响应信息
        ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
        //转成json
        String jsonString = JSON.toJSONString(responseResult);
        currentContext.setResponseBody(jsonString);
        //转成json。设置contentType
        response.setContentType("application/json;charset=utf-8");

    }
}

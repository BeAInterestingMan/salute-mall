package com.salute.mall.common.security.filter;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.security.context.AuthUserContext;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.common.security.properties.MallSecurityProperties;
import com.salute.mall.common.security.utils.HttpResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
public class AuthFilter implements Filter {

    @Autowired
    private MallSecurityProperties mallSecurityProperties;

    @Autowired
    private RedisHelper redisHelper;

    public static final String  AUTHORIZATION = "Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.校验请求是否白名单  直接放行
        if(isWhiteList(request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }
        //2.校验是否登录
        String accessToken = request.getHeader(AUTHORIZATION);
        if(StringUtils.isBlank(accessToken)){
            HttpResponseUtil.responseToWeb(Result.error("401","Unauthorized"));
            return;
        }
        //判断是会员还是后台用户 会员只需要校验是否登录   后台用户需要校验权限
        String userTypeStr = Base64Utils.encodeToString("MEMBER".getBytes(StandardCharsets.UTF_8));
        try {
            AuthUserEntity userEntity = getUser(userTypeStr, accessToken);
            // 3.会员校验
            if (accessToken.contains(userTypeStr)) {
                if (Objects.nonNull(userEntity)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                HttpResponseUtil.responseToWeb(Result.error("401", "Unauthorized"));
                return;
            }
            // 4.PC后台用户校验
            AuthUserContext.setUser(new AuthUserEntity());
        } finally {
            AuthUserContext.clear();
        }
    }

    public AuthUserEntity getUser(String userTypeStr,String accessToken){
        String redisTokenInfo = (String) redisHelper.get(userTypeStr + accessToken);
        if(StringUtils.isBlank(redisTokenInfo)){
            return null;
        }
        return JSON.parseObject(redisTokenInfo,AuthUserEntity.class);
    }



    public boolean isWhiteList(String uri){
        List<String> whiteUrlList = Arrays.stream(mallSecurityProperties.getWhiteUrlList().split(",")).collect(Collectors.toList());
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String whiteUrl : whiteUrlList) {
            if (pathMatcher.match(whiteUrl, uri)) {
                log.info("当前请求是白名单，whiteUrl：{},uri:{}",whiteUrl,uri);
                return true;
            }
        }
        return false;
    }

    public boolean checkPermission(){

        return false;
    }

}

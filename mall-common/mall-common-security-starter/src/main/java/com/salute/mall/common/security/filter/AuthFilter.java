package com.salute.mall.common.security.filter;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.security.context.AuthUserContext;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.common.security.properties.MallSecurityProperties;
import com.salute.mall.common.security.utils.HttpResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class AuthFilter implements Filter {

    @Autowired
    private MallSecurityProperties mallSecurityProperties;

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
        try {
            //3.校验token
            AuthUserContext.setUser(new AuthUserEntity());
            //4.校验角色权限(用户的角色是否设置了改访问权限)
            filterChain.doFilter(request, response);
        }finally {
            AuthUserContext.clear();
        }
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

}

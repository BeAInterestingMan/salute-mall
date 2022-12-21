package com.salute.mall.common.security.filter;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.api.client.BaseAuthInfoClient;
import com.salute.mall.auth.api.request.MallAuthenticationRequest;
import com.salute.mall.auth.api.request.MallAuthorizationRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.security.constants.SecurityConstants;
import com.salute.mall.common.security.context.AuthUserContext;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.common.security.properties.MallSecurityProperties;
import com.salute.mall.common.security.utils.HttpResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
@EnableConfigurationProperties(value = MallSecurityProperties.class)
public class AuthFilter implements Filter {

    @Autowired
    private MallSecurityProperties mallSecurityProperties;

    @Autowired
    private BaseAuthInfoClient baseAuthInfoClient;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //2.校验请求是否白名单  直接放行
        String uri = request.getRequestURI();
        if(isWhiteList(uri)){
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取accessToken
        String accessToken = request.getHeader(SecurityConstants.AUTHORIZATION);
        if(StringUtils.isBlank(accessToken)){
            HttpResponseUtil.responseToWeb(Result.error("401","请先登陆"));
            return;
        }
        try {
            // 1.登录认证
            MallAuthenticationRequest authenticationRequest = new MallAuthenticationRequest();
            authenticationRequest.setAccessToken(accessToken);
            log.error("execute authentication info ,req:{}",JSON.toJSONString(request));
            Result<SimpleUserInfoResponse> result = baseAuthInfoClient.authentication(authenticationRequest);
            if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE) || Objects.isNull(result.getResult())){
                log.warn("execute authentication info ,req:{},resp:{}",JSON.toJSONString(request),JSON.toJSONString(result));
                HttpResponseUtil.responseToWeb(Result.error(result.getCode(), result.getMessage()));
                return;
            }
            SimpleUserInfoResponse userInfoResponse = result.getResult();
            // 2,权限授权
            MallAuthorizationRequest authorizationRequest = new MallAuthorizationRequest();
            authorizationRequest.setUserCode(userInfoResponse.getUserCode());
            authorizationRequest.setUri(uri);
            Result<Void> authorizationResult = baseAuthInfoClient.authorization(authorizationRequest);
            if(Objects.isNull(authorizationResult) || !Objects.equals(authorizationResult.isSuccess(),Boolean.TRUE)){
                log.warn("execute authorization info ,req:{},resp:{}",JSON.toJSONString(request),JSON.toJSONString(result));
                HttpResponseUtil.responseToWeb(Result.error(authorizationResult.getCode(), authorizationResult.getMessage()));
                return;
            }
            AuthUserEntity authUserEntity = buildAuthUserEntity(userInfoResponse);
            // 3.保存用户信息进入threadLocal
            log.info("execute authFilter success,accessToken:{},uri:{},resp:{}",accessToken,uri,JSON.toJSONString(authUserEntity));
            AuthUserContext.setUser(authUserEntity);
            filterChain.doFilter(request, response);
        } finally {
            AuthUserContext.clear();
        }
    }

    /**
     * @Description 构建用户信息
     * @author liuhu
     * @param userInfoResponse
     * @date 2022/12/19 18:54
     * @return com.salute.mall.common.security.dto.AuthUserEntity
     */
    private AuthUserEntity buildAuthUserEntity(SimpleUserInfoResponse userInfoResponse) {
        AuthUserEntity entity = new AuthUserEntity();
        entity.setUserName(userInfoResponse.getUserName());
        entity.setUserCode(userInfoResponse.getUserCode());
        entity.setSystemUserType(userInfoResponse.getSystemUserType());
        entity.setAvatar(userInfoResponse.getAvatar());
        return entity;
    }

    /**
     * @Description 是否白名单
     * @author liuhu
     * @param uri
     * @date 2022/12/14 19:45
     * @return boolean
     */
    public boolean isWhiteList(String uri){
        if(StringUtils.isEmpty(mallSecurityProperties.getWhiteUrlList())){
            return false;
        }
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

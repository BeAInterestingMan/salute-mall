package com.salute.mall.common.security.filter;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.security.constants.SecurityConstants;
import com.salute.mall.common.security.context.AuthUserContext;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.common.security.dto.AuthUserPermissionEntity;
import com.salute.mall.common.security.properties.MallSecurityProperties;
import com.salute.mall.common.security.utils.HttpResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(value = MallSecurityProperties.class)
public class AuthFilter implements Filter {

    @Autowired
    private MallSecurityProperties mallSecurityProperties;

    @Autowired
    private RedisHelper redisHelper;

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
        //4.判断是会员还是后台用户 会员只需要校验是否登录   后台用户需要校验权限
        try {
            AuthUserEntity userEntity = getUser(accessToken);
            if (Objects.isNull(userEntity)) {
                HttpResponseUtil.responseToWeb(Result.error("401", "用户身份已失效"));
                return;
            }
            // 5.pc后台用户需要权限校验
            if(!checkPermission(userEntity,uri,accessToken)){
                HttpResponseUtil.responseToWeb(Result.error("403", "无权访问"));
                return;
            }
            AuthUserContext.setUser(userEntity);
            filterChain.doFilter(request, response);
        } finally {
            AuthUserContext.clear();
        }
    }

    /**
     * @Description 获得用户信息
     * @author liuhu
     * @param accessToken
     * @date 2022/12/14 19:46
     * @return com.salute.mall.common.security.dto.AuthUserEntity
     */
    public AuthUserEntity getUser(String accessToken){
        String redisTokenInfo = (String) redisHelper.hGet(SecurityConstants.ACCESS_TOKEN_PREFIX,accessToken);
        if(StringUtils.isBlank(redisTokenInfo)){
            return null;
        }
        return JSON.parseObject(redisTokenInfo,AuthUserEntity.class);
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

    /**
     * @Description 校验是否有菜单按钮权限
     * @author liuhu
     * @param authUserEntity
     * @param uri
     * @param accessToken
     * @date 2022/12/14 19:45
     * @return boolean
     */
    public boolean checkPermission(AuthUserEntity authUserEntity,String uri,String accessToken){
        String pc = Base64Utils.encodeToString("PC".getBytes(StandardCharsets.UTF_8));
        // 只有PC用户需要鉴权
        if(!accessToken.contains(pc)){
            return true;
        }
        String redisPermission = (String) redisHelper.hGet(SecurityConstants.PERMISSION_PREFIX,authUserEntity.getUserCode());
        if(StringUtils.isBlank(redisPermission)){
            return false;
        }
        AuthUserPermissionEntity permissionEntity = JSON.parseObject(redisPermission, AuthUserPermissionEntity.class);
        if(Objects.isNull(permissionEntity) || CollectionUtils.isEmpty(permissionEntity.getPermissionList())){
            return false;
        }
        return permissionEntity.getPermissionList().contains(uri);
    }

}

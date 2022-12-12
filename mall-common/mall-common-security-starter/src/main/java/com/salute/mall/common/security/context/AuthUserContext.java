package com.salute.mall.common.security.context;

import com.salute.mall.common.security.dto.AuthUserEntity;

import java.util.Objects;

/**
 *  @Description
 *  @author liuhu
 *  @Date 2022/12/12 17:37
 */
public class AuthUserContext {

     public static final ThreadLocal<AuthUserEntity> AUTH_USER = new ThreadLocal<>();


     public static AuthUserEntity getUser(){
        return AUTH_USER.get();
     }

    public static void setUser(AuthUserEntity authUserEntity){
       if(Objects.isNull(authUserEntity)){
           return;
        }
        AUTH_USER.set(authUserEntity);
    }

    public static void clear(){
       if(Objects.nonNull(AUTH_USER.get())){
           AUTH_USER.remove();
       }
    }
}

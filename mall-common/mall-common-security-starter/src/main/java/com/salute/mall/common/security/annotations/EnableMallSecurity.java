package com.salute.mall.common.security.annotations;

import com.salute.mall.common.security.filter.AuthFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(AuthFilter.class)
public @interface EnableMallSecurity {
}

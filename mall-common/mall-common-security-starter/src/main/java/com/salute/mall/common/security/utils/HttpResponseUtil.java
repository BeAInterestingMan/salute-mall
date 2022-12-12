package com.salute.mall.common.security.utils;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salute.mall.common.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class HttpResponseUtil {

	public static  <T> void responseToWeb(Result<T> result) {
		if (result == null) {
			return;
		}
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletResponse response = requestAttributes.getResponse();
		if (response == null) {
			return;
		}
		response.setCharacterEncoding(CharsetUtil.UTF_8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.write(JSON.toJSONString(result));
		} catch (Exception e) {
			log.error("系统输出到浏览器异常",e);
		}
	}

}

package com.faithbj.shop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("loginUrlEntryPoint")
public class LoginUrlEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String targetUrl = null;
        String url = request.getRequestURI();
 
        //未登录而访问后台受控资源时，跳转到后台登录页面
        if(url.startsWith("/faith/")&&!StringUtils.equals(url, "/faith/loginview")){
           
            targetUrl = "/faith/loginview";

        }
        else if(url.startsWith("/cjlhome/")&&!StringUtils.equals(url, "/cjlhome/loginview")){
            //未登录而访问前台受控资源时，跳转到前台登录页面
            targetUrl = "/cjlhome/loginview";
        }
        targetUrl = request.getContextPath() + targetUrl;
        response.sendRedirect(targetUrl);
        
	}

}

package com.faithbj.shop.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faithbj.shop.entity.Member;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器 - 判断会员是否登录
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class MemberLoginVerifyInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -86246303854807787L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String loginMemberId = (String) invocation.getInvocationContext().getSession().get(Member.LOGIN_MEMBER_ID_SESSION_NAME);
		if (loginMemberId == null) {
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
			Cookie cookie = new Cookie(Member.LOGIN_MEMBER_USERNAME_COOKIE_NAME, null);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			String redirectionUrl = request.getRequestURL().toString();
			String queryString = request.getQueryString();
			if (StringUtils.isNotEmpty(queryString)) {
				redirectionUrl += "?" + queryString;
			}
			request.getSession().setAttribute(Member.LOGIN_REDIRECTION_URL_SESSION_NAME, redirectionUrl);
			return "login";
		}
		return invocation.invoke();
	}

}

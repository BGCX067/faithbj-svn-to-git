package com.faithbj.shop.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.faithbj.shop.model.configuration.CartItemCookie;
import com.faithbj.shop.utils.JsonUtil;

public class CookiesLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(StringUtils.equals(cookie.getName(),"memberUsername"))
			{
				cookie.setMaxAge(0);
				cookie.setValue(null);
			}
		}
		String jsonArray=JsonUtil.toJson(cookies);
		Cookie cookie = new Cookie("memberUsername", jsonArray);
		cookie.setPath(request.getContextPath() + "/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

	}

}

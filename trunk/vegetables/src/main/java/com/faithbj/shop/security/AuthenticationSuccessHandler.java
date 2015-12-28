package com.faithbj.shop.security;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.MemberService;

public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	@Resource
	private MemberService memberService;
	/**
	 * 登录成功后的跳转方法
	 * @author lin
	 *
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
			//记录登录日志
		Members user = (Members) authentication.getPrincipal();
//		if(user.getIsnew()==1){
//			response.sendRedirect("/pages/password.jsp");
//		}
//		if(user.getLocked()==1){
//			Date loginTime = (Date) session.getAttribute("loginTime"); 
//			if(loginTime == null)
//				response.sendRedirect("/pages/common/userLocked.jsp");
//			loginTime.setMinutes(loginTime.getMinutes()+5);
//			Date nowDate = new Date();
//			if(loginTime.getTime()>nowDate.getTime()){
//				response.sendRedirect("/pages/common/userLocked.jsp");
//				loginTime.setMinutes(loginTime.getMinutes()-5);
//			}else{
//				user.setLocked(0);
//				userService.updateBySelf(user);
//			}
//		}
//		if(user.getRole().equals("")){
//			response.sendRedirect("/pages/common/norole.jsp");
//		}
			session.setAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME, user.getUsername());// 增加失败次数
			super.onAuthenticationSuccess(request, response, authentication);
	}
}

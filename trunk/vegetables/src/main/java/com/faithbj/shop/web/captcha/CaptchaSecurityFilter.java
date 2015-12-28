package com.faithbj.shop.web.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * 拦截器 - 生成验证码图片
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: www.faithbj.com
 * </p>
 * 
 * @author faithbj
 * @date 2011-12-16
 * @version 1.0
 */

//@Component("captchaSecurityFilter")
public class CaptchaSecurityFilter implements Filter {

	@Resource
	private CaptchaService captchaService;

	// 默认值定义
	public static final String FRONT_PROCESSES_URL = "/cjlhome/login";// 前台验证码输入表单名称
	public static final String FRONT_FAILURE_URL = "/cjlhome/loginview?error=captcha";
	
	public static final String ADMIN_PROCESSES_URL = "/faith/login";// 后台验证码输入表单名称
	public static final String ADMIN_FAILURE_URL = "/faith/loginview?error=captcha";
	

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain filterChain) 
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;  
        HttpServletResponse response = (HttpServletResponse) servletResponse;  
        String servletPath = request.getServletPath();
        request.getParameter(JCaptchaEngine.CAPTCHA_INPUT_NAME);
        
        if(StringUtils.equals(servletPath, JCaptchaEngine.CAPTCHA_IMAGE_URL)){
        	genernateCaptchaImage(request, response);
        }
        
        else if(StringUtils.equals(servletPath,FRONT_PROCESSES_URL )){
        	 boolean validated = validateCaptchaChallenge(request);  
             if (validated) {  
             	filterChain.doFilter(request, response);  
             } else {  
                 redirectFailureUrl(request, response,FRONT_FAILURE_URL);  
             }
        }
        else if(StringUtils.equals(servletPath, ADMIN_PROCESSES_URL.substring(1))){
        	boolean validated = validateCaptchaChallenge(request);  
        	if (validated) {  
        		filterChain.doFilter(request, response);  
        	} else {  
        		redirectFailureUrl(request, response,ADMIN_FAILURE_URL.substring(1));  
        	}  
        }
        else
        	filterChain.doFilter(request, response);  
//
//        //符合filterProcessesUrl为验证处理请求,其余为生成验证图片请求.  
//        if (StringUtils.startsWith(servletPath, filterFrontProcessesUrl)||StringUtils.startsWith(servletPath, filterAdminProcessesUrl)) {  
//            boolean validated = validateCaptchaChallenge(request);  
//            if (validated) {  
//            	filterChain.doFilter(request, response);  
//            } else {  
//                redirectFailureUrl(request, response);  
//            }  
//        } 
//        else {  
//            
//        }  
		
	}

	/**
	 * 生成验证码图片.
	 */
	protected void genernateCaptchaImage(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		// setDisableCacheHeader(response);

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) captchaService
					.getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 验证验证码.
	 */
	protected boolean validateCaptchaChallenge(final HttpServletRequest request) {
		try {
			String captchaID = request.getSession().getId();
			String challengeResponse = StringUtils.upperCase(request.getParameter(JCaptchaEngine.CAPTCHA_INPUT_NAME));
			
			return captchaService.validateResponseForID(captchaID,challengeResponse);
		} catch (CaptchaServiceException e) {
			// logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 跳转到失败页面.
	 * 
	 * 可在子类进行扩展, 比如在session中放入SpringSecurity的Exception.
	 * @param failureUrl 
	 */
	protected void redirectFailureUrl(final HttpServletRequest request,
			final HttpServletResponse response, String failureUrl) throws IOException {
		// logger.info("跳转到失败页面:"+request.getContextPath()+failureUrl);
		response.sendRedirect(request.getContextPath() + failureUrl);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}

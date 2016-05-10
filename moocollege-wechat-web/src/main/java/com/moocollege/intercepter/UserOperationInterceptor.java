package com.moocollege.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import team.ascent.util.Constant;

/**
 * 主要获取用户信息用
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年5月9日 下午1:20:49 
 */
public class UserOperationInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		
		request.getSession().setAttribute(Constant.USERID, "hujinhu");

		
		
		log.info("**执行顺序: 1、preHandle**");
		log.info("_进入拦截器：URL:{}", request.getRequestURI());
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute(Constant.USERID);
		if (userId != null) {
			log.info("session中已有userId:{}", userId);
			return true;
		} else {
			// 取cookie中的userid
		}
		
		// 重新oAuth2认证
		String resultUrl = request.getRequestURL().toString();
		String param = request.getQueryString();
		if (param != null) {
			resultUrl += "?" + param;
		}
		response.sendRedirect("/oAuth2/step1?resultUrl=" + resultUrl);//跳转到用户认证controller
		return false;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView modelAndView) throws Exception {
		log.info("**执行顺序: 2、postHandle**");
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("**执行顺序: 3、afterCompletion**");
	}
}

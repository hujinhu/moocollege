package com.moocollege.intercepter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 

public class UserOperationInterceptor extends HandlerInterceptorAdapter {
	private static Log log = LogFactory.getLog(UserOperationInterceptor.class);
//	 
//
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		 session.setAttribute(Constant.KEY_ACCOUNT,
//		 "9b17469c892c41f4b99707ab1c19a8e1");
//		 session.setAttribute(Constant.KEY_CORPID, "wx796d48dcf81df41f");
//		 session.setAttribute(Constant.KEY_COMPANYID, 2654);
//		
//		xdsevdsfc_ = System.currentTimeMillis();
//		LOG.error(xdsevdsfc_ + "_进入拦截器：URL：" + request.getRequestURI());
//		String code = request.getParameter("code");
//		String corpId = request.getParameter("corpId");
//		String _agentId = request.getParameter("agentId");
//		if (StringUtil.isEmpty(_agentId)) {
//			_agentId = request.getParameter("state");
//		}
//		IPersonInfoService personInfoService = SpringContextHolder.getBean("personInfoServiceImpl");
//		if (StringUtil.isNotEmpty(corpId) && StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(_agentId)) {
//			LOG.error(xdsevdsfc_ + "_重定向到拦截器，根据CODE重新获取用户信息");
//			IQyDevelopService qyDevelopService = SpringContextHolder.getBean("qyDevelopService");
//			ICompanyInfoService companyInfoService = SpringContextHolder.getBean("companyInfoService");
//
//			QyDevelopInfoCriteria qyDevelopInfoCriteria = new QyDevelopInfoCriteria();
//			qyDevelopInfoCriteria.createCriteria().andCorpIdEqualTo(corpId);
//			List<QyDevelopInfo> qyDevelopInfoList = qyDevelopService.selectByExample(qyDevelopInfoCriteria);
//			if (qyDevelopInfoList.size() > 0) {
//				int companyId = qyDevelopInfoList.get(0).getCompanyId();
//				CompanyInfoDTO company = companyInfoService.getComById(companyId);
//				JSONObject obj_ = null;
//				// =========================================判断哪种方式安装应用===================================
//				if (company.getInstallType() != null && company.getInstallType() == 1) {
//					obj_ = JSONObject.fromObject(QYPlatformUtil.getUserInfoByCodeOld(companyId, code, _agentId));
//				} else {
//					obj_ = JSONObject.fromObject(QYPlatformUtil.getUserInfoByCode(companyId, code, _agentId));
//				}
//				if (obj_ != null) {
//					String UserId = (String) obj_.get("UserId");
//					String DeviceId = (String) obj_.get("DeviceId");
//					if (StringUtil.isNotEmpty(UserId)) {
//						LOG.error(xdsevdsfc_ + "_根据CODE成功获取到用户信息companyId：" + companyId + "，Account：" + UserId + ",CorpId" + corpId);
//						Map<String, Object> map = new HashMap<String, Object>();
//						map.put("account", UserId);
//						map.put("companyId", companyId);
//						PersonInfo p = personInfoService.selectPersonByAccountNoDel(map);
//						if (p != null) {
//							session.setAttribute(Constant.KEY_ACCOUNT, UserId);
//							session.setAttribute(Constant.KEY_CORPID, corpId);
//							session.setAttribute(Constant.KEY_COMPANYID, companyId);
//							setInfoToCookie(response, corpId, UserId, companyId, DeviceId);// 用户信息存cookie
//							return true;
//						} else {
//							response.sendRedirect("/error/personError.html");
//							return false;
//						}
//					} else {
//						LOG.error(xdsevdsfc_ + "_根据CODE获取用户信息失败,错误信息如下：" + obj_);
//						return false;
//					}
//				} else {
//					LOG.error("用户套件安装方式有误");
//					response.sendRedirect("/error/suiteError.html");
//					return false;
//				}
//			} else {
//				LOG.error("qyDevelopInfoList.size() 为 0");
//				response.sendRedirect("/error/suiteError.html");
//				return false;
//			}
//		} else {
//			String corp = request.getParameter("corpId");
//			String agentId = request.getParameter("agentId");
//			String session_corp = (String) session.getAttribute(Constant.KEY_CORPID);
//			Object account = session.getAttribute(Constant.KEY_ACCOUNT);
//			Object companyId = session.getAttribute(Constant.KEY_COMPANYID);
//			if (StringUtil.isEmpty(session_corp) || (account == null) || (companyId == null)) {
//				LOG.error(xdsevdsfc_ + "_SESSION 中没有用户信息");
//				if (StringUtil.isEmpty(corp)) {
//					corp = CookieUtil.cookie("_corpId", request);// 保存5天
//				}
//				getInfoByCookie(request, corp);
//				session_corp = (String) session.getAttribute(Constant.KEY_CORPID);
//				account = session.getAttribute(Constant.KEY_ACCOUNT);
//				companyId = session.getAttribute(Constant.KEY_COMPANYID);
//			}
//			if (StringUtil.isNotEmpty(session_corp) && (account != null) && (companyId != null)) {
//
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("account", account);
//				map.put("companyId", companyId);
//				PersonInfo p = personInfoService.selectPersonByAccountNoDel(map);
//				if (p != null) {
//					LOG.error(xdsevdsfc_ + "_session中有用户用户信息Account:" + account + ",companyId:" + companyId);
//					if (StringUtil.isNotEmpty(corp) && StringUtil.isNotEmpty(agentId)) {
//						if (session_corp.equals(corp)) {
//							LOG.error(xdsevdsfc_ + "_用户点击菜单进入");
//							return true;
//						} else {
//							LOG.error(xdsevdsfc_ + "_用户点击菜单进入,企业号变更");
//							return getUserInfo(request, response, corp, agentId);
//						}
//					} else {
//						return true;
//					}
//				} else {
//					response.sendRedirect("/error/personError.html");
//					return false;
//				}
//
//			} else {
//				LOG.error(xdsevdsfc_ + "_session中没有用户信息");
//				if (StringUtil.isNotEmpty(corp) && StringUtil.isNotEmpty(agentId)) {
//					LOG.error(xdsevdsfc_ + "_是点击菜单进入，根据URL重新认证用户信息");
//					return getUserInfo(request, response, corp, agentId);
//				} else {
//					LOG.error(xdsevdsfc_ + "_异常操作，url,cookie,session中都获取不到用户信息");// 这里要给个404
//					response.sendRedirect("/error/personError.html");
//					return false;
//				}
//
//			}
//		}
		return true;
	}
//
//	private void getInfoByCookie(HttpServletRequest request, String corpId) {
//		String account = CookieUtil.cookie(corpId + "_account", request);
//		String companyId = CookieUtil.cookie(corpId + "_companyId", request);
//		if (StringUtil.isNotEmpty(account) && StringUtil.isNotEmpty(companyId)) {
//			LOG.error(xdsevdsfc_ + "_从Cookie中取的Account:" + account + "，companyId:" + companyId);
//			IPersonInfoService personInfoService = SpringContextHolder.getBean("personInfoServiceImpl");
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("account", account);
//			map.put("companyId", companyId);
//			PersonInfo p = personInfoService.selectPersonByAccountNoDel(map);
//			if (p != null) {
//				request.getSession().setAttribute(Constant.KEY_ACCOUNT, account);
//				request.getSession().setAttribute(Constant.KEY_COMPANYID, Integer.valueOf(companyId));
//				request.getSession().setAttribute(Constant.KEY_CORPID, corpId);
//				LOG.error(xdsevdsfc_ + "_Cookie中的account和companyId有效，并存Session");
//			} else {
//				LOG.error(xdsevdsfc_ + "_用户account和cookie中不一致，重新认证,cookie中Account：" + account);
//			}
//
//		}
//	}
//
//	private void setInfoToCookie(HttpServletResponse response, String corpId, String account, Integer companyId, String DeviceId) {
//		LOG.error(xdsevdsfc_ + "_用户信息存Cookie,");
//		CookieUtil.cookie("_corpId", corpId, 60 * 60 * 24 * 5, response);// 保存5天
//		CookieUtil.cookie(account + "_deviceId", DeviceId, 60 * 60 * 24 * 5, response);// 保存5天
//		CookieUtil.cookie(corpId + "_account", account, 60 * 60 * 24 * 5, response);// 保存5天
//		CookieUtil.cookie(corpId + "_companyId", String.valueOf(companyId), 60 * 60 * 24 * 5, response);// 保存5天
//	}
//
//	/**
//	 * 获取用户信息
//	 * 
//	 * @param request
//	 * @param session
//	 * @return
//	 */
//	private boolean getUserInfo(HttpServletRequest request, HttpServletResponse response, String corp, String agentId) {
//		String resultUrl = request.getRequestURL().toString();
//		String param = request.getQueryString();
//		if (param != null) {
//			resultUrl += "?" + param;
//		}
//		String redirectUrl = "";
//		if (resultUrl != null) {
//			if(resultUrl.indexOf("corpId")==-1){
//				resultUrl= resultUrl + "&corpId=" + corp + "&agentId=" + agentId;	
//			}
//			redirectUrl = OAuth2Util.oAuth2Url(corp, resultUrl);
//			try {
//				response.sendRedirect(redirectUrl);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		LOG.error(xdsevdsfc_ + "_本次请求总时长--" + (System.currentTimeMillis() - xdsevdsfc_));
//	}
}

package com.moocollege.controller.wy;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * OAuth2 处理控制器
 * @author Sunlight
 */
@Controller
@RequestMapping("oAuth2")
public class OAuth2Controller {
	private static Log log = LogFactory.getLog(OAuth2Controller.class);
	/**
	 * 根据code获取Userid后跳转到需要带用户信息的最终页面
	 * @param request
	 * @param code  获取微信重定向到自己设置的URL中code参数
	 * @param oauth2url 跳转到最终页面的地址
	 * @return
	 */
	@RequestMapping("/oauth2url")
	public String Oauth2MeUrl(HttpServletRequest request, @RequestParam String code, @RequestParam String corpId, @RequestParam String agentId, @RequestParam String oauth2url) {
		log.error("########"+request.getHeader("referer"));
		log.error("微信认证后的地址，从code中获取用户信息并保存");
//		if (StringUtil.isNotEmpty(corpId) && StringUtil.isNotEmpty(code)) {
//			QyDevelopInfoCriteria qyDevelopInfoCriteria = new QyDevelopInfoCriteria();
//			qyDevelopInfoCriteria.createCriteria().andCorpIdEqualTo(corpId);
//			List<QyDevelopInfo> qyDevelopInfoList = qyDevelopService.selectByExample(qyDevelopInfoCriteria);
//			if (qyDevelopInfoList.size() > 0) {
//				try {
//					JSONObject obj_ = JSONObject.fromObject(QYPlatformUtil.getUserInfoByCode(qyDevelopInfoList.get(0).getCompanyId(), code, agentId));
//					String UserId = String.valueOf( obj_.get("UserId"));
//					if (StringUtil.isNotEmpty(UserId)) {
//						HttpSession session = request.getSession();
//						session.setAttribute(Constant.KEY_ACCOUNT, UserId);
//						session.setAttribute(Constant.KEY_CORPID,corpId);
//						session.setAttribute(Constant.KEY_COMPANYID, qyDevelopInfoList.get(0).getCompanyId());
//						System.out.println("从微信成功获取到用户id"+UserId);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		// 这里简单处理,存储到session中
		return "forward:" + oauth2url;
	}

	 

}
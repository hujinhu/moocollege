package com.moocollege.controller.wy;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.ascent.util.Constant;
import team.ascent.util.weixin.QyTokenUtil;
import team.ascent.util.weixin.QyUserUtil;
import team.ascent.util.weixin.response.user.UserOauthResponse;

import com.moocollege.controller.base.BaseController;

/**
 * OAuth2 处理控制器
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年5月9日 下午2:16:30 
 */
@Controller
@RequestMapping("/oAuth2")
public class OAuth2Controller extends BaseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * step1.构造微信跳转地址,带上原url
	 */
	@RequestMapping("/step1")
	public String Oauth2API(HttpServletRequest request, @RequestParam String resultUrl) {
		String CropId = this.getCorpId();
		String redirectUrl = "";
		if (resultUrl != null) {
			resultUrl = "http://" + request.getServerName() + "/oAuth2/step2?oauth2url=" + resultUrl + "";
			redirectUrl = oAuth2Url(CropId, resultUrl);
		}
		return "redirect:" + redirectUrl;
	}

	/**
	 * step2.微信回调,带了code,获取完用户信息,再跳转原URL
	 */
	@RequestMapping(value = "/step2")
	public String Oauth2MeUrl(HttpServletRequest request, @RequestParam String code, @RequestParam String oauth2url) {
		UserOauthResponse userOauthResponse = QyUserUtil.getUserInfo(QyTokenUtil.getAccessToken().getAccess_token(), code);
		if (userOauthResponse != null && userOauthResponse.getUserId() != null) {
			log.info("_根据CODE成功获取到用户信息 userId:{}", userOauthResponse.getUserId());
			// 处理业务
			// step1. 根据userId获取业务中关联的用户
			// step2.根据userId获取用户信息
			// step3.存session
			request.getSession().setAttribute(Constant.USERID, userOauthResponse.getUserId());
		}
		return "redirect:" + oauth2url;
	}

	/**
	 * 构造带员工身份信息的URL
	 * @param corpid  企业id
	 * @param redirect_uri    授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * @param state 重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值
	 * @return
	 */
	private String oAuth2Url(String corpid, String redirect_uri) {
		try {
			redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + corpid + "&redirect_uri=" + redirect_uri +"&response_type=code&scope=snsapi_base&state=sunlight#wechat_redirect";
		log.info("oauth2Url={}", oauth2Url);
		return oauth2Url;
	}

}
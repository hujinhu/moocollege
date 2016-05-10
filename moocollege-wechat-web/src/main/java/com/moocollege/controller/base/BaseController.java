package com.moocollege.controller.base;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import team.ascent.util.weixin.QyTokenUtil;
import team.ascent.util.weixin.response.token.TokenResult;

public class BaseController {
	@Value(value = "#{commonConfig[corpId]}")
	private String corpId;
	@Value(value = "#{commonConfig[secret]}")
	private String secret;
	private static Log log = LogFactory.getLog(BaseController.class);

	/**
	 * 所有的子类方法执行之前都要先执行此方法，子类方法不需要在model此方法中的参数
	 * @param model
	 * @param platformUserName 微信/易信账号
	 */
	@ModelAttribute
	public void init(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
	}

	/**
	 * 获取token
	 */
	public String getAccessToken() {
		TokenResult tokenResult = QyTokenUtil.getAccessToken();
		return tokenResult.getAccess_token();
	}

	/**
	 * @param url
	 * @param model
	 * @param request
	 * @param company
	 * @param accessTokenService
	 * @param qyDevelopService
	 * @param suiteIndex 应用所在的套件索引,3456其中一个
	 */
	public void getSinAndOther(String url, ModelMap model, HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			if (url == null) {
				url = request.getRequestURL().toString();
				String param = request.getQueryString();
				if (param != null) {
					url += "?" + param;
				}
				if (url != null && url.contains("#")) {
					url = url.split("#")[0];
				}
			}
			log.error("获取jsSDK的URL:" + url);

			// 查询公司应用安装方式
			String accessToken = "";
			String jsapi_ticket = "";// obj.getString("ticket");
			ret = sign(jsapi_ticket, url);
			for (Map.Entry entry : ret.entrySet()) {
				model.addAttribute("_" + entry.getKey(), entry.getValue());
			}
			// model.addAttribute("_corpId",de.getCorpId());

		} catch (Exception e) {
			log.error("获取jsSDK 签名错误。");
			e.printStackTrace();
			model.addAttribute("_url", "");
			model.addAttribute("_corpId", "");
			model.addAttribute("_jsapi_ticket", "");
			model.addAttribute("_nonceStr", "");
			model.addAttribute("_timestamp", "");
			model.addAttribute("_signature", "");

		}
	}

	/**********jsSDK 使用 start******************************************/
	public static Map<String, Object> sign(String jsapi_ticket, String url) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println("jsSDK 签名 字符串 " + string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	/**********jsSDK 使用 end******************************************/

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	
	
}

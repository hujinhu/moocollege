package team.ascent.util.weixin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import team.ascent.util.Config;
import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.response.token.JsapiTicketResult;
import team.ascent.util.weixin.response.token.TokenResult;

public class QyTokenUtil {
	
	
	private static final String QY_WEIXIN_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/";
	private static JsonUtil json = JsonUtil.nonDefaultMapper();
	private static Log log = LogFactory.getLog(QyTokenUtil.class);

	/**
	 * 根据corpId和secret获取token
	 * @return TokenResult
	 */
	public static TokenResult getAccessToken() {
		// 判断token有没有过期,没过期直接返回
		if (TokenResult.tokenResult != null && !TokenResult.tokenResult.isInvalid()) {
			log.info("Token还未过期,直接返回");
			return TokenResult.tokenResult;
		}
		log.info("还没有token或已过期,重新获取");
		String url = QY_WEIXIN_PREFIX + "gettoken?corpid=" + Config.getString("corpId") + "&corpsecret=" + Config.getString("secret");
		String result = HttpUtil.get(url);
		TokenResult.tokenResult = json.fromJson(result, TokenResult.class);
		TokenResult.tokenResult.setUpdateTime(new Date());
		return TokenResult.tokenResult;
	}

	public static JsapiTicketResult getJsapiTicket(String accessToken) {
		String url = QY_WEIXIN_PREFIX + "get_jsapi_ticket?access_token=" + accessToken;
		String result = HttpUtil.get(url);
		return json.fromJson(result, JsapiTicketResult.class);
	}

}

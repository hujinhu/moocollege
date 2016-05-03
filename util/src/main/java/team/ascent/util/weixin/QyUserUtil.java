package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.common.Encodes;
import team.ascent.util.weixin.response.user.UserOauthResponse;

public class QyUserUtil {

	private static final String QY_WECHAT_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String QY_WEIXIN_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/";
	private static JsonUtil json = JsonUtil.nonDefaultMapper();

	/** 
     * 构造带员工身份信息的URL 
     * @param corpid    企业id 
     * @param redirectUrl   授权后回调地址
     * @return 
     */  
    public static String assembleOauthUrl(String corpid, String redirectUrl) {  
        return String.format("%s?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=sunlight#wechat_redirect", 
        		QY_WECHAT_OAUTH_URL, corpid, Encodes.urlEncode(redirectUrl));
    }  
	
    /**
     * 获取用户信息
     * @param token
     * @param code
     * @return
     */
	public static UserOauthResponse getUserInfo(String token, String code) {
		String url = String.format("%suser/getuserinfo?access_token=%s&code=%s", QY_WEIXIN_PREFIX, token, code);
		String result = HttpUtil.get(url);
		System.out.println(result);
		return json.fromJson(result, UserOauthResponse.class);
	}
	
	public static void main(String[] args) {
		UserOauthResponse response = getUserInfo("_J-aeXLkt4v5o8GgL4QhxHH-bObFDSHspCJ5EtPbzeHoAS7uETdKecQaNmxnXJ1K", "7e197286e59c74ce454fb003050bd9ff");
		System.out.println(response);
	}
}

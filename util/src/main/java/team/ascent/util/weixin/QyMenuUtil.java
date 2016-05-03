package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.request.menu.Menu;
import team.ascent.util.weixin.response.WeiXinResult;

public class QyMenuUtil {
	private static final String QY_WEIXIN_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/";

	private static JsonUtil json = JsonUtil.nonEmptyMapper();

	/**
	 * 设置应用菜单
	 * @param token
	 * @param agent
	 * @return
	 */
	public static WeiXinResult createMenu(String access_token, Integer agentId, Menu menu) {
		String url = QY_WEIXIN_PREFIX + "menu/create?access_token=" + access_token + "&agentid=" + agentId;
		String result = HttpUtil.postJson(url, json.toJson(menu));
		System.out.println(result);
		return json.fromJson(result, WeiXinResult.class);
	}
}

package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.request.message.BaseMessage;
import team.ascent.util.weixin.response.WeiXinResult;

public class QyMessageUtil {
	private static final String QY_WEIXIN_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message";

	private static JsonUtil json = JsonUtil.nonEmptyMapper();

	/**
	 * 发送消息
	 * 
	 * @param data
	 * @param accessToken
	 * @return
	 */
	public static WeiXinResult sendMsg(BaseMessage baseMessage, String accessToken) {
		String url = QY_WEIXIN_MESSAGE_URL + "/send?access_token=" + accessToken;
		String result = HttpUtil.postJson(url, json.toJson(baseMessage));
		return json.fromJson(result, WeiXinResult.class);
	}
	
	
//	/**
//	 * 发送消息
//	 */
//	private void sendMsg(final WechatMsgInfo msgInfo, List<String> toUserList, List<Integer> toPartyList, final String accessToken, final BaseMsgEntity base) {
//		// 分批按人发消息
//		if (!toUserList.isEmpty()) {
//			if (toUserList.contains("-9")) {
//				base.setToUser("@all");
//				String result = WechatSendMsgUtil.sendMsg(jsonUtil.toJson(base), accessToken);
//				if (!result.equals("0")) {
//					wxMsgInfoService.fail(msgInfo, result);
//				}
//			} else {
//				Collections3.batchProcess(toUserList, 999, new Collections3.Process<String>() {
//					public void process(List<String> toUser) {
//						String toUserStr = Collections3.convertToString(toUser, "|");
//						base.setToUser(toUserStr);
//						String result = WechatSendMsgUtil.sendMsg(jsonUtil.toJson(base), accessToken);
//						if (!result.equals("0")) {
//							wxMsgInfoService.fail(msgInfo, result);
//						}
//					};
//				});
//			}
//		}
//		// 分批按部门发消息
//		if (!toPartyList.isEmpty()) {
//			Collections3.batchProcess(toPartyList, 99, new Collections3.Process<Integer>() {
//				public void process(List<Integer> toParty) {
//					String toPartyStr = Collections3.convertToString(toParty, "|");
//					base.setToParty(toPartyStr);
//					String result = WechatSendMsgUtil.sendMsg(jsonUtil.toJson(base), accessToken);
//					if (!result.equals("0")) {
//						wxMsgInfoService.fail(msgInfo, result);
//					}
//				};
//			});
//		}
//	}
//
	
	
	

}

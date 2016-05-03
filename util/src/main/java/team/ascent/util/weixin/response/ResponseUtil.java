package team.ascent.util.weixin.response;

import java.util.List;

import team.ascent.util.weixin.enumeration.ResponseType;


/**
 * 被动响应消息的拼装
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年4月30日 下午10:20:10 
 */
public class ResponseUtil {

	/**
	 * 响应文本消息
	 * @param io
	 * @param content
	 */
	public static void responseText(ResponseDTO response, String content) {
		response.setMsgType(ResponseType.TEXT.toString());
		response.setContent(content);
	}
	
	/**
	 * 响应图文消息
	 * @param io
	 * @param content
	 */
	public static void responseNews(ResponseDTO response, List<ArticleItem> items) {
		response.setFuncFlag(1);
		response.setMsgType(ResponseType.NEWS.toString());
		response.setArticleCount(items.size());
		response.setItems(items);
	}
}

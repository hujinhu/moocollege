package team.ascent.util.weixin.request.message;
/**
 * 响应消息之文本消息
 * @author sunlight
 *
 */
public class TextMessage extends BaseMessage {
 	private String msgtype;

	private String content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public TextMessage() {
		super();
		this.msgtype="text";
	}
    
}

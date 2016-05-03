package team.ascent.util.weixin.enumeration;

/**
 * 微信请求类型
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年4月30日 下午10:05:00 
 */
public enum RequestType {
	TEXT(1, "text", "文本"),
	EVENT(2, "event", "事件推送。事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)"),
	IMAGE(3, "image", "图片"),
	LOCATION(4, "location", "地理位置"),
	LINK(5, "link", "链接消息"),
	SUBSCRIBE(6, "subscribe", "关注"),
	VOICE(7, "voice", "声音消息"),
	URL(8, "url", "跳转地址");

	private Integer type;
	private String value;
	private String desc;
	
	private RequestType(Integer type, String value, String desc) {
		this.type = type;
		this.value = value;
		this.desc = desc;
	}
	
	public static RequestType getByValue(String value) {
		for(RequestType rt : RequestType.values()) {
			if(rt.getValue().equals(value))
				return rt;
		}
		throw new RuntimeException("unknown value of request type.");
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}

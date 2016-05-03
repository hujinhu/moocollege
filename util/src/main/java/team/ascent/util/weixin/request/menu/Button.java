package team.ascent.util.weixin.request.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年4月30日 上午12:19:44 
 */
public class Button{
	private String type;
	private String name;
	
	@JsonProperty("key")
	private String keywords;
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
package team.ascent.util.weixin.request.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Callback {

	@JsonProperty("url")
	String url;
	/**
	 * token 用于生成签名
	 */
	@JsonProperty("token")
	String token;
	@JsonProperty("encodingaeskey")
	String aeskey;
	public Callback() {
	}
	public Callback(String url, String token, String aeskey) {
		super();
		this.url = url;
		this.token = token;
		this.aeskey = aeskey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAeskey() {
		return aeskey;
	}
	public void setAeskey(String aeskey) {
		this.aeskey = aeskey;
	}
}

package team.ascent.util.weixin.response.user;

import team.ascent.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserOauthResponse {

	@JsonProperty("UserId")
	private String userId;
	@JsonProperty("DeviceId")
	private String deviceId;
	
	private String errcode;
    private String errmsg;
    
    public boolean isSuccess() {
    	return StringUtil.isNotBlank(userId);
    }
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}

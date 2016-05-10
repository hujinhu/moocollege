package team.ascent.util.weixin.response.user;

import team.ascent.util.StringUtil;
import team.ascent.util.weixin.response.WeiXinResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserOauthResponse extends WeiXinResult {

	@JsonProperty("UserId")
	private String userId;
	@JsonProperty("DeviceId")
	private String deviceId;
	
 
    
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
}

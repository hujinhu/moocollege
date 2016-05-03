package team.ascent.util.weixin.request.token;

public class AccessTokenRequest {

	private String suite_id;
	private String auth_corpid;
	private String permanent_code;
	public String getSuite_id() {
		return suite_id;
	}
	public void setSuite_id(String suite_id) {
		this.suite_id = suite_id;
	}
	public String getAuth_corpid() {
		return auth_corpid;
	}
	public void setAuth_corpid(String auth_corpid) {
		this.auth_corpid = auth_corpid;
	}
	public String getPermanent_code() {
		return permanent_code;
	}
	public void setPermanent_code(String permanent_code) {
		this.permanent_code = permanent_code;
	}
}

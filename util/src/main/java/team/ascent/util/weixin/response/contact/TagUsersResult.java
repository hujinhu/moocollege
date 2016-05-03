package team.ascent.util.weixin.response.contact;

import team.ascent.util.weixin.response.WeiXinResult;


public class TagUsersResult extends WeiXinResult {

	private String invalidlist;
	private int[] invalidparty;
	public String getInvalidlist() {
		return invalidlist;
	}
	public void setInvalidlist(String invalidlist) {
		this.invalidlist = invalidlist;
	}
	public int[] getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(int[] invalidparty) {
		this.invalidparty = invalidparty;
	}
	
}

package team.ascent.util.weixin.response.contact;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;


/**
 * 标签
 */
public class TagDetailResult extends WeiXinResult {
	private List<User> userlist;
	private Integer[] partylist;

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public Integer[] getPartylist() {
		return partylist;
	}

	public void setPartylist(Integer[] partylist) {
		this.partylist = partylist;
	}

}

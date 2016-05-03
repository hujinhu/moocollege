package team.ascent.util.weixin.request.contact;

public class TagUsersRequest {

	private String tagid;
	private String[] userlist;
	private int[] partylist;
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String[] getUserlist() {
		return userlist;
	}
	public void setUserlist(String[] userlist) {
		this.userlist = userlist;
	}
	public int[] getPartylist() {
		return partylist;
	}
	public void setPartylist(int[] partylist) {
		this.partylist = partylist;
	}
}

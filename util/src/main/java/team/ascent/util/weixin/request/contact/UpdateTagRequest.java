package team.ascent.util.weixin.request.contact;

public class UpdateTagRequest {

	private int tagid;
	private String tagname;
	public int getTagid() {
		return tagid;
	}
	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
}

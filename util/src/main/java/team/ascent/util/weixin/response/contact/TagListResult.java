package team.ascent.util.weixin.response.contact;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;


/**
 * 标签
 */
public class TagListResult extends WeiXinResult {
	private List<TagResult> taglist;

	public List<TagResult> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<TagResult> taglist) {
		this.taglist = taglist;
	}

}

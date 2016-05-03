package team.ascent.util.weixin.response;

/**
 * 微信返回通用类
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class WeiXinResult {
	private String errcode;
	private String errmsg;

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

	private boolean isSuccess() {
		return this.getErrcode().equals("0");
	}
}

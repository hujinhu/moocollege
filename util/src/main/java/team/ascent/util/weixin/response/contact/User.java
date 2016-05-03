package team.ascent.util.weixin.response.contact;

/**
 * @ClassName: Person
 * @Description:微信通讯录人员管理接口返回
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年12月1日下午4:05:24
 */
public class User {
	private String userid;
	private String name;
	private Integer[] department;
	private String position;
	private String mobile;
	private int gender;// 1表示男 2表示女
	private String email;
	private String weixinid;
	private String avatar_mediaid;
	private String avatar;
	private int status;
	private UserAttrList extattr;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserAttrList getExtattr() {
		return extattr;
	}

	public void setExtattr(UserAttrList extattr) {
		this.extattr = extattr;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getDepartment() {
		return department;
	}

	public void setDepartment(Integer[] department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
}

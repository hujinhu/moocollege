package team.ascent.util.weixin.enumeration;

/**
 * 消息类型
 */
public enum MsgType {

	Text(1, "text"), Image(2, "image"), Voice(3, "voice"), Video(4, "video"), File(5, "file"), News(6, "news"), Mpnews(7, "mpnews");

	private Integer id;
	private String value;

	private MsgType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public static MsgType formId(Integer id) {
		for (MsgType type : MsgType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}
		return null;
	}

}

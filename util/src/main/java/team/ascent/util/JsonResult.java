package team.ascent.util;

/**
 * $.ajax后需要接受的JSON
 */
public class JsonResult {

	private boolean success = true;// 是否成功
	private String msg;// 提示信息
	private Object obj = null;// 其他信息
	private boolean needLogin;
	
	public static JsonResult ERROR_PARAM = failure("[ERROR PARAM]");
	
	public static JsonResult failure(String message) {
		return new JsonResult(false, message, null, false);
	}
	public static JsonResult success(Object obj) {
		return new JsonResult(true, null, obj, false);
	}
	public static JsonResult success(String message, Object obj) {
		return new JsonResult(true, message, obj, false);
	}
	public static JsonResult needLogin() {
		return new JsonResult(false, null, null, true);
	}
	
	public JsonResult() {
		super();
	}
	public JsonResult(boolean success, String msg, Object obj, boolean needLogin) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
		this.needLogin = needLogin;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isNeedLogin() {
		return needLogin;
	}
	public void setNeedLogin(boolean needLogin) {
		this.needLogin = needLogin;
	}
}


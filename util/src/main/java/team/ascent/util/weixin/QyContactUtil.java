package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.StringUtil;
import team.ascent.util.weixin.request.contact.Callback;
import team.ascent.util.weixin.request.contact.SyncReplacePartyRequest;
import team.ascent.util.weixin.response.WeiXinResult;
import team.ascent.util.weixin.response.contact.Dept;
import team.ascent.util.weixin.response.contact.DeptListResult;
import team.ascent.util.weixin.response.contact.DeptResult;
import team.ascent.util.weixin.response.contact.SyncUserJobResult;
import team.ascent.util.weixin.response.contact.SyncUserResult;
import team.ascent.util.weixin.response.contact.User;
import team.ascent.util.weixin.response.contact.UserListResult;
import team.ascent.util.weixin.response.contact.UserResult;


/**
 * 企业号通讯录相关接口，包括部门，人员和标签的管理
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年12月1日下午4:26:00
 */
public class QyContactUtil {
	private static final String QY_WEIXIN_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/";
	private static JsonUtil json = JsonUtil.nonDefaultMapper();

	/**
	 * 创建部门
	 * 
	 * @param token token凭据
	 * @param dept 
	 *            部门实体类，name(部门名称)和parentid(父部门Id，根部门为1)必填，id和order选填，order越小越靠前
	 * @return
	 */
	public static DeptResult createDept(String token, Dept dept) {
		String url = QY_WEIXIN_PREFIX + "department/create?access_token=" + token;
		String result = HttpUtil.postJson(url, json.toJson(dept));
		return json.fromJson(result, DeptResult.class);
	}

	/**
	 * 更新部门
	 * 
	 * @param token token凭据
	 * @param dept 
	 *            部门实体类，id必填，name(部门名称)和parentid(父部门Id，根部门为1)和order选填，order越小越靠前
	 * @return
	 */
	public static DeptResult updateDept(String token, Dept dept) {
		String url = QY_WEIXIN_PREFIX + "department/update?access_token=" + token;
		String result = HttpUtil.postJson(url, json.toJson(dept));
		return json.fromJson(result, DeptResult.class);
	}

	/**
	 * @Title: delDept
	 * @Description:删除部门
	 * @param token token凭据
	 * @param id 待删除部门id
	 * @return
	 */
	public static DeptResult delDept(String token, int id) {
		String url = QY_WEIXIN_PREFIX + "department/delete?access_token=" + token + "&id=" + id;
		String result = HttpUtil.get(url);
		return json.fromJson(result, DeptResult.class);
	}

	/**
	 * 获取部门列表
	 * 
	 * @param token token凭据
	 * @param id 部门id。获取指定部门及其下的子部门 可选
	 * @return
	 */
	public static DeptListResult getDeptList(String token, Integer id) {
		String url = QY_WEIXIN_PREFIX + "department/list?access_token=" + token;
		if (id != null) {
			url += "&id=" + id;
		}
		String result = HttpUtil.get(url);
		return json.fromJson(result, DeptListResult.class);
	}

	/**
	 * 创建用户，扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static UserResult createUser(String token, User user) {
		String url = QY_WEIXIN_PREFIX + "user/create?access_token=" + token;
		String result = HttpUtil.postJson(url, json.toJson(user));
		return json.fromJson(result, UserResult.class);
	}

	/**
	 * 更新人员
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static UserResult updateUser(String token, User user) {
		String url = QY_WEIXIN_PREFIX + "user/update?access_token=" + token;
		String result = HttpUtil.postJson(url, json.toJson(user));
		return json.fromJson(result, UserResult.class);
	}

	/**
	 * 删除人员
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	public static UserResult delUser(String token, String userId) {
		String url = QY_WEIXIN_PREFIX + "user/delete?access_token=" + token + "&userid=" + userId;
		String result = HttpUtil.get(url);
		return json.fromJson(result, UserResult.class);
	}

	/**
	 * 批量删除人员
	 * 
	 * @param token
	 * @param userIds
	 * @return
	 */
	public static UserResult batchDelUser(String token, String[] userIds) {
		String url = QY_WEIXIN_PREFIX + "user/batchdelete?access_token=" + token;
		String data = "{\"useridlist\": " + json.toJson(userIds) + "}";
		System.out.println(data);
		String result = HttpUtil.postJson(url, data);
		return json.fromJson(result, UserResult.class);
	}

	/**
	 * 获取成员信息
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	public static UserResult getUser(String token, String userId) {
		String url = QY_WEIXIN_PREFIX + "user/get?access_token=" + token + "&userid=" + userId;
		String result = HttpUtil.get(url);
		System.out.println(result);
		return json.fromJson(result, UserResult.class);
	}

	/**
	 * 获取部门下成员列表
	 * 
	 * @param token
	 * @param deptId
	 * @param fetchChild
	 * @param status
	 * @return
	 */
	public static UserListResult getUserList(String token, int deptId, int fetchChild, int status) {
		String url = QY_WEIXIN_PREFIX + "user/list?access_token=" + token + "&department_id=" + deptId + "&fetch_child=" + fetchChild + "&status=" + status;
		// String url=QY_WEIXIN_PREFIX+"user/list";
		// Map<String,String> param=new HashMap<String, String>();
		// param.put("access_token", token);
		// param.put("department_id", String.valueOf(deptId));
		// param.put("fetch_child", String.valueOf(fetchChild));
		// param.put("status", String.valueOf(status));
		// String result=HttpUtil.post(url, param);
		String result = HttpUtil.get(url);
		// {"errcode":60011,"errmsg":"no privilege to access\/modify
		// contact\/party\/agent "}
		return json.fromJson(result, UserListResult.class);
	}

	/**
	 * 异步接口：全量覆盖部门
	 */
	public static SyncUserResult replaceDepartment(String token, String mediaId, String callbackUrl, String signToken, String aeskey) {
		String url = QY_WEIXIN_PREFIX + "batch/replaceparty?access_token=" + token;
		System.out.println(url);
		SyncReplacePartyRequest request = new SyncReplacePartyRequest();
		request.setMediaId(mediaId);
		if (StringUtil.isNotBlank(callbackUrl)) {
			Callback callback = new Callback(callbackUrl, signToken, aeskey);
			request.setCallback(callback);
		}
		System.out.println("request data: \n" + json.toJson(request));
		String result = HttpUtil.postJson(url, json.toJson(request));
		return json.fromJson(result, SyncUserResult.class);
	}

	/**
	 * 批量更新
	 * 
	 * @param token
	 * @param mediaId
	 * @return
	 */
	public static SyncUserResult batchUpdateUser(String token, String mediaId) {
		String url = QY_WEIXIN_PREFIX + "batch/syncuser?access_token=" + token;
		String data = "{\"media_id\":\"" + mediaId + "\"}";
		String result = HttpUtil.postJson(url, data);
		return json.fromJson(result, SyncUserResult.class);
	}

	/**
	 * 批量替换用户
	 * 
	 * @param token
	 * @param mediaId
	 * @return
	 */
	public static SyncUserResult batchReplaceUser(String token, String mediaId) {
		String url = QY_WEIXIN_PREFIX + "batch/replaceuser?access_token=" + token;
		String data = "{\"media_id\":\"" + mediaId + "\"}";
		String result = HttpUtil.postJson(url, data);
		return json.fromJson(result, SyncUserResult.class);
	}

	/**
	 * @Title: getAsyncTaskResult
	 * @Description:获取异步任务结果
	 * @param token
	 * @param taskid
	 * @return
	 */
	public static SyncUserJobResult getAsyncTaskResult(String token, String taskid) {
		String url = QY_WEIXIN_PREFIX + "batch/getresult?access_token=" + token + "&jobid=" + taskid;
		String result = HttpUtil.get(url);
		return json.fromJson(result, SyncUserJobResult.class);
	}

	/**
	 * 
	 * 邀请成员关注
	 * 
	 * @param @param token
	 * @param @param userid
	 * @param @return
	 * @return WeiXinResult
	 */
	public static WeiXinResult inviteSend(String token, String userid) {
		String url = QY_WEIXIN_PREFIX + "invite/send?access_token=" + token;
		String data = "{\"userid\":\"" + userid + "\"}";
		String result = HttpUtil.postJson(url, data);
		return json.fromJson(result, WeiXinResult.class);
	}

	/**
	 * 分页拉取人员
	 * @param token
	 * @param deptId
	 * @param fetchChild
	 * @param offset 和limit参数配套使用，表示从哪一条记录开始拉取
	 * @param limit 和offset参数配套使用，表示一次拉取记录的上限，最高为5000
	 * @return 
	 */
	public static UserListResult listUserPage(String token, int deptId, int fetchChild, int offset, int limit) {
		String url = QY_WEIXIN_PREFIX + "user/list_page?access_token=" + token;
		String data = "{\"department_id\":\"" + deptId + "\",";
		data += "\"fetch_child\":\"" + fetchChild + "\",";
		data += "\"status\":\"" + 0 + "\",";
		data += "\"offset\":\"" + offset + "\",";
		data += "\"limit\":\"" + limit + "\",";
		data += "\"get_detail_info\":\"" + 1 + "\"}";
		String result = HttpUtil.postJson(url, data);

		System.out.println(result);

		return json.fromJson(result, UserListResult.class);
	}

	public static void main(String[] args) {
		String token = "tcBrbhwPE5rvZOXyLBH7UVSlXVnO6-rTe4uRflwvQMPJu8BVtSvjXFnlNJy_W0SV";

		UserListResult UserListResult = listUserPage(token, 1, 1, 0, 4999);
		System.out.println(JsonUtil.nonEmptyMapper().toJson(UserListResult));

	}
}

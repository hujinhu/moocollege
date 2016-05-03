package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.request.contact.CreateTagRequest;
import team.ascent.util.weixin.request.contact.TagNoIdRequest;
import team.ascent.util.weixin.request.contact.TagUsersRequest;
import team.ascent.util.weixin.request.contact.UpdateTagRequest;
import team.ascent.util.weixin.response.WeiXinResult;
import team.ascent.util.weixin.response.contact.CreateTagResult;
import team.ascent.util.weixin.response.contact.TagDetailResult;
import team.ascent.util.weixin.response.contact.TagListResult;
import team.ascent.util.weixin.response.contact.TagUsersResult;


public class QyTagUtil {
	private static final String QY_WEIXIN_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/";
	private static JsonUtil json = JsonUtil.nonDefaultMapper();

	public static TagListResult listTag(String token) {
		String url = QY_WEIXIN_PREFIX + "tag/list?access_token=" + token;
		String result = HttpUtil.get(url);
		return json.fromJson(result, TagListResult.class);
	}

	public static TagDetailResult getTag(String token, Integer tagId) {
		String url = QY_WEIXIN_PREFIX + "tag/get?access_token=" + token + "&tagid=" + tagId;
		String result = HttpUtil.get(url);
		return json.fromJson(result, TagDetailResult.class);
	}
	
	public static CreateTagResult createTag(String token, String tagname,Integer tagId) {
		String url = QY_WEIXIN_PREFIX + "tag/create?access_token=" + token;
		String josnStr = null;
		if( tagId != null ){
			CreateTagRequest request = new CreateTagRequest();
			request.setTagid(tagId);
			request.setTagname(tagname);
			josnStr = json.toJson(request);
		}else{
			TagNoIdRequest request = new TagNoIdRequest();
			request.setTagname(tagname);
			josnStr = json.toJson(request);
		}

		String result = HttpUtil.postJson(url, josnStr);
		return json.fromJson(result, CreateTagResult.class);
	}
	
	public static WeiXinResult updateTag(String token, String tagname,Integer tagId) {
		String url = QY_WEIXIN_PREFIX + "tag/update?access_token=" + token;
		UpdateTagRequest request = new UpdateTagRequest();
		
		request.setTagid(tagId);
		request.setTagname(tagname);

		String result = HttpUtil.postJson(url, json.toJson(request));
		return json.fromJson(result, WeiXinResult.class);
	}
	
	public static WeiXinResult deleteTag(String token, Integer tagId) {
		String url = QY_WEIXIN_PREFIX + "tag/delete?access_token=" + token + "&tagid=" + tagId;
		String result = HttpUtil.get(url);
		return json.fromJson(result, WeiXinResult.class);
	}
	
	public static TagUsersResult addTagUsers(String token,String tagId,String[] userList,int[] partylist){
		String url = QY_WEIXIN_PREFIX + "tag/addtagusers?access_token=" + token;
		TagUsersRequest request = new TagUsersRequest();
		request.setTagid(tagId);
		request.setUserlist(userList);
		request.setPartylist(partylist);

		String result = HttpUtil.postJson(url, json.toJson(request));
		return json.fromJson(result, TagUsersResult.class);
	}
	
	public static TagUsersResult deleteTagUsers(String token,String tagId,String[] userList,int[] partylist){
		String url = QY_WEIXIN_PREFIX + "tag/deltagusers?access_token=" + token;
		TagUsersRequest request = new TagUsersRequest();
		request.setTagid(tagId);
		request.setUserlist(userList);
		request.setPartylist(partylist);

		String result = HttpUtil.postJson(url, json.toJson(request));
		return json.fromJson(result, TagUsersResult.class);
	}
	
	public static void main(String[] args){
//		QyTagUtil.createTag("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", "打扫卫生22", 1002);
//		WeiXinResult result = QyTagUtil.updateTag("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz","打扫卫生11", 1000);
//		TagListResult result = QyTagUtil.listTag("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz");
//
//		 List<TagResult> taglist = result.getTaglist();
//		 for(TagResult tagResult:taglist){
//			 System.out.println("tagid:"+tagResult.getTagid()+",tagname:"+tagResult.getTagname());
//		 }

//		UserListResult result = QyContactUtil.getUserList("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", 1, 1, 0);
//		List<User> userList = result.getUserlist();
//		for(int i=0; i<100; i++ ){
//			User user = userList.get(i);
//			System.out.println("userId:"+user.getUserid()+","+"name:"+user.getName());
//		}
		
//		DeptListResult result = QyContactUtil.getDeptList("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", 1);
//		List<Dept> deptList = result.getDepartment();
//		for(Dept dept:deptList){
//			System.out.println("deptId:"+dept.getId()+","+"name:"+dept.getName());
//		}
		
//		String[] userIds = new String[]{"E00244","E09027","E11012","E17421","E33146","E36116","E44128","E44553","E52402","E53405"};
//		int[] deptIds = new int[]{1,273,149,152,153,154,155,156,157,158,159};
//		
//		TagUsersResult result = QyTagUtil.addTagUsers("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", "1000", userIds, deptIds);
//		System.out.println("errcode:"+result.getErrcode()+","+"errmsg:"+result.getErrmsg());
//		System.out.println("invalidlist:"+result.getInvalidlist()+","+"invalidparty:"+result.getInvalidparty().toString());

		
//		TagDetailResult result = QyTagUtil.getTag("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", 1000);
//		List<User> userList = result.getUserlist();
//		for(User user:userList){
//			System.out.println("userid:"+user.getUserid()+","+"username"+user.getName());
//		}
//		Integer[] departIds = result.getPartylist();
//		for(int departId:departIds){
//			System.out.println("departId:"+departId);
//		}
		
//		String[] userIds = new String[]{"E36116","E44128","E44553","E52402","E53405"};
//		int[] deptIds = new int[]{154,155,156,157,158,159};
//		
//		TagUsersResult result = QyTagUtil.deleteTagUsers("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", "1000", userIds, deptIds);
//		System.out.println("errcode:"+result.getErrcode()+","+"errmsg:"+result.getErrmsg());
//		System.out.println("invalidlist:"+result.getInvalidlist()+","+"invalidparty:"+result.getInvalidparty().toString());
		
//		WeiXinResult result = QyTagUtil.deleteTag("uS73sC6KLeNrvm2yjK75RIxiCjJL_hPMfE9eynmgQnyCD5VHRLHVA6Pet_8zGgQz", 1000);
//		System.out.println("errcode:"+result.getErrcode()+","+"errmsg:"+result.getErrmsg());
		
		CreateTagResult result = QyTagUtil.createTag("lDFD2pdFlhG2rGdX-Fnb60WZmgDAIkKgOZsNqw9uqdhpViYBs89LNyTncQb8H59S", "男同事", null);
		System.out.println("errcode:"+result.getErrcode()+","+"errmsg:"+result.getErrmsg());
		System.out.println(result.getTagid());
	}
	
}

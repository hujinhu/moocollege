package team.ascent.util.weixin.response.contact;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;

/**
 * 
 * @ClassName: PersonList
 * @Description:微信通讯录人员管理列表接口返回
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年12月2日上午10:28:17
 */
public class UserListResult extends WeiXinResult {
    private List<User> userlist;

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }
    
    
}

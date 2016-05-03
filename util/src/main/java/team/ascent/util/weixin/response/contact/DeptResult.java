package team.ascent.util.weixin.response.contact;

import team.ascent.util.weixin.response.WeiXinResult;

/**
 * 通讯录部门返回
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class DeptResult extends WeiXinResult{
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

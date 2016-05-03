package team.ascent.util.weixin.response.contact;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;


/**
 * 通讯录部门列表返回
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class DeptListResult extends WeiXinResult{
    private List<Dept> department;
    public List<Dept> getDepartment() {
        return department;
    }
    public void setDepartment(List<Dept> department) {
        this.department = department;
    }
    
}

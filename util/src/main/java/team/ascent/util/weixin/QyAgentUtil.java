package team.ascent.util.weixin;

import team.ascent.util.HttpUtil;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.response.WeiXinResult;
import team.ascent.util.weixin.response.agent.Agent;


/**
 * 
 * 管理企业号应用
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class QyAgentUtil {
    private static final String QY_WEIXIN_PREFIX="https://qyapi.weixin.qq.com/cgi-bin/";
    private static JsonUtil json = JsonUtil.nonDefaultMapper();
    /**
     * 设置应用
     * @param token
     * @param agent
     * @return
     */
    public static WeiXinResult setAgent(String token,Agent agent){
        String url=QY_WEIXIN_PREFIX+"agent/set?access_token="+token;
        String result=HttpUtil.postJson(url, json.toJson(agent));
        return json.fromJson(result, WeiXinResult.class);
    }
    /**
     * 获取应用列表
     * @param token
     * @param agent
     * @return
     *//*
    public static AgentListResult getAgentList(String token){
        String url=QY_WEIXIN_PREFIX+"agent/list?access_token="+token;
        String result=HttpUtil.get(url);
        return json.fromJson(result, AgentListResult.class);
    }*/
}

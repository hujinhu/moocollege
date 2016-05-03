package team.ascent.util.weixin.response.agent;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;


public class AgentListResult extends WeiXinResult{
    private List<Agent> agentlist;

    public List<Agent> getAgentlist() {
        return agentlist;
    }

    public void setAgentlist(List<Agent> agentlist) {
        this.agentlist = agentlist;
    }
    
}

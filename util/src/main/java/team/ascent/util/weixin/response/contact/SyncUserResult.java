package team.ascent.util.weixin.response.contact;

import team.ascent.util.weixin.response.WeiXinResult;


public class SyncUserResult extends WeiXinResult{
    private String jobid;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    
}

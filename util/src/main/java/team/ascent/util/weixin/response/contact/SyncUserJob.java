package team.ascent.util.weixin.response.contact;

public class SyncUserJob {
    private int action;
    private String userid;
    private String errcode;
    private String errmsg;
    public int getAction() {
        return action;
    }
    public void setAction(int action) {
        this.action = action;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
}

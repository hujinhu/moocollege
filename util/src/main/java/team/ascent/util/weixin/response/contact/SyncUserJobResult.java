package team.ascent.util.weixin.response.contact;

import java.util.List;

import team.ascent.util.weixin.response.WeiXinResult;


public class SyncUserJobResult extends WeiXinResult{
    private int status;
    private String type;
    private int total;
    private int percentage;
    private int remaintime;
    private List<SyncUserJob> result;
    /**
     * 任务状态，整型，1表示任务开始，2表示任务进行中，3表示任务已完成
     * @return
     */
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    public int getRemaintime() {
        return remaintime;
    }
    public void setRemaintime(int remaintime) {
        this.remaintime = remaintime;
    }
    public List<SyncUserJob> getResult() {
        return result;
    }
    public void setResult(List<SyncUserJob> result) {
        this.result = result;
    }
    
}

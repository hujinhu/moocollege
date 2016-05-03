package team.ascent.util.weixin.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import team.ascent.util.weixin.request.RequestDTO;


/**
 * 被动响应消息的实体
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年4月30日 下午10:19:31 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class ResponseDTO {
	@XmlElement(name = "ToUserName")
	private String toUserName;
	@XmlElement(name = "FromUserName")
	private String platformUserName;
	@XmlElement(name = "CreateTime")
	private String createTime;
	@XmlElement(name = "MsgType")
	private String msgType;
	@XmlElement(name = "Content",required=false)
	private String content;
	@XmlElement(name = "FuncFlag",required=false)
	private Integer funcFlag;
	@XmlElement(name = "ArticleCount",required=false)
	private Integer articleCount;
	@XmlElementWrapper(name="Articles")@XmlElements(@XmlElement(name="item",required=false))
	private List<ArticleItem> items;
	//企业号应用ID
	@XmlElement(name = "AgentID")
	private String agentID;
	
	private Integer id;
	
	public ResponseDTO(){
		
	}
	public ResponseDTO(String toUserName,String platformUserName,String createTime,String agentId){
		this.toUserName=toUserName;
		this.platformUserName=platformUserName;
		this.createTime=createTime;
		this.agentID=agentId;
	}
	
	public ResponseDTO(RequestDTO request){
		this(request.getPlatformUserName(), request.getToUserName(), request.getCreateTime(),request.getAgentID());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getPlatformUserName() {
		return platformUserName;
	}
	public void setFromUserName(String platformUserName) {
		this.platformUserName = platformUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		this.setFuncFlag(0);
		this.setMsgType("text");
	}
	public Integer getFuncFlag() {
		return funcFlag;
	}
	public void setFuncFlag(Integer funcFlag) {
		this.funcFlag = funcFlag;
	}

	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public List<ArticleItem> getItems() {
		return items;
	}
	public void setItems(List<ArticleItem> items) {
		this.items = items;
		this.setArticleCount(items.size());
		this.setFuncFlag(1);
		this.setMsgType("news");
	}
	
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	@Override
	public String toString() {
		if(this.getContent()!=null && !this.getContent().equals("")){
			return "WeixinResponse [toUserName=" + toUserName
					+ ", platformUserName=" + platformUserName + ", createTime="
					+ createTime + ", msgType=" + msgType + ", content=" + content
					+ ", funcFlag=" + funcFlag + "]";
		}else{
			ArticleItem item=new ArticleItem();
			return "WeixinResponse [toUserName=" + toUserName
			+ ", platformUserName=" + platformUserName + ", createTime="
			+ createTime + ", msgType=" + msgType + ", articleCount=" + articleCount + ", items=" + item.toString()
			+ ", funcFlag=" + funcFlag + "]";
		}
	}
}

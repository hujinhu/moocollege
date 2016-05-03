package team.ascent.util.weixin.response.agent;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 应用类
 * 
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date 2016年4月28日 下午4:56:46
 */
public class Agent {
	@JsonProperty("agentid")
	private Integer agentId;
	@JsonProperty("report_location_flag")
	private Integer reportLocationFlag;
	@JsonProperty("logo_mediaid")
	private String logoMediaid;
	private String name;
	private String description;
	@JsonProperty("redirect_domain")
	private String redirectDomain;
	private Integer isreportuser;
	private Integer isreportenter;
	@JsonProperty("home_url")
	private String homeUrl;

	public String getLogoMediaid() {
		return logoMediaid;
	}

	public void setLogoMediaid(String logoMediaid) {
		this.logoMediaid = logoMediaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRedirectDomain() {
		return redirectDomain;
	}

	public void setRedirectDomain(String redirectDomain) {
		this.redirectDomain = redirectDomain;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getReportLocationFlag() {
		return reportLocationFlag;
	}

	public void setReportLocationFlag(Integer reportLocationFlag) {
		this.reportLocationFlag = reportLocationFlag;
	}

	public Integer getIsreportuser() {
		return isreportuser;
	}

	public void setIsreportuser(Integer isreportuser) {
		this.isreportuser = isreportuser;
	}

	public Integer getIsreportenter() {
		return isreportenter;
	}

	public void setIsreportenter(Integer isreportenter) {
		this.isreportenter = isreportenter;
	}

}

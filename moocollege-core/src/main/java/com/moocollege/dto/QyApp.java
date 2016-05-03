package com.moocollege.dto;

import java.io.Serializable;

public class QyApp implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.name
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.description
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.logo_mediaid
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String logoMediaid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.agent_id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer agentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.redirect_domain
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String redirectDomain;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.isreportuser
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer isreportuser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.isreportenter
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer isreportenter;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.home_url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String homeUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.report_location_flag
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer reportLocationFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.type
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String url;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.token
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String token;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qy_app.aes_key
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private String aesKey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table qy_app
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.id
	 * @return  the value of qy_app.id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.id
	 * @param id  the value for qy_app.id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.name
	 * @return  the value of qy_app.name
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.name
	 * @param name  the value for qy_app.name
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.description
	 * @return  the value of qy_app.description
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.description
	 * @param description  the value for qy_app.description
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.logo_mediaid
	 * @return  the value of qy_app.logo_mediaid
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getLogoMediaid() {
		return logoMediaid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.logo_mediaid
	 * @param logoMediaid  the value for qy_app.logo_mediaid
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setLogoMediaid(String logoMediaid) {
		this.logoMediaid = logoMediaid == null ? null : logoMediaid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.agent_id
	 * @return  the value of qy_app.agent_id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.agent_id
	 * @param agentId  the value for qy_app.agent_id
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.redirect_domain
	 * @return  the value of qy_app.redirect_domain
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getRedirectDomain() {
		return redirectDomain;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.redirect_domain
	 * @param redirectDomain  the value for qy_app.redirect_domain
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setRedirectDomain(String redirectDomain) {
		this.redirectDomain = redirectDomain == null ? null : redirectDomain.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.isreportuser
	 * @return  the value of qy_app.isreportuser
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getIsreportuser() {
		return isreportuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.isreportuser
	 * @param isreportuser  the value for qy_app.isreportuser
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setIsreportuser(Integer isreportuser) {
		this.isreportuser = isreportuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.isreportenter
	 * @return  the value of qy_app.isreportenter
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getIsreportenter() {
		return isreportenter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.isreportenter
	 * @param isreportenter  the value for qy_app.isreportenter
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setIsreportenter(Integer isreportenter) {
		this.isreportenter = isreportenter;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.home_url
	 * @return  the value of qy_app.home_url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getHomeUrl() {
		return homeUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.home_url
	 * @param homeUrl  the value for qy_app.home_url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl == null ? null : homeUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.report_location_flag
	 * @return  the value of qy_app.report_location_flag
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getReportLocationFlag() {
		return reportLocationFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.report_location_flag
	 * @param reportLocationFlag  the value for qy_app.report_location_flag
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setReportLocationFlag(Integer reportLocationFlag) {
		this.reportLocationFlag = reportLocationFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.type
	 * @return  the value of qy_app.type
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.type
	 * @param type  the value for qy_app.type
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.url
	 * @return  the value of qy_app.url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.url
	 * @param url  the value for qy_app.url
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.token
	 * @return  the value of qy_app.token
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getToken() {
		return token;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.token
	 * @param token  the value for qy_app.token
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qy_app.aes_key
	 * @return  the value of qy_app.aes_key
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public String getAesKey() {
		return aesKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qy_app.aes_key
	 * @param aesKey  the value for qy_app.aes_key
	 * @mbggenerated  Fri Apr 29 10:38:08 CST 2016
	 */
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey == null ? null : aesKey.trim();
	}
}
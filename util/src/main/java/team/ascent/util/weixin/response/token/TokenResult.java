package team.ascent.util.weixin.response.token;

import java.util.Calendar;
import java.util.Date;

import team.ascent.util.DateUtil;
import team.ascent.util.weixin.response.WeiXinResult;

/**
 * 获取token返回类
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class TokenResult extends WeiXinResult {
	private String access_token;
	private int expires_in;
	private Date updateTime;

	/**
	 * 获取token
	 * 
	 * @return token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * 设置token
	 * 
	 * @param access_token
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * 获取有效时间
	 * 
	 * @return 有效时间，单位（秒）
	 */
	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * 设置有效时间
	 * 
	 * @param expires_in
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 是否过期,和上次取的时间相差2小时以上就算过期
	 * @return 
	 */
	public boolean isInvalid() {
		int diffMinute = DateUtil.timeBetween(updateTime, new Date(),
				Calendar.MINUTE);
		System.out.println("相差时间:"+diffMinute);
		if (diffMinute > 120) {
			return true;
		}
		return false;
	}
	
	
	
	//单例
	
	public static TokenResult tokenResult =null;
//	public TokenResult(){
//		this.updateTime=DateUtil.parse("2010-4-29 08:00:00");
//	};
//	public static TokenResult getInstance(){
//		if(tokenResult==null){
//			System.out.println("tokenResult 是空的");
//			tokenResult=new TokenResult();
//		}
//		return tokenResult;
//	}
}

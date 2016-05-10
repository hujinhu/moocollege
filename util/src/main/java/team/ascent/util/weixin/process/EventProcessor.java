package team.ascent.util.weixin.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.ascent.util.weixin.InstructionDTO;
import team.ascent.util.weixin.enumeration.EventType;
import team.ascent.util.weixin.request.RequestDTO;
import team.ascent.util.weixin.response.ArticleItem;
import team.ascent.util.weixin.response.ResponseUtil;

/**
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年5月9日 下午10:28:29 
 */
@Service
@Transactional(readOnly = false)
public class EventProcessor implements IProcessor<InstructionDTO>{

	private static Log log = LogFactory.getLog(EventProcessor.class);

	public void actualProcess(InstructionDTO io) {
		switch (EventType.getByValue(io.getRequest().getEvent())) {
		case subscribe:
			 event_Subscribe(io);
			break;
		case LOCATION:
			event_Location(io);
			break;
		case unsubscribe:
			event_Unsubscribe(io);
			break;
		case click:
			event_Click(io);
			break;
		case view:
			break;
		case scancode_push:
			event_scancode_push(io);
			break;
		case scancode_waitmsg:
			break;
		case pic_sysphoto:
			break;
		case pic_photo_or_album:
			break;
		case pic_weixin:
			break;
		case location_select:
			break;
		default:
			break;
		}
	}

	
	/**
	 * 扫码推送事件 
	 * @param io 
	 */
	private void event_scancode_push(InstructionDTO io) {
		log.info("扫码推送事件!");
	}

	/**
	 * 取消关注
	 *
	 * @param io
	 */
	private void event_Unsubscribe(InstructionDTO io) {
		log.info("取消了关注");
		// 只处理取消企业号(agentId为0)的事件
		if (io.getRequest().getAgentID().equals(0)) {
//			QyUserAppInfo qyUserApp = qyUserAppInfoService.getUserAppInfo(io.getRequest().getToUserName(),
//					io.getRequest().getAgentID());
//			if (qyUserApp != null && qyUserApp.getAppId() != null && qyUserApp.getAppId() != 0) {
//				Person person = personInfoService.getByAccount(qyUserApp.getCompanyId(),
//						io.getRequest().getPlatformUserName());
//				if (person != null && person.getBound().equals("1")) {
//					personInfoService.updateBound(person.getCompanyId(), person.getId(), false, new Date());
//				}
//			}
		}

	}

	/**
	 * 点击菜单
	 */
	private void event_Click(InstructionDTO io) {
		log.info("点击了菜单");
		//ResponseUtil.responseText(io.getResponse(), "点击了按钮,再给你个图文消息");
		RequestDTO request = io.getRequest();
		if ("123".equals(request.getEventKey())) {
			List<ArticleItem> items = new ArrayList<ArticleItem>();
			ArticleItem ai = new ArticleItem();
			ai.setDescription("点击进入百度");
			ai.setPicUrl("http://b.zol-img.com.cn/soft/6/320/cegA2QuijOjy.jpg");
			ai.setTitle("推送图文示例");
			ai.setUrl("www.baidu.com");
			items.add(ai);
			ResponseUtil.responseNews(io.getResponse(), items);
		}
	}

	/**
	 * 上报地理位置
	 */
	private void event_Location(InstructionDTO io) {
		log.info("用户上报位置");
		try {
			RequestDTO request = io.getRequest();
			
//
//			Integer agentId = Integer.valueOf(request.getAgentID());
//			QyUserAppInfo qyUserApp = qyUserAppInfoService.getCompanyAndAppId(request.getToUserName(),
//					String.valueOf(agentId));
//			if (qyUserApp != null && qyUserApp.getAppId() != null && qyUserApp.getAppId() != 0) {
//
//				KQPositions kq = new KQPositions();
//				kq.setType("1");
//				kq.setLatitude(Double.valueOf(request.getLatitude()));
//				kq.setLongitude(Double.valueOf(request.getLongitude()));
//				kq.setAccount(request.getPlatformUserName());
//				kq.setAgentid(Integer.valueOf(request.getAgentID()));
//
//				kq.setCorpid(String.valueOf(qyUserApp.getCompanyId()));
//
//				kq.setCreatetime(new Date());
//				kq.setPrec(Double.valueOf(request.getPrecision()));
//				kQPositionsService.insert(kq);
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关注事件
	 */
	private void event_Subscribe(InstructionDTO io) {
		log.info("关注了企业号");
		try {
//			QyUserAppInfo qyUserApp = qyUserAppInfoService.getUserAppInfo(io.getRequest().getToUserName(),
//					io.getRequest().getAgentID());
//			if (qyUserApp != null && qyUserApp.getAppId() != null && qyUserApp.getAppId() != 0) {
//				String suffixUrl = "&agentId=" + io.getRequest().getAgentID();
//				List<ArticleItem> items = new ArrayList<ArticleItem>();
//				ArticleItem ai = new ArticleItem();
//				if (qyUserApp.getUserAppName() != null && !qyUserApp.getUserAppName().equals("")) {
//					ai.setTitle(qyUserApp.getUserAppName());
//				} else {
//					ai.setTitle(qyUserApp.getAppName());
//				}
//				ai.setDescription("");
//				if (qyUserApp.getUserFirstVisitPic() != null && !qyUserApp.getUserFirstVisitPic().equals("")) {
//					ai.setPicUrl(qyUserApp.getUserFirstVisitPic());
//				} else {
//					ai.setPicUrl(Config.getString("webPath") + qyUserApp.getAppTwImg());
//				}
//				ai.setUrl(Config.getString("webPath") + qyUserApp.getAppTwUrl() + io.getRequest().getToUserName()
//						+ suffixUrl);
//				items.add(ai);
//				log.error("title:" + ai.getTitle() + " img:" + ai.getPicUrl() + " url:" + ai.getUrl() + " ");
//
//				Person person = personInfoService.getByAccount(qyUserApp.getCompanyId(),
//						io.getRequest().getPlatformUserName());
//				if (person != null) {
//					personInfoService.updateBound(person.getCompanyId(), person.getId(), true, new Date());
//				} else {
//					log.error("公司ID:" + qyUserApp.getCompanyId() + ",从微信获取用户account为"
//							+ io.getRequest().getPlatformUserName() + ",成员不存");
//				}
//				ResponseUtil.responseNews(io.getResponse(), items);
//			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
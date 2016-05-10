package com.moocollege.menu;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import team.ascent.util.JsonUtil;
import team.ascent.util.common.Collections3;
import team.ascent.util.weixin.QyMenuUtil;
import team.ascent.util.weixin.QyTokenUtil;
import team.ascent.util.weixin.request.menu.Button;
import team.ascent.util.weixin.request.menu.Menu;
import team.ascent.util.weixin.response.WeiXinResult;
import team.ascent.util.weixin.response.token.TokenResult;

import com.moocollege.dto.QyApp;
import com.moocollege.dto.QyMenu;
import com.moocollege.service.IQyAppService;
import com.moocollege.service.IQyMenuService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
@Transactional
public class CreateMenu extends AbstractTransactionalJUnit4SpringContextTests {
	private static Log log = LogFactory.getLog(CreateMenu.class);

 
	@Value(value = "#{commonConfig[domain]}")
	private String domain;

	@Autowired
	private IQyAppService qyAppService;
	@Autowired
	private IQyMenuService qyMenuService;

	@Test
	public void install() throws Exception {
		TokenResult tokenResult = QyTokenUtil.getAccessToken();
		System.out.println(tokenResult.getAccess_token());
		List<QyApp> listAllApp = qyAppService.listByType(0);//只查消息型应用
		List<QyMenu> listAllMenu = qyMenuService.listAll();
		Map<Integer, List<QyMenu>> listAllMenuMap = Collections3.extractToMapList(listAllMenu, "appId");

		for (QyApp app : listAllApp) {
			Menu menu = new Menu();
			List<QyMenu> listMenu = listAllMenuMap.get(app.getId());
			if (listMenu == null || listMenu.isEmpty()) {
				continue;
			}
			for (QyMenu m : listMenu) {
				Button button = new Button();
				PropertyUtils.copyProperties(button, m);
				menu.getButton().add(button);
			}

			WeiXinResult result = QyMenuUtil.createMenu(tokenResult.getAccess_token(), app.getAgentId(), menu);
			JsonUtil.nonDefaultMapper().toJson(result);
		}

	}
	
	
	
	

}

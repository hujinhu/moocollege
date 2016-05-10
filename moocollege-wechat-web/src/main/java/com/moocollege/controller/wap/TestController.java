package com.moocollege.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import team.ascent.util.Constant;
import team.ascent.util.JsonUtil;
import team.ascent.util.weixin.QyContactUtil;
import team.ascent.util.weixin.QyMessageUtil;
import team.ascent.util.weixin.request.message.NewsMessage;
import team.ascent.util.weixin.request.message.NewsMessage.News.Articles;
import team.ascent.util.weixin.response.WeiXinResult;
import team.ascent.util.weixin.response.contact.UserResult;

import com.moocollege.controller.base.BaseController;

/**
 * 测试用
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年5月10日 上午10:30:25 
 */
@Controller
public class TestController extends BaseController {
	private static Log log = LogFactory.getLog(TestController.class);

	@RequestMapping("/test")
	public String Test(HttpServletRequest request, ModelMap model) {
		String UserId = (String) request.getSession().getAttribute(Constant.USERID);
		UserResult userResult = QyContactUtil.getUser(getAccessToken(), UserId);
		model.addAttribute("userInfo", userResult);
		return "user";
	}

	@RequestMapping("/sendMsg")
	public String sendMsg(HttpServletRequest request, ModelMap model) {
		String UserId = (String) request.getSession().getAttribute(Constant.USERID);

		NewsMessage news = new NewsMessage();
		news.setAgentid(19);
		news.setTouser(UserId);

		Articles articles = new Articles();
		articles.setDescription("描述");
		articles.setPicurl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2609450077,4178219781&fm=116&gp=0.jpg");
		articles.setTitle("www.baidu.com");
		articles.setUrl("www.baidu.com");
		
		news.getNews().getArticles().add(articles);

		WeiXinResult weiXinResult =QyMessageUtil.sendMsg(news, this.getAccessToken());
		System.out.println(JsonUtil.nonDefaultMapper().toJson(weiXinResult));
		return "user";
	}

}
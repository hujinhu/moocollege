package team.ascent.util.weixin.request.message;

import java.util.ArrayList;
import java.util.List;


/**
 * 响应消息之图文消息
 * 
 * @author sunlight
 *
 */
public class NewsMessage extends BaseMessage {
 	private String msgtype;

	private News news;
	public static class News {
		private List<Articles> articles;

		public List<Articles> getArticles() {
			return articles;
		}

		public void setArticles(List<Articles> articles) {
			this.articles = articles;
		}

		public static class Articles {
			private String title;
			private String description;
			private String url;
			private String picurl;

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getPicurl() {
				return picurl;
			}

			public void setPicurl(String picurl) {
				this.picurl = picurl;
			}

		}

		public News() {
			super();
			this.articles = new ArrayList<Articles>();
		}

	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public NewsMessage() {
		super();
		this.news= new News();
		this.msgtype="news";
	}

	
}

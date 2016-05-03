package team.ascent.util.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.util.HtmlUtils;

import team.ascent.util.StringUtil;

public class HtmlFilter {
	
	// >
	public static final String GT = "&gt;";
	// <
	public static final String LT = "&lt;";
	// "
	public static final String QUOT = "&quot;";
	// &
	public static final String AMP = "&amp;";
	// 空格
	public static final String SPACE = "&nbsp;";
	// ©
	public static final String COPYRIGHT = "&copy;";
	// ®
	public static final String REG = "&reg;";
	// ™
	public static final String TM = "&trade;";
	// ¥
	public static final String RMB = "&yen;";

	private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	private static final Pattern P_SCRIPT = Pattern.compile(REGEX_SCRIPT,
			Pattern.CASE_INSENSITIVE);
	
	private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
	private static final Pattern P_STYLE = Pattern
			.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
	
	private static final String REGEX_HTML = "<[^>]+>";
	private static final Pattern P_HTML = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
	

	private final static Whitelist user_content_filter = Whitelist.relaxed();

	static {
		user_content_filter.addTags("embed", "object", "param", "span", "div");
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");
		user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
		user_content_filter.addAttributes("param", "name", "value");
		user_content_filter.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen", "allowScriptAccess", "flashvars", "name", "type", "pluginspage");
	}

	/**
	 * 对用户输入内容进行过滤
	 * 
	 * @param html
	 * @return
	 */
	public static String filter(String html) {
		if (StringUtil.isBlank(html))return "";
		html = HtmlUtils.htmlUnescape(html);
		
		return Jsoup.clean(html, user_content_filter);
	}

	/**
	 * 删除script标签
	 * 
	 * @param str
	 * @return
	 */
	public static String delScriptTag(String str) {
		Matcher m_script = P_SCRIPT.matcher(str);
		str = m_script.replaceAll("");
		return str.trim();
	}

	/**
	 * 删除style标签
	 * 
	 * @param str
	 * @return
	 */
	public static String delStyleTag(String str) {
		Matcher m_style = P_STYLE.matcher(str);
		str = m_style.replaceAll("");
		return str;
	}

	/**
	 * 删除HTML标签
	 * 
	 * @param str
	 * @return
	 */
	public static String delHTMLTag(String str) {
		if(StringUtil.isBlank(str)) return "";
		
		Matcher m_html = P_HTML.matcher(str);
		str = m_html.replaceAll("");
		return str;
	}

	/**
	 * 删除所有标签
	 * 
	 * @param str
	 * @return
	 */
	public static String delAllTag(String str) {
		// 删script
		str = delScriptTag(str);
		// 删style
		str = delStyleTag(str);
		// 删HTML
		str = delHTMLTag(str);
		return str;
	}

	/**
	 * 清除标签,恢复HTML转义字符
	 * 
	 * @param str
	 * @return
	 */
	public static String clean(String str) {
		str = delAllTag(str);
		str = str.replaceAll(SPACE, " ");
		str = str.replaceAll(GT, ">");
		str = str.replaceAll(LT, "<");
		str = str.replaceAll(QUOT, "\"");
		str = str.replaceAll(AMP, "&");
		str = str.replaceAll(COPYRIGHT, "©");
		str = str.replaceAll(REG, "®");
		str = str.replaceAll(TM, "™");
		str = str.replaceAll(RMB, "¥");
		return str;
	}

	/**
	 * 过滤指定标签
	 * 
	 * @param str
	 * @param tag
	 *            指定标签
	 * @return String
	 */
	public static String fiterHtmlTag(String str, String tag) {
		String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 替换指定的标签
	 * 
	 * @param str
	 * @param beforeTag
	 *            要替换的标签
	 * @param tagAttrib
	 *            要替换的标签属性值
	 * @param startTag
	 *            新标签开始标记
	 * @param endTag
	 *            新标签结束标记
	 * @return String example: 替换img标签的src属性值为[img]属性值[/img]
	 */
	public static String replaceHtmlTag(String str, String beforeTag,
			String tagAttrib, String startTag, String endTag) {
		String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
					.group(1));
			if (matcherForAttrib.find()) {
				matcherForAttrib.appendReplacement(sbreplace, startTag
						+ matcherForAttrib.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		String src = "<img src=\"scrscriptipt:alert(123)\"></img><embed src='http://www.baidu.com'></embed><div>divtest</div><h1>123<script>alert(123)</script></h1>";
		
		
		src = "<p style=\"text-align:center\"><strong><span style=\"font-size:24px;font-family:宋体\">通知</span></strong></p>" +
				"<p><span style=\"font-size:19px;font-family:宋体\">各科室：</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:宋体\">定于</span><span style=\"font-size:19px\">2016</span><span style=\"font-size:19px;font-family:宋体\">年</span><span style=\"font-size:19px\">3</span><span style=\"font-size:19px;font-family: 宋体\">月</span><span style=\"font-size: 19px\">21</span><span style=\"font-size:19px;font-family:宋体\">日（下周一）晚五点在门诊楼五楼会议室进行全院业务学习。</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px\">1</span><span style=\"font-size:19px;font-family:宋体\">、主讲人：辛士军</span> <span style=\"font-size:19px;font-family: 宋体\">胸腔镜在胸外科的应用</span></p>" +
				"<p style=\"text-indent:140px\"><span style=\"font-size:19px;font-family:宋体\">王绍辉</span> <span style=\"font-size:19px;font-family: 宋体\">减盐防控高血压</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px\">2</span><span style=\"font-size:19px;font-family:宋体\">、请各科室主任通知到每一位科室人员，做好笔记，培训内容将作为业务考试内容，学习笔记王院长将进行审阅。</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px\">3</span><span style=\"font-size:19px;font-family:宋体\">、请假者需向张院长请假，请假扣</span><span style=\"font-size:19px\">30</span><span style=\"font-size:19px;font-family:宋体\">元，未请假者扣</span><span style=\"font-size:19px\">80</span><span style=\"font-size:19px;font-family: 宋体\">元。</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px\">4</span><span style=\"font-size:19px;font-family:宋体\">、所需课件请课后到医务科拷贝。</span></p>" +
				"<p style=\"text-indent:37px\"><span style=\"font-size:19px\">5</span><span style=\"font-size:19px;font-family:宋体\">、培训期间请将手机调至震动或静音，需接听电话者请到会议室外。</span></p>" +
				"<p style=\"text-indent: 37px;\"><span style=\"font-family: 宋体; font-size: 19px; text-indent: 448px;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 医务科</span></p>" +
				"<p style=\"text-indent: 37px;\"><span style=\"font-size: 19px;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span><span style=\"text-indent: 448px; text-align: right; font-size: 19px; font-family: 宋体;\">二〇一六年三月十五日</span></p>" +
				"<p>" +
				"    <br />" +
				"</p>";
		
		System.out.println(delHTMLTag(src));
	}
}

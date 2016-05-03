package team.ascent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @ClassName: StringUtil
 * @Description:String类型帮助类
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年11月19日下午4:12:28
 */
public class StringUtil extends StringUtils {
	/**
	 * 判断是否是QQ表情
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;
		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		StringBuilder buf = new StringBuilder();
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				buf.append(codePoint);
			} 
		}
		return buf.toString();
	}
	
	
	
	/**
	 * 删除逗号
	 * 
	 * @param s
	 * @return
	 */
	public static String removeComma(String s) {
		if (s == null || s.equals("")) {
			return s;
		}
		if (s.startsWith(",")) {
			s = s.substring(1, s.length());
		}

		if (s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}

		s = s.replaceAll(",,", ",");
		return s;
	}

	/**
	 * string按,转换为list
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> stringToArray(String s) {
		List<String> list = new ArrayList<String>();
		s = removeComma(s);

		if (s == null || s.equals("")) {
			return null;
		}
		String s_array[] = s.split(",");
		for (int i = 0; i < s_array.length; i++) {
			list.add(s_array[i]);
		}

		return list;
	}

	/**
	 * String 转为数组,Integer类型
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static Integer[] stringToArray(String s, int length) {

		s = removeComma(s);
		if (s == null || s.equals("")) {
			return null;
		}
		String s_array[] = s.split(",");
		Integer ids[] = new Integer[s_array.length];
		for (int i = 0; i < s_array.length; i++) {
			ids[i] = Integer.parseInt(s_array[i]);
		}
		return ids;
	}

	/**
	 * String转为list,Integer
	 * 
	 * @param s
	 * @return
	 */
	public static List<Integer> stringToIntArray(String s) {
		List<Integer> list = new ArrayList<Integer>();
		s = removeComma(s);
		if (s == null || s.equals("")) {
			return null;
		}
		String s_array[] = s.split(",");
		for (int i = 0; i < s_array.length; i++) {
			list.add(Integer.parseInt(s_array[i]));
		}
		return list;
	}

	/**
	 * 去除提交文本中得特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceScript(String str) {
		str = str.replaceAll("\\?|java|script|\\(|\\)|\\<|\\>|\"|\'|\\@|\\#|\\%|\\&|\\*|\\!|or|alert|\\:|\\;|", "");
		return str;
	}
	
}

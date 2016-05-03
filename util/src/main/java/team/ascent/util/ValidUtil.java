package team.ascent.util;
/**
 * 
 * @ClassName: ValidUtil
 * @Description:通用验证类
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年11月23日上午11:39:26
 */
public class ValidUtil {

	
	private static final String PATTERN_MOBILE = "1[0-9]{10}";
	private static final String PATTERN_EMAIL1 = "^[A-Za-z0-9._^+-]+@[\\w0-9-]+\\.[\\w.]+$";
	
	public static boolean isMobile(String src) {
		return StringUtil.isNotBlank(src) && src.matches(PATTERN_MOBILE);
	}
	
	public static boolean isEmail(String src) {
		return StringUtil.isNotBlank(src) && src.matches(PATTERN_EMAIL1);
	}
	
	public static void main(String[] args) {
		System.out.println(isEmail("farac01@farlogistics.com "));
	}
}

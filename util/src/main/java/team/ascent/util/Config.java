package team.ascent.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取配置文件中的值  ResourceBundle 方式可以在非web项目中使用
 * @author <a href="mailto:1933549135@qq.com">Amr</a>
 * @date  2016年5月9日 上午11:36:43 
 */
public class Config {
	private static Logger log = LoggerFactory.getLogger("team.ascent.util.Config");
	private static ResourceBundle resources = null;
	private static ResourceBundle namedResources = null;

	private static void getBundle() {
		try {
			resources = ResourceBundle.getBundle("common", Locale.getDefault());
		} catch (MissingResourceException mre) {
			log.error("", mre);
		}
	}

	private static void getNamedBundle(String filename) {
		try {
			namedResources = ResourceBundle.getBundle(filename, Locale.getDefault());
		} catch (MissingResourceException mre) {
			log.error("", mre);
		}
	}

	public static String getValue(String key, String filename) {
		getNamedBundle(filename);
		try {
			return namedResources.getString(key);
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean checkResources() {
		if (resources == null)
			getBundle();
		return (resources != null);
	}

	private static boolean changeToBoolean(String str) throws Exception {
		String tmp = str.toLowerCase();
		if (tmp.equals("true"))
			return true;
		else if (tmp.equals("false"))
			return false;
		else
			throw new Exception("不能找到资源文件");
	}

	public static boolean getBoolean(String key) {
		String str = getString(key);
		try {
			return changeToBoolean(str);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		String str = getString(key);
		try {
			return changeToBoolean(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private static int changeToInt(String str) throws Exception {
		return Integer.parseInt(str);
	}

	public static int getInt(String key) {
		String str = getString(key);
		try {
			return changeToInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getInt(String key, int defaultValue) {
		String str = getString(key);
		try {
			return changeToInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String getString(String key, String defaultValue) {
		String tmp = null;
		if (checkResources()) {
			try {
				tmp = resources.getString(key);
			} catch (Exception e) {
				tmp = defaultValue;
			}
		}
		return tmp;
	}

	public static String getString(String key) {
		if (checkResources()) {
			try {
				return resources.getString(key);
			} catch (Exception e) {
				;
			}
		}
		return null;
	}

	public static String[] getStringArray(String key) {
		if (checkResources())
			return resources.getStringArray(key);
		return null;
	}

	public static Enumeration getKeys() {
		return resources.getKeys();
	}

	public static String getDynamicString(String key, String[] param) {
		String content = getString(key);
		String regex = "\\{([0-9]*)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		if (count == param.length) {
			for (int i = 0; i < count; i++) {
				content = content.replaceFirst(regex, param[i]);
			}
			return content;
		} else {
			return "error";
		}
	}

}

/**
 * 
 */
package team.ascent.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author lenovo
 *
 */
public class BeanUtils {
	private static Logger logger = Logger.getLogger(BeanUtils.class);

	/**
	 * 对象拷贝
	 * 
	 * @param targetObj
	 *            目标bean
	 * @param sourceObj
	 *            源bean
	 */
	public static void copyProperties(Object targetObj, Object sourceObj) {
		try {
			PropertyUtils.copyProperties(targetObj, sourceObj);
		} catch (IllegalAccessException e) {
			logger.error("BeanUtils copyProperties: " + e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error("BeanUtils copyProperties: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error("BeanUtils copyProperties: " + e.getMessage());
		}
	}
}

package team.ascent.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil extends DateUtils {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static long SENCOD_IN_MILLS = 1000L;
	public static long MINUTE_IN_MILLS = SENCOD_IN_MILLS * 60;
	public static long HOUR_IN_MILLS = MINUTE_IN_MILLS * 60;
	public static long DAY_IN_MILLS = HOUR_IN_MILLS * 24;
	
	private static final String[] parsePatterns = new String[] {  
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyyMMdd",
        "yyyy-MM-dd",
        "yyyy-MM",
        "HH:mm",
        "MM/dd/yyyy HH:mm:ss",  
        "MM/dd/yyyy"
        // 这里可以增加更多的日期格式，用得多的放在前面  
    };  
	
	public static String format(Date date, String format) {
		if(date == null) return "";
		
		SimpleDateFormat df = new SimpleDateFormat(StringUtils.defaultIfEmpty(
				format, DEFAULT_FORMAT));
		return df.format(date);
	}
	
	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT);
	}

	public static Date parse(String date) {
		try {
			return parseDate(date, parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* 判断是否是昨天的某个时间
	 */
	public static boolean isYesterdaySomeTime(Date date) {
    	return isSomeTimeInDay(date, -1);
    }
	
	public static boolean isSomeTimeInDay(Date date, int dayIndex) {
    	if(date == null) return false;
    	
    	Date d2 = addDays(new Date(), dayIndex);
    	return isSameDay(date, d2);
    }
	
	
	/**
	 * 获取某一天及其前后几天的零点
	 */
	public static Date getZeroTime(Date date, int n){
		return addDays(truncate(date, Calendar.DAY_OF_MONTH), n);
	}
	
	/**
	 *判断两个日期是否是同一天
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if(date1 == null || date2 == null) return false;
		
		return truncatedEquals(date1, date2, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * @param early
	 * @param late
	 * @param type {@link Calendar#HOUR_OF_DAY} etc..
	 * @return
	 */
	public static int timeBetween(Date early, Date late, int type) {
		long v = 1L;
		switch (type) {
		case Calendar.DAY_OF_MONTH:
			v = DAY_IN_MILLS;
			break;
		case Calendar.HOUR:
		case Calendar.HOUR_OF_DAY:
			v = HOUR_IN_MILLS;
			break;
		case Calendar.MINUTE:
			v = MINUTE_IN_MILLS;
			break;
		case Calendar.SECOND:
			v = SENCOD_IN_MILLS;
			break;
		case Calendar.MILLISECOND:
			// default 1
			break;
		default:
			throw new RuntimeException("Unsupported type of:" + type);	
		}
		
		return (int)((late.getTime() - early.getTime()) / v);
	}
	
	public static void main(String[] args) {
		Date d1 = parse("2015-12-20 23:00:00");
		Date d2 = parse("2015-12-20 24:00:01");
		System.out.println(timeBetween(d1, d2, Calendar.SECOND));
	}
	
	/**
	 * 数据库定义 1 SOLAR 阳历; 2 LUNAR 阴历; 增加START 占位enum
	 * @author iacdp
	 *
	 */
	public enum Type {
		START(""), SOLAR("阳历"), LUNAR("阴历");
		
		private String description;

		private Type(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            要判断的时间
	 * @return dayForWeek 判断结果
	 */
	public static String dayForWeek ( Date dateTime ) throws Exception
	{
		Calendar c = Calendar.getInstance ( );
		c.setTime ( dateTime );
		int dayForWeek = 0;
		if ( c.get ( Calendar.DAY_OF_WEEK ) == 1 )
		{
			dayForWeek = 7;
		}
		else
		{
			dayForWeek = c.get ( Calendar.DAY_OF_WEEK ) - 1;
		}
		return String.valueOf(dayForWeek);
	}
	
}

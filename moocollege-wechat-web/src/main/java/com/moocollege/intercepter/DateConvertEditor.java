package com.moocollege.intercepter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class DateConvertEditor implements Converter<String, Date> {
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat datetimeFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	public Date convert(String source) {
		dateFormat.setLenient(false);
		if (StringUtils.hasText(source)) {
			try {
				if(source.indexOf(":") != -1 && source.length() == 5){
					return (this.timeFormat.parse(source));
				}else if (source.indexOf(":") == -1 && source.length() == 7) {
					return (this.monthFormat.parse(source));
				}else if (source.indexOf(":") == -1 && source.length() == 10) {
					return (this.dateFormat.parse(source));
				} else if (source.indexOf(":") > 0 && source.length() == 19) {
					return (this.datetimeFormat.parse(source));
				} else if (source.indexOf(":") > 0 && source.length() == 21) {
					source = source.replace(".0", "");
					return (this.datetimeFormat.parse(source));
				}else if(source.indexOf(":")>0 && source.length()==16){
					return (this.datetimeFormat2.parse(source));
				} else {
					throw new IllegalArgumentException("Could not parse date, date format is error ");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
package team.ascent.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public final class WebUtils {
	
	//通过前端的负载均衡服务器时，请求对象中的IP会变成负载均衡服务器的IP，因此需要特殊处理，下同。
    public static final String X_REAL_IP = "X-Real-IP";
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";

	/**
	 * 不可实例化
	 */
	private WebUtils() {
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 * @param secure
	 *            是否启用加密
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
		addCookie(request, response, name, value, maxAge, "/", null, null);
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, null, "/", null, null);
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie名称
	 * @return 若不存在则返回null
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		removeCookie(request, response, name, "/", null);
	}

	/**
	 * 获取参数
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param encoding
	 *            编码格式
	 * @param name
	 *            参数名称
	 * @return 参数
	 */
	public static String getParameter(String queryString, String encoding, String name) {
		String[] parameterValues = getParameterMap(queryString, encoding).get(name);
		return parameterValues != null && parameterValues.length > 0 ? parameterValues[0] : null;
	}

	/**
	 * 获取参数
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param encoding
	 *            编码格式
	 * @param name
	 *            参数名称
	 * @return 参数
	 */
	public static String[] getParameterValues(String queryString, String encoding, String name) {
		return getParameterMap(queryString, encoding).get(name);
	}

	/**
	 * 获取参数
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param encoding
	 *            编码格式
	 * @return 参数
	 */
	public static Map<String, String[]> getParameterMap(String queryString, String encoding) {
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		Charset charset = Charset.forName(encoding);
		if (StringUtils.isNotEmpty(queryString)) {
			byte[] bytes = queryString.getBytes(charset);
			if (bytes != null && bytes.length > 0) {
				int ix = 0;
				int ox = 0;
				String key = null;
				String value = null;
				while (ix < bytes.length) {
					byte c = bytes[ix++];
					switch ((char) c) {
					case '&':
						value = new String(bytes, 0, ox, charset);
						if (key != null) {
							putMapEntry(parameterMap, key, value);
							key = null;
						}
						ox = 0;
						break;
					case '=':
						if (key == null) {
							key = new String(bytes, 0, ox, charset);
							ox = 0;
						} else {
							bytes[ox++] = c;
						}
						break;
					case '+':
						bytes[ox++] = (byte) ' ';
						break;
					case '%':
						bytes[ox++] = (byte) ((convertHexDigit(bytes[ix++]) << 4) + convertHexDigit(bytes[ix++]));
						break;
					default:
						bytes[ox++] = c;
					}
				}
				if (key != null) {
					value = new String(bytes, 0, ox, charset);
					putMapEntry(parameterMap, key, value);
				}
			}
		}
		return parameterMap;
	}

	private static void putMapEntry(Map<String, String[]> map, String name, String value) {
		String[] newValues = null;
		String[] oldValues = map.get(name);
		if (oldValues == null) {
			newValues = new String[] { value };
		} else {
			newValues = new String[oldValues.length + 1];
			System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
			newValues[oldValues.length] = value;
		}
		map.put(name, newValues);
	}

	private static byte convertHexDigit(byte b) {
		if ((b >= '0') && (b <= '9')) {
			return (byte) (b - '0');
		}
		if ((b >= 'a') && (b <= 'f')) {
			return (byte) (b - 'a' + 10);
		}
		if ((b >= 'A') && (b <= 'F')) {
			return (byte) (b - 'A' + 10);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * 获取远程访问IP 前段有负载均衡时获取负载服务转发后的IP地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
        String remoteIp = request.getHeader(X_REAL_IP); //nginx反向代理
        if (StringUtils.isNotBlank(remoteIp)) {
            return remoteIp;
        } else {
            remoteIp = request.getHeader(X_FORWARDED_FOR);//apache反射代理
            if (StringUtils.isNotBlank(remoteIp)) {
                String[] ips = remoteIp.split(",");
                for (String ip : ips) {
                    if (!"null".equalsIgnoreCase(ip)) {
                        return ip;
                    }
                }
            }
            return request.getRemoteAddr();
        }
    }
	
	/**
	 * 获取相对地址与参数
	 * 
	 * @param request
	 * @return	example /activity?from=llgj return /activity
	 * @throws IOException
	 */
	public static String getRelativeUrl(HttpServletRequest request) {
		String result = request.getRequestURI().substring(request.getContextPath().length());
		int index = result.indexOf(";");
		if(index != -1) {
			result = result.substring(0, index);
		}
		return result;
	}
	
	/**
	 * 获取访问全地址 不包括参数
	 * 
	 * @param request
	 * @return example http://3g.k189.cn:8100/flow-service-wap/index
	 */
	public static String getCompleteUrl(HttpServletRequest request) {
		return getDomainUrl(request) + request.getRequestURI();
	}
	
	public static String getDomainUrl(HttpServletRequest request) {
		return request.getScheme() + "://"
				+ request.getServerName() + 
				(request.getServerPort() == 80 ? "" : ":" + request.getServerPort())
				;
	}
	
	/**
	 * 获取 http://域名：端口/context
	 * 
	 * @param request
	 * @return	example http://3g.k189.cn:8100/flow-service-assets-query
	 */
	public static String getRequestContext(HttpServletRequest request) {
		return getDomainUrl(request) + request.getContextPath();
	}
}
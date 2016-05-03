package team.ascent.util;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.Charsets;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @ClassName: HttpUtil
 * @Description:网络请求通用类
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年6月24日上午10:26:02
 */
public class HttpUtil {
	/**
	 * HTTPCLIENT连接池配置
	 */
	private static HttpClient HTTPCLIENT = null;

	// private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 
	 * @Title: postJson
	 * @Description:post方法，不指定参数名
	 * @param url
	 *            请求地址
	 * @param json
	 *            请求json内容
	 * @param @return
	 * @return JSONObject
	 */
	public static String postJson(String url, String json) {
		HTTPCLIENT = getHttpClient();
		// HttpClient HttpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpEntity resEntity = null;
		StringEntity entity = null;
		int i = 1;
		while (true) {
			try {
				// 不设置会报java.net.SocketTimeoutException: Read timed out....
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();// 设置请求和传输超时时间
				post.setConfig(requestConfig);

				entity = new StringEntity(json, "UTF-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");// 设置为 json数据
				post.setEntity(entity);
				HttpResponse response = HTTPCLIENT.execute(post);
				resEntity = response.getEntity();
				String res = EntityUtils.toString(resEntity, "UTF-8");
				return res;
			} catch (Exception e) {
				if (e instanceof SocketException) {
					System.out.println("异常:java.net.SocketException: Connection reset已处理..重试第" + i + "次");
					if (i > 5) {
						return null;
					}
					i++;
				} else {
					e.printStackTrace();
					return null;
				}
			} finally {
				post.releaseConnection();
				EntityUtils.consumeQuietly(entity);
				EntityUtils.consumeQuietly(resEntity);
			}
		}
	}

	/**
	 * 
	 * @Title: postMediaFile
	 * @Description:post方法，上传素材文件
	 * @param url
	 *            请求地址
	 * @param file
	 *            待上传文件
	 * @param fileType
	 *            待上传文件类型           
	 * @param @return
	 * @return JSONObject
	 */
	public static String postMediaFile(String url, File file) {
		HTTPCLIENT = getHttpClient();
		HttpPost post = new HttpPost(url);
		HttpEntity reqEntity = null;
		HttpEntity resEntity = null;
		try {
			FileBody fileBody = new FileBody(file);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create().addPart("media", fileBody);
			reqEntity = builder.build();
			post.setEntity(reqEntity);
			HttpResponse response = HTTPCLIENT.execute(post);
			resEntity = response.getEntity();
			String res = EntityUtils.toString(resEntity);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			post.releaseConnection();
			EntityUtils.consumeQuietly(reqEntity);
			EntityUtils.consumeQuietly(resEntity);
		}
	}

	/**
	 * 
	* @Title: post
	* @Description:post请求
	* @param url 请求地址
	* @param params 请求参数
	* @return String
	 */
	public static String post(String url, Map<String, String> params) {
		HTTPCLIENT = getHttpClient();
		String result = null;
		HttpPost httpPost = null;
		HttpEntity reqEntity = null;
		HttpEntity responseEntity = null;
		try {
			httpPost = new HttpPost(url);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (String name : params.keySet()) {
				formparams.add(new BasicNameValuePair(name, params.get(name)));
			}
			reqEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
			httpPost.setEntity(reqEntity);
			HttpResponse response = HTTPCLIENT.execute(httpPost);
			responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			EntityUtils.consumeQuietly(reqEntity);
			EntityUtils.consumeQuietly(responseEntity);
		}

		return result;
	}

	/**
	 * 
	 * @Title: get
	 * @Description:get方法，当只需要请求地址就能取到想要得数据时使用此方法
	 * @param url 请求地址
	 * @return String
	 */
	public static String get(String url) {
		HTTPCLIENT = getHttpClient();
		HttpGet get = new HttpGet();
		String result = "";
		HttpEntity resEntity = null;
		try {
			get.setURI(new URI(url));
			HttpResponse response = HTTPCLIENT.execute(get);
			if (response != null) {
				resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "utf-8");
				}
			}
			get.releaseConnection();
			get.abort();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
			get.abort();
			try {
				EntityUtils.consume(resEntity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getHttpClient
	 * @Description:HTTPCLIENT实例化，自动
	 * @param @return
	 * @return HttpClient
	 */
	private static HttpClient getHttpClient() {
		if (HTTPCLIENT != null) {
			return HTTPCLIENT;
		} else {
			RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create();
			ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
			registryBuilder.register("http", plainSF);
			// 指定信任密钥存储对象和连接套接字工厂
			try {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();
				LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				registryBuilder.register("https", sslSF);
			} catch (KeyStoreException e) {
				throw new RuntimeException(e);
			} catch (KeyManagementException e) {
				throw new RuntimeException(e);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
			Registry<ConnectionSocketFactory> registry = registryBuilder.build();
			// 设置连接管理器
			int maxConn = 200;
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
			// 设置编码格式
			connManager.setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(Charsets.toCharset(Charset.forName("UTF-8"))).build());
			// 设置超时
			connManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(120000).build());
			// 设置最大路由
			connManager.setDefaultMaxPerRoute(maxConn);
			// 设置最大链接数
			connManager.setMaxTotal(maxConn);
			// 构建客户端
			HTTPCLIENT = HttpClientBuilder.create().setConnectionManager(connManager).build();
			return HTTPCLIENT;
		}

	}

}

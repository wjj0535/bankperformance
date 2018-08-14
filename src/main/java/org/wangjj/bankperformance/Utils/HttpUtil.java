package org.wangjj.bankperformance.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	private static CloseableHttpClient httpClient = null;
	
	static {
//		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", plainsf)
//                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加到200
        cm.setMaxTotal(1000);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(1000);
        // 将目标主机的最大连接数增加到50
        SocketConfig socketConfig = SocketConfig.custom()
				.setTcpNoDelay(true)     //是否立即发送数据，设置为true会关闭Socket缓冲，默认为false
				.setSoReuseAddress(true) //是否可以在一个进程关闭Socket后，即使它还没有释放端口，其它进程还可以立即重用端口
				//.setSoTimeout(1)       //接收数据的等待超时时间，单位ms
				//.setSoLinger(60)         //关闭Socket时，要么发送完所有数据，要么等待60s后，就关闭连接，此时socket.close()是阻塞的
	            .setSoKeepAlive(true)    //开启监视TCP连接是否有效
	            .build();
        cm.setDefaultSocketConfig(socketConfig);
      //请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,int executionCount, HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃                    
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试                    
                    return true;
                }          
                if (exception instanceof InterruptedIOException) {// 超时                    
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达                    
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝                    
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {                    
                    return true;
                }
                return false;
            }
        };  
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler)
                .build();
	}

	/**
	 * post发送json数据
	 * 功能：
	 * 时间：2018年3月21日
	 * 作者：wangjunjie
	 */
	public static String httpPostWithJson(String postJsonData, String url) {

		//CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);
		
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(1000).setConnectionRequestTimeout(1000)  
		        .setSocketTimeout(1000).build();
		
		httpPost.setConfig(requestConfig);

		StringEntity se = new StringEntity(postJsonData, "UTF-8");

		se.setContentEncoding("UTF-8");
		se.setContentType("application/json");
		
		httpPost.setEntity(se);
		//httpPost.setHeader("Connection", "close");

		CloseableHttpResponse httpResponse;

		String result = null;
		
		try {

			httpResponse = httpClient.execute(httpPost);
			
			int nRetCode = httpResponse.getStatusLine().getStatusCode();
			if (nRetCode == 200) {
//				InputStream is = httpResponse.getEntity().getContent();
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(is));
//
//				String inputLine ;
//				StringBuffer response = new StringBuffer();
//
//				while ((inputLine = reader.readLine()) != null) {
//					response.append(inputLine);
//				}
//				is.close();
//				reader.close();
//				
//				result = response.toString();
				
                HttpEntity entity = httpResponse.getEntity();  
                result =  EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
				httpResponse.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发送http请求失败！", e);
		} finally {
//			try {
//				
//				httpClient.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.error("关闭httpclient失败！", e);
//			}
		}
		return result;
	}

	/**
	 * 键值对post的方式（未完成）
	 * 功能：
	 * 时间：2018年3月21日
	 * 作者：wangjunjie
	 */
	public static boolean httpPostWithDataPair(String postJsonData, String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CloseableHttpResponse response2 = null;

		try {
			response2 = httpclient.execute(httpPost);
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(response2 != null)
				try {
					response2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}

	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	public static void main(String[] args) {
		HttpUtil.httpPostWithJson("", "http://127.0.0.1:8080/logServer/recvProductLog");
	}
}

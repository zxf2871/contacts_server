package com.sectong.thirdparty.sms;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * 短信平台
 * 
 * @author jiekechoo
 *
 */
public class SendSMS {
	/**
	 * 群发短信
	 * 
	 * @param url
	 * @param account
	 * @param pswd
	 * @param mobile
	 * @param msg
	 * @param needstatus
	 * @param extno
	 * @return
	 * @throws Exception
	 */
	public static String batchSend(String url, String account, String pswd, String mobile, String msg,
			boolean needstatus, String extno) throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("account", account),
					new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", msg),
					new NameValuePair("extno", extno), });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * 检查账号状态
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String checkAccountStatus(String account, String password) throws Exception {
		String url = "http://erp.253.com/api/";// 应用地址
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {

			URI base = new URI(url, false);
			method.setURI(new URI(base, "checkaccount", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("did", "57"),
					new NameValuePair("account", account), new NameValuePair("password", password), });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
	}

}
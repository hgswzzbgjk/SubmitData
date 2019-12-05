package com.dgpt.submitdata;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class LoginUtils {
	//使用HttpURLConnection   GET方式提交数据
	public static String loginByGet(String username, String password) {
		try {
			//拼装URL 注意为了防止乱码 这里需要将参数进行编码
			String path = "http://192.168.97.229:83/register/loginbyget.php?username="
					+ URLEncoder.encode(username, "UTF-8")
					+ "&password="
					+ URLEncoder.encode(password);
			//创建URL实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);        //设置超时时间
			conn.setRequestMethod("GET");        //设置访问方式
			int code = conn.getResponseCode(); //拿到返回的状态码
			if (code == 200) {                    // 请求成功
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//使用HttpURLConnection  POST方式提交数据
	public static String loginByPost(String username, String password) {
		try {
			//要访问的资源路径
			String path = "http://192.168.97.229:83/register/loginbypost.php";
			//创建URL的实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//设置超时时间
			conn.setConnectTimeout(5000);
			//指定请求方式
			conn.setRequestMethod("POST");
			//准备数据 将参数编码
			String data = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password);
			//设置请求头
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length() + "");
			//将数据写给服务器
			conn.setDoOutput(true);
			//得到输出流
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());           //将数据写入输出流中
			int code = conn.getResponseCode(); //那到服务器返回的状态码
			if (code == 200) {
				//得到服务器返回的输入流
				InputStream is = conn.getInputStream();
				//将输入流转换成字符串
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//使用HttpURLConnection  POST JSON方式提交数据
	//{"username":"admin","password":"123456"}
	//{"RESULT":"S","ERRMSG":"json success"}
	//{"RESULT":"F","ERRMSG":"json failed"}
	public static String loginByJson(String jsonstr) {
		try {
			//要访问的资源路径
			String path = "http://192.168.97.229:83/register/loginbyjson.php";
			//创建URL的实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			/* optional request header */
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			//设置超时时间
			conn.setConnectTimeout(5000);
			//指定请求方式
			conn.setRequestMethod("POST");
			//设置请求头
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			//conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", jsonstr.length() + "");
			conn.setRequestProperty("accept", "application/json");
			//将数据写给服务器
			conn.setDoOutput(true);
			//得到输出流
			OutputStream os = conn.getOutputStream();
			os.write(jsonstr.getBytes());           //将数据写入输出流中
			os.flush();
			os.close();

			int code = conn.getResponseCode(); //那到服务器返回的状态码
			if (code == 200) {
				//得到服务器返回的输入流
				InputStream is = conn.getInputStream();
				//将输入流转换成字符串
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//使用HttpURLConnection  POST JSON方式提交数据
	//{"username":"admin","password":"123456"}
	//{"RESULT":"S","ERRMSG":"json success"}
	//{"RESULT":"F","ERRMSG":"json failed"}
	public static String loginByWWW(String wwwstr) {
		try {
			//要访问的资源路径
			String path = "http://192.168.97.229:83/register/loginbyjson.php";
			//创建URL的实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			//设置超时时间
			conn.setConnectTimeout(5000);
			//指定请求方式
			conn.setRequestMethod("POST");
			//设置请求头

			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", wwwstr.length() + "");
			//将数据写给服务器
			conn.setDoOutput(true);
			//得到输出流
			OutputStream os = conn.getOutputStream();
			os.write(wwwstr.getBytes());           //将数据写入输出流中
			os.flush();
			os.close();

			int code = conn.getResponseCode(); //那到服务器返回的状态码
			if (code == 200) {
				//得到服务器返回的输入流
				InputStream is = conn.getInputStream();
				//将输入流转换成字符串
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Function  :   封装请求体信息
	 * Param     :   params请求体内容，encode编码格式
	 */
	public static StringBuffer getRequestData(Map<String, String> params, String encode) {
		StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
		try {
			for(Map.Entry<String, String> entry : params.entrySet()) {
				stringBuffer.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

	//使用HttpURLConnection  获取数据
	public static String getAllUser() {
		List<UserInfo>list;
		try {
			//要访问的资源路径
			String path = "http://192.168.97.229:83/register/getAllUser.php";
			//创建URL的实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			//设置超时时间
			conn.setConnectTimeout(5000);
			//指定请求方式
			conn.setRequestMethod("POST");
			//设置请求头

			int code = conn.getResponseCode(); //那到服务器返回的状态码
			if (code == 200) {
				//得到服务器返回的输入流
				InputStream is = conn.getInputStream();
				//将输入流转换成字符串
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//使用HttpURLConnection  获取数据
	public static String getAllUserNew() {
		List<UserInfo>list;
		try {
			//要访问的资源路径
			String path = "http://192.168.97.229:83/register/getAllUserNew.php";
			//创建URL的实例
			URL url = new URL(path);
			//获取HttpURLConnection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			//设置超时时间
			conn.setConnectTimeout(5000);
			//指定请求方式
			conn.setRequestMethod("POST");
			//设置请求头

			int code = conn.getResponseCode(); //那到服务器返回的状态码
			if (code == 200) {
				//得到服务器返回的输入流
				InputStream is = conn.getInputStream();
				//将输入流转换成字符串
				String text = StreamTools.readInputStream(is);
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}



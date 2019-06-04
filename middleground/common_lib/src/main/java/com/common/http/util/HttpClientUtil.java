package com.common.http.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtil {
	
	private final static int TIME_OUT=60;//秒

/**
 * 
 * @param urlStr  请求地址
 * @param request  请求参数
 * @return
 * @throws Exception
 */
	public static String doPost(String urlStr, String request) throws Exception {
		HttpURLConnection http = null;
		BufferedReader inputStream = null;
		OutputStream outputStream = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,故此处最好将其转化为HttpURLConnection类型的对象,以便用到HttpURLConnection更多的API.如下:
			http = (HttpURLConnection) url.openConnection();
			// post请求，以下两项必须为true；
			http.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
			http.setDoOutput(true);// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
			// Post 请求不能使用缓存
			http.setUseCaches(false);
			
			
			//设置超时时间
			http.setConnectTimeout(TIME_OUT*1000);//毫秒  设置连接超时 如果在建立连接之前超时期满，则会引发一个java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
			http.setReadTimeout(TIME_OUT*1000);//毫秒  设置读取超时 如果在数据可读取之前超时期满，则会引发一个java.net.SocketTimeoutException。超时时间为零表示无穷大超时。

			
			// 设定请求的方法为"POST"，默认是GET
			http.setRequestMethod("POST");
			//设置http请求头
//			http.setRequestProperty("Content-Type", "application/x-java-serialized-object");// 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)

			// 连接，从上述url.openConnection()至此的配置必须要在connect之前完成
			http.connect();
			// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)。
			outputStream = http.getOutputStream();

//			// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。 
//			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm); 
//			// 向对象输出流写出数据，这些数据将存到内存缓冲区中 
//			objOutputStrm.writeObject(new String("我是测试数据")); 
//			// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream） 
//			objOutputStm.flush(); 
//			// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器 
//			objOutputStm.close(); 
//			// 调用HttpURLConnection连接对象的getInputStream()函数,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。 
//			InputStream inStrm = httpConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里 
//			// 至此上边的httpConn.getInputStream()方法已调用,本次HTTP请求已结束。因此，要重新发送数据时需要重新创建连接、重新设参数、重新创建流对象、重新写数据、 重新发送数据

			// 通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
			OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");
			osw.write(request);// 将要发送的数据存入缓存
			osw.flush();// 刷新对象输出流，将任何字节都写入潜在的流中
			osw.close();// 关闭流

			if (http.getResponseCode() == 200) {// 成功处理http请求
//				inputStream = http.getInputStream();// <===注意，实际发送请求的代码段就在这里
				inputStream=new BufferedReader(new InputStreamReader(http.getInputStream()));// <===注意，实际发送请求的代码段就在这里
				String line;
				while ((line = inputStream.readLine()) != null) {
					result += line;
				}
			} else {
				throw new RuntimeException("http read [" + http.getResponseCode() + "]");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (http != null) {
				http.disconnect();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		return result; 
	}
	
	
	
	
	
	
	
	//测试部分
	public static void main(String[] args) throws Exception {
		

	}
}
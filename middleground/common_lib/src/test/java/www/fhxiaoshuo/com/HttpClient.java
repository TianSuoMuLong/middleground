package www.fhxiaoshuo.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 此脚本只适用于网站 https://www.fushuwang.com/
 */
public class HttpClient {
	// 保存位置
	private final static String FILE_NAME = "C:\\迅雷下载\\小说待整理\\重生之嫡妻归来by水墨青烟.txt";
	// 字符集
	private final static String CHARACTER_SET = "GBK";

	// 地址https://www.fhxiaoshuo.com/read/
	// 源地址
	private final static String URL = "https://www.fhxiaoshuo.com/read/18/18952/";

	public static void main(String[] args) {
		File file = new File(FILE_NAME);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			// 获取章节URL集合
			ArrayList<String> listURL = getContextURL(URL);

			for (int i = 0; i < listURL.size(); i++) {
//				System.out.println(listURL.get(i));
				downLoad(listURL.get(i), fw);
				System.out.println("第" + i + "张完成");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 获取章节URL集合
	private static ArrayList getContextURL(String url) {
		ArrayList list = new ArrayList();
		String result = doGet(url);
		String[] splits = result.split("\\r\\n");
		for (int i = 0; i < splits.length; i++) {
//			System.out.println(splits[i].trim());
			String temp = splits[i].trim();
			if (temp.startsWith("<dd><a href=\"") && temp.endsWith("</a></dd>")) {
				
				list.add(temp.substring(13, 67));
//				System.out.println(temp.substring(13, 67));
			}
		}
		Collections.sort(list);
		return list;
	}

//下载小说内容
	public static void downLoad(String url, FileWriter fw) throws IOException {
		String result = doGet(url);
		String[] splits = result.split("\\r\\n");
		String temp = null;
		for (int i = 0; i < splits.length; i++) {
			if (splits[i].trim().startsWith("<h1>") && splits[i].trim().endsWith("</h1>")) {
				temp = splits[i].trim().replaceAll("<(S*?)[^>]*>.*?|<.*? />|[&nbsp; |&gt;|/a&gt;|&lt;]", "");
				fw.write(temp + "\n"); // 输出
			}

			if (splits[i].contains("<div class=\"zhangjieTXT\" id=\"TXT\">")) {
				while (true) {
					i++;
					if (splits[i].contains("<!--go-->") || splits[i].trim().length() == 0) {
						continue;
					}
					temp = splits[i].trim().replaceAll("<br /><br />", "\n");

					if (splits[i].contains("<div class=\"bottem\">")) {
						fw.flush();
						return;
					}
					fw.write(temp + "\n"); // 输出
				}
			}
		}
	}

	public static void writeToTXT(String text) throws IOException {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		fw.write(text); // 输出

	}

	public static String doGet(String httpurl) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(httpurl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod("GET");
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
//                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				br = new BufferedReader(new InputStreamReader(is, CHARACTER_SET));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			connection.disconnect();// 关闭远程连接
		}

		return result;
	}

	public static String doPost(String httpUrl, String param) {

		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}
}
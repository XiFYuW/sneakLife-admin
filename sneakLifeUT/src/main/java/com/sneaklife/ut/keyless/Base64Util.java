package com.sneaklife.ut.keyless;

import com.sneaklife.ut.file.FileCode;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Base64Util {
	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * 将文件编码为BASE64字符串
	 * @param filePath 文件绝对路径
	 * @return String
	 * @throws Exception 异常信息
	 */
	public static String encodeFile(String filePath) throws Exception {
		byte[] bytes = fileToByte(filePath);
		return base64Encode(bytes);
	}

	/**
	 * BASE64字符串转回文件
	 * @param filePath 文件绝对路径
	 * @param base64 编码字符串
	 * @throws Exception 异常信息
	 */
	public static void decodeToFile(String filePath, String base64) throws Exception {
		byte[] bytes = base64Decode(base64);
		byteArrayToFile(bytes, filePath);
	}

	private static void toFile(InputStream in, OutputStream out) throws Exception{
		byte[] cache = new byte[CACHE_SIZE];
		int nRead = 0;
		while ((nRead = in.read(cache)) != -1) {
			out.write(cache, 0, nRead);
			out.flush();
		}
		out.close();
		in.close();
	}

	/**
	 * 文件转换为二进制数组
	 * @param filePath 文件路径
	 * @return static
	 * @throws Exception 异常信息
	 */
	public static byte[] fileToByte(String filePath) throws Exception {
		byte[] data = new byte[0];
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
			toFile(in, out);
			data = out.toByteArray();
		}
		return data;
	}

	/**
	 * 二进制数据写文件
	 * @param bytes 二进制数据
	 * @param filePath 文件生成目录
	 * @throws Exception 异常信息
	 */
	public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
		InputStream in = new ByteArrayInputStream(bytes);
		File destFile = new File(filePath);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		destFile.createNewFile();
		OutputStream out = new FileOutputStream(destFile);
		toFile(in, out);
	}

	/**
	 * 将base64编码字符串转换为图片
	 * @param imgStr base64编码字符串
	 * @param path 图片路径--图片存放的物理路径地址
	 * @return String
	 * @throws IOException 异常信息
	 */
	public static String generateImage(String imgStr,String path) throws IOException{
		if(imgStr.contains("data:image/jpeg;base64,")){
			//存在对应的转换信息,去掉
			imgStr = imgStr.substring(23);
		}
		if(imgStr.contains("data:image/png;base64,")){
			//存在对应的转换信息,去掉
			imgStr = imgStr.substring(22);
		}
		BASE64Decoder decoder = new BASE64Decoder();
		// 解密
		byte[] b = decoder.decodeBuffer(imgStr);
		BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(b));
		if (null == bufImg) {
			return "";
		}
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		ImageIO.write(bufImg, FileCode.JPG.toValue(), new FileOutputStream(file));
		return imgStr;
	}

	/**
	 * base64 加密
	 * @param bytes 字节数组
	 * @return  String
	 */
	public static String base64Encode(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * base64 解密
	 * @param base64Code base64字符串
	 * @return byte[]
	 * @throws Exception 异常信息
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return Base64.decodeBase64(base64Code);
	}

	/**
	 * 本地图片转换成base64字符串
	 * @param imgFile 图片本地路径
	 * @return String
	 * @throws IOException 异常信息
	 */
	public static String imageToBase64ByLocal(String imgFile) throws IOException {
		InputStream in;
		byte[] data;
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * 在线图片转换成base64字符串
	 * @param imgURL 图片线上路径
	 * @return String
	 * @throws IOException 异常信息
	 */
	public static String imageToBase64ByOnline(String imgURL) throws IOException {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		// 创建URL
		URL url = new URL(imgURL);
		byte[] by = new byte[1024];
		// 创建链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		InputStream is = conn.getInputStream();
		// 将内容读取内存中
		int len = -1;
		while ((len = is.read(by)) != -1) {
			data.write(by, 0, len);
		}
		is.close();
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data.toByteArray());
	}
}

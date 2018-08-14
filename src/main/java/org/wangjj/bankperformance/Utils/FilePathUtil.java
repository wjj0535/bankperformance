package org.wangjj.bankperformance.Utils;

import java.io.File;
import java.net.*;

import org.springframework.util.ClassUtils;

public class FilePathUtil {
	/**
	 * 获取当前jar包路径
	 * 功能：
	 * 时间：2018年3月20日
	 * 作者：wangjunjie
	 */
	public static String GetJarPath()
	{
//		URL url = FilePathUtil.class.getProtectionDomain().getCodeSource().getLocation();
//        String filePath = null;
//        try {
//            filePath = URLDecoder.decode(url.getPath(), "utf-8");
//            filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//            System.out.println(filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        if (filePath.endsWith(".jar"))
//            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
//        File file = new File(filePath);
//        filePath = file.getAbsolutePath();
//        return filePath;
		return "";
	}
}

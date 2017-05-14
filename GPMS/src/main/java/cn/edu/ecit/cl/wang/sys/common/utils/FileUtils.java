package cn.edu.ecit.cl.wang.sys.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	public static boolean saveFile(MultipartFile file, String savePath, String fileName) {
		String path = System.getProperty("web.root") + savePath;
		File targetFile = new File(path, fileName);
		try {
			file.transferTo(targetFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

package cn.edu.ecit.cl.wang.sys.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	public static boolean saveFile(MultipartFile file, String savePath) {
		if (file != null && file.getSize() != 0) {
			String path = System.getProperty("web.root") + savePath;
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+ file.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				file.transferTo(targetFile);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}

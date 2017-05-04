package cn.edu.ecit.cl.wang.sys.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;

@Controller
@RequestMapping("file")
public class FileDown {
	
	@Autowired
	GlobalProperties gp;

	@RequestMapping(value = "/download", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<byte[]> download(@RequestParam(name = "fileName") String fileName,
	        HttpServletRequest request) {
	    HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 获取物理路径
            String filePath = System.getProperty("web.root") + gp.getUploadPath();
            File f = new File(filePath,  fileName);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return null;
	}
}

package cn.edu.ecit.cl.wang.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;

@Controller
public class HomeController {

	@Autowired
	private GlobalProperties globalProperties;

	@RequestMapping({ "/home" })
	public String home() {
		return globalProperties.getExtJsHomePath();
	}

	@RequestMapping({ "/index" })
	public String index() {
		return "index";
	}

}

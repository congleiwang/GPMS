package cn.edu.ecit.cl.wang.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;

@Controller
public class PageController {

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

	@RequestMapping({ "/login" })
	public String login(Boolean error, RedirectAttributes attr) {
		if ((error != null) && (error.booleanValue())) {
			attr.addAttribute("error", error);
		}
		return "login";
	}

	@RequestMapping({ "/logintwice" })
	public String logintwice(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(408);
		return "login";
	}

}

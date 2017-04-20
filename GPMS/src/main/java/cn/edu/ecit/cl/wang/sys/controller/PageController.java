package cn.edu.ecit.cl.wang.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.security.MyUserDetails;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;

@Controller
public class PageController {
	
	@Autowired
	IMenuService menuService;

	@RequestMapping({ "/home" })
	public String home(HttpServletRequest request) {
		MyUserDetails user=SpringSecurityUtils.getCurrentUser();
		HttpSession session=request.getSession();
		session.setAttribute("user",user);
		return "index";
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

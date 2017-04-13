package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;

@Controller
public class PageController {
	
	@Autowired
	IMenuService menuService;

	@RequestMapping({ "/home" })
	public String home(HttpServletRequest request) {
		List<MenuTree> menuTreeList=menuService.getPermTree();
		HttpSession session=request.getSession();
		session.setAttribute("menuTreeList",menuTreeList);
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

package cn.edu.ecit.cl.wang.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping({ "/login" })
	public String login(Boolean error, RedirectAttributes attr) {
		if ((error != null) && (error.booleanValue())) {
			attr.addAttribute("error", error);
		}
		return "login";
	}

	@RequestMapping({ "/logout" })
	public String logout() {
		return "login";
	}

	@RequestMapping({ "/sessionout" })
	public String esessionout(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(408);
		return "login";
	}

	@RequestMapping({ "/logintwice" })
	public String logintwice(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(408);
		return "login";
	}

}

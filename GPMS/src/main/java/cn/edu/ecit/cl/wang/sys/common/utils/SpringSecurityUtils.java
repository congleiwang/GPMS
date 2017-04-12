package cn.edu.ecit.cl.wang.sys.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cn.edu.ecit.cl.wang.sys.security.MyUserDetails;

public class SpringSecurityUtils {
	public static MyUserDetails getCurrentUser() {
		// 获取上下文
		SecurityContext secCtx = SecurityContextHolder.getContext();
		// 获取认证对象
		Authentication auth = secCtx.getAuthentication();
		// 在认证对象中获取主体对象

		if (auth != null) {
			return (MyUserDetails) auth.getPrincipal();
		}

		MyUserDetails userDetails=new MyUserDetails();
		userDetails.setUserId(1L);
		return userDetails;
	}

}

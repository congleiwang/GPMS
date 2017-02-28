package cn.edu.ecit.cl.wang.sys.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.edu.ecit.cl.wang.sys.po.User;

public class PermissionFilterInvocationDefinitionSource
		implements FilterInvocationSecurityMetadataSource, InitializingBean {
	public void afterPropertiesSet() throws Exception {
	}

	public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {
		Set<ConfigAttribute> allAttributes = null;

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(user instanceof User)) {
			allAttributes = new HashSet<ConfigAttribute>();
			allAttributes.add(new SecurityConfig("ROLE_LOGOUT"));
			return allAttributes;
		}

		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}
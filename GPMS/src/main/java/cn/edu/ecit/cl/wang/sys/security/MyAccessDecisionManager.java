package cn.edu.ecit.cl.wang.sys.security;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

public class MyAccessDecisionManager implements AccessDecisionManager {
	
	Logger log=LogManager.getLogger(MyAccessDecisionManager.class);
	
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		//如果对应资源没有找到角色 则放行  
		if (CollectionUtils.isEmpty(configAttributes)) {
			return;
		}
		log.info("请求待匹配的URL:"+object.toString()); // object is a URL.
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute().trim();

			// 匹配用户拥有的角色和url所属的角色
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority().trim())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("没有权限访问！");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}

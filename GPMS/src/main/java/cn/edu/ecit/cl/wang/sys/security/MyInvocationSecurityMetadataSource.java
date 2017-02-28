package cn.edu.ecit.cl.wang.sys.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;

/**
 * 查找和匹配所有角色的资源对应关系
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource,InitializingBean  {
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private static Logger log=LogManager.getLogger(MyInvocationSecurityMetadataSource.class);
	
	@Autowired
	private IRoleService roleService;
	
	/**
	 *  在Web服务器启动时，提取系统中的所有权限。  加载所有资源与权限的关系  
	 * 
	 */
	private void loadResourceDefine() {
		if(resourceMap == null) {  
	  
			//应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。 
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
	        List<UrlAndRoleId> urlAndRoleIdList = roleService.getUrlAndRoleIdList();  
	        for (UrlAndRoleId urlAndRoleId : urlAndRoleIdList) {
	            Long roleId = urlAndRoleId.getRoleId();  
	              
	            ConfigAttribute ca = new SecurityConfig(String.valueOf(roleId));  
	  
	            List<String> urls = roleService.getMenuUrlsByRoleId(roleId);
	            
	            if(urls.isEmpty()){
	            	continue;
	            }
	            for (String url : urls) {  
	                //判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。 
	                if (resourceMap.containsKey(url)) {  
	                    Collection<ConfigAttribute> value = resourceMap.get(url);  
	                    value.add(ca);  
	                    resourceMap.put(url, value);  
	                } else {  
	                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
	                    atts.add(ca);  
	                    resourceMap.put(url, atts);  
	                }  
	  
	            }  
	        }  
              
        }  
	}

	
	/**
	 * 根据调用的url，返回需要的角色列表
	 * 
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)	throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
        log.info("requestUrl is " + url);
        int firstQuestionMarkIndex = url.indexOf("?");  
        //判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index?a=1&b=2  --> /index)  
        if(firstQuestionMarkIndex != -1){  
            url = url.substring(0,firstQuestionMarkIndex);  
        }  
        if(resourceMap == null) {
            loadResourceDefine(); 
        }  
        log.info("通过资源定位到的权限："+resourceMap.get(url));
        return resourceMap.get(url);  
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadResourceDefine();
	}


}

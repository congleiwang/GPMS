package cn.edu.ecit.cl.wang.sys.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SystemUtils;
import cn.edu.ecit.cl.wang.sys.controller.UserController;
import cn.edu.ecit.cl.wang.sys.po.LoginRecord;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.service.ILoginRecordService;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

public class MyUserDetailServiceImpl implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private GlobalProperties globalProperties;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IOrgService orgService;

	@Autowired
	private ILoginRecordService loginRecordService;

	/**
	 * 根据用户名，查询用户
	 */
	public UserDetails loadUserByUsername(String loginNm) throws UsernameNotFoundException, DataAccessException,
			LockedException, AccountExpiredException, DisabledException {
		//登陆名为空
		if (StringUtils.isEmpty(loginNm)) {
			logger.debug("loadUserByUsername->UsernameNotFoundException: " + loginNm + " isEmpty");
			throw new UsernameNotFoundException("用户 " + loginNm + " 不存在");
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//从数据库中找到该用户
		Long usrId=userService.getIdByLoginNm(loginNm);
		if (usrId == null || usrId==0) {
			logger.debug("loadUserByUsername->UsernameNotFoundException:" + loginNm + "= null");
			//保存其登陆记录
			loginRecordService.insert(new LoginRecord(loginNm, "0", SystemUtils.getIpAddress(request)));
			throw new UsernameNotFoundException("用户 " + loginNm + " 不存在");
		}
		boolean isUserLocked = userService.isUserLocked(usrId);
		if (isUserLocked) {
			logger.debug("loadUserByUsername->LockedException:" + loginNm + " isUserLocked");
			throw new LockedException("用户 " + loginNm + " 已被锁定!");
		}

		CurrentUser cuser = userService.getCurrentUserById(usrId);
		if (cuser != null) {
			cuser.setSubOrgList(orgService.getSubOrgIdList(cuser.getOrgId()));
		}
		//向CurrentUser中注入其角色和菜单列表
		setCurrentUserRoleAndMenu(cuser);
		//保存其登陆记录
		loginRecordService.insert(new LoginRecord(loginNm, "1", SystemUtils.getIpAddress(request)));

		return cuser;
	}

	private void setCurrentUserRoleAndMenu(CurrentUser cuser) {
		//获取用户角色列表
		List<Long> roleIds = userService.getRolesByUserid(cuser.getUserId());
		//若用户角色列表为空，加入系统设置的默认角色
		if ((roleIds.isEmpty()) && (globalProperties.getDefaultRoleId() != null)) {
			Role role = roleService.selectById(globalProperties.getDefaultRoleId());
			if (role != null) {
				roleIds.add(role.getRoleId());
			}
		}
		if (!roleIds.isEmpty()) {
			List<String> menuCds = roleService.getMenuCodesByRoleIds(roleIds);
			cuser.setMenuCds(menuCds);
			cuser.setRoleIds(roleIds);
		}
	}

	/**
	 * 读取菜单资源，对应的角色列表
	 * @return
	 */
	public Map<String, Collection<SecurityConfig>> loadPermissionAuthorities() {
		
		//object[] ==> 0:MENU_CD  1:ROLE_ID
		List<Object[]> codeList = roleService.getMenuAndRoleCodeList();
		Map<String, Collection<SecurityConfig>> permisCodeMap = new HashMap<String, Collection<SecurityConfig>>();

		for (Object[] roleAtuth : codeList) {
			String permisMenuCode = (String) roleAtuth[0];
			if (StringUtils.isNotEmpty(permisMenuCode)) {
				if (permisCodeMap.containsKey(permisMenuCode)) {
					permisCodeMap.get(permisCodeMap).add(new SecurityConfig((String)roleAtuth[0]));
				} else {
					Set<SecurityConfig> roleCodeList = new HashSet<SecurityConfig>();
					roleCodeList.add(new SecurityConfig((String)roleAtuth[1]));
					permisCodeMap.put(permisMenuCode, roleCodeList);
				}
			}
		}
		return permisCodeMap;
	}

	static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}

	static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

}
package cn.edu.ecit.cl.wang.sys.security;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SystemUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TimeUtils;
import cn.edu.ecit.cl.wang.sys.controller.UserController;
import cn.edu.ecit.cl.wang.sys.po.LoginRecord;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.ILoginRecordService;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

public class MyUserDetailService implements UserDetailsService {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginRecordService loginRecordService;
	
	@Autowired
	private IOrgService orgService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IMenuService menuServcie;
	
	@Autowired
	private GlobalProperties globalProperties;

	public UserDetails loadUserByUsername(String loginNm) throws UsernameNotFoundException, DataAccessException {
		
		//登陆名为空
		if (StringUtils.isEmpty(loginNm)) {
			logger.debug("loadUserByUsername->UsernameNotFoundException: " + loginNm + " isEmpty");
			throw new UsernameNotFoundException("用户 " + loginNm + " 不存在");
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//从数据库中找到该用户
		Long userId=userService.getIdByLoginNm(loginNm);
		if (userId == null || userId==0) {
			logger.debug("loadUserByUsername->UsernameNotFoundException:" + loginNm + "= null");
			//保存其登陆记录
			loginRecordService.insert(new LoginRecord(loginNm, "0", SystemUtils.getIpAddress(request)));
			throw new UsernameNotFoundException("用户 " + loginNm + " 不存在");
		}
		boolean isUserLocked = userService.isUserLocked(userId);
		if (isUserLocked) {
			logger.debug("loadUserByUsername->LockedException:" + loginNm + " isUserLocked");
			throw new LockedException("用户 " + loginNm + " 已被锁定!");
		}
		
		User user=new User();
		user.setUserId(userId);
		user.setIpAddr(SystemUtils.getIpAddress(request));
		user.setLastLoginAt(TimeUtils.getNow());
		user.setPasswdErr(0);
		userService.updateById(user);
		//保存其登陆记录
		loginRecordService.insert(new LoginRecord(loginNm, "1", SystemUtils.getIpAddress(request)));
		
		return setGrantedAuthority(userId);
	}

	/**
	 * 向CurrentUser中注入其角色和菜单列表
	 * 
	 * 从数据库读取权限信息，若没有权限，则赋予配置文件中的默认权限，并设置到MyUserDetails对象中
	 * 
	 * @param username
	 * @return
	 */
	public MyUserDetails setGrantedAuthority(Long userId) {
		MyUserDetails userDetails=new MyUserDetails(userService.getUnLockUserById(userId));
		//机构权限
		userDetails.setSubOrgIds(orgService.getSubOrgIdList(userDetails.getOrgId()));
		userDetails.setOrgTrees(orgService.getPermOrgTree(userDetails.getOrgId()));
		//获取用户角色列表
		List<Role> roles = roleService.getRolesByUserId(userDetails.getUserId());
		//若用户角色列表为空，加入系统设置的默认角色
		if ((CollectionUtils.isEmpty(roles)) && (globalProperties.getDefaultRoleId() != null)) {
			Role role = roleService.selectById(Long.valueOf(globalProperties.getDefaultRoleId()));
			if (role != null) {
				roles.add(role);
			}
		}
		if (!CollectionUtils.isEmpty(roles)) {
			userDetails.setRoles(roles);
		}
		userDetails.setMenuTrees(menuServcie.getPermTree(roles));
		
		//放入用户所有权限
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(Role role:roles){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleId().toString()));
		}
		userDetails.setGrantedAuthorities(grantedAuthorities);
		return userDetails;
	}
}

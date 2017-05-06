package cn.edu.ecit.cl.wang.sys.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;
import cn.edu.ecit.cl.wang.sys.pojo.OrgTree;

public class MyUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = -8456779485140979882L;
	
	private List<OrgTree> orgTrees;
	
	private List<Long> subOrgIds;
	
	private List<Role> roles;
	
	private List<MenuTree> menuTrees;
	
	private Collection<GrantedAuthority> grantedAuthorities;
	
	public MyUserDetails() {
	}

	public MyUserDetails(User user) {
		setAddress(user.getAddress());
		setCreateAt(user.getCreateAt());
		setCreator(user.getCreator());
		setEmail(user.getEmail());
		setIpAddr(user.getIpAddr());
		setIsDel(user.getIsDel());
		setIsLock(user.getIsLock());
		setLastLoginAt(user.getLastLoginAt());
		setLoginNm(user.getLoginNm());
		setModAt(user.getModAt());
		setModCount(user.getModCount());
		setModer(user.getModer());
		setOrgId(user.getOrgId());
		setOrgNm(user.getOrgNm());
		setPasswd(user.getPasswd());
		setPasswdErr(user.getPasswdErr());
		setPhoneNum(user.getPhoneNum());
		setRemark(user.getRemark());
		setSignAt(user.getSignAt());
		setUserId(user.getUserId());
		setUserNm(user.getUserNm());
	}
	
	public List<OrgTree> getOrgTrees() {
		return orgTrees;
	}

	public void setOrgTrees(List<OrgTree> orgTrees) {
		this.orgTrees = orgTrees;
	}

	public List<Long> getSubOrgIds() {
		return subOrgIds;
	}

	public void setSubOrgIds(List<Long> subOrgIds) {
		this.subOrgIds = subOrgIds;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<MenuTree> getMenuTrees() {
		return menuTrees;
	}

	public void setMenuTrees(List<MenuTree> menuTrees) {
		this.menuTrees = menuTrees;
	}

	public Collection<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return getPasswd();
	}

	@Override
	public String getUsername() {
		return getUserNm();
	}

	/** 用户账号是否过期 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return "0".equals(getIsLock());
	}

	/** 用户密码是否过期 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return "0".equals(getIsDel());
	}

	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return getUserId().equals(user.getUserId());
		}
		return false;
	}

	public int hashCode() {
		return getUserId().intValue();
	}
}

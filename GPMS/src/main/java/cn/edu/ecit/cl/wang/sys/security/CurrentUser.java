package cn.edu.ecit.cl.wang.sys.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.edu.ecit.cl.wang.sys.po.User;

public class CurrentUser extends User implements UserDetails {

	private static final long serialVersionUID = -8456779485140979882L;

	private Collection<String> menuCds;
	private Collection<Long> roleIds;
	private Collection<GrantedAuthority> grantedAuthorities;
	private String instNm;
	private String instCd;
	private List<Long> subOrgList;
	
	public CurrentUser(){}
	
	public CurrentUser(User user){
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
		setOrg(user.getOrg());
		setOrgId(user.getOrgId());
		setPasswd(user.getPasswd());
		setPasswdErr(user.getPasswdErr());
		setPhoneNum(user.getPhoneNum());
		setRemark(user.getRemark());
		setRoles(user.getRoles());
		setSignAt(user.getSignAt());
		setUserId(user.getUserId());
		setUserNm(user.getUserNm());
	}

	public Collection<String> getMenuCds() {
		return menuCds;
	}

	public void setMenuCds(Collection<String> menuCds) {
		this.menuCds = menuCds;
	}

	public Collection<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Collection<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Collection<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public String getInstNm() {
		return instNm;
	}

	public void setInstNm(String instNm) {
		this.instNm = instNm;
	}

	public String getInstCd() {
		return instCd;
	}

	public void setInstCd(String instCd) {
		this.instCd = instCd;
	}

	public List<Long> getSubOrgList() {
		return subOrgList;
	}

	public void setSubOrgList(List<Long> subOrgList) {
		this.subOrgList = subOrgList;
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

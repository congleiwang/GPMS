package cn.edu.ecit.cl.wang.sys.pojo;

public class RoleAtuth {
	
	private Long roleId;
	
	private String menuCd;

	public RoleAtuth(){}
	
	public RoleAtuth(Long roleId, String menuCd) {
		super();
		this.roleId = roleId;
		this.menuCd = menuCd;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	
}

package cn.edu.ecit.cl.wang.sys.pojo;

public class UserRole {
	
	private Long roleId;
	
	private Long userId;
	
	public UserRole(){}
	
	public UserRole(Long roleId, Long userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}

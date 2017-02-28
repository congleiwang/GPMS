package cn.edu.ecit.cl.wang.sys.po;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("BASE_ROLE")
public class Role extends BaseEntity{

	private static final long serialVersionUID = 4262324022214199109L;

	/** 角色ID */
	@TableId(value = "ROLE_ID" , type = IdType.INPUT)
	private Long roleId;
	
	/** 角色名 */
	@TableField("ROLE_NM")
	private String roleNm;
	
	/** 角色类型（1：学生、2教师、3管理员） */
	@TableField("ROLE_TYPE")
	private String roleType;
	
	/** 备注 */
	@TableField("REMARK")
	private String remark;
	
	/** 是否启用 */
	@TableField("IS_USE")
	private String isUse;

	/** 权限菜单码 */
	@TableField(exist=false)
	private List<String> menuCds;
	
	/** 用户id */
	@TableField(exist=false)
	private List<Long> userIds;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public List<String> getMenuCds() {
		return menuCds;
	}

	public void setMenuCds(List<String> menuCds) {
		this.menuCds = menuCds;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

}

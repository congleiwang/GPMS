package cn.edu.ecit.cl.wang.sys.pojo;

import cn.edu.ecit.cl.wang.sys.po.BaseTree;

public class MenuTree extends BaseTree{
	/** 系统名称 */
	private String sysNm;
	/** 菜单名称 */
	private String menuNm;
	/** 菜单类型 */
	private String menuType;
	/** 菜单描述*/
	private String remark;
	/** 菜单图片*/
	private String iconFile;
	/** 排序 */
	private String orderBy;
	/** 是否启用 */
	private String isUse;
	/** 菜单控制器 */
	private String menuController;
	/** 菜单URL */
	private String menuView;
	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIconFile() {
		return iconFile;
	}
	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getMenuController() {
		return menuController;
	}
	public void setMenuController(String menuController) {
		this.menuController = menuController;
	}
	public String getMenuView() {
		return menuView;
	}
	public void setMenuView(String menuView) {
		this.menuView = menuView;
	}
}

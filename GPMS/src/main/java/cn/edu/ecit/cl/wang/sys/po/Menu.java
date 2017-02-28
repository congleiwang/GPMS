package cn.edu.ecit.cl.wang.sys.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("BASE_MENU")
public class Menu {
	
	/** 系统名称 */
	@TableField("SYS_NM")
	private String sysNm;
	
	/** 菜单编号 */
	@TableField("MENU_CD")
	private String menuCd;
	
	/** 菜单名称 */
	@TableField("MENU_NM")
	private String menuNm;
	
	/** 菜单类型 */
	@TableField("MENU_TYPE")
	private String menuType;
	
	/** 父菜单编号 */
	@TableField("PMENU_CD")
	private String pmenuCd;
	
	/** 菜单描述*/
	@TableField("REMARK")
	private String remark;
	/** 菜单图片*/
	@TableField("ICON_FILE")
	private String iconFile;
	/** 排序 */
	@TableField("ORDER_BY")
	private String orderBy;
	
	/** 是否启用 */
	@TableField("IS_USE")
	private String isUse;
	
	/** 菜单控制器 */
	@TableField("MENU_CONTROLLER")
	private String menuController;
	
	/** 菜单URL */
	@TableField("MENU_VIEW")
	private String menuView;

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
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

	public String getPmenuCd() {
		return pmenuCd;
	}

	public void setPmenuCd(String pmenuCd) {
		this.pmenuCd = pmenuCd;
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

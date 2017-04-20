package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("BASE_MENU")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 6273070602827621234L;

	/** 系统名称 */
	@TableField("SYS_NM")
	private String sysNm;
	
	/** 菜单编号 */
	@TableId("MENU_CD")
	private String menuCd;
	
	/** 菜单名称 */
	@TableField("MENU_NM")
	private String menuNm;
	
	/** 菜单后台路径 */
	@TableField("MENU_URL")
	private String menuUrl;
	
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
	
	/** 菜单URL */
	@TableField("JSP_URL")
	private String jspUrl;

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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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

	public String getJspUrl() {
		return jspUrl;
	}

	public void setJspUrl(String jspUrl) {
		this.jspUrl = jspUrl;
	}

}

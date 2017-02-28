package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("BASE_DICTIONARY")
public class Dictionary  implements Serializable{
	
	private static final long serialVersionUID = 2797135080407020956L;

	/** 系统名称  */
	@TableField("SYS_NM")
	private String sysNm;
	
	/** 键值码 */
	@TableField("KEY_CD")
	private String keyCd;
	
	/** 键值 */
	@TableField("KEY_VALUE")
	private String keyValue;
	
	/** 中文名称 */
	@TableField("CAPTION")
	private String caption;
	
	/** 是否可更改 */
	@TableField("IS_MOD")
	private String isMod;
	
	/** 备注 */
	@TableField("REMARK")
	private String remark;
	
	/** 是否启用 */
	@TableField("IS_USE")
	private String isUse;
	
	/** 英文 */
	@TableField("ENGLISH")
	private String english;
	
	/** 排序 */
	@TableField("ORDER_BY")
	private Integer orderBy;

	public Dictionary(){
	}
	
	public Dictionary(String sysNm, String keyCd) {
		super();
		this.sysNm = sysNm;
		this.keyCd = keyCd;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getKeyCd() {
		return keyCd;
	}

	public void setKeyCd(String keyCd) {
		this.keyCd = keyCd;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getIsMod() {
		return isMod;
	}

	public void setIsMod(String isMod) {
		this.isMod = isMod;
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

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}

package cn.edu.ecit.cl.wang.sys.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("BASE_ORG")
public class Org extends BaseEntity{
	
	private static final long serialVersionUID = 7093168071965694752L;

	/** 机构id  */
	@TableId(value = "ORG_ID" , type = IdType.INPUT)
	private Long orgId;
	
	/** 父id  */
	@TableField("PORG_ID")
	private Long porgId;
	
	/** 机构类型 */
	@TableField("ORG_TYPE")
	private String orgType;
	
	/** 机构名 */
	@TableField("ORG_NM")
	private String orgNm;
	
	/** 机构描述 */
	@TableField("ORG_DESC")
	private String orgDesc;
	
	/** 地址 */
	@TableField("ADDRESS")
	private String address;
	
	/** 是否启用 */
	@TableField("IS_USE")
	private String isUse;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPorgId() {
		return porgId;
	}

	public void setPorgId(Long porgId) {
		this.porgId = porgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}

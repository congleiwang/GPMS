package cn.edu.ecit.cl.wang.sys.po;

public class OrgTree extends BaseTree{

	private Long orgId;
	
	private Long porgId;
	
	private String orgType;
	
	private String orgNm;
	
	private String orgDesc;
	
	private String address;

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

}

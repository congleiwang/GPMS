package cn.edu.ecit.cl.wang.sys.po;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *
 * 系统用户表
 *
 */
@TableName("BASE_USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = -2604728209142614350L;

	/** 用户ID */
	@TableId(value = "USER_ID", type = IdType.INPUT)
	private Long userId;

	/** 机构ID */
	@TableField("ORG_ID")
	private Long orgId;

	/** 机构名 */
	@TableField(exist = false)
	private Long orgNm;

	/** 用户名 */
	@TableField("USER_NM")
	private String userNm;

	/** 登陆名 */
	@TableField("LOGIN_NM")
	private String loginNm;

	/** 密码 */
	@TableField("PASSWD")
	private String passwd;

	/** 密码错误次数 */
	@TableField("PASSWD_ERR")
	private Integer passwdErr;

	/** 电话号码 */
	@TableField("PHONE_NUM")
	private String phoneNum;

	/** 地址 */
	@TableField("ADDRESS")
	private String address;

	/** Ip地址 */
	@TableField("IP_ADDR")
	private String ipAddr;

	/** 邮箱 */
	@TableField("EMAIL")
	private String email;

	/** 是否锁定 1：是，0否 */
	@TableField("is_LOCK")
	private String isLock;

	/** 注册时间 */
	@TableField("SIGN_AT")
	private Timestamp signAt;

	/** 最后一次登陆时间 */
	@TableField("LAST_LOGIN_AT")
	private Timestamp lastLoginAt;

	/** 备注 */
	@TableField("REMARK")
	private String remark;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(Long orgNm) {
		this.orgNm = orgNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getLoginNm() {
		return loginNm;
	}

	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getPasswdErr() {
		return passwdErr;
	}

	public void setPasswdErr(Integer passwdErr) {
		this.passwdErr = passwdErr;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public Timestamp getSignAt() {
		return signAt;
	}

	public void setSignAt(Timestamp signAt) {
		this.signAt = signAt;
	}

	public Timestamp getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Timestamp lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loginNm == null) ? 0 : loginNm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (loginNm == null) {
			if (other.loginNm != null)
				return false;
		} else if (!loginNm.equals(other.loginNm))
			return false;
		return true;
	}

}
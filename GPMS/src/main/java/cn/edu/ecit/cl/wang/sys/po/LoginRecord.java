package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("BASE_LOGIN_RECORD")
public class LoginRecord implements Serializable{

	private static final long serialVersionUID = 351747974236081682L;

	/** 记录id  */
	@TableId(value = "RECORD_ID" , type = IdType.INPUT)
	private Long recordId;
	
	/** 创建时间  */
	@TableField("CREATE_AT")
	private Timestamp createAt;
	
	@TableField(exist=false)
	private String createAtStart;

	@TableField(exist=false)
	private String createAtEnd;
	
	/** 登陆名 */
	@TableField("LOGIN_NM")
	private String loginNm;
	
	/** 登陆状态 */
	@TableField("LOGIN_STATE")
	private String loginState;
	
	/** IP地址 */
	@TableField("IP_ADDR")
	private String ipAddr;
	
	public LoginRecord(){}

	public LoginRecord(String loginNm, String loginState, String ipAddr) {
		super();
		this.loginNm = loginNm;
		this.loginState = loginState;
		this.ipAddr = ipAddr;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public String getCreateAtStart() {
		return createAtStart;
	}

	public void setCreateAtStart(String createAtStart) {
		this.createAtStart = createAtStart;
	}

	public String getCreateAtEnd() {
		return createAtEnd;
	}

	public void setCreateAtEnd(String createAtEnd) {
		this.createAtEnd = createAtEnd;
	}

	public String getLoginNm() {
		return loginNm;
	}

	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}

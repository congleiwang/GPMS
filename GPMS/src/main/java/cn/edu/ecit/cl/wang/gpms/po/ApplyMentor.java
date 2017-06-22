package cn.edu.ecit.cl.wang.gpms.po;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("GPMS_APPLY_MENTOR")
public class ApplyMentor {
	
	/** 发送者 */
	@TableField("SENDER")
	private Long sender;

	@TableField(exist = false)
	private String senderNm;

	/** 接受者 */
	@TableField("RECEIVER")
	private Long receiver;

	@TableField(exist = false)
	private String receiverNm;

	/** 申请时间 */
	@TableField("CREATE_AT")
	private Timestamp createAt;
	
	@TableField(exist=false)
	private String createAtEnd;
	
	@TableField(exist=false)
	private String createAtStart;

	/** 状态：1:创建 2：发送，3：同意 4：拒绝 */
	@TableField("STATE")
	private String state;

	/** 申请标题 */
	@TableField("APPLY_SBJT")
	private String applySbjt;

	/** 申请正文*/
	@TableField("apply_cont")
	private String applyCont;
	
	/** 同意或拒绝时间 */
	@TableField("UPDATE_AT")
	private Timestamp updateAt;

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public String getSenderNm() {
		return senderNm;
	}

	public void setSenderNm(String senderNm) {
		this.senderNm = senderNm;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getReceiverNm() {
		return receiverNm;
	}

	public void setReceiverNm(String receiverNm) {
		this.receiverNm = receiverNm;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCreateAtEnd() {
		return createAtEnd;
	}

	public void setCreateAtEnd(String createAtEnd) {
		this.createAtEnd = createAtEnd;
	}

	public String getCreateAtStart() {
		return createAtStart;
	}

	public void setCreateAtStart(String createAtStart) {
		this.createAtStart = createAtStart;
	}

	public String getApplySbjt() {
		return applySbjt;
	}

	public void setApplySbjt(String applySbjt) {
		this.applySbjt = applySbjt;
	}

	public String getApplyCont() {
		return applyCont;
	}

	public void setApplyCont(String applyCont) {
		this.applyCont = applyCont;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	
}

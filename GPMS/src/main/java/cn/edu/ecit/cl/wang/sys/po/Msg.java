package cn.edu.ecit.cl.wang.sys.po;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("BASE_MSG")
public class Msg {
	
	@TableId(value="msg_id",type=IdType.INPUT)
	private Long msgId;
	
	@TableField("msg_title")
	private String msgTitle;
	
	@TableField("msg_cont")
	private String msgCont;
	
	@TableField("send_At")
	private Timestamp sendAt;
	
	@TableField("sender")
	private Long sender;
	
	@TableField("receiver")
	private Long receiver;
	
	@TableField("msg_state")
	private String msgState;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgCont() {
		return msgCont;
	}

	public void setMsgCont(String msgCont) {
		this.msgCont = msgCont;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getMsgState() {
		return msgState;
	}

	public void setMsgState(String msgState) {
		this.msgState = msgState;
	}
	
}

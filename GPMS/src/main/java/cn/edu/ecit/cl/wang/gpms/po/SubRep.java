package cn.edu.ecit.cl.wang.gpms.po;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("GPMS_SUBJECT_REPORT")
public class SubRep {

	/** 主键 */
	@TableId(value = "SR_ID")
	private Long srId;

	/** 标题 */
	@TableField("TITLE")
	private String title;

	/** 开题报告的描述 */
	@TableField("SR_REMARK")
	private String srRemark;

	/** 开题报告文件路径 */
	@TableField("SR_FILE_URL")
	private String srFileUrl;

	@TableField(exist = false)
	private MultipartFile srFile;

	/** 状态 */
	@TableField("STATE")
	private String state;

	/** 创建时间 */
	@TableField("CREATE_AT")
	private Timestamp createAt;
	
	@TableField(exist = false)
	private String createAtStart;
	
	@TableField(exist = false)
	private String createAtEnd;

	/** 创建人 */
	@TableField("CREATOR")
	private Long creator;

	@TableField(exist = false)
	private String createNm;

	/** 发送提交时间 */
	@TableField("SEND_AT")
	private Timestamp sendAt;
	
	@TableField(exist = false)
	private String sendAtStart;
	
	@TableField(exist = false)
	private String sendAtEnd;
	
	/** 审批人 */
	@TableField("EXAMOR")
	private Long examor;
	
	@TableField(exist = false)
	private String examorNm;

	/** 审批时间 */
	@TableField("EXAM_AT")
	private Timestamp examAt;
	
	@TableField(exist = false)
	private String examAtStart;
	
	@TableField(exist = false)
	private String examAtEnd;

	/** 审批备注 */
	@TableField("EXAM_REMARK")
	private String examRemark;

	/** 审批文件路径 */
	@TableField("EXAM_FILE_URL")
	private String examFileUrl;
	
	@TableField(exist=false)
	private MultipartFile examFile;

	public Long getSrId() {
		return srId;
	}

	public void setSrId(Long srId) {
		this.srId = srId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSrRemark() {
		return srRemark;
	}

	public void setSrRemark(String srRemark) {
		this.srRemark = srRemark;
	}

	public String getSrFileUrl() {
		return srFileUrl;
	}

	public void setSrFileUrl(String srFileUrl) {
		this.srFileUrl = srFileUrl;
	}

	public MultipartFile getSrFile() {
		return srFile;
	}

	public void setSrFile(MultipartFile srFile) {
		this.srFile = srFile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreateNm() {
		return createNm;
	}

	public void setCreateNm(String createNm) {
		this.createNm = createNm;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
	}

	public Long getExamor() {
		return examor;
	}

	public void setExamor(Long examor) {
		this.examor = examor;
	}

	public String getExamorNm() {
		return examorNm;
	}

	public void setExamorNm(String examorNm) {
		this.examorNm = examorNm;
	}

	public Timestamp getExamAt() {
		return examAt;
	}

	public void setExamAt(Timestamp examAt) {
		this.examAt = examAt;
	}

	public String getExamRemark() {
		return examRemark;
	}

	public void setExamRemark(String examRemark) {
		this.examRemark = examRemark;
	}

	public String getExamFileUrl() {
		return examFileUrl;
	}

	public void setExamFileUrl(String examFileUrl) {
		this.examFileUrl = examFileUrl;
	}

	public MultipartFile getExamFile() {
		return examFile;
	}

	public void setExamFile(MultipartFile examFile) {
		this.examFile = examFile;
	}

	public String getSendAtStart() {
		return sendAtStart;
	}

	public void setSendAtStart(String sendAtStart) {
		this.sendAtStart = sendAtStart;
	}

	public String getSendAtEnd() {
		return sendAtEnd;
	}

	public void setSendAtEnd(String sendAtEnd) {
		this.sendAtEnd = sendAtEnd;
	}

	public String getExamAtStart() {
		return examAtStart;
	}

	public void setExamAtStart(String examAtStart) {
		this.examAtStart = examAtStart;
	}

	public String getExamAtEnd() {
		return examAtEnd;
	}

	public void setExamAtEnd(String examAtEnd) {
		this.examAtEnd = examAtEnd;
	}

}

package cn.edu.ecit.cl.wang.gpms.po;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("GPMS_PAPER")
public class Paper {

	@TableId(value = "PAPER_ID", type = IdType.INPUT)
	private Long paperId;

	@TableField("TITLE")
	private String title;

	@TableField("ABSTRACT")
	private String abs;

	@TableField("PFILE_URL")
	private String pfileUrl;

	@TableField(exist = false)
	private MultipartFile pfile;

	@TableField("STATE")
	private String state;

	@TableField("CREATE_AT")
	private Timestamp createAt;

	@TableField(exist = false)
	private String createAtStart;

	@TableField(exist = false)
	private String createAtEnd;

	@TableField("CREATOR")
	private Long creator;

	@TableField(exist = false)
	private String createNm;

	@TableField(exist = false)
	private Long orgId;

	@TableField(exist = false)
	private String orgNm;

	@TableField("SEND_AT")
	private Timestamp sendAt;

	@TableField(exist = false)
	private String sendAtStart;

	@TableField(exist = false)
	private String sendAtEnd;

	@TableField("EXAMOR")
	private Long examor;

	@TableField(exist = false)
	private String examorNm;

	@TableField("EXAM_AT")
	private Timestamp examAt;

	@TableField(exist = false)
	private String examAtStart;

	@TableField(exist = false)
	private String examAtEnd;

	@TableField("EXAM_FILE_URL")
	private String examFileUrl;

	@TableField(exist = false)
	private MultipartFile examFile;

	@TableField("EXAM_REMARK")
	private String examRemark;

	@TableField("score")
	private double score;

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getPfileUrl() {
		return pfileUrl;
	}

	public void setPfileUrl(String pfileUrl) {
		this.pfileUrl = pfileUrl;
	}

	public MultipartFile getPfile() {
		return pfile;
	}

	public void setPfile(MultipartFile pfile) {
		this.pfile = pfile;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
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

	public String getExamRemark() {
		return examRemark;
	}

	public void setExamRemark(String examRemark) {
		this.examRemark = examRemark;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
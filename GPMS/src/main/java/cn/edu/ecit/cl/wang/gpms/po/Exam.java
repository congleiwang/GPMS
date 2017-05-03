package cn.edu.ecit.cl.wang.gpms.po;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("GPMS_EXAM")
public class Exam {

	@TableField("EXAM_TARGET")
	private Long examTarget;

	@TableField("EXAMOR")
	private Long examor;

	@TableField("EXAM_AT")
	private Timestamp examAt;

	@TableField("REMARK")
	private String remark;

	@TableField("FILE_URL")
	private String fileUrl;
	
	@TableField(exist=false)
	private MultipartFile file;

	public Long getExamTarget() {
		return examTarget;
	}

	public void setExamTarget(Long examTarget) {
		this.examTarget = examTarget;
	}

	public Long getExamor() {
		return examor;
	}

	public void setExamor(Long examor) {
		this.examor = examor;
	}

	public Timestamp getExamAt() {
		return examAt;
	}

	public void setExamAt(Timestamp examAt) {
		this.examAt = examAt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
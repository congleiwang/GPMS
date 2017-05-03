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

	/** 备注 */
	@TableField("REMARK")
	private String remark;

	/** 文件路径 */
	@TableField("FILE_URL")
	private String fileUrl;

	@TableField(exist = false)
	private MultipartFile file;

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

	/** 修改时间 */
	@TableField("MOD_AT")
	private Timestamp modAt;

	/** 修改人 */
	@TableField("MODER")
	private Long moder;

	/** 发送提交时间 */
	@TableField("SEND_AT")
	private Timestamp sendAt;

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

	public Timestamp getModAt() {
		return modAt;
	}

	public void setModAt(Timestamp modAt) {
		this.modAt = modAt;
	}

	public Long getModer() {
		return moder;
	}

	public void setModer(Long moder) {
		this.moder = moder;
	}

	public Timestamp getSendAt() {
		return sendAt;
	}

	public void setSendAt(Timestamp sendAt) {
		this.sendAt = sendAt;
	}

}

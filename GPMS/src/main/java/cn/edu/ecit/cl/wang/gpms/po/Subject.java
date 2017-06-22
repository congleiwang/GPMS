package cn.edu.ecit.cl.wang.gpms.po;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("GPMS_SUBJECT")
public class Subject {

	/** 课题id */
	@TableId("SUB_ID")
	private Long subId;

	/** 标题  */
	@TableField("TITLE")
	private String title;

	/** 要求 */
	@TableField("REQUIRE")
	private String require;

	/** 简介 */
	@TableField("INTRO")
	private String intro;

	/** 状态 */
	@TableField("STATE")
	private String state;

	/** 创建时间 */
	@TableField("CREATE_AT")
	private Timestamp createAt;
	
	@TableField(exist=false)
	private String createAtStart;
	
	@TableField(exist=false)
	private String createAtEnd;

	/** 创建者 */
	@TableField("CREATOR")
	private Long creator;
	
	@TableField(exist=false)
	private String createNm;
	
	/** 完成者 */
	@TableField("DOER")
	private Long doer;
	
	@TableField(exist=false)
	private String doerNm;
	
	/** 认领时间 */
	@TableField("DO_AT")
	private Timestamp doAt;
	
	/** 完成时间 */
	@TableField("FINISH_AT")
	private Timestamp finishAT;

	/** 修改时间  */
	@TableField("MOD_AT")
	private Timestamp modAt;

	/** 修改者 */
	@TableField("MODER")
	private Long moder;
	
	@TableField(exist=false)
	private String moderNm;

	/** 附件路径  */
	@TableField("FILE_URL")
	private String fileUrl;
	
	@TableField(exist=false)
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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

	public Long getDoer() {
		return doer;
	}

	public void setDoer(Long doer) {
		this.doer = doer;
	}

	public Timestamp getDoAt() {
		return doAt;
	}

	public void setDoAt(Timestamp doAt) {
		this.doAt = doAt;
	}

	public Timestamp getFinishAT() {
		return finishAT;
	}

	public void setFinishAT(Timestamp finishAT) {
		this.finishAT = finishAT;
	}

	public String getCreateNm() {
		return createNm;
	}

	public void setCreateNm(String createNm) {
		this.createNm = createNm;
	}

	public String getDoerNm() {
		return doerNm;
	}

	public void setDoerNm(String doerNm) {
		this.doerNm = doerNm;
	}

	public String getModerNm() {
		return moderNm;
	}

	public void setModerNm(String moderNm) {
		this.moderNm = moderNm;
	}

}

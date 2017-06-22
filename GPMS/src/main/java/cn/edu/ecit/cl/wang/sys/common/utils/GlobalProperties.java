package cn.edu.ecit.cl.wang.sys.common.utils;

/**
 * 全局配置属性
 * 
 * @author i
 *
 */
public class GlobalProperties {

	/** 项目名 */
	private String projectName;

	/** 项目主页 */
	private String homeUrl;

	/** 默认角色 */
	private String defaultRoleId;
	
	/** 教师角色 */
	private String teacherRoleId;
	
	/** 审核人员的roldId */
	private String  verifyRoleId;

	/** 上传文件所存的路径 */
	private String uploadPath;
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public String getTeacherRoleId() {
		return teacherRoleId;
	}

	public void setTeacherRoleId(String teacherRoleId) {
		this.teacherRoleId = teacherRoleId;
	}

	public String getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(String defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getVerifyRoleId() {
		return verifyRoleId;
	}

	public void setVerifyRoleId(String verifyRoleId) {
		this.verifyRoleId = verifyRoleId;
	}

}

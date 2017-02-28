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

	private String homeUrl;

	private String webappPath;

	private Long defaultRoleId;

	private String extJsHomePath;

	private String storageType;

	private String storagePath;

	private String ftpPath;
	
	private String ftpIp;
	
	private String ftpPort;
	
	private String ftpUser;
	
	private String ftpPassword;

	private String runautotask;

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

	public String getWebappPath() {
		return webappPath;
	}

	public void setWebappPath(String webappPath) {
		this.webappPath = webappPath;
	}

	public Long getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(Long defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getExtJsHomePath() {
		return extJsHomePath;
	}

	public void setExtJsHomePath(String extJsHomePath) {
		this.extJsHomePath = extJsHomePath;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getStorageMode() {
		return storageMode;
	}

	public void setStorageMode(String storageMode) {
		this.storageMode = storageMode;
	}

	private String storageMode;

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getRunautotask() {
		return runautotask;
	}

	public void setRunautotask(String runautotask) {
		this.runautotask = runautotask;
	}

}

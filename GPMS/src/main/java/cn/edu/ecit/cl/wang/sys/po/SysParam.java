package cn.edu.ecit.cl.wang.sys.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("BASE_SYS_PARAM")
public class SysParam implements Serializable{
	
	private static final long serialVersionUID = 603099438889135098L;

	/** 系统名称  */
	@TableField("SYS_NM")
	private String sysNm;
	
	/** 键值码 */
	@TableId("PARAM_KEY")
	private String paramKey;
	
	/** 键值 */
	@TableField("PARAM_VALUE")
	private String paramValue;
	
	/** 值类型 */
	@TableField("TYPE")
	private String type;
	
	/** 值域 */
	@TableField("VALUE_BOUND")
	private String valueBound;
	
	/** 描述 */
	@TableField("PARAM_DESC")
	private String paramDesc;

	/** 排序 */
	@TableField("ORDER_BY")
	private Integer orderBy;

	public SysParam(){
	}
	
	public SysParam(String sysNm, String paramKey) {
		super();
		this.sysNm = sysNm;
		this.paramKey = paramKey;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValueBound() {
		return valueBound;
	}

	public void setValueBound(String valueBound) {
		this.valueBound = valueBound;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
}

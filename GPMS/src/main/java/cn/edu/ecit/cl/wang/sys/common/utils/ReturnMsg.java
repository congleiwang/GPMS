package cn.edu.ecit.cl.wang.sys.common.utils;

import java.util.List;

import com.baomidou.mybatisplus.toolkit.StringUtils;

public class ReturnMsg {
	private boolean success;
	private String msg;
	
	private Integer total;
	
	private List<?> rows;
	
	public ReturnMsg(){}
	
	public ReturnMsg(boolean success,String msg){
		this.success=success;
		this.msg=msg;
	}
	
	public static ReturnMsg success(){
		return new ReturnMsg(true,"操作成功");
	}
	
	public static ReturnMsg success(String msg){
		if(StringUtils.isEmpty(msg)){
			return success();
		}
		return new ReturnMsg(true,msg);
	}
	
	public static ReturnMsg fail(){
		return new ReturnMsg(false,"操作失败");
	}
	
	public static ReturnMsg fail(String msg){
		if(StringUtils.isEmpty(msg)){
			return fail();
		}
		return new ReturnMsg(false,msg);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}

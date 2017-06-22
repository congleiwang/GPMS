package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.constant.OptionConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.po.LoginRecord;
import cn.edu.ecit.cl.wang.sys.service.ILoginRecordService;

@Controller
@RequestMapping("loginRecord")
public class LoginRecordController extends BaseController<LoginRecord>{
	
	@Autowired
	ILoginRecordService loginRecordService;
	
	@RequestMapping("/deleteBatchIds")
	@ResponseBody
	public ReturnMsg deleteBatchIds(@RequestBody List<Long> ids) {
		if(loginRecordService.deleteBatchIds(ids)){
			if(ids.size()==1){
				return ReturnMsg.success(OptionConstant.delSucc);
			}
			return ReturnMsg.success(OptionConstant.delBatchSucc);
		}
		if(ids.size()==1){
			return ReturnMsg.fail(OptionConstant.delFail);
		}
		return ReturnMsg.fail(OptionConstant.delBatchFail);
	}

	@Override
	public Page<LoginRecord> selectPage(LoginRecord obj, int currPage, int pageSize) {
		return loginRecordService.selectPage(obj,currPage,pageSize);
	}

	@Override
	public boolean addData(LoginRecord obj) {
		return false;
	}

	@Override
	public boolean updateData(LoginRecord obj) {
		return false;
	}

	@Override
	public boolean delData(String id) {
		return false;
	}

	@Override
	public List<LoginRecord> selectList(LoginRecord obj) {
		return null;
	}

	@Override
	public LoginRecord selectById(String id) {
		return null;
	}

}

package cn.edu.ecit.cl.wang.gpms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.gpms.po.SubRep;
import cn.edu.ecit.cl.wang.gpms.service.ISubRepService;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.controller.BaseController;

@Controller
@RequestMapping("subRep")
public class SubRepController extends BaseController<SubRep>{
	
	@Autowired
	ISubRepService subRepService;
	
	/** 列出我的开题报告 */
	@RequestMapping("mySubRepList")
	@ResponseBody
	public ReturnMsg mySubRepList(SubRep obj,
			@RequestParam(value = "page", required = false) Integer currPage,
			@RequestParam(value = "rows", required = false) Integer pageSize)
			throws Exception {
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<SubRep> p= subRepService.mySubRepList(obj, currPage, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	@RequestMapping("examList")
	@ResponseBody
	public ReturnMsg examList(SubRep obj,
			@RequestParam(value = "page", required = false) Integer currPage,
			@RequestParam(value = "rows", required = false) Integer pageSize)
					throws Exception {
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<SubRep> p= subRepService.examList(obj, currPage, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	@RequestMapping("subRepSend")
	@ResponseBody
	public ReturnMsg subRepSend(SubRep subRep){
		if(subRepService.subRepSend(subRep)){
			return ReturnMsg.success("提交成功");
		}
		return ReturnMsg.fail("提交失败");
	}
	
	@RequestMapping("examSubRepAllow")
	@ResponseBody
	public ReturnMsg examSubRepAllow(SubRep subRep){
		if(subRepService.examSubRepAllow(subRep)){
			return ReturnMsg.success("提交成功");
		}
		return ReturnMsg.fail("提交失败");
	}
	
	@RequestMapping("examSubRepReject")
	@ResponseBody
	public ReturnMsg examSubRepReject(SubRep subRep){
		if(subRepService.examSubRepReject(subRep)){
			return ReturnMsg.success("提交成功");
		}
		return ReturnMsg.fail("提交失败");
	}

	@Override
	public Page<SubRep> selectPage(SubRep obj, int currPage, int pageSize) {
		return subRepService.selectPage(obj,currPage,pageSize);
	}

	@Override
	public boolean addData(SubRep obj) {
		return subRepService.insert(obj);
	}

	@Override
	public boolean updateData(SubRep obj) {
		return subRepService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		return subRepService.deleteById(id);
	}

	@Override
	public List<SubRep> selectList(SubRep obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubRep selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

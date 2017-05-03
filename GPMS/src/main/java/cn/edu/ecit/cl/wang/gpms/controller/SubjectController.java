package cn.edu.ecit.cl.wang.gpms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.gpms.po.Subject;
import cn.edu.ecit.cl.wang.gpms.service.ISubjectService;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.controller.BaseController;

@Controller
@RequestMapping("subject")
public class SubjectController extends BaseController<Subject>{
	
	@Autowired
	ISubjectService subjectService;

	@Override
	public Page<Subject> selectPage(Subject obj, int currPage, int pageSize) {
		return subjectService.selectPage(obj,currPage,pageSize);
	}
	
	/** 发布课题列表 */
	@ResponseBody
	@RequestMapping(value = "/releaseList", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnMsg releaseList(Subject obj,
			@RequestParam(value = "page", required = false) Integer currPage,
			@RequestParam(value = "rows", required = false) Integer pageSize){
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<Subject> p= subjectService.releaseList(obj, currPage, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	/** 审批课题列表 */
	@ResponseBody
	@RequestMapping(value = "/examList", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnMsg examList(Subject obj, 
							@RequestParam(value = "page", required = false) Integer currPage,
							@RequestParam(value = "rows", required = false) Integer pageSize){
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<Subject> p= subjectService.examSelectPage(obj, currPage, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	/** 选择课题列表 */
	@ResponseBody
	@RequestMapping(value = "/selectSubList", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnMsg selectSubList(Subject obj, 
							@RequestParam(value = "page", required = false) Integer currPage,
							@RequestParam(value = "rows", required = false) Integer pageSize){
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<Subject> p= subjectService.selectSubList(obj, currPage, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}

	@Override
	public boolean addData(Subject obj) {
		return subjectService.insert(obj);
	}

	@Override
	public boolean updateData(Subject obj) {
		return subjectService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		return subjectService.deleteById(id);
	}
	
	@RequestMapping("subSend")
	@ResponseBody
	public ReturnMsg subSend(Long subId){
		if(subjectService.subSend(subId)){
			return ReturnMsg.success("提交成功！");
		}
		return ReturnMsg.fail("提交失败！");
	}
	
	@RequestMapping("examSubReject")
	@ResponseBody
	public ReturnMsg examSubReject(Subject subject){
		if(subjectService.examSubReject(subject)){
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}
	
	@RequestMapping("examSubAllow")
	@ResponseBody
	public ReturnMsg examSubAllow(Subject subject){
		if(subjectService.examSubAllow(subject)){
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}
	
	@RequestMapping("selectSub")
	@ResponseBody
	public ReturnMsg selectSub(Subject subject){
		if(subjectService.selectSub(subject)){
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}
	
	@RequestMapping("getMySub")
	@ResponseBody
	public Subject getMySub(){
		return subjectService.getMySub();
	}

	@Override
	public List<Subject> selectList(Subject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

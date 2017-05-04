package cn.edu.ecit.cl.wang.gpms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.gpms.po.Paper;
import cn.edu.ecit.cl.wang.gpms.service.IPaperService;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.controller.BaseController;

@Controller
@RequestMapping("paper")
public class PaperController extends BaseController<Paper> {

	@Autowired
	IPaperService paperService;

	/** 列出我的论文 */
	@RequestMapping("myPaperList")
	@ResponseBody
	public ReturnMsg myPaperList(Paper paper, @RequestParam(value = "page", required = false) Integer currPage,
			@RequestParam(value = "rows", required = false) Integer pageSize) throws Exception {
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<Paper> p = paperService.myPaperList(paper, currPage, pageSize);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}

	@RequestMapping("examList")
	@ResponseBody
	public ReturnMsg examList(Paper paper, @RequestParam(value = "page", required = false) Integer currPage,
			@RequestParam(value = "rows", required = false) Integer pageSize) throws Exception {
		if (currPage == null || currPage < 1) {
			currPage = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<Paper> p = paperService.examList(paper, currPage, pageSize);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}

	@RequestMapping("paperSend")
	@ResponseBody
	public ReturnMsg paperSend(Paper paper) {
		if (paperService.paperSend(paper)) {
			return ReturnMsg.success("提交成功");
		}
		return ReturnMsg.fail("提交失败");
	}

	@RequestMapping("examPaperAllow")
	@ResponseBody
	public ReturnMsg examPaperAllow(Paper paper) {
		if (paperService.examPaperAllow(paper)) {
			return ReturnMsg.success("审批成功");
		}
		return ReturnMsg.fail("审批失败");
	}

	@RequestMapping("examPaperReject")
	@ResponseBody
	public ReturnMsg examPaperReject(Paper paper) {
		if (paperService.examPaperReject(paper)) {
			return ReturnMsg.success("审批成功");
		}
		return ReturnMsg.fail("审批失败");
	}
	
	@RequestMapping("getAllowPaper")
	@ResponseBody
	public Paper getAllowPaper(){
		return paperService.getAllowPaper();
	}

	@Override
	public Page<Paper> selectPage(Paper paper, int currPage, int pageSize) {
		return paperService.selectPage(paper, currPage, pageSize);
	}

	@Override
	public boolean addData(Paper paper) {
		return paperService.insert(paper);
	}

	@Override
	public boolean updateData(Paper paper) {
		return paperService.updateById(paper);
	}

	@Override
	public boolean delData(String id) {
		return paperService.deleteById(id);
	}

	@Override
	public List<Paper> selectList(Paper obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paper selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

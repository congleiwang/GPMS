package cn.edu.ecit.cl.wang.gpms.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.gpms.po.ApplyMentor;
import cn.edu.ecit.cl.wang.gpms.service.IApplyMentorService;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.controller.BaseController;
import cn.edu.ecit.cl.wang.sys.po.User;

@Controller
@RequestMapping("applyMentor")
public class ApplyMentorController extends BaseController<ApplyMentor>{
	
	@Autowired
	IApplyMentorService applyMentorService;

	@Override
	public Page<ApplyMentor> selectPage(ApplyMentor applyMentor, int currPage, int pageSize) {
		return applyMentorService.selectPage(applyMentor,currPage,pageSize);
	}
	
	/**
	 *  列出可选导师
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectMentor")
	@ResponseBody
	public ReturnMsg selectMentor(User user,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer pageSize) {
		if (page == null || page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<User> p= applyMentorService.selectMentor(user, page, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	/**
	 * 获取到非拒绝状态的申请
	 * @return
	 */
	@RequestMapping("/getApply")
	@ResponseBody
	public ApplyMentor getApply(){
		return applyMentorService.getApplyBySender();
	}
	
	/**
	 * 提交申请
	 * @return
	 */
	@RequestMapping("/sendApply")
	@ResponseBody
	public ReturnMsg sendApply(ApplyMentor applyMentor){
		if(applyMentor != null){
			boolean result=false;
			if(StringUtils.isEmpty(applyMentor.getState())){
				result=addData(applyMentor);
			}else{
				result=true;
			}
			if(result){
				result=applyMentorService.sendApply(applyMentor);
				if(result){
					return ReturnMsg.success("已发生申请，请等待通知");
				}
			}
		}
		return ReturnMsg.success("发生申请失败，请重试或联系管理员");
	}
	
	/**
	 * 查看学生的申请
	 * @return
	 */
	@RequestMapping("/stuApplyList")
	@ResponseBody
	public ReturnMsg stuApplyList(ApplyMentor applyMentor,
				@RequestParam(value = "page", required = false) Integer currPage,
				@RequestParam(value = "rows", required = false) Integer pageSize) {
			if (currPage == null || currPage < 1) {
				currPage = PageConstant.defCurrPageNum;
			}
			if (pageSize == null || pageSize < 1) {
				pageSize = PageConstant.defPageSize;
			}
			Page<ApplyMentor> p= applyMentorService.stuApplyList(applyMentor, currPage, pageSize);
			ReturnMsg returnMsg=new ReturnMsg();
			returnMsg.setTotal(p.getTotal());
			returnMsg.setRows(p.getRecords());
			return returnMsg;
	}
	
	/**
	 * 同意学生的申请
	 * @return
	 */
	@RequestMapping("/stuApplyAllow")
	@ResponseBody
	public ReturnMsg stuApplyAllow(Long sender) {
		if(sender!=null && sender!=0){
			if(applyMentorService.stuApplyAllow(sender)){
				return ReturnMsg.success();
			}
		}
		return ReturnMsg.fail();
	}
	
	/**
	 * 拒绝学生的申请
	 * @return
	 */
	@RequestMapping("/stuApplyReject")
	@ResponseBody
	public ReturnMsg stuApplyReject(Long sender) {
		if(sender!=null && sender!=0){
			if(applyMentorService.stuApplyReject(sender)){
				return ReturnMsg.success();
			}
		}
		return ReturnMsg.fail();
	}
	
	/**
	 * 查看我的学生
	 * @return
	 */
	@RequestMapping("/getMyStuList")
	@ResponseBody
	public List<User> getMyStuList() {
		return applyMentorService.getMyStuList();
	}
	
	/**
	 * 查看我的导师
	 * @return
	 */
	@RequestMapping("/getMyMentor")
	@ResponseBody
	public User getMyMentor() {
		return applyMentorService.getMyMentor();
	}
	
	@Override
	public boolean addData(ApplyMentor obj) {
		return applyMentorService.insert(obj);
	}

	@Override
	public boolean updateData(ApplyMentor obj) {
		return applyMentorService.update(obj);
	}
	
	@Override
	public boolean delData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ApplyMentor> selectList(ApplyMentor obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplyMentor selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

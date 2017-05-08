package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.constant.OptionConstant;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

/**
 * Date: 16/10/9 Time: 上午11:58 Describe: 用户控制器
 * 
 * 
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User> {

	@Autowired
	private IUserService userService;

	@Override
	public Page<User> selectPage(User user, int pageNum, int pageSize) {
		return userService.selectPage(user, pageNum, pageSize);
	}
	
	@Override
	public User selectById(String id) {
		return userService.selectById(id);
	}

	@Override
	public boolean addData(User obj) {
		return userService.insert(obj);
	}
	
	@RequestMapping("/getUsersExRoleId")
	@ResponseBody
	public ReturnMsg getUsersExRoleId(User user,Long roleId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer pageSize) {
		if (page == null || page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<User> p= userService.getUsersExRoleId(user,roleId, page, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	@RequestMapping("/getUsersByRoleId")
	@ResponseBody
	public ReturnMsg getUsersByRoleId(User user,Long roleId,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer pageSize) {
		if (page == null || page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<User> p= userService.getUsersByRoleId(user,roleId, page, pageSize);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}
	
	@RequestMapping("/deleteBatchIds")
	@ResponseBody
	public ReturnMsg deleteBatchIds(@RequestBody List<Long> ids) {
		if(userService.deleteBatchIds(ids)){
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
	
	@RequestMapping("/lockUser")
	@ResponseBody
	public ReturnMsg lockUser(@RequestBody List<Long> ids) {
		if(userService.lockUserBatchIds(ids)){
			if(ids.size()==1){
				return ReturnMsg.success(OptionConstant.lockSucc);
			}
			return ReturnMsg.success(OptionConstant.lockBatchSucc);
		}
		if(ids.size()==1){
			return ReturnMsg.fail(OptionConstant.lockFail);
		}
		return ReturnMsg.fail(OptionConstant.lockBatchFail);
	}
	
	@RequestMapping("/unLockUser")
	@ResponseBody
	public ReturnMsg unLockUser(@RequestBody List<Long> ids) {
		if(userService.unLockUserBatchIds(ids)){
			if(ids.size()==1){
				return ReturnMsg.success(OptionConstant.unLockFail);
			}
			return ReturnMsg.success(OptionConstant.unLockBatchSucc);
		}
		if(ids.size()==1){
			return ReturnMsg.fail(OptionConstant.unLockFail);
		}
		return ReturnMsg.fail(OptionConstant.unLockBatchFail);
	}
	
	@RequestMapping("/canApply")
	@ResponseBody
	public ReturnMsg canApply(User user, HttpServletRequest request) {
		if(userService.upMyInfo(user)){
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("user");
			u.setCanApply(user.getCanApply());
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}
	
	@RequestMapping("/upMyInfo")
	@ResponseBody
	public ReturnMsg upMyInfo(User user, HttpServletRequest request) {
		if(userService.upMyInfo(user)){
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("user");
			u.setPhoneNum(user.getPhoneNum());
			u.setEmail(user.getEmail());
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}
	
	@RequestMapping("/changePasswd")
	@ResponseBody
	public ReturnMsg changePasswd(String oldPasswd,String newPasswd) {
		int result=userService.changePasswd(oldPasswd,newPasswd);
		if(result==3){
			return ReturnMsg.fail("原密码错误，修改失败！");
		}
		if(result==1){
			return ReturnMsg.success("密码修改成功,请重新登陆！");
		}
		return ReturnMsg.fail("密码修改失败");
	}
	
	@Override
	public boolean updateData(User obj) {
		return userService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> selectList(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

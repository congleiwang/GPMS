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
	public List<User> getUsersExRoleId(User user,Long roleId) {
		return userService.getUsersExRoleId(user,roleId);
	}
	
	@RequestMapping("/getUsersByRoleId")
	@ResponseBody
	public List<User> getUsersByRoleId(Long roleId) {
		return userService.getUsersByRoleId(roleId);
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

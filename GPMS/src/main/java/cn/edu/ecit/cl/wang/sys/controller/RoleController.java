package cn.edu.ecit.cl.wang.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.RoleAtuth;
import cn.edu.ecit.cl.wang.sys.pojo.UserRole;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController<Role>{
	
	@Autowired
	IRoleService roleService;
	
	@Override
	public Page<Role> selectPage(Role obj, int currPage, int pageSize) {
		return roleService.selectPage(obj,currPage,pageSize);
	}
	
	@RequestMapping("putAtuth")
	@ResponseBody
	public ReturnMsg putAtuth(Long roleId ,String menuCds) {
		String[] cds=menuCds.split(",");
		List<RoleAtuth> roleAtuths=new ArrayList<RoleAtuth>();
		for(String menucd:cds){
			if(StringUtils.isNotEmpty(menucd)){
				RoleAtuth roleAtuth=new RoleAtuth(roleId,menucd);
				roleAtuths.add(roleAtuth);
			}
		}
		if(roleService.putAtuth(roleAtuths)){
			return ReturnMsg.success("添加成功");
		}
		return ReturnMsg.fail("添加失败");
	}
	
	@RequestMapping("putUsers")
	@ResponseBody
	public ReturnMsg putUsers(Long roleId ,String userIds) {
		String[] ids=userIds.split(",");
		List<UserRole> userRoles=new ArrayList<UserRole>();
		for(String id:ids){
			if(StringUtils.isNotEmpty(id)){
				UserRole userRole=new UserRole(roleId,Long.valueOf(id));
				userRoles.add(userRole);
			}
		}
		if(roleService.putUsers(userRoles)){
			return ReturnMsg.success("添加成功");
		}
		return ReturnMsg.fail("添加失败");
	}
	
	@RequestMapping("removeUsers")
	@ResponseBody
	public ReturnMsg removeUsers(Long roleId ,String userIds) {
		String[] ids=userIds.split(",");
		List<UserRole> userRoles=new ArrayList<UserRole>();
		for(String id:ids){
			if(StringUtils.isNotEmpty(id)){
				UserRole userRole=new UserRole(roleId,Long.valueOf(id));
				userRoles.add(userRole);
			}
		}
		if(roleService.removeUsers(userRoles)){
			return ReturnMsg.success("移除成功");
		}
		return ReturnMsg.fail("移除失败");
	}

	@Override
	public boolean addData(Role obj) {
		return roleService.insert(obj);
	}

	@Override
	public boolean updateData(Role obj) {
		return roleService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		return roleService.deleteById(id);
	}

	@Override
	public List<Role> selectList(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

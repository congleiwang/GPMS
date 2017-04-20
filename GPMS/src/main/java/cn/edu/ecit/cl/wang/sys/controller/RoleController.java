package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.po.Role;
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

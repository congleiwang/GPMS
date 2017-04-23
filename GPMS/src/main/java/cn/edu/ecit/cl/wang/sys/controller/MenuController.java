package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController<Menu> {

	@Autowired
	IMenuService menuService;
	
	@RequestMapping("getPermTree")
	@ResponseBody
	public List<MenuTree> getPermTree(){
		List<Role> roles =SpringSecurityUtils.getCurrentUser().getRoles();
		return menuService.getPermTree(roles);
	}
	
	@RequestMapping("getMenuCdsByRoleId")
	@ResponseBody
	public List<String> getMenuCdsByRoleId(Long roleId){
		return menuService.getMenuCdsByRoleId(roleId);
	}
	
	@RequestMapping("getAllMenuTree")
	@ResponseBody
	public List<MenuTree> getAllMenuTree(){
		return menuService.getAllMenuTree();
	}
	
	@RequestMapping("getSubMenuTreeById")
	@ResponseBody
	public List<MenuTree> getSubMenuTreeById(String id){
		return menuService.getSubMenuTreeById(id);
	}


	@Override
	public Page<Menu> selectPage(Menu obj, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addData(Menu obj) {
		return menuService.insert(obj);
	}

	@Override
	public boolean updateData(Menu obj) {
		return menuService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		return menuService.deleteById(id);
	}

	@Override
	public List<Menu> selectList(Menu obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

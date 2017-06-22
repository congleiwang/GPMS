package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;

public interface IMenuService  extends IService<Menu> {
	
	public List<MenuTree> getPermTree(List<Role> roles);

	public List<MenuTree> getSubMenuTreeById(String id);

	public List<MenuTree> getAllMenuTree();

	public List<String> getMenuCdsByRoleId(Long roleId);

}

package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;

public interface IMenuService {
	
	public List<String> getMenuCdsByRoles(@Param("roles") List<Role> roles);
	
	public List<MenuTree> getPermTree();

	public List<MenuTree> getFirstMenu();
	
}

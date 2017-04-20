package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.po.Role;

public interface MenuDao extends BaseMapper<Menu> {
	public List<Menu> getPermTree(@Param("roles")List<Role> roles);
	public List<Menu> getFirstMenu();
	public List<Menu> getMenuWithSubById(@Param("id")String id);
	public List<Menu> getAllMenuTree();
	public List<String> getMenuCdsByRoleId(@Param("roleId")Long roleId);
}

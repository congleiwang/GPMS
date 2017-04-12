package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;

public interface MenuDao extends BaseMapper<Menu> {
	public List<String> getMenuCdsByRoles(@Param("roles") List<Role> roles);
	public List<MenuTree> getPermTree(@Param("menuCds")List<String> menuCds);
	public List<MenuTree> getFirstMenu();
}

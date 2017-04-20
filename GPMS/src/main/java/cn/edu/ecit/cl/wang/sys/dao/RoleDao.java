package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;

public interface RoleDao extends BaseMapper<Role>{
	
	public List<String> getMenuUrlsByRoleId(@Param("roleId")Long roleId);

	public List<UrlAndRoleId> getUrlAndRoleIdList();

	public List<Role> getRolesByUserId(@Param("userId")Long userId);
	
	public Role selectById(@Param("roleId")Long roleId);

	public void delAtuthByRoleId(@Param("roleId")Long roleId);
}

package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Role;

public interface RoleDao extends BaseMapper<Role>{
	
	public List<String> getMenuCodesByRoleIds(@Param("roleIds")List<Long> roleIds);
	
	public List<Object[]> getMenuAndRoleCodeList();
}

package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;

public interface RoleDao extends BaseMapper<Role>{
	
	public List<String> getUrlsByRoleIds(@Param("roleIds")List<Long> roleIds);
	
	public List<String> getMenuUrlsByRoleId(@Param("roleId")Long roleId);

	public List<UrlAndRoleId> getUrlAndRoleIdList();
}

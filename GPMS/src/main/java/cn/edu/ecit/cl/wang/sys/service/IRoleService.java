package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Role;

public interface IRoleService extends IService<Role>{
	public List<String> getMenuCodesByRoleIds(List<Long> roleIds);
	public List<Object[]> getMenuAndRoleCodeList();
}

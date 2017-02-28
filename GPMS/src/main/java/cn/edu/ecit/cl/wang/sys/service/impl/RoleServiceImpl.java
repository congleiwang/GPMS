package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.RoleDao;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public List<String> getMenuCodesByRoleIds(List<Long> roleIds) {
		return roleDao.getMenuCodesByRoleIds(roleIds);
	}

	@Override
	public List<Object[]> getMenuAndRoleCodeList() {
		return roleDao.getMenuAndRoleCodeList();
	}
	
}

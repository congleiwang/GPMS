package cn.edu.ecit.cl.wang.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.RoleDao;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public List<String> getMenuUrlsByRoleId(Long roleId) {
		return roleDao.getMenuUrlsByRoleId(roleId);
	}
	
	@Override
	public List<UrlAndRoleId> getUrlAndRoleIdList() {
		return roleDao.getUrlAndRoleIdList();
	}

	@Override
	public List<Role> getRolesByUserId(Long userId) {
		return roleDao.getRolesByUserId(userId);
	}
	
	@Override
	public Role selectById(Serializable id) {
		return roleDao.selectById((Long)id);
	}
	
	@Override
	public boolean deleteById(Serializable id) {
		roleDao.delAtuthByRoleId((Long)id);
		return super.deleteById(id);
	}

	@Override
	public Page<Role> selectPage(Role obj, int currPage, int pageSize) {
		EntityWrapper<Role> ew=new EntityWrapper<Role>(obj);
		Page<Role> page=new Page<Role>(currPage,pageSize);
		page.setRecords(roleDao.selectPage(page, ew));
		return page;
	}
}

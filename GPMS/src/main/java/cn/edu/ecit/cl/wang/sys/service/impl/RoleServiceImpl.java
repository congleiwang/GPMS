package cn.edu.ecit.cl.wang.sys.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.dao.RoleDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.RoleAtuth;
import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;
import cn.edu.ecit.cl.wang.sys.pojo.UserRole;
import cn.edu.ecit.cl.wang.sys.security.MyInvocationSecurityMetadataSource;
import cn.edu.ecit.cl.wang.sys.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

	@Autowired
	RoleDao roleDao;
	@Autowired
	UtilsDao utilsDao;
	@Autowired
	MyInvocationSecurityMetadataSource securityMetadataSource;

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
	
	//删除role
	@Override
	public boolean deleteById(Serializable id) {
		roleDao.delAtuthByRoleId(Long.valueOf(id.toString()));
		return super.deleteById(id);
	}

	@Override
	public Page<Role> selectPage(Role obj, int currPage, int pageSize) {
		EntityWrapper<Role> ew=new EntityWrapper<Role>(obj);
		Page<Role> page=new Page<Role>(currPage,pageSize);
		page.setRecords(roleDao.selectPage(page, ew));
		return page;
	}
	
	@Override
	public boolean insert(Role entity) {
		entity.setRoleId(utilsDao.selectKey("seq_base_role"));
		entity.setIsDel("0");
		entity.setCreateAt(new Timestamp(System.currentTimeMillis()));
		entity.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
		return super.insert(entity);
	}
	
	@Override
	public boolean updateById(Role entity) {
		entity.setModAt(new Timestamp(System.currentTimeMillis()));
		entity.setModer(SpringSecurityUtils.getCurrentUser().getUserId());
		return super.updateById(entity);
	}

	@Override
	public boolean putUsers(List<UserRole> userRoles) {
		return roleDao.putUsers(userRoles)==userRoles.size()?true:false;
	}

	@Override
	public boolean removeUsers(List<UserRole> userRoles) {
		return roleDao.removeUsers(userRoles)==userRoles.size()?true:false;
	}

	@Override
	public boolean putAtuth(List<RoleAtuth> roleAtuths) {
		roleDao.delAtuthByRoleId(roleAtuths.get(0).getRoleId());
		boolean result=roleDao.putAtuth(roleAtuths)==roleAtuths.size()?true:false;
		if(result){
			//重新加载map中的角色与权限（menuUrl）的关系
			securityMetadataSource.loadResourceDefine();
		}
		return result;
	}
}

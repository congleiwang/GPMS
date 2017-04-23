package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.RoleAtuth;
import cn.edu.ecit.cl.wang.sys.pojo.UrlAndRoleId;
import cn.edu.ecit.cl.wang.sys.pojo.UserRole;

public interface IRoleService extends IService<Role>{
	public List<String> getMenuUrlsByRoleId(Long roleId);
	public List<UrlAndRoleId> getUrlAndRoleIdList();
	public List<Role> getRolesByUserId(Long userId);
	public Page<Role> selectPage(Role obj, int currPage, int pageSize);
	public boolean putUsers(List<UserRole> userRoles);
	public boolean removeUsers(List<UserRole> userRoles);
	public boolean putAtuth(List<RoleAtuth> roleAtuths);
}

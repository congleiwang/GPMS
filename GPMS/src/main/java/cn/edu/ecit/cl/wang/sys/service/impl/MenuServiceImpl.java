package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TreeUtils;
import cn.edu.ecit.cl.wang.sys.dao.MenuDao;
import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService{

	@Autowired
	MenuDao menuDao;
	
	@Override
	public List<MenuTree> getPermTree() {
		List<String> menuCds=(List<String>) SpringSecurityUtils.getCurrentUser().getMenuCds();
		List<MenuTree> menuTreeList=menuDao.getPermTree(menuCds);
		TreeUtils<MenuTree> treeUtils=new TreeUtils<MenuTree>();
		Map<String, List<MenuTree>> map=treeUtils.getTreeMap(menuTreeList);
		return treeUtils.buildTree(menuTreeList, getFirstMenu(), map);
	}

	@Override
	public List<String> getMenuCdsByRoles(List<Role> roles) {
		return menuDao.getMenuCdsByRoles(roles);
	}
	
	@Override
	public List<MenuTree> getFirstMenu(){
		return menuDao.getFirstMenu();
	}

}

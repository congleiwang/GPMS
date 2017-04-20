package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.ArrayList;
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
	public List<String> getMenuCdsByRoleId(Long roleId){
		return menuDao.getMenuCdsByRoleId(roleId);
	}
	
	@Override
	public List<MenuTree> getPermTree(List<Role> roles) {
		List<Menu> menuList=menuDao.getPermTree(roles);
		List<MenuTree> menuTreeList=new ArrayList<MenuTree>();
		for(Menu menu:menuList){
			MenuTree menuTree=new MenuTree(menu);
			menuTreeList.add(menuTree);
		}
		TreeUtils<MenuTree> treeUtils=new TreeUtils<MenuTree>();
		Map<String, List<MenuTree>> map=treeUtils.getTreeMap(menuTreeList);
		return treeUtils.buildTree(getFirstMenuTree(), map);
	}

	private List<MenuTree> getFirstMenuTree(){
		List<MenuTree> firstMenuTreeList=new ArrayList<MenuTree>();
		for(Menu menu:menuDao.getFirstMenu()){
			MenuTree menuTree=new MenuTree(menu);
			firstMenuTreeList.add(menuTree);
		}
		return firstMenuTreeList;
	}

	@Override
	public List<MenuTree> getSubMenuTreeById(String id) {
		List<MenuTree> menuTrees=SpringSecurityUtils.getCurrentUser().getMenuTrees();
		TreeUtils<MenuTree> treeUtils=new TreeUtils<MenuTree>();
		return treeUtils.getSubTree(id, menuTrees);
	}

	@Override
	public List<MenuTree> getAllMenuTree() {
		List<Menu> menuList=menuDao.getAllMenuTree();
		List<MenuTree> menuTreeList=new ArrayList<MenuTree>();
		for(Menu menu:menuList){
			MenuTree menuTree=new MenuTree(menu);
			menuTreeList.add(menuTree);
		}
		TreeUtils<MenuTree> treeUtils=new TreeUtils<MenuTree>();
		Map<String, List<MenuTree>> map=treeUtils.getTreeMap(menuTreeList);
		return treeUtils.buildTree(getFirstMenuTree(), map);
		
	}

}

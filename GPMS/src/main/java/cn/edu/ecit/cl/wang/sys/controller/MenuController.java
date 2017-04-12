package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.po.Menu;
import cn.edu.ecit.cl.wang.sys.pojo.MenuTree;
import cn.edu.ecit.cl.wang.sys.service.IMenuService;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController<Menu> {

	@Autowired
	IMenuService menuService;

	@RequestMapping("/getPermTree")
	@ResponseBody
	public List<MenuTree> getPermTree(){
		return menuService.getPermTree();
	}
	
	@Override
	public boolean addData(Menu obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Menu obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delDataCompkeys(Menu obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Menu> selectList(Menu obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Menu> selectPage(String jsonObj, int pageNum, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

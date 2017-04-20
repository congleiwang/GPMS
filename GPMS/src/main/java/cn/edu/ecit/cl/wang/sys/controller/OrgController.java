package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.po.Org;
import cn.edu.ecit.cl.wang.sys.pojo.OrgTree;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;

@Controller
@RequestMapping("org")
public class OrgController extends BaseController<Org>{
	
	@Autowired
	IOrgService orgService;
	
	@ResponseBody
	@RequestMapping("/getPermOrgTree")
	public List<OrgTree> getPermOrgTree(){
		return SpringSecurityUtils.getCurrentUser().getOrgTrees();
		
	}

	@Override
	public Page<Org> selectPage(Org obj, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addData(Org obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Org obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Org> selectList(Org obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Org selectById(String id) {
		return null;
	}

}

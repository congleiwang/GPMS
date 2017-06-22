package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.po.SysParam;
import cn.edu.ecit.cl.wang.sys.service.ISysParamService;

@Controller
@RequestMapping("sysParam")
public class SysParamController extends BaseController<SysParam>{
	
	@Autowired
	ISysParamService sysParamService;

	@Override
	public Page<SysParam> selectPage(SysParam obj, int currPage, int pageSize) {
		return sysParamService.selectPage(obj,currPage,pageSize);
	}

	@Override
	public boolean addData(SysParam obj) {
		return sysParamService.insert(obj);
	}

	@Override
	public boolean updateData(SysParam obj) {
		return sysParamService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SysParam> selectList(SysParam obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysParam selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

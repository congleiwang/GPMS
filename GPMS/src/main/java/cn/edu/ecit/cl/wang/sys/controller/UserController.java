package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

/**
 * Date: 16/10/9
 * Time: 上午11:58
 * Describe: 用户控制器
 * 
 * 
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User>{

	@Autowired
    private IUserService userService;

	@Override
	public Page<User> selectPage(String jsonObj, int pageNum, int pageSize) throws Exception {
		return userService.selectPage(jsonObj,pageNum,pageSize);
	}

	@Override
	public boolean addData(User obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(User obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delDataCompkeys(User obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> selectList(User obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

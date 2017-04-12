package cn.edu.ecit.cl.wang.sys.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.StringUtils;
import cn.edu.ecit.cl.wang.sys.dao.SysParamDao;
import cn.edu.ecit.cl.wang.sys.dao.UserDao;
import cn.edu.ecit.cl.wang.sys.po.SysParam;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IUserService;
import net.sf.json.JSONObject;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

	@Autowired
	SysParamDao sysParamDao;

	@Autowired
	UserDao userDao;
	
	/**
	 * 根据用户登陆名得到id
	 */
	@Override
	public Long getIdByLoginNm(String loginNm) {
		return userDao.getIdByLoginNm(loginNm);
	}

	/**
	 * 密码输入错误次数加一，如次数达到系统设置值，则锁定用户
	 */
	@Override
	public void updatePassErr(Long userId){
		User user = selectById(userId);
		if (user != null) {
			String userPasswderr = sysParamDao.getParamValueByEntiry(new SysParam("system", "userPasswderr"));
			if ((userPasswderr != null) && (Integer.valueOf(userPasswderr).intValue() != 0)) {
				userDao.updatePassErr(userId);
				if (user.getPasswdErr() + 1 >= Integer.valueOf(userPasswderr).intValue()) {
					userDao.updateLockUser(userId);
				}
			}
		}
	}

	/**
	 * 根据用户名查询用户是否被锁定
	 */
	@Override
	public boolean isUserLocked(Long userId) {
		String flag=userDao.isUserLocked(userId);
		return "1".equals(flag);
	}
	
	@Override
	public User selectById(Serializable id) {
		return userDao.selectById((Long)id);
	}

	@Override
	public Page<User> selectPage(String jsonObj, int pageNum, int pageSize) {
		User user=null;
		if(StringUtils.isNotEmpty(jsonObj)){
			JSONObject obj=JSONObject.fromObject(jsonObj);
			user=(User) JSONObject.toBean(obj, User.class);
		}
		EntityWrapper<User> ew=new EntityWrapper<User>(user);
		Page<User> page=new Page<User>(pageNum,pageSize);
		page.setRecords(userDao.selectPage(page, ew));
		return page;
	}

}
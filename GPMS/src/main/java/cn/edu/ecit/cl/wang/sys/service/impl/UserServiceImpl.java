package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.SysParamDao;
import cn.edu.ecit.cl.wang.sys.dao.UserDao;
import cn.edu.ecit.cl.wang.sys.po.Role;
import cn.edu.ecit.cl.wang.sys.po.SysParam;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.security.MyUserDetails;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

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
	 * 根据用户登陆名获取用户所有信息
	 */
	@Override
	public MyUserDetails getUserDetailsById(Long userId) {
		User user = userDao.getUserDetailsById(userId);
		MyUserDetails cuser = null;
		if (user != null) {
			cuser = new MyUserDetails(user);
		}
		return cuser;
	}

	/**
	 * 根据用户名查询用户是否被锁定
	 */
	@Override
	public boolean isUserLocked(Long userId) {
		String flag=userDao.isUserLocked(userId);
		return "1".equals(flag);
	}

	/**
	 * 根据用户Id 获取角色id列表
	 */
	@Override
	public List<Long> getRolesByUserid(Long userId) {
		User user=userDao.selectById(userId);
		List<Long> roleIds=new ArrayList<Long>();
		if(user.getRoles()!=null){
			for (Role role:user.getRoles()) {
				roleIds.add(role.getRoleId());
			}
		}
		return roleIds;
	}


}
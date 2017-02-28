package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.security.CurrentUser;


public interface IUserService extends IService<User> {
	public void updatePassErr(Long userId);
	public CurrentUser getCurrentUserById(Long userId);
	public boolean isUserLocked(Long userId);
	public List<Long> getRolesByUserid(Long userId);
	public Long getIdByLoginNm(String loginNm);
	
}
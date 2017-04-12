package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.User;


public interface IUserService extends IService<User> {
	public void updatePassErr(Long userId);
	public boolean isUserLocked(Long userId);
	public Long getIdByLoginNm(String loginNm);
	public Page<User> selectPage(String jsonObj, int pageNum, int pageSize);
	
}
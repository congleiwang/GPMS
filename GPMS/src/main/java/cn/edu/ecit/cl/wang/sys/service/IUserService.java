package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.User;


public interface IUserService extends IService<User> {
	public void updatePassErr(Long userId);
	public boolean isUserLocked(Long userId);
	public Long getIdByLoginNm(String loginNm);
	public Page<User> selectPage(User user, int pageNum, int pageSize);
	public void updateLoginAt(Long userId);
	public void cleanPassErr(Long userId);
	public User getUnLockUserById(Long id);
	public boolean lockUserBatchIds(List<Long> ids);
	public boolean unLockUserBatchIds(List<Long> ids);
	public List<User> getUsersByRoleId(Long roleId);
	public List<User> getUsersExRoleId(User user, Long roleId);
}
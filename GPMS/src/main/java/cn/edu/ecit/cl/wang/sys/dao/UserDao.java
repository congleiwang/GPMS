package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.User;

public interface UserDao extends BaseMapper<User>{
	
	public Long getIdByLoginNm(@Param("loginNm")String loginNm);
	
	public void updatePassErr(@Param("userId") Long userId);
	
	public Integer updateLockUser(@Param("userId") Long userId);
	
	public Integer updateUnLockUser(@Param("userId") Long userId);
	
	public String isUserLocked(@Param("userId")Long userId);
	
	public void updateLoginAt(@Param("userId")Long userId);

	public void cleanPassErr(@Param("userId")Long userId);

	public User getUnLockUserById(@Param("userId")Long id);

	public void updateModCount(@Param("userId")Long userId);

	public List<User> getUsersByRoleId(@Param("roleId")Long roleId);

	public List<User> getUsersExRoleId(@Param("user")User user,@Param("roleId")Long roleId);
	
	
}
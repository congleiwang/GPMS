package cn.edu.ecit.cl.wang.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.User;

public interface UserDao extends BaseMapper<User>,UserDetailsService{
	
	public Long getIdByLoginNm(@Param("loginNm")String loginNm);
	
	public void updatePassErr(@Param("userId") Long userId);
	
	public void updateLockUser(@Param("userId") Long userId);
	
	public User getUserDetailsById(@Param("userId")Long userId);
	
	public String isUserLocked(@Param("userId")Long userId);
	
	
}
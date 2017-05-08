package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.edu.ecit.cl.wang.sys.po.User;

public interface UserDao extends BaseMapper<User> {

	public Long getIdByLoginNm(@Param("loginNm") String loginNm);

	public void updatePassErr(@Param("userId") Long userId);

	public Integer updateLockUser(@Param("userId") Long userId);

	public Integer updateUnLockUser(@Param("userId") Long userId);

	public String isUserLocked(@Param("userId") Long userId);

	public User getUnLockUserById(@Param("userId") Long id);

	public void updateModCount(@Param("userId") Long userId);

	public List<User> getUsersByRoleId(RowBounds paramRowBounds, @Param("ew") Wrapper<User> paramWrapper,
			@Param("roleId") Long roleId);

	public List<User> getUsersExRoleId(RowBounds paramRowBounds, @Param("ew") Wrapper<User> paramWrapper,
			@Param("roleId") Long roleId);
	
	public List<User> getMyStuList(@Param("receiver") Long receiver);

	public User getMyMentor(@Param("sender") Long sender);

	public boolean lockApply(@Param("userId") Long userId);

	public boolean unLockApply(@Param("userId") Long userId);

}
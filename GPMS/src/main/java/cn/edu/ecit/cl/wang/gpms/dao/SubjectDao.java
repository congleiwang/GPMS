package cn.edu.ecit.cl.wang.gpms.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.gpms.po.Subject;

public interface SubjectDao extends BaseMapper<Subject> {

	int updateState(@Param("subject")Subject subject);

	Subject getMySub(@Param("userId")Long userId);

}

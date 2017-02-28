package cn.edu.ecit.cl.wang.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.SysParam;

public interface SysParamDao extends BaseMapper<SysParam>{
	public String getParamValueByEntiry(@Param("sysParam")SysParam sysParam);
}

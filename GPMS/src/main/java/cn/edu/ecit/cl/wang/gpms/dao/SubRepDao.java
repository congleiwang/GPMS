package cn.edu.ecit.cl.wang.gpms.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.gpms.po.SubRep;

public interface SubRepDao extends BaseMapper<SubRep>{

	SubRep getAllowSubRep(@Param("creator")Long creator);

}

package cn.edu.ecit.cl.wang.gpms.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.gpms.po.Paper;

public interface PaperDao extends BaseMapper<Paper>{

	Paper getAllowPaper(@Param("creator")Long creator);

}

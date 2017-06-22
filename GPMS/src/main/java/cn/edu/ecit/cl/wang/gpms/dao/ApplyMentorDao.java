package cn.edu.ecit.cl.wang.gpms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.gpms.po.ApplyMentor;

public interface ApplyMentorDao extends BaseMapper<ApplyMentor> {
	public List<ApplyMentor> getRejectApply(@Param("sender") Long sender);
	public ApplyMentor getApplyBySender(@Param("sender") Long sender);
	public boolean update(@Param("applyMentor") ApplyMentor applyMentor);
	public List<ApplyMentor> getApplysByRec(@Param("receiver") Long receiver);
}

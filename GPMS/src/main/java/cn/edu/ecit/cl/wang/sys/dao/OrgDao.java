package cn.edu.ecit.cl.wang.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.edu.ecit.cl.wang.sys.po.Org;

public interface OrgDao extends BaseMapper<Org>{
	public List<Long> getSubOrgIdList(@Param("orgId")Long orgId);

	public List<Org> getFirstOrg();

	public List<Org> getPermOrgTree(@Param("orgId") Long orgId);

	public List<Org> getAllOrgTree();
}

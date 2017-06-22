package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Org;
import cn.edu.ecit.cl.wang.sys.pojo.OrgTree;

public interface IOrgService extends IService<Org>{
	public List<Long> getSubOrgIdList(Long orgId);
	
	public List<OrgTree> getPermOrgTree(Long orgId);

	public List<OrgTree> getAllOrgTree();
}

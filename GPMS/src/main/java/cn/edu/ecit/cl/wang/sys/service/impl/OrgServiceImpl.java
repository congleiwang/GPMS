package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.OrgDao;
import cn.edu.ecit.cl.wang.sys.po.Org;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;

@Service("orgService")
public class OrgServiceImpl extends ServiceImpl<OrgDao, Org> implements IOrgService{

	@Autowired
	private OrgDao orgDao;
	
	@Override
	public List<Long> getSubOrgIdList(Long orgId) {
		return orgDao.getSubOrgIdList(orgId);
	}

}

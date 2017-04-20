package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.TreeUtils;
import cn.edu.ecit.cl.wang.sys.dao.OrgDao;
import cn.edu.ecit.cl.wang.sys.po.Org;
import cn.edu.ecit.cl.wang.sys.pojo.OrgTree;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;

@Service("orgService")
public class OrgServiceImpl extends ServiceImpl<OrgDao, Org> implements IOrgService{

	@Autowired
	private OrgDao orgDao;
	
	@Override
	public List<Long> getSubOrgIdList(Long orgId) {
		return orgDao.getSubOrgIdList(orgId);
	}

	@Override
	public List<OrgTree> getPermOrgTree(Long orgId) {
		List<Org> orgList=orgDao.getPermOrgTree(orgId);
		List<OrgTree> orgTreeList=new ArrayList<OrgTree>();
		List<OrgTree> belongOrg=new ArrayList<OrgTree>();
		for(Org org:orgList){
			OrgTree orgTree=new OrgTree(org);
			orgTreeList.add(orgTree);
			if(org.getOrgId().equals(orgId)){
				belongOrg.add(orgTree);
			}
		}
		TreeUtils<OrgTree> treeUtils=new TreeUtils<OrgTree>();
		Map<String, List<OrgTree>> map=treeUtils.getTreeMap(orgTreeList);
		return treeUtils.buildTree(belongOrg, map);
	}
	
	
}

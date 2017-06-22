package cn.edu.ecit.cl.wang.sys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TreeUtils;
import cn.edu.ecit.cl.wang.sys.dao.OrgDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.Org;
import cn.edu.ecit.cl.wang.sys.pojo.OrgTree;
import cn.edu.ecit.cl.wang.sys.service.IOrgService;

@Service("orgService")
public class OrgServiceImpl extends ServiceImpl<OrgDao, Org> implements IOrgService{

	@Autowired
	private OrgDao orgDao;
	
	@Autowired
	private UtilsDao utilsDao;
	
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

	@Override
	public List<OrgTree> getAllOrgTree() {
		List<Org> orgList=orgDao.getAllOrgTree();
		List<OrgTree> orgTreeList=new ArrayList<OrgTree>();
		for(Org org:orgList){
			OrgTree orgTree=new OrgTree(org);
			orgTreeList.add(orgTree);
		}
		TreeUtils<OrgTree> treeUtils=new TreeUtils<OrgTree>();
		Map<String,List<OrgTree>> map=treeUtils.getTreeMap(orgTreeList);
		return treeUtils.buildTree(getFirstOrg(), map);
	}
	
	private List<OrgTree> getFirstOrg(){
		List<OrgTree> firstOrgTreeList=new ArrayList<OrgTree>();
		for(Org org:orgDao.getFirstOrg()){
			OrgTree orgTree=new OrgTree(org);
			firstOrgTreeList.add(orgTree);
		}
		return firstOrgTreeList;
	}
	
	@Override
	public boolean insert(Org entity) {
		Long orgId=utilsDao.selectKey("seq_base_org");
		entity.setOrgId(orgId);
		entity.setIsDel("0");
		entity.setCreateAt(new Timestamp(System.currentTimeMillis()));
		entity.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
		return super.insert(entity);
	}
	
	@Override
	public boolean updateById(Org entity) {
		entity.setModAt(new Timestamp(System.currentTimeMillis()));
		entity.setModer(SpringSecurityUtils.getCurrentUser().getUserId());
		return super.updateById(entity);
	}
}

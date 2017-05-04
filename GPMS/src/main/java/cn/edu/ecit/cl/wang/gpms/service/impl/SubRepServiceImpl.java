package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.SubRepDao;
import cn.edu.ecit.cl.wang.gpms.po.SubRep;
import cn.edu.ecit.cl.wang.gpms.service.ISubRepService;
import cn.edu.ecit.cl.wang.sys.common.utils.FileUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TimeUtils;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

@Service("subRepService")
public class SubRepServiceImpl extends ServiceImpl<SubRepDao, SubRep> implements ISubRepService{
	
	@Autowired
	SubRepDao subRepDao;

	@Autowired
	IUserService userService;
	
	@Autowired
	GlobalProperties gp;
	
	@Autowired
	UtilsDao utilsDao;
	
	@Override
	public Page<SubRep> selectPage(SubRep obj, int currPage, int pageSize) {
		obj.setState("3");
		EntityWrapper<SubRep> ew=new EntityWrapper<SubRep>(obj);
		Page<SubRep> page=new Page<SubRep>(currPage,pageSize);
		return selectPage(page, ew);
	}

	
	@Override
	public boolean insert(SubRep entity) {
		try {
			if(FileUtils.saveFile(entity.getSrFile(), gp.getUploadPath())){
				entity.setSrFileUrl(SpringSecurityUtils.getCurrentUser().getUsername() + "_" + 
									TimeUtils.getNowStr() + "_" +
									entity.getSrFile().getOriginalFilename());			
			}
			entity.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
			entity.setCreateAt(TimeUtils.getNow());
			entity.setState("1");
			Long srId = utilsDao.selectKey("seq_gpms_subject_report");
			entity.setSrId(srId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.insert(entity);
	}
	
	@Override
	public boolean updateById(SubRep entity) {
		if(FileUtils.saveFile(entity.getSrFile(), gp.getUploadPath())){
			entity.setSrFileUrl(SpringSecurityUtils.getCurrentUser().getUsername() + "_" + 
								TimeUtils.getNowStr() + "_" +
								entity.getSrFile().getOriginalFilename());			
		}
		return super.updateById(entity);
	}

	@Override
	public Page<SubRep> mySubRepList(SubRep obj, Integer currPage, Integer pageSize) {
		obj.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
		EntityWrapper<SubRep> ew=new EntityWrapper<SubRep>(obj);
		Page<SubRep> page=new Page<SubRep>(currPage,pageSize);
		return selectPage(page, ew);
	}

	@Override
	public boolean subRepSend(SubRep subRep) {
		subRep.setState("2");
		subRep.setSendAt(TimeUtils.getNow());
		return updateById(subRep);
	}

	@Override
	public Page<SubRep> examList(SubRep obj, Integer currPage, Integer pageSize) {
		obj.setState("2");
		EntityWrapper<SubRep> ew=new EntityWrapper<SubRep>(obj);
		Page<SubRep> page=new Page<SubRep>(currPage,pageSize);
		
		List<User> users=userService.getMyStuList();
		if(CollectionUtils.isNotEmpty(users)){
			List<Long> userIds=new ArrayList<Long>();
			for(User user:users){
				userIds.add(user.getUserId());
			}
			ew.in("sr.creator", userIds);
			return selectPage(page, ew);
		}
		return page;
	}

	@Override
	public boolean examSubRepAllow(SubRep subRep) {
		subRep.setExamAt(TimeUtils.getNow());
		subRep.setExamor(SpringSecurityUtils.getCurrentUser().getUserId());
		if(FileUtils.saveFile(subRep.getExamFile(), gp.getUploadPath())){
			subRep.setExamFileUrl(subRep.getExamFile().getOriginalFilename());
		}
		subRep.setState("3");
		return subRepDao.updateById(subRep)>0;
	}

	@Override
	public boolean examSubRepReject(SubRep subRep) {
		subRep.setExamAt(TimeUtils.getNow());
		subRep.setExamor(SpringSecurityUtils.getCurrentUser().getUserId());
		if(FileUtils.saveFile(subRep.getExamFile(), gp.getUploadPath())){
			subRep.setExamFileUrl(subRep.getExamFile().getOriginalFilename());
		}
		subRep.setState("4");
		return subRepDao.updateById(subRep)>0;
	}


	@Override
	public SubRep getAllowSubRep() {
		return subRepDao.getAllowSubRep(SpringSecurityUtils.getCurrentUser().getUserId());
	}

}

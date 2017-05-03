package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.SubRepDao;
import cn.edu.ecit.cl.wang.gpms.po.Exam;
import cn.edu.ecit.cl.wang.gpms.po.SubRep;
import cn.edu.ecit.cl.wang.gpms.service.IExamService;
import cn.edu.ecit.cl.wang.gpms.service.ISubRepService;
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
	
	@Autowired
	IExamService examService;
	
	@Override
	public boolean insert(SubRep entity) {
		try {
			if(entity.getFile().getSize()!=0){
				String path = System.getProperty("web.root") + gp.getUploadPath();
				String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
						+ entity.getFile().getOriginalFilename();
				File file = new File(path, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}
				entity.setFileUrl(fileName);
				entity.getFile().transferTo(file);
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
		if(entity.getFile()!=null && entity.getFile().getSize()!=0){
			String path = System.getProperty("web.root") + gp.getUploadPath();
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+ entity.getFile().getOriginalFilename();
			File file = new File(path, fileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				entity.getFile().transferTo(file);
				entity.setFileUrl(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		entity.setModer(SpringSecurityUtils.getCurrentUser().getUserId());
		entity.setModAt(TimeUtils.getNow());
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
	public boolean examSubRepAllow(Exam exam) {
		//插入审批信息
		if(examService.insert(exam)){
			SubRep subRep=new SubRep();
			subRep.setSrId(exam.getExamTarget());
			subRep.setState("3");
			//更新状态
			if(subRepDao.updateById(subRep)>0){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean examSubRepReject(Exam exam) {
		//插入审批信息
		if(examService.insert(exam)){
			SubRep subRep=new SubRep();
			subRep.setSrId(exam.getExamTarget());
			subRep.setState("4");
			//更新状态
			if(subRepDao.updateById(subRep)>0){
				return true;
			}
		}
		return false;
	}

}

	package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.SubjectDao;
import cn.edu.ecit.cl.wang.gpms.po.Subject;
import cn.edu.ecit.cl.wang.gpms.service.ISubjectService;
import cn.edu.ecit.cl.wang.sys.common.utils.FileUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TimeUtils;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IMsgService;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

@Service("subjectService")
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, Subject> implements ISubjectService {

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	UtilsDao utilsDao;

	@Autowired
	GlobalProperties globalProperties;
	
	@Autowired
	IMsgService msgService;
	
	@Autowired
	IUserService userService;

	@Override
	public Page<Subject> selectPage(Subject obj, int currPage, int pageSize) {
		EntityWrapper<Subject> ew = new EntityWrapper<Subject>(obj);
		Page<Subject> page = new Page<Subject>(currPage, pageSize);
		return selectPage(page, ew);
	}

	@Override
	public boolean insert(Subject entity) {
		try {
			if(entity.getFile()!=null && StringUtils.isNotEmpty(entity.getFile().getOriginalFilename())){
				String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
						+entity.getFile().getOriginalFilename();
				if(FileUtils.saveFile(entity.getFile(), globalProperties.getUploadPath(),fileName)){
					entity.setFileUrl(fileName);
				}
			}
			entity.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
			entity.setState("1");
			Long subId = utilsDao.selectKey("seq_gpms_subject");
			entity.setSubId(subId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.insert(entity);
	}

	@Override
	public boolean updateById(Subject entity) {
		if(entity.getFile()!=null && StringUtils.isNotEmpty(entity.getFile().getOriginalFilename())){
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+entity.getFile().getOriginalFilename();
			if(FileUtils.saveFile(entity.getFile(), globalProperties.getUploadPath(),fileName)){
				entity.setFileUrl(fileName);
			}
		}
		return super.updateById(entity);
	}
	
	@Override
	public boolean subSend(Long subId) {
		Subject sj = new Subject();
		sj.setSubId(subId);
		sj.setState("2");
		sj.setCreateAt(new Timestamp(System.currentTimeMillis()));
		return updateById(sj);
	}

	@Override
	public Page<Subject> examSelectPage(Subject obj, Integer currPage, Integer pageSize) {
		obj.setState("2");
		EntityWrapper<Subject> ew = new EntityWrapper<Subject>(obj);
		Page<Subject> page = new Page<Subject>(currPage, pageSize);
		return selectPage(page, ew);
	}

	@Override
	public boolean examSubReject(Subject subject) {
		subject.setState("4");
		subject.setModer(SpringSecurityUtils.getCurrentUser().getUserId());
		subject.setModAt(new Timestamp(System.currentTimeMillis()));
		msgService.sysSendMsg(subject.getCreator(),"课题审批结果通知！", "您的课题 《"+subject.getTitle()+"》 未通过！");
		return updateById(subject);
	}

	@Override
	public boolean examSubAllow(Subject subject) {
		subject.setState("3");
		subject.setModer(SpringSecurityUtils.getCurrentUser().getUserId());
		subject.setModAt(new Timestamp(System.currentTimeMillis()));
		msgService.sysSendMsg(subject.getCreator(),"课题审批结果通知！", "您的课题 《"+subject.getTitle()+"》 已通过！");
		return updateById(subject);
	}

	@Override
	public Page<Subject> selectSubList(Subject obj, Integer currPage, Integer pageSize) {
		User teacher=userService.getMyMentor();
		obj.setCreator(teacher.getUserId());
		obj.setState("3");
		EntityWrapper<Subject> ew = new EntityWrapper<Subject>(obj);
		Page<Subject> page = new Page<Subject>(currPage, pageSize);
		return selectPage(page, ew);
	}

	@Override
	public boolean selectSub(Subject subject) {
		subject.setState("3");
		subject.setDoer(SpringSecurityUtils.getCurrentUser().getUserId());
		subject.setDoAt(new Timestamp(System.currentTimeMillis()));
		return updateById(subject);
	}

	@Override
	public Subject getMySub() {
		return subjectDao.getMySub(SpringSecurityUtils.getCurrentUser().getUserId());
	}

	@Override
	public Page<Subject> releaseList(Subject obj, Integer currPage, Integer pageSize) {
		obj.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
		EntityWrapper<Subject> ew = new EntityWrapper<Subject>(obj);
		Page<Subject> page = new Page<Subject>(currPage, pageSize);
		return selectPage(page, ew);
	}
}

package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.PaperDao;
import cn.edu.ecit.cl.wang.gpms.po.Paper;
import cn.edu.ecit.cl.wang.gpms.service.IPaperService;
import cn.edu.ecit.cl.wang.sys.common.utils.FileUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TimeUtils;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

@Service("parperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements IPaperService{
	
	@Autowired
	PaperDao paperDao;
	
	@Autowired
	GlobalProperties globalProperties;
	
	@Autowired
	UtilsDao utilsDao;
	
	@Autowired
	IUserService userService;

	@Override
	public Page<Paper> selectPage(Paper obj, int currPage, int pageSize) {
		if(obj.getOrgId()==null){
			obj.setOrgId(SpringSecurityUtils.getCurrentUser().getOrgId());
		}
		obj.setState("3");
		EntityWrapper<Paper> ew=new EntityWrapper<Paper>(obj);
		Page<Paper> page=new Page<Paper>(currPage,pageSize);
		return selectPage(page, ew);
	}

	
	@Override
	public boolean insert(Paper entity) {
		try {
			if(entity.getPfile()!=null && StringUtils.isNotEmpty(entity.getPfile().getOriginalFilename())){
				String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
						+entity.getPfile().getOriginalFilename();
				if(FileUtils.saveFile(entity.getPfile(), globalProperties.getUploadPath(),fileName)){
					entity.setPfileUrl(fileName);			
				}
			}
			entity.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
			entity.setCreateAt(TimeUtils.getNow());
			entity.setState("1");
			Long paperId = utilsDao.selectKey("seq_gpms_paper");
			entity.setPaperId(paperId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.insert(entity);
	}
	
	@Override
	public boolean updateById(Paper entity) {
		if(entity.getPfile()!=null && StringUtils.isNotEmpty(entity.getPfile().getOriginalFilename())){
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+entity.getPfile().getOriginalFilename();
			if(FileUtils.saveFile(entity.getPfile(), globalProperties.getUploadPath(),fileName)){
				entity.setPfileUrl(fileName);			
			}
		}
		return super.updateById(entity);
	}

	@Override
	public Page<Paper> myPaperList(Paper obj, Integer currPage, Integer pageSize) {
		obj.setCreator(SpringSecurityUtils.getCurrentUser().getUserId());
		EntityWrapper<Paper> ew=new EntityWrapper<Paper>(obj);
		Page<Paper> page=new Page<Paper>(currPage,pageSize);
		return selectPage(page, ew);
	}

	@Override
	public boolean paperSend(Paper paper) {
		paper.setState("2");
		paper.setSendAt(TimeUtils.getNow());
		return updateById(paper);
	}

	@Override
	public Page<Paper> examList(Paper obj, Integer currPage, Integer pageSize) {
		obj.setState("2");
		EntityWrapper<Paper> ew=new EntityWrapper<Paper>(obj);
		Page<Paper> page=new Page<Paper>(currPage,pageSize);
		
		List<User> users=userService.getMyStuList();
		if(CollectionUtils.isNotEmpty(users)){
			List<Long> userIds=new ArrayList<Long>();
			for(User user:users){
				userIds.add(user.getUserId());
			}
			ew.in("p.creator", userIds);
			return selectPage(page, ew);
		}
		return page;
	}

	@Override
	public boolean examPaperAllow(Paper paper) {
		paper.setExamAt(TimeUtils.getNow());
		paper.setExamor(SpringSecurityUtils.getCurrentUser().getUserId());
		if(paper.getExamFile()!=null && StringUtils.isNotEmpty(paper.getExamFile().getOriginalFilename())){
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+paper.getExamFile().getOriginalFilename();
			if(FileUtils.saveFile(paper.getExamFile(), globalProperties.getUploadPath(),fileName)){
				paper.setExamFileUrl(fileName);
			}
		}
		paper.setState("3");
		return paperDao.updateById(paper)>0;
	}

	@Override
	public boolean examPaperReject(Paper paper) {
		paper.setExamAt(TimeUtils.getNow());
		paper.setExamor(SpringSecurityUtils.getCurrentUser().getUserId());
		if(paper.getExamFile()!=null && StringUtils.isNotEmpty(paper.getExamFile().getOriginalFilename())){
			String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
					+paper.getExamFile().getOriginalFilename();
			if(FileUtils.saveFile(paper.getExamFile(), globalProperties.getUploadPath(),fileName)){
				paper.setExamFileUrl(fileName);
			}
		}
		paper.setState("4");
		return paperDao.updateById(paper)>0;
	}


	@Override
	public Paper getAllowPaper() {
		return paperDao.getAllowPaper(SpringSecurityUtils.getCurrentUser().getUserId());
	}


	@Override
	public boolean saveScore(List<Paper> paperList) {
		boolean result=true;
		for(Paper paper: paperList){
			Paper p=new Paper();
			p.setPaperId(paper.getPaperId());
			p.setScore(paper.getScore());
			if(!updateById(p)){
				result=false;
			}
		}
		return result;
	}

}

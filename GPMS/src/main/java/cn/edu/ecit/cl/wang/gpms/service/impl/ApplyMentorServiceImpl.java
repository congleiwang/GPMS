package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.ApplyMentorDao;
import cn.edu.ecit.cl.wang.gpms.po.ApplyMentor;
import cn.edu.ecit.cl.wang.gpms.service.IApplyMentorService;
import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IMsgService;
import cn.edu.ecit.cl.wang.sys.service.IUserService;

@Service("applyMentorService")
public class ApplyMentorServiceImpl extends ServiceImpl<ApplyMentorDao, ApplyMentor> implements IApplyMentorService {

	@Autowired
	ApplyMentorDao applyMentorDao;

	@Autowired
	GlobalProperties globalProperties;

	@Autowired
	IUserService userService;
	
	@Autowired
	IMsgService msgService;

	@Override
	public Page<ApplyMentor> selectPage(ApplyMentor applyMentor, int currPage, int pageSize) {
		EntityWrapper<ApplyMentor> ew = new EntityWrapper<ApplyMentor>(applyMentor);
		Page<ApplyMentor> page = new Page<ApplyMentor>(currPage, pageSize);
		page.setRecords(applyMentorDao.selectPage(page, ew));
		return page;
	}

	@Override
	public Page<User> selectMentor(User user, Integer page, Integer pageSize) {
		Long roleId = Long.valueOf(globalProperties.getTeacherRoleId());// 配置文件中设置的教师角色id
		// 所有教师
		Page<User> p = userService.getUsersByRoleId(user, roleId, page, pageSize);
		// 拒绝了申请的教师
		List<ApplyMentor> applyMentorList = applyMentorDao
				.getRejectApply(SpringSecurityUtils.getCurrentUser().getUserId());

		if (!CollectionUtils.isEmpty(applyMentorList)) {
			List<User> result = new ArrayList<User>();
			// 剔除掉拒绝过的不可申请的教师
			for (User u : p.getRecords()) {
				for (ApplyMentor applyMentor : applyMentorList) {
					if (u.getUserId() == applyMentor.getReceiver()) {
						continue;
					}
					result.add(user);
				}
			}
			p.setRecords(result);
		}
		return p;
	}

	@Override
	public boolean update(ApplyMentor applyMentor) {
		applyMentor.setSender(SpringSecurityUtils.getCurrentUser().getUserId());
		return applyMentorDao.update(applyMentor);
	}

	@Override
	public boolean insert(ApplyMentor entity) {
		entity.setSender(SpringSecurityUtils.getCurrentUser().getUserId());
		entity.setState("1");
		return super.insert(entity);
	}

	@Override
	public ApplyMentor getApplyBySender() {
		return applyMentorDao.getApplyBySender(SpringSecurityUtils.getCurrentUser().getUserId());
	}

	@Override
	public boolean sendApply(ApplyMentor applyMentor) {
		applyMentor.setState("2");
		applyMentor.setCreateAt(new Timestamp(System.currentTimeMillis()));
		return update(applyMentor);
	}

	@Override
	public Page<ApplyMentor> stuApplyList(ApplyMentor applyMentor, Integer currPage, Integer pageSize) {
		Page<ApplyMentor> page = new Page<ApplyMentor>(currPage, pageSize);
		applyMentor.setReceiver(SpringSecurityUtils.getCurrentUser().getUserId());
		applyMentor.setState("2");//已申请状态
		EntityWrapper<ApplyMentor> ew = new EntityWrapper<ApplyMentor>(applyMentor);
		page.setRecords(applyMentorDao.selectPage(page,ew));
		return page;
	}

	@Override
	public boolean stuApplyAllow(Long sender) {
		ApplyMentor applyMentor=new ApplyMentor();
		applyMentor.setSender(sender);
		applyMentor.setReceiver(SpringSecurityUtils.getCurrentUser().getUserId());
		applyMentor.setState("3");
		msgService.sysSendMsg(sender, "导师申请结果通知！", "您的导师申请已通过，可在“我的导师”菜单查看导师信息！");
		return applyMentorDao.update(applyMentor);
	}

	@Override
	public boolean stuApplyReject(Long sender) {
		ApplyMentor applyMentor=new ApplyMentor();
		applyMentor.setSender(sender);
		applyMentor.setReceiver(SpringSecurityUtils.getCurrentUser().getUserId());
		applyMentor.setState("4");
		msgService.sysSendMsg(sender, "导师申请结果通知！", "您的导师申请未通过，请及时重新申请！");
		return applyMentorDao.update(applyMentor);
	}

	@Override
	public List<User> getMyStuList() {
		return userService.getMyStuList();
	}

	@Override
	public User getMyMentor() {
		return userService.getMyMentor();
	}
}
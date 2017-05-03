package cn.edu.ecit.cl.wang.gpms.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.gpms.po.ApplyMentor;
import cn.edu.ecit.cl.wang.sys.po.User;

public interface IApplyMentorService extends IService<ApplyMentor>{

	Page<ApplyMentor> selectPage(ApplyMentor applyMentor, int currPage, int pageSize);

	Page<User> selectMentor(User user, Integer page, Integer pageSize);

	boolean update(ApplyMentor obj);

	ApplyMentor getApplyBySender();
	
	boolean sendApply(ApplyMentor applyMentor);

	Page<ApplyMentor> stuApplyList(ApplyMentor applyMentor, Integer page, Integer pageSize);

	boolean stuApplyAllow(Long sender);

	boolean stuApplyReject(Long sender);

	List<User> getMyStuList();

	User getMyMentor();

}

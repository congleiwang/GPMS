package cn.edu.ecit.cl.wang.gpms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.gpms.po.Subject;

public interface ISubjectService extends IService<Subject>{

	Page<Subject> selectPage(Subject obj, int currPage, int pageSize);

	boolean subSend(Long subId);

	Page<Subject> examSelectPage(Subject obj, Integer currPage, Integer pageSize);

	boolean examSubReject(Subject obj);

	boolean examSubAllow(Subject obj);

	Page<Subject> selectSubList(Subject obj, Integer currPage, Integer pageSize);

	boolean selectSub(Subject subject);

	Subject getMySub();

	Page<Subject> releaseList(Subject obj, Integer currPage, Integer pageSize);

}

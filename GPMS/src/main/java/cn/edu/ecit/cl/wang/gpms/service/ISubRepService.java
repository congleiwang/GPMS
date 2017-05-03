package cn.edu.ecit.cl.wang.gpms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.gpms.po.Exam;
import cn.edu.ecit.cl.wang.gpms.po.SubRep;

public interface ISubRepService extends IService<SubRep>{

	Page<SubRep> mySubRepList(SubRep obj, Integer currPage, Integer pageSize);

	boolean subRepSend(SubRep subRep);

	Page<SubRep> examList(SubRep obj, Integer currPage, Integer pageSize);

	boolean examSubRepAllow(Exam exam);

	boolean examSubRepReject(Exam exam);

}

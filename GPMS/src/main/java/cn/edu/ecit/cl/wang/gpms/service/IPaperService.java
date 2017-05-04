package cn.edu.ecit.cl.wang.gpms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.gpms.po.Paper;

public interface IPaperService extends IService<Paper> {

	boolean paperSend(Paper paper);

	boolean examPaperReject(Paper paper);

	boolean examPaperAllow(Paper paper);

	Page<Paper> examList(Paper paper, Integer currPage, Integer pageSize);

	Page<Paper> myPaperList(Paper paper, Integer currPage, Integer pageSize);

	Page<Paper> selectPage(Paper paper, int currPage, int pageSize);

	Paper getAllowPaper();

}

package cn.edu.ecit.cl.wang.gpms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.gpms.po.Reply;

public interface IReplyService extends IService<Reply>{

	Page<Reply> selectPage(Reply obj, int currPage, int pageSize);

}

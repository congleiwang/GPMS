package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Msg;

public interface IMsgService extends IService<Msg>{
	public boolean sysSendMsg(Long to,String title,String content);

	public Page<Msg> selectPage(Msg obj, int currPage, int pageSize);

	public boolean readMsg(Msg obj);

	public int unreadCount();
}

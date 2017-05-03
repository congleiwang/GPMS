package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Msg;

public interface IMsgService extends IService<Msg>{
	public boolean sysSendMsg(Long to,String title,String content);
}

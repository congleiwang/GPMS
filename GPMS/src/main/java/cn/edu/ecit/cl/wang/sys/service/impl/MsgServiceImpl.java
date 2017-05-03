package cn.edu.ecit.cl.wang.sys.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.MsgDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.Msg;
import cn.edu.ecit.cl.wang.sys.service.IMsgService;

@Service("msgService")
public class MsgServiceImpl extends ServiceImpl<MsgDao, Msg> implements IMsgService{

	@Autowired
	UtilsDao utilsDao;
	
	@Autowired
	MsgDao msgDao;
	
	/** 插入来自系统的消息 */
	public boolean sysSendMsg(Long to,String title,String content){
		Msg msg=new Msg();
		Long msgId=utilsDao.selectKey("seq_base_msg");
		msg.setMsgId(msgId);
		msg.setSender(0L);
		msg.setReceiver(to);
		msg.setSendAt(new Timestamp(System.currentTimeMillis()));
		msg.setMsgState("0");
		msg.setMsgTitle(title);
		msg.setMsgCont(content);
		return msgDao.insert(msg)>0?true:false;
	}

}

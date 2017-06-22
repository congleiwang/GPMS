package cn.edu.ecit.cl.wang.sys.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.common.utils.MailUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.dao.MsgDao;
import cn.edu.ecit.cl.wang.sys.dao.UserDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.Msg;
import cn.edu.ecit.cl.wang.sys.po.User;
import cn.edu.ecit.cl.wang.sys.service.IMsgService;

@Service("msgService")
public class MsgServiceImpl extends ServiceImpl<MsgDao, Msg> implements IMsgService{

	@Autowired
	UtilsDao utilsDao;
	
	@Autowired
	MsgDao msgDao;
	
	@Autowired
	UserDao userDao;
	
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
		boolean result=msgDao.insert(msg)>0?true:false;
		if(result){
			User user=userDao.getUnLockUserById(to);
			try {
				MailUtils.sendMail(user.getEmail(), title, content);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		return result;
	}

	@Override
	public Page<Msg> selectPage(Msg obj, int currPage, int pageSize) {
		obj.setReceiver(SpringSecurityUtils.getCurrentUser().getUserId());
		Page<Msg> page=new Page<Msg>(currPage,pageSize);
		EntityWrapper<Msg> ew=new EntityWrapper<Msg>(obj);
		page.setRecords(msgDao.selectPage(page, ew));
		return page;
	}

	@Override
	public boolean readMsg(Msg obj) {
		obj.setMsgState("1");//已读
		return msgDao.updateById(obj)>0;
	}

	@Override
	public int unreadCount() {
		Msg msg=new Msg();
		msg.setReceiver(SpringSecurityUtils.getCurrentUser().getUserId());
		msg.setMsgState("0");
		EntityWrapper<Msg> ew=new EntityWrapper<Msg>(msg);
		return msgDao.selectList(ew).size();
	}

}

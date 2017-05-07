package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.po.Msg;
import cn.edu.ecit.cl.wang.sys.service.IMsgService;

@Controller
@RequestMapping("msg")
public class MsgController extends BaseController<Msg> {

	@Autowired
	IMsgService msgService;
	
	@Override
	public Page<Msg> selectPage(Msg obj, int currPage, int pageSize) {
		return msgService.selectPage(obj,currPage,pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value = "/unreadCount", method = { RequestMethod.GET, RequestMethod.POST })
	public int unreadCount() {
		return msgService.unreadCount();
	}

	
	@ResponseBody
	@RequestMapping("readMsg")
	public ReturnMsg readMsg(Msg obj){
		if(msgService.readMsg(obj)){
			return ReturnMsg.success();
		}
		return ReturnMsg.fail();
	}

	@Override
	public boolean addData(Msg obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Msg obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(String id) {
		return msgService.deleteById(id);
	}

	@Override
	public List<Msg> selectList(Msg obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Msg selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

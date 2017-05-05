package cn.edu.ecit.cl.wang.gpms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.gpms.po.Reply;
import cn.edu.ecit.cl.wang.gpms.service.IReplyService;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;
import cn.edu.ecit.cl.wang.sys.controller.BaseController;

@Controller
@RequestMapping("reply")
public class ReplyController extends BaseController<Reply> {
	
	@Autowired
	IReplyService replyService;

	@Override
	public Page<Reply> selectPage(Reply obj, int currPage, int pageSize) {
		return replyService.selectPage(obj,currPage, pageSize);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public ReturnMsg save(@RequestBody Reply obj){
		boolean result=false;
		if(obj.getRid()==null || obj.getRid()==0){
			result=replyService.insert(obj);
		}else{
			result=replyService.updateById(obj);
		}
		if(result){
			return ReturnMsg.success("保存成功！");
		}
		return ReturnMsg.fail("保存失败！");
	}

	@Override
	public boolean addData(Reply obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Reply obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reply> selectList(Reply obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

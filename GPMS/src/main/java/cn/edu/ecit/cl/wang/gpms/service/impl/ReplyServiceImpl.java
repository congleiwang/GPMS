package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.ReplyDao;
import cn.edu.ecit.cl.wang.gpms.po.Reply;
import cn.edu.ecit.cl.wang.gpms.service.IReplyService;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;

@Service("replyService")
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, Reply> implements IReplyService {

	@Autowired
	UtilsDao utilsDao;
	
	@Override
	public Page<Reply> selectPage(Reply obj, int currPage, int pageSize) {
		if(obj.getOrgId()==null){
			obj.setOrgId(SpringSecurityUtils.getCurrentUser().getOrgId());
		}
		EntityWrapper<Reply> ew=new EntityWrapper<Reply>(obj);
		Page<Reply> page=new Page<Reply>(currPage,pageSize);
		Page<Reply> result=selectPage(page,ew);
		
		//将orgId 和Reply 对象对应
		Map<Long, Reply> map = new HashMap<Long, Reply>();
		for (Reply r:result.getRecords()) {
			map.put(r.getOrgId(), r);
		}
		for (int i = 0; i <result.getRecords().size(); i++) {
			Long pid=result.getRecords().get(i).get_parentId();
			//如果map中没有这个父节点，则将父节点置为Null
			if(map.get(pid)==null){
				result.getRecords().get(i).set_parentId(null);
			}
		}
		return result;
	}
	
	@Override
	public boolean insert(Reply entity) {
		Long rid=utilsDao.selectKey("seq_gpms_reply");
		entity.setRid(rid);
		return super.insert(entity);
	}

}

package cn.edu.ecit.cl.wang.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.SysParamDao;
import cn.edu.ecit.cl.wang.sys.po.SysParam;
import cn.edu.ecit.cl.wang.sys.service.ISysParamService;

@Service("sysParamService")
public class SysParamServiceImpl extends ServiceImpl<SysParamDao, SysParam> implements ISysParamService {

	@Autowired
	SysParamDao sysParamDao;
	
	@Override
	public String getParamValueByEntiry(SysParam sysParam) {
		return sysParamDao.getParamValueByEntiry(sysParam);
	}
	
}

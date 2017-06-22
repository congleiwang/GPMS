package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.SysParam;

public interface ISysParamService extends IService<SysParam>{
	public String getParamValueByEntiry(SysParam sysParam);

	public Page<SysParam> selectPage(SysParam obj, int currPage, int pageSize);
}

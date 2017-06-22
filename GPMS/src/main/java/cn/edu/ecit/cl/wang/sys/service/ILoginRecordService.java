package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.LoginRecord;

public interface ILoginRecordService extends IService<LoginRecord>{

	Page<LoginRecord> selectPage(LoginRecord obj, int currPage, int pageSize);

}

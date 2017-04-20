package cn.edu.ecit.cl.wang.sys.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.LoginRecordDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.LoginRecord;
import cn.edu.ecit.cl.wang.sys.service.ILoginRecordService;

@Service("loginRecordService")
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordDao, LoginRecord> implements ILoginRecordService{

	@Autowired
	UtilsDao utilsDao;
	
	@Autowired
	LoginRecordDao loginRecordDao;
	
	@Override
	public boolean insert(LoginRecord loginRecord) {
		loginRecord.setRecordId(utilsDao.selectKey("SEQ_BASE_LOGIN_RECORD"));
		loginRecord.setCreateAt(new Timestamp(System.currentTimeMillis()));
		return super.insert(loginRecord);
	}

	@Override
	public Page<LoginRecord> selectPage(LoginRecord obj, int currPage, int pageSize) {
		EntityWrapper<LoginRecord> ew=new EntityWrapper<LoginRecord>(obj);
		Page<LoginRecord> page=new Page<LoginRecord>(currPage,pageSize);
		page.setRecords(loginRecordDao.selectPage(page, ew));
		return page;
	} 
}

package cn.edu.ecit.cl.wang.sys.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.LoginRecordDao;
import cn.edu.ecit.cl.wang.sys.dao.UtilsDao;
import cn.edu.ecit.cl.wang.sys.po.LoginRecord;
import cn.edu.ecit.cl.wang.sys.service.ILoginRecordService;

@Service("loginRecordService")
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordDao, LoginRecord> implements ILoginRecordService{

	@Autowired
	UtilsDao utilsDao;
	
	@Override
	public boolean insert(LoginRecord loginRecord) {
		loginRecord.setRecordId(utilsDao.selectKey("SEQ_BASE_LOGIN_RECORD"));
		loginRecord.setCreateAt(new Timestamp(System.currentTimeMillis()));
		return super.insert(loginRecord);
	} 
}

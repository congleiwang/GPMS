package cn.edu.ecit.cl.wang.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.sys.dao.DictionaryDao;
import cn.edu.ecit.cl.wang.sys.po.Dictionary;
import cn.edu.ecit.cl.wang.sys.service.IDictionaryService;

@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements IDictionaryService {
	
	@Autowired
	DictionaryDao dictionaryDao;
	
	public String getDicValueByEntiry(Dictionary dictionary) {
		return dictionaryDao.getDicValueByEntiry(dictionary);
	}

}

package cn.edu.ecit.cl.wang.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

	@Override
	public List<Dictionary> getDictionaryByKey(Dictionary dictionary) {
		return dictionaryDao.getDictionaryByKey(dictionary);
	}

	@Override
	public Page<Dictionary> selectPage(Dictionary obj, int pageNum, int pageSize) {
		EntityWrapper<Dictionary> ew=new EntityWrapper<>(obj);
		Page<Dictionary> page=new Page<Dictionary>(pageNum,pageSize);
		page.setRecords(dictionaryDao.selectPage(page, ew));
		return page;
	}

}

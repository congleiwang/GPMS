package cn.edu.ecit.cl.wang.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Dictionary;

public interface IDictionaryService extends IService<Dictionary>{
	public String getDicValueByEntiry(Dictionary dictionary);

	public List<Dictionary> getDictionaryByKey(Dictionary dictionary);

	public Page<Dictionary> selectPage(Dictionary obj, int pageNum, int pageSize);
}

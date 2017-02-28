package cn.edu.ecit.cl.wang.sys.service;

import com.baomidou.mybatisplus.service.IService;

import cn.edu.ecit.cl.wang.sys.po.Dictionary;

public interface IDictionaryService extends IService<Dictionary>{
	public String getDicValueByEntiry(Dictionary dictionary);
}

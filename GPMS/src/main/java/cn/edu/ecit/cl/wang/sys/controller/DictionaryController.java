package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.po.Dictionary;
import cn.edu.ecit.cl.wang.sys.service.IDictionaryService;

@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController<Dictionary>{
	
	@Autowired
	IDictionaryService dictionaryService;
	
	@ResponseBody
	@RequestMapping("getDictionaryByKey")
	public List<Dictionary> getDictionaryByKey(Dictionary dictionary){
		return dictionaryService.getDictionaryByKey(dictionary);
	}

	@Override
	public Page<Dictionary> selectPage(Dictionary obj, int pageNum, int pageSize) {
		return dictionaryService.selectPage(obj,pageNum,pageSize);
	}

	@Override
	public boolean addData(Dictionary obj) {
		return dictionaryService.insert(obj);
	}

	@Override
	public boolean updateData(Dictionary obj) {
		return dictionaryService.updateById(obj);
	}

	@Override
	public boolean delData(String id) {
		return dictionaryService.deleteById(id);
	}

	@Override
	public List<Dictionary> selectList(Dictionary obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dictionary selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

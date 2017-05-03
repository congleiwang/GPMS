package cn.edu.ecit.cl.wang.gpms.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.edu.ecit.cl.wang.gpms.dao.ExamDao;
import cn.edu.ecit.cl.wang.gpms.po.Exam;
import cn.edu.ecit.cl.wang.gpms.service.IExamService;
import cn.edu.ecit.cl.wang.sys.common.utils.GlobalProperties;
import cn.edu.ecit.cl.wang.sys.common.utils.SpringSecurityUtils;
import cn.edu.ecit.cl.wang.sys.common.utils.TimeUtils;

@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements IExamService{
	
	@Autowired
	GlobalProperties gp;
	
	@Override
	public boolean insert(Exam exam) {
		exam.setExamAt(TimeUtils.getNow());
		exam.setExamor(SpringSecurityUtils.getCurrentUser().getUserId());
		if(exam.getFile()!=null && exam.getFile().getSize()!=0){
			try {
				String path = System.getProperty("web.root") + gp.getUploadPath();
				String fileName = SpringSecurityUtils.getCurrentUser().getUsername() + "_" + TimeUtils.getNowStr() + "_"
						+ exam.getFile().getOriginalFilename();
				File file = new File(path, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}
				exam.setFileUrl(fileName);
				exam.getFile().transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return super.insert(exam);
	}
}

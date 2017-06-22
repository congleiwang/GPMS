package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.constant.OptionConstant;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.utils.ReturnMsg;

/**
 * 
 * @param <T>
 */
public abstract class BaseController<E> {
	
	protected final static Logger logger = LogManager.getLogger(BaseController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnMsg doGetList(E obj, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer pageSize, HttpServletRequest request)
			throws Exception {
		if (page == null || page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = PageConstant.defPageSize;
		}
		Page<E> p= selectPage(obj, page, pageSize, request);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg.setTotal(p.getTotal());
		returnMsg.setRows(p.getRecords());
		return returnMsg;
	}

	/**
	 * 
	 * 注意: 接收文件在Entity 中使用 MultipartFile file接收， 或者参数使用@RequestParam("file")
	 * MultipartFile file接收
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public ReturnMsg doFormAdd(@ModelAttribute E obj) {
		if (addData(obj)) {
			return ReturnMsg.success(OptionConstant.addSucc);
		}
		return ReturnMsg.fail(OptionConstant.addFail);
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public ReturnMsg doFormUpdate(@ModelAttribute E obj) throws Exception {
		if (updateData(obj)) {
			return ReturnMsg.success(OptionConstant.updateSucc);
		}
		return ReturnMsg.fail(OptionConstant.updateFail);
	}

	/**
	 * 主键删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnMsg doDelete(String id) throws Exception {
		if (delData(id)) {
			return ReturnMsg.success(OptionConstant.delSucc);
		}
		return ReturnMsg.fail(OptionConstant.delFail);
	}
	
	/**
	 * selectbyid
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectById", method = { RequestMethod.GET, RequestMethod.POST })
	public E getEntityById(@RequestParam(value="id")String id) throws Exception {
		return selectById(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public abstract Page<E> selectPage(E obj, int currPage, int pageSize);

	public Page<E> selectPage(E obj, int currPage, int pageSize, HttpServletRequest request) {
		return selectPage(obj, currPage, pageSize);
	}

	/**
	 * 添加数据
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public abstract boolean addData(E obj);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public abstract boolean updateData(E obj);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public abstract boolean delData(String id);

	/**
	 * 根据条件查询所有数据
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public abstract List<E> selectList(E obj);
	public abstract E selectById(String id);

}

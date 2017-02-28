package cn.edu.ecit.cl.wang.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.edu.ecit.cl.wang.sys.common.constant.OptionConstant;
import cn.edu.ecit.cl.wang.sys.common.constant.PageConstant;
import cn.edu.ecit.cl.wang.sys.common.result.ReturnResult;

/**
 * 
 * @param <T>
 */
public abstract class BaseController<E> {
	protected final static Logger logger = LogManager.getLogger(BaseController.class);
	
	/**
	 * REST 方式提交，查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ReturnResult doRestList(@RequestBody E obj, int page, int limit, HttpServletRequest request) throws Exception{
		logger.debug("doRestList->searchData=" + obj.toString());
		ReturnResult ret = null;
		if (page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (limit < 1) {
			limit = PageConstant.defPageSize;
		}
		try {
			Page<E> p = selectPage(obj, page, limit, request);
			ret = ReturnResult.success(p.getTotal(), p.getRecords());
		} catch (Exception e) {
			ret = ReturnResult.failure(e.getMessage());
		}
		return ret;
	}

	/**
	 * REST 方式提交，增加
	 * 
	 * @param obj
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ReturnResult doRestAdd(@RequestBody E obj)  throws Exception{
		try {
			if(addData(obj)){
				ReturnResult.success(OptionConstant.addSuccess);
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.addFailure);
	}

	/**
	 * REST 方式提交 修改
	 * 
	 * @param obj
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ReturnResult doRestUpdate(@RequestBody E obj)  throws Exception{
		try {
			if(updateData(obj)){
				return ReturnResult.success(OptionConstant.updateSuccess);				
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.updateFailure);
	}

	/**
	 * REST 方式提交 删除
	 * 
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ReturnResult doRestDel(@PathVariable String id)  throws Exception{
		try {
			if(delData(id)){
				return ReturnResult.success(OptionConstant.deleteSuccess);
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.deleteFailure);
	}


    /**
     * Form 方式提交
     * 
     * 注意: 接收文件在Entity 中使用 MultipartFile file接收，
     * 或者参数使用@RequestParam("file") MultipartFile file接收
     * 
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ReturnResult doFormAdd(@ModelAttribute E obj)  throws Exception{
    	try {
			if(addData(obj)){
				ReturnResult.success(OptionConstant.addSuccess);
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.addFailure);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/update/{id}", method = {RequestMethod.POST})
    public ReturnResult doFormUpdate(@ModelAttribute E obj)  throws Exception{
    	try {
			if(updateData(obj)){
				return ReturnResult.success(OptionConstant.updateSuccess);				
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.updateFailure);
    }
    

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnResult doGetList(@RequestParam("entity") E entity, @RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit, HttpServletRequest request)  throws Exception{
		ReturnResult ret = null;
		if (page < 1) {
			page = PageConstant.defCurrPageNum;
		}
		if (limit < 1) {
			limit = PageConstant.defPageSize;
		}
		Page<E> p;
		try {
			p = selectPage(entity, page, limit, request);
			List<?> list = p.getRecords();
			int total = 0;
			if (list != null && list.size() > 0) {
				total = list.size();
			}
			ret = ReturnResult.success(total, list);
		} catch (Exception e) {
			ret = ReturnResult.failure(e.getMessage());
		}

		return ret;
	}

	/**
	 * 主键删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnResult doDelete(String id)  throws Exception{
		try {
			if(delData(id)){
				return ReturnResult.success(OptionConstant.deleteSuccess);				
			}
		} catch (Exception e) {
			ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.deleteFailure);				
	}
	
	/**
	 * 联合主键删除
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnResult doDeleteCompkeys(@ModelAttribute E obj)  throws Exception{
		try {
			if(delDataCompkeys(obj)){
				return ReturnResult.success(OptionConstant.deleteSuccess);				
			}
		} catch (Exception e) {
			return ReturnResult.failure(e.getMessage());
		}
		return ReturnResult.failure(OptionConstant.deleteFailure);
	}

	

	/**
	 * 分页查询
	 * @param condition
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public abstract Page<E> selectPage(E obj, int pageNum, int pageSize)  throws Exception;

	public Page<E> selectPage(E obj, int pageNum, int pageSize, HttpServletRequest request)  throws Exception{
		return selectPage(obj, pageNum, pageSize);
	}
	/**
	 * 添加数据
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public abstract boolean addData(E obj)  throws Exception;

	/**
	 * 更新数据
	 * @param obj
	 * @throws Exception
	 */
	public abstract boolean updateData(E obj)  throws Exception;

	/**
	 * 根据主键删除
	 * @param id
	 * @throws Exception
	 */
	public abstract boolean delData(String id)  throws Exception;
	
	/**
	 * 根据联合主键删除
	 * @param obj
	 * @throws Exception
	 */
	public abstract boolean delDataCompkeys(E obj)  throws Exception;
	
	/**
	 * 根据条件查询所有数据
	 * @param obj
	 * @throws Exception
	 */
	public abstract List<E> selectList(E obj)  throws Exception;

}

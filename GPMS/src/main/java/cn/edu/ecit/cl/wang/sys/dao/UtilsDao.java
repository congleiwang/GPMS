package cn.edu.ecit.cl.wang.sys.dao;

import org.apache.ibatis.annotations.Param;

public interface UtilsDao {
	public Long selectKey(@Param("seq") String seq);
}

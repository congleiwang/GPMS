package cn.edu.ecit.cl.wang.sys.common.utils;

import com.baomidou.mybatisplus.mapper.Wrapper;

public class EntityWrapper extends Wrapper<Object>{

	private static final long serialVersionUID = -3027263875557417625L;

	@Override
	public String getSqlSegment() {
		return sql.toString();
	}

}

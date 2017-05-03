package cn.edu.ecit.cl.wang.sys.common.utils;

import java.sql.Timestamp;

public class TimeUtils {
	
	public static Timestamp getNow(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String getNowStr(){
		return new Timestamp(System.currentTimeMillis()).toString().replace(" ", "_").replace(":", "-");
	}

}

package cn.edu.ecit.cl.wang.sys.pojo;

import java.util.HashMap;
import java.util.Map;

import cn.edu.ecit.cl.wang.sys.po.BaseTree;
import cn.edu.ecit.cl.wang.sys.po.Menu;

public class MenuTree extends BaseTree{
	private static final long serialVersionUID = 8832934525796562160L;
	
	public MenuTree(){
		
	}
	public MenuTree(Menu menu){
		Map<String, Object> map= new HashMap<String,Object>();
		map.put("sysNm",menu.getSysNm());
		map.put("menuType",menu.getMenuType());
		map.put("orderBy",menu.getOrderBy());
		map.put("jspUrl",menu.getJspUrl());
		map.put("isUse",menu.getIsUse());
		map.put("remark",menu.getRemark());
		map.put("pmenuCd",menu.getPmenuCd());
		setAttributes(map);
		setId(menu.getMenuCd());
		setText(menu.getMenuNm());
		setIconCls(menu.getIconFile());
		setParentId(menu.getPmenuCd());
	}
	
}

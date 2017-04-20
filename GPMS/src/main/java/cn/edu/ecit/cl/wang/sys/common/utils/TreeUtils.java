package cn.edu.ecit.cl.wang.sys.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import cn.edu.ecit.cl.wang.sys.po.BaseTree;

public class TreeUtils<T extends BaseTree> {
	
	public List<T> buildTree(List<T>treeList, Map<String, List<T>> treeMap) {
		for(T tree:  treeList){
			List<T> list=treeMap.get(tree.getId());
			if (!CollectionUtils.isEmpty(list)) {
				tree.setChildren(list);
				buildTree(list, treeMap);
			}
		}
		return treeList;
	}
	
	public Map<String, List<T>> getTreeMap(List<T> treeList) {
		Map<String, List<T>> map = new HashMap<String,List<T>>();
		for (T tree : treeList) {
			String parentCode = tree.getParentId();
			if(StringUtils.isNotEmpty(parentCode)){
				if (map.containsKey(parentCode)) {
					List<T> list = (List<T>) map.get(parentCode);
					list.add(tree);
					map.put(parentCode, list);
				} else {
					List<T> list = new ArrayList<T>();
					list.add(tree);
					map.put(parentCode, list);
				}
			}
		}

		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getSubTree(String id,List<? extends BaseTree> treelist){
		for(BaseTree tree:treelist){
			if(tree.getId().equals(id)){
				return (List<T>) tree.getChildren();
			}
			if(tree.getChildren()!=null){
				getSubTree(id,tree.getChildren());
			}
		}
		return null;
	}

}

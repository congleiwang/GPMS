package cn.edu.ecit.cl.wang.sys.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import cn.edu.ecit.cl.wang.sys.po.BaseTree;

public class TreeUtils<T extends BaseTree> {
	
	private List<T> rootTree=new ArrayList<T>();
	
	public List<T> buildTree(List<T> treeList,List<T> firstMenu, Map<String, List<T>> treeMap) {
		for (T tree : treeList) {
			List<T> list = (List<T>) treeMap.get(tree.getId());
			if (!CollectionUtils.isEmpty(list)) {
				tree.setChildren(list);
				for(T first:firstMenu){
					if(first.getId().equals(tree.getId())){
						rootTree.add(tree);						
					}
				}
				buildTree(list,firstMenu, treeMap);
			}
		}
		return rootTree;
	}

	public Map<String, List<T>> getTreeMap(List<T> treeList) {
		Map<String, List<T>> map = new HashMap<String,List<T>>();
		for (T tree : treeList) {
			String parentCode = tree.getParentId();
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

		return map;
	}

}

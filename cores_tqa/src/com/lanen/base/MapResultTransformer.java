package com.lanen.base;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

public class MapResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 984121496900697770L;

	@SuppressWarnings("unchecked")
	public List transformList(List list) {
		return list;
	}

	public Map<String,Object> transformTuple(Object[] values, String[] columns) {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		int i = 0;
		for(String column:columns){
			map.put(column, values[i++]);
		}
		return map;
	}

}

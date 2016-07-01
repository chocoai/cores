package com.lanen.jsonAndModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lanen.model.clinicaltest.TblClinicalTestData;
import com.lanen.model.studyplan.DictAnimalStrain;


public class JsonUtil {
	/**
	 * 把 List 格式转换成  json 格式
	 * @param list
	 * @param name    
	 * @return
	 */
//	[{"name":"北京"},{"name":"通县"},{"name":"昌平"},{"name":"大兴"},
//	 {"name":"密云"},{"name":"延庆"},{"name":"顺义"},{"name":"怀柔"},{"name":"平台"}]


//	 [{"areacode":"010","id":1,"name":"北京","pid":1,"postcode":"100000","pycode":"bj"},
//	  {"areacode":"010","id":2,"name":"通县","pid":1,"postcode":"101100","pycode":"tx"},
//	  {"areacode":"010","id":3,"name":"昌平","pid":1,"postcode":"102200","pycode":"cp"},
//	  {"areacode":"010","id":4,"name":"大兴","pid":1,"postcode":"102600","pycode":"dx"},
//	  {"areacode":"010","id":5,"name":"密云","pid":1,"postcode":"101500","pycode":"my"},
//	  {"areacode":"010","id":6,"name":"延庆","pid":1,"postcode":"102100","pycode":"yq"},
//	  {"areacode":"010","id":7,"name":"顺义","pid":1,"postcode":"101300","pycode":"sy"},
//	  {"areacode":"010","id":8,"name":"怀柔","pid":1,"postcode":"101400","pycode":"hr"},
//	  {"areacode":"010","id":9,"name":"平台","pid":1,"postcode":"101200","pycode":"pt"}]
	public static String cityList2json(List<DictAnimalStrain> list,String name){
		StringBuffer sb =new StringBuffer("[");
		if(list.size()<1||list==null){
			return null;
		}
		for(int i=0;i<list.size();i++){
			sb.append("{").append("\"").append(name).append("\"")
			.append(":").append("\"").append(list.get(i).getStrainName()).append("\"")
			.append("}").append(",")
			;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
}

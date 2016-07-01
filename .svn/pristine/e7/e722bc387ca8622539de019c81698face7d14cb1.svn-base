package com.lanen.view.action.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 存放 字典项,dictPathCommon中用
 * @author 黄国刚
 *
 */
public class DictTypeForPath {
	private static Map<Integer,String> map = null;
	
	public static void init(){
		map = new HashMap<Integer,String>();
		map.put(1,  "解剖学所见部位");
		map.put(2,  "剖检通用所见");
		map.put(3,  "剖检特殊所见");
		map.put(4,  "体表部位");
		map.put(5,  "位置");
		map.put(6,  "分布");
		map.put(7,  "数量");
		map.put(8,  "形状");
		map.put(9,  "颜色");
		map.put(10, "硬度");
		map.put(11, "大小");
		map.put(12, "病变程度");
		map.put(13, "组织学所见部位");
		map.put(14, "肿瘤性病变");// visceraCode = ‘00000000000' 为其他 ，及非脏器（大腿肿物等使用）
//		map.put(14, "肿瘤性病变（良性）");
//		map.put(15, "肿瘤性病变（恶性）");
		map.put(16, "非肿瘤性病变"); // visceraCode = ‘00000000000' 为其他 ，及非脏器（大腿肿物等使用）

		map.put(17, "死亡动物致死原因");
//		map.put(1,  "（剖检）1：解剖学所见（注：脏器相关）");
//		map.put(2,  "（剖检）2：剖检通用所见");
//		map.put(3,  "（剖检）3：剖检特殊所见（注：脏器相关）");
//		map.put(4,  "（剖检）4：体表部位");
//		map.put(5,  "（剖检）5：位置");
//		map.put(6,  "（剖检）6：分布");
//		map.put(7,  "（剖检）7：数量");
//		map.put(8,  "（剖检）8：形状");
//		map.put(9,  "（剖检）9：颜色");
//		map.put(10, "（剖检）10：硬度");
//		map.put(11, "（剖检）11：大小");
//		map.put(12, "（剖检）12：病变程度");
//		map.put(13, "（镜检）13：组织学所见（注：脏器相关）");
//		map.put(14, "（镜检）14：肿瘤性病变（良性）（注：脏器相关）");
//		map.put(15, "（镜检）15：肿瘤性病变（恶性）（注：脏器相关）");
//		map.put(16, "（镜检）16：非肿瘤性病变");
//		map.put(16, "17：死亡动物致死原因（脏器无关）");
		
	}
	public static List<Map<String,Object>> getList(){
		if(null == map){
			init();
		}
		Set<Integer> keySet = map.keySet();
		List<Integer> keyList = new ArrayList<Integer>(keySet);
		Collections.sort(keyList);
		Iterator<Integer> iterator = keyList.iterator();
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> currentMap = null;
		while(iterator.hasNext()){
			currentMap = new HashMap<String, Object>();
			Integer index = (Integer) iterator.next();
			currentMap.put("id", index);
			currentMap.put("text", map.get(index));
			list.add(currentMap);
		}
		return list;
	}
	
	public static String get(Integer index){
		if(null == map){
			init();
		}
		return map.get(index);
	}
}

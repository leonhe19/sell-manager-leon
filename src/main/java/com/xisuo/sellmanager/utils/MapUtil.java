package com.xisuo.sellmanager.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Title: MapUtil 
 * Description: map工具类
 * @author zk  
 * @date 2018年3月21日
 */
public class MapUtil {
	
	/**
	 * Title: getMap 
	 * Description: map 工具类,把value为null的全部变成""
	 * @param map
	 * @return 原来的map进行返回
	 * @author zk
	 */
	public static    Map  getMap(Map  map){
		Set<Entry> set = map.entrySet();
		for (Entry entry : set) {
			if(entry.getValue()==null){
				map.put(entry.getKey(), "");
			}
		}
		return map;
	}
	
	/**
	 * Title: getMaps 
	 * Description:list中的map进行全部的null的替换,返回新的ArrayList
	 * @param lists 
	 * @author zk
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static    List<Map> getMaps(List<Map>  lists){
		List<Map> newList = new ArrayList<Map>();
		for (Map map : lists) {
			Map newMap = getMap(map);
			newList.add(newMap);
		}
		return newList;
	}
	
	/**
	 * Title: List2Map 
	 * Description: List 变成 Map
	 * @param  
	 * @date 2018年3月27日 
	 * @author zk
	 */
	public static Map<String,String>  List2Map(List<Map<String,Object>> lists){
		Map<String, String> hashMap = new HashMap<String,String>(lists.size());
		for (Map<String, Object> map : lists) {
			hashMap.put(String.valueOf(map.get("name")),String.valueOf(map.get("id")));
		}
		return hashMap;
	}


	/**
	 * 读取properties 配置文件中的所有内容,全部读取,变成map,以key-value保存
	 * @param object
	 * @throws IllegalAccessException
	 */
	public static Map<String,String> handleBean2Map(Object object) throws IllegalAccessException{
		Map<String, String> map = new HashMap<>(16);
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			String value = String.valueOf(field.get(object));
			map.put(name,value);
		}
		return map;
	}
	
	
	

}

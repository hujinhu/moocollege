package team.ascent.util.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import team.ascent.util.map.Reflections;

/**
 * Collections工具集.
 * 
 * 在JDK的Collections和Guava的Collections2后, 命名为Collections3.
 * 
 * 函数主要由两部分组成，一是自反射提取元素的功能，二是源自Apache Commons Collection, 争取不用在项目里引入它。
 * 
 */
public class Collections3 {

	/**
	 * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
	 * 
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名.
	 * @param valuePropertyName 要提取为Map中的Value值的属性名.
	 */
	public static Map extractToMap(final Collection collection, final String keyPropertyName,
			final String valuePropertyName) {
		Map map = new HashMap(collection.size());

		try {
			for (Object obj : collection) {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return map;
	}
	
	/**
	 * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
	 * 
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名. -- > value self
	 */
	public static Map extractToMap(final Collection collection, final String keyPropertyName) {
		Map map = new HashMap(collection.size());

		try {
			for (Object obj : collection) {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName), obj);
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return map;
	}
	
	
	
	/**
	 * 返回一个map<Akey,BValue>
	 * 例如 map<personId,departNames/departNames..>
	 * 
	 * @param listA  例如connPersonDepart表
	 * @param Akey   例如 connPersonDepart 中的 personId 
	 * @param ListA_connPropertyName  例如connPersonDepart 中的 departId
	 * @param ListB 例如depart表 
	 * @param ListB_connPropertyName  例如 depart表中的id
	 * @param BValue  例如 depart表中的name 
	 * @return
	 */
	public static Map  extractToMapString(final Collection listA,  final String Akey,final String  ListA_connPropertyName ,final Collection ListB,final String  ListB_connPropertyName ,final String BValue) {
		Map<Object,String>   map = new HashMap<Object,String>  (ListB.size());
		Map<Object,Object> deptsMap = Collections3.extractToMap(ListB,ListB_connPropertyName);
		try {
			for (Object obj : listA) {
				Object _tempObj = map.get(PropertyUtils.getProperty(obj,Akey));
				String _tempStr=null;
				if(_tempObj!=null){
					 _tempStr= (String)_tempObj;
				}
				 String _deptStr = (String) PropertyUtils.getProperty(deptsMap.get(PropertyUtils.getProperty(obj, ListA_connPropertyName)),BValue);
				 if(_tempStr==null){
					 _tempStr=_deptStr;
				 }else{
					 _tempStr+="/"+_deptStr;
				 }
				map.put(PropertyUtils.getProperty(obj, Akey),_tempStr);
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
		return map;
	}
	
	
	

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	public static List extractToList(final Collection collection, final String propertyName) {
		List list = new ArrayList(collection.size());

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 */
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null) || collection.isEmpty();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isEmpty(Map map) {
		return (map == null) || map.isEmpty();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isNotEmpty(Collection collection) {
		return (collection != null) && !(collection.isEmpty());
	}

	/**
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 */
	public static <T> T getLast(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		// 当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List) {
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		// 其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = collection.iterator();
		while (true) {
			T current = iterator.next();
			if (!iterator.hasNext()) {
				return current;
			}
		}
	}

	/**
	 * 返回a+b的新List.
	 */
	public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
		List<T> result = new ArrayList<T>(a);
		result.addAll(b);
		return result;
	}

	/**
	 * 返回a-b的新List.
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
		List<T> list = new ArrayList<T>(a);
		for (T element : b) {
			list.remove(element);
		}

		return list;
	}

	/**
	 * 返回a与b的交集的新List.
	 */
	public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
		List<T> list = new ArrayList<T>();

		for (T element : a) {
			if (b.contains(element)) {
				list.add(element);
			}
		}
		return list;
	}
	
	public static <T> void batchProcess(List<T> collection, int batchSize, Process<T> callback) {
		if(batchSize <= 1) throw new IllegalArgumentException("Parameter batchSize should large then 1");
		
    	for(int i=0; i<collection.size(); ) {
    		int endIdex = Math.min(i + batchSize, collection.size());
    		List<T> tmp = collection.subList(i, endIdex);
    		
    		callback.process(tmp);
    		
    		i = i + tmp.size();
    		if(tmp.size() == 0) {
    			throw new RuntimeException("Index error, please check wheathe you have a delete or add operation during iterate.");
    		}
    	}
	}
	
	public interface Process<T> {
		public void process(List<T> batch);
	}

	/**
	 * 返回map<String,List>;
	 * 根据传入的属性,返回一个map,key为属性值 value为传入的list中满足传入属性的list
	 * @param collection  
	 * @param keyPropertyName  collection中的属性   
	 * @return
	 */
	public static Map extractToMapList(Collection collection, String keyPropertyName) {
		Map map = new HashMap(collection.size());
		try {
			for (Object obj : collection) {
				Object _list = map.get(PropertyUtils.getProperty(obj, keyPropertyName));
				List<Object> list = null;
				if(_list==null){
					list = new ArrayList<Object>();
				}else{
					list=(List) _list;
				}
				list.add(obj);
				map.put(PropertyUtils.getProperty(obj, keyPropertyName), list);
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}
		return map;
	}
}

package team.ascent.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * 
 * @ClassName: JsonUtil
 * @Description:json接口类，不再使用jsonObject
 * @author  作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年11月23日上午9:26:39
 */
public class JsonUtil {
    /**
     * log4j实例
     */
    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    /**
     * jsonmapper实例
     */
    private ObjectMapper mapper;

    public JsonUtil() {
        this(Include.ALWAYS);
    }

    public JsonUtil(Include include) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(include);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 
    * @Title: nonEmptyMapper
    * @Description:创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
    * @return JsonUtil
     */
    public static JsonUtil nonEmptyMapper() {
        return new JsonUtil(Include.NON_EMPTY);
    }

   /**
    * 
   * @Title: nonDefaultMapper
   * @Description:创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
   * @return JsonUtil
    */
    public static JsonUtil nonDefaultMapper() {
        return new JsonUtil(Include.NON_DEFAULT);
    }

    /**
     * 
    * @Title: toJson
    * @Description:Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
    * @param object 要转成json的对象
    * @return String
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     * 
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
     * 
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
     * 
     * @see #fromJson(String, JavaType)
     */
    /**
     * 
    * @Title: fromJson 
    * @Description:将json转成collection（如List<String>）
    * @param jsonString 待转集合的json
    * @param clazz 要转的集合类型 
    * @return 集合对象
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtil.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            LOGGER.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     * 
     * @see #createCollectionType(Class, Class...)
     */
    /**
     * 
    * @Title: fromJson
    * @Description:
    * @param jsonString
    * @param javaType
    * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtil.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            LOGGER.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 構造泛型的Collection Type如: ArrayList<MyBean>,
     * 则调用constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 
    * @Title: update
    * @Description:當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性
    * @param jsonString 待更新的json字符串
    * @param object 要更新的数据类型
    * @return 更新后的结果
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String jsonString, T object) {
        try {
            return (T) mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
        return null;
    }

    /**
     * 
    * @Title: toJsonP
    * @Description:输出jsonp格式数据
    * @param functionName 方法名
    * @param object 对象
    * @return jsonp格式字符串
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void enableEnumUseToString() {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * 
    * @Title: getMapper
    * @Description:取出Mapper做进一步的设置或使用其他序列化API
    * @return jsonMapper对象
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
}

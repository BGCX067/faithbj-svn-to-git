package com.faithbj.shop.utils;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonUtil {
	
    protected static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
	private static final ObjectMapper objectMapper=new ObjectMapper();
	
	public JsonUtil(){
		
	}
    public JsonUtil(Inclusion inclusion) {  
        //设置输出包含的属性  
//    	ObjectMapper.getSerializationConfig().withSerializationInclusion(inclusion);  
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性  
//    	ObjectMapper.getDeserializationConfig().set(  
//                org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
    }  
	
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 如果对象为Null,返回"null".
     * 如果集合为空集合,返回"[]".
     */
    public static String toJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }
    
    
    
/*	public static String writeValueAsString(Object paramObject) {
		try {
			return objectMapper.writeValueAsString(paramObject);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<?> readJson2Entity(String parameterStore,Class<?> productParameter) {
	    try {
	    	List<ProductParameter> parameters=Arrays.asList(objectMapper.readValue(parameterStore, ProductParameter[].class));
			return parameters;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	  
	 @SuppressWarnings("unchecked")
	public static <T> T Json2List(String paramString, TypeReference<?> valueTypeRef)
	  {
	    try {
			return (T) objectMapper.readValue(paramString,valueTypeRef);
		} 
	    catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	  }	  
	 
	  /**
	  * 取出Mapper做进一步的设置或使用其他序列化API.
	  */
	  public static ObjectMapper getMapper() {
	    return objectMapper;
	  }
	  
}

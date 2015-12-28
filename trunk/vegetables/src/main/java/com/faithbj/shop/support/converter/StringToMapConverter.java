package com.faithbj.shop.support.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

public class StringToMapConverter implements Converter<String,Map>{

	@Override
	public Map convert(String source) {
		 if(source != null){  
	           HashMap<String, String> map = new HashMap<String, String>();  
	           String[] items = source.split(",");  
	           for (String item : items) {  
	               String[] keyValue = item.split(":");  
	               map.put(keyValue[0],keyValue[1]);  
	           }  
	           return map;  
	       }else{  
	           return null;  
	       }  
	}

}

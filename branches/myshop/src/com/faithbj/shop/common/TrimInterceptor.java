package com.faithbj.shop.common;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器 - 去除页面参数字符串两端的空格
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class TrimInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2365641900033439481L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value instanceof String[]) {
				String[] valueArray = (String[]) value;
				for (int i = 0; i < valueArray.length; i++) {
					valueArray[i] = valueArray[i].trim();
				}
				parameters.put(key, valueArray);
			}
		}
		return invocation.invoke();
	}

}

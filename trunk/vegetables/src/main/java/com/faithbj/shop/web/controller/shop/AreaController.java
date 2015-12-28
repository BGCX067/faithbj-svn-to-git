package com.faithbj.shop.web.controller.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import com.faithbj.shop.model.entity.Area;
import com.faithbj.shop.service.AreaService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台Action类 - 地区
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/area")
public class AreaController extends BaseShopController {

	private static final long serialVersionUID = 1321099291073671591L;

	@Resource
	private AreaService areaService;

	// 根据地区Path值获取下级地区JSON数据
	@RequestMapping("ajaxArea")
	public void ajaxArea(String path,String id,HttpServletResponse response) {
		if (StringUtils.contains(path,  Area.PATH_SEPARATOR)) {
			id = StringUtils.substringAfterLast(path, Area.PATH_SEPARATOR);
		} else {
			id = path;
		}
		List<Area> childrenAreaList = new ArrayList<Area>();
		if (StringUtils.isEmpty(id)) {
			childrenAreaList = areaService.getRootAreaList();
		} else {
			childrenAreaList = new ArrayList<Area>(areaService.load(id).getChildren());
		}
		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
		for (Area area : childrenAreaList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", area.getName());
			map.put("value", area.getPath());
			optionList.add(map);
		}
		JSONArray jsonArray = JSONArray.fromObject(optionList);
		try {
			response.setContentType("text/html" + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(jsonArray.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

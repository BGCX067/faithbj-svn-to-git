package com.faithbj.shop.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.entity.Area;
import com.faithbj.shop.service.AreaService;

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
@RequestMapping("/faith/area")
public class AdminAreaController extends BaseAdminController {

	private static final long serialVersionUID = 6254431866456845575L;
	private final String OPERRATE_RETURN_URL="faith/area/list/";
	private final String LIST="admin/area_list";
	private final String INPUT="admin/area_input";
//	private String parentId;
//	private Area parent;
//	private List<Area> areaList = new ArrayList<Area>();

	@Resource
	private AreaService areaService;

//	// 是否已存在ajax验证
//	public String checkName() {
//		String oldName = getParameter("oldValue");
//		String parentId = getParameter("parentId");
//		String newName = area.getName();
//		if (areaService.isNameUnique(parentId, oldName, newName)) {
//			return ajaxText("true");
//		} else {
//			return ajaxText("false");
//		}
//	}

	// 添加
	@RequestMapping("/new/{parentId}")
	public String add(@PathVariable String parentId,ModelMap map) {
		Area parent = new Area();
		if (!parentId.equals("0")) {
			parent = areaService.load(parentId);
		}
		map.put("parent", parent);
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable String id,ModelMap map) {
		Area area = areaService.load(id);
		Area parent = area.getParent();
		map.put("area", area);
		map.put("parent", parent);
		map.put("isAddAction", false);
		return INPUT;
	}

	// 列表
	@RequestMapping("/list")
	public String list(ModelMap map) {
		List<Area> areaList = new ArrayList<Area>();
		Area parent = new Area();
		areaList = areaService.getRootAreaList();
		map.put("parent",parent);
		map.put("areaList",areaList);
		return LIST;
	}
	
	// 列表
		@RequestMapping("/list/{parentId}")
		public String childrenlist(@PathVariable String parentId,ModelMap map) {
			List<Area> areaList = new ArrayList<Area>();
			Area parent = new Area();
			if (!parentId.equals("0")) {
				parent = areaService.load(parentId);
				areaList = new ArrayList<Area>(parent.getChildren());
			} else {
				areaList = areaService.getRootAreaList();
			}
			map.put("parent",parent);
			map.put("areaList",areaList);
			return LIST;
		}

	// 删除
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id,ModelMap map) {
		areaService.delete(id);
		map.put("redirectUrl", "../"+OPERRATE_RETURN_URL + "");
		return SUCCESS;
	}

	// 保存
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "area.name", message = "地区名称不允许为空!")
//		}
//	)
//	@InputConfig(resultName = "error")
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(Area area,@RequestParam String parentId,ModelMap map) {
		String newName = area.getName();
		if (!areaService.isNameUnique(parentId, null, newName)) {
			map.put("errorMessages", "地区名称已存在!");
			return ERROR;
		}
		if (!parentId.equals("0")) {
			area.setParent(areaService.load(parentId));
		} else {
			area.setParent(null);
		}
		areaService.save(area);
		map.put("redirectUrl", OPERRATE_RETURN_URL + parentId);
		return SUCCESS;
	}

	// 更新
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "area.name", message = "地区名称不允许为空!")
//		}
//	)
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(Area area,@RequestParam String id,@RequestParam String parentId,ModelMap map) {
		Area persistent = areaService.load(id);
		Area parent = persistent.getParent();
		if (parent != null) {
			parentId = parent.getId();
		}
		if (!areaService.isNameUnique(parentId, persistent.getName(), area.getName())) {
			map.put("errorMessages", "地区名称已存在!");
			return ERROR;
		}
		persistent.setName(area.getName());
		areaService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL + parentId);
		return SUCCESS;
	}
	
	// 根据地区Path值获取下级地区JSON数据
//	public String ajaxChildrenArea() {
//		String path = getParameter("path");
//		if (StringUtils.contains(path,  Area.PATH_SEPARATOR)) {
//			id = StringUtils.substringAfterLast(path, Area.PATH_SEPARATOR);
//		} else {
//			id = path;
//		}
//		List<Area> childrenAreaList = new ArrayList<Area>();
//		if (StringUtils.isEmpty(id)) {
//			childrenAreaList = areaService.getRootAreaList();
//		} else {
//			childrenAreaList = new ArrayList<Area>(areaService.load(id).getChildren());
//		}
//		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
//		for (Area area : childrenAreaList) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("title", area.getName());
//			map.put("value", area.getPath());
//			optionList.add(map);
//		}
//		JSONArray jsonArray = JSONArray.fromObject(optionList);
//		return ajaxJson(jsonArray.toString());
//	}


}

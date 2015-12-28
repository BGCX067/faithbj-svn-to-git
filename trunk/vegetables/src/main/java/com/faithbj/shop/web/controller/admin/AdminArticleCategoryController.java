package com.faithbj.shop.web.controller.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.service.ArticleCategoryService;

/**
 * 后台Action类 - 文章分类
 * <p>Copyright: Copyright (c) 2012</p> 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-07
 * @version 1.0
 */

@Controller
@RequestMapping("/faith/article_category")
public class AdminArticleCategoryController extends BaseAdminController {

	private static final long serialVersionUID = -7786508966240073537L;
	
	private final String LIST="admin/article_category_list";
	private final String INPUT="admin/article_category_input";
	private final String HAS_CHILDREN_CATEGORY="此文章分类存在下级分类，删除失败!";
	private final String HAS_ARTICLES="此文章分类下存在文章，删除失败!";
	private final String OPERRATE_RETURN_URL="faith/article_category/list";
	
	@Resource
	private ArticleCategoryService articleCategoryService;
	

	// 添加
	@RequestMapping("/add")
	public String add(ModelMap map) {
		List<ArticleCategory> articleCategoryTreeList=articleCategoryService.getRootArticleCategoryList();
		map.put("articleCategoryTreeList", articleCategoryTreeList);
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable String id,ModelMap map) {
		ArticleCategory articleCategory = articleCategoryService.load(id);
		map.put("articleCategory", articleCategory);
		map.put("isAddAction", false);
		return INPUT;
	}

	// 列表
	@RequestMapping("/list")
	public String list(ModelMap map) {
		List<ArticleCategory> articleCategoryTreeList = articleCategoryService.getArticleCategoryTreeList();
		map.put("articleCategoryTreeList", articleCategoryTreeList);
		return LIST;
	}

	/**
	 * 不是form表单，也没用ajax请求，所以只能用get方法，否则可以用delete方法
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id,ModelMap map) {
		ArticleCategory articleCategory = articleCategoryService.load(id);
		
		Set<ArticleCategory> childrenArticleCategoryList = articleCategory.getChildren();
		if (childrenArticleCategoryList != null && childrenArticleCategoryList.size() > 0) {
			map.put("errorMessages", HAS_CHILDREN_CATEGORY);
			return ERROR;
		}
		
		Set<Article> articleSet = articleCategory.getArticleSet();
		if (articleSet != null && articleSet.size() > 0) {
			map.put("errorMessages", HAS_ARTICLES);
			return ERROR;
		}
		
		articleCategoryService.delete(id);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(ArticleCategory articleCategory,@RequestParam("parentId") String parentId,ModelMap map) {
		if (StringUtils.isNotEmpty(parentId)) {
			ArticleCategory parentCategory = articleCategoryService.load(parentId);
			articleCategory.setParent(parentCategory);
		} else {
			articleCategory.setParent(null);
		}
		articleCategoryService.save(articleCategory);
		
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="/update")
	public String update(@RequestParam String id,ArticleCategory articleCategory,ModelMap map) {
		ArticleCategory persistent = articleCategoryService.load(id);
		BeanUtils.copyProperties(articleCategory, persistent, new String[]{"id", "createDate", "modifyDate", "path", "parent", "children", "articleSet"});
		articleCategoryService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

}

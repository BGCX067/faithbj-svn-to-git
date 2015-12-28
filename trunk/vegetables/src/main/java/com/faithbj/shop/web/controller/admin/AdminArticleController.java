package com.faithbj.shop.web.controller.admin;
import java.util.List;
import javax.annotation.Resource;
import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;
import com.faithbj.shop.service.ArticleCategoryService;
import com.faithbj.shop.service.ArticleService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台Action类 - 新闻
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
@RequestMapping("/faith/article")
public class AdminArticleController extends BaseAdminController {

	private static final long serialVersionUID = -6825456589196458406L;

	private final String OPERRATE_RETURN_URL="faith/article/list";
	private final String LIST="admin/article_list";
	private final String INPUT="admin/article_input";
	
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articleCategoryService;
	
	@RequestMapping("/menu")
	public String index(){
		return "admin/menu_content";
	}

	// 添加
	@RequestMapping("/add")
	public String add(ModelMap map) {
		List<ArticleCategory> articleCategoryTreeList=articleCategoryService.getRootArticleCategoryList();
		map.put("articleCategoryTreeList", articleCategoryTreeList);
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") String id,ModelMap map) {
		List<ArticleCategory> articleCategoryTreeList=articleCategoryService.getRootArticleCategoryList();
		map.put("articleCategoryTreeList", articleCategoryTreeList);
		Article article = articleService.load(id);
		map.put("article", article);
		map.put("isAddAction", false);
		return INPUT;
	}

	// 列表
	@RequestMapping("/list")
	public String list(ModelMap map) {
		pager = articleService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}

	// 删除
	@RequestMapping("/delete")
	public String delete(@RequestParam String id,ModelMap map){
		articleService.delete(id);
//		flushCache();
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(Article article, ModelMap map) {
		article.setHits(0);//点击次数
		articleService.save(article);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(Article article, ModelMap map) {
		Article persistent = articleService.get(article.getId());
		BeanUtils.copyProperties(article, persistent, new String[] {"id", "createDate", "modifyDate", "pageCount", "htmlFilePath", "hits"});
		articleService.update(persistent);
//		flushCache();
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

}

package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;
import com.faithbj.shop.service.ArticleCategoryService;
import com.faithbj.shop.service.ArticleService;
import com.faithbj.shop.support.annotation.NeedNavigation;

/**
 * 前台Action类 - 文章
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-07
 * @version 1.0
 */

@Controller
public class ArticleController extends BaseShopController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
//	private ArticleCategory articleCategory;
//	private List<Article> recommendArticleList;
//	private List<Article> hotArticleList;
//	private List<Article> newArticleList;
//	private List<ArticleCategory> pathList;

	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articleCategoryService;
	

	/**
	 * 文章列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/article")
	@NeedNavigation()
	public String index(ModelMap map) {
//		List<Article> recommendArticleList = articleService.getRecommendArticleList(Article.MAX_RECOMMEND_ARTICLE_LIST_COUNT);
		List<Article> hotArticleList = articleService.getHotArticleList(Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(Article.MAX_NEW_ARTICLE_LIST_COUNT);
		List<ArticleCategory> pathList =articleCategoryService.getAll();
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		pager.setProperty(null);
		try {
			pager.setKeyword(null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		pager = articleService.findByPager(pager);
		map.put("pager", pager);
//		map.put("recommendArticleList", recommendArticleList);
		map.put("hotArticleList", hotArticleList);
		map.put("newArticleList", newArticleList);
		map.put("pathList", pathList);
		return "shop/article_index";
	}
	
	/**
	 * 文章分类列表
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/articleCategory/{id}")
	@NeedNavigation()
	public String index(ModelMap map,@PathVariable String id) {
		ArticleCategory articleCategory = articleCategoryService.load(id); 
//		List<Article> recommendArticleList = articleService.getRecommendArticleList(Article.MAX_RECOMMEND_ARTICLE_LIST_COUNT);
		List<Article> hotArticleList = articleService.getHotArticleList(articleCategory,Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(articleCategory,Article.MAX_NEW_ARTICLE_LIST_COUNT);
		List<ArticleCategory> pathList =articleCategoryService.getArticleCategoryPathList(articleCategory);
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		pager.setProperty(null);
		try {
			pager.setKeyword(null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		pager = articleService.getArticlePager(articleCategory, pager);
		map.put("pager", pager);
//		map.put("recommendArticleList", recommendArticleList);
		map.put("hotArticleList", hotArticleList);
		map.put("newArticleList", newArticleList);
		map.put("pathList", pathList);
		return "shop/article_index";
	}

	@NeedNavigation
	@RequestMapping("/article/deliveryview")
	public String delivery(ModelMap map){
		List<Article> hotArticleList = articleService.getHotArticleList(Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(Article.MAX_NEW_ARTICLE_LIST_COUNT);
		List<ArticleCategory> pathList =articleCategoryService.getAll();
		map.put("hotArticleList", hotArticleList);
		map.put("newArticleList", newArticleList);
		map.put("pathList", pathList);
		return "shop/period_deleviery";
	}	
	
/*	@NeedNavigation
	@RequestMapping("/list")
	public String list(@RequestParam("id") String id,ModelMap map) {
		ArticleCategory articleCategory = articleCategoryService.load(id);
		List<Article> recommendArticleList = articleService.getRecommendArticleList(articleCategory, Article.MAX_RECOMMEND_ARTICLE_LIST_COUNT);
		List<Article> hotArticleList = articleService.getHotArticleList(articleCategory, Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(articleCategory, Article.MAX_NEW_ARTICLE_LIST_COUNT);
		List<ArticleCategory> pathList = articleCategoryService.getArticleCategoryPathList(articleCategory);
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		pager.setProperty(null);
		try {
			pager.setKeyword(null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		pager = articleService.getArticlePager(articleCategory, pager);
		map.put("pager", pager);
		map.put("recommendArticleList", recommendArticleList);
		map.put("hotArticleList", hotArticleList);
		map.put("newArticleList", newArticleList);
		map.put("pathList", pathList);
		map.put("articleCategory", articleCategory);
		return "shop/article_list";
	}
*/	
	// 文章搜索
/*	public String search() throws Exception {
		List<Article> recommendArticleList = articleService.getRecommendArticleList(Article.MAX_RECOMMEND_ARTICLE_LIST_COUNT);
		List<Article> hotArticleList = articleService.getHotArticleList(Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(Article.MAX_NEW_ARTICLE_LIST_COUNT);
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		pager = articleService.search(pager);
		return "search";
	}*/
	
	// 文章点击统计
/*	@ResponseBody
	public String ajaxCounter() {
		Article article =  articleService.load(id);
		if (!article.getIsPublication()) {
			return "您访问的文章尚未发布！";
		}
		Integer hits = article.getHits() + 1;
		article.setHits(hits);
		articleService.update(article);
		Map<String, String> jsonMap = new HashMap<String, String>();
//		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put("hits", hits.toString());
		return jsonMap.toString();
	}*/

	@RequestMapping("/article/content/{id}")
	@NeedNavigation
	public String content(ModelMap map,@PathVariable("id") String id){
		Article article = articleService.load(id);
		ArticleCategory articleCategory = article.getArticleCategory();
		List<Article> hotArticleList = articleService.getHotArticleList(articleCategory,Article.MAX_HOT_ARTICLE_LIST_COUNT);
		List<Article> newArticleList = articleService.getNewArticleList(articleCategory,Article.MAX_NEW_ARTICLE_LIST_COUNT);
		List<ArticleCategory> pathList =articleCategoryService.getArticleCategoryPathList(articleCategory);
		map.put("hotArticleList", hotArticleList);
		map.put("newArticleList", newArticleList);
		map.put("pathList", pathList);
		map.put("article", article);
		return "shop/article_content";
	}
}

package com.faithbj.shop.action.shop;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.entity.Article;
import com.faithbj.shop.entity.ArticleCategory;
import com.faithbj.shop.service.ArticleService;


/**
 * 后台Action类 - 首页动态新闻显示
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("shop")
public class IndexAction extends BaseShopAction {

	private static final long serialVersionUID = 3066159260207583127L;
	
	private ArticleCategory articleCategory;
	
	private List<Article> hotArticleList;
	
	@Resource
	private ArticleService articleService;
	

	public String all() throws Exception{
		hotArticleList=articleService.getHotArticleList(10);
		return LIST;
	}

	


		public ArticleCategory getArticleCategory() {
			return articleCategory;
		}


		public void setArticleCategory(ArticleCategory articleCategory) {
			this.articleCategory = articleCategory;
		}


		public List<Article> getHotArticleList() {
			return hotArticleList;
		}


		public void setHotArticleList(List<Article> hotArticleList) {
			this.hotArticleList = hotArticleList;
		}
		
		
	

	
	
	
	
	

}

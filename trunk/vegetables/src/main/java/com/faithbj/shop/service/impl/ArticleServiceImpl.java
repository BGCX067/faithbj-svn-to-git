package com.faithbj.shop.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.ArticleDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;
import com.faithbj.shop.service.ArticleService;

/**
 * Service实现类 - 文章
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements ArticleService {

	@Resource(name="articleDao")
	private ArticleDao articleDao;

	@Resource(name="articleDao")
	public void setBaseDao(ArticleDao articleDao) {
		super.setBaseDao(articleDao);
	}

	public List<Article> getArticleList(ArticleCategory articleCategory) {
		return articleDao.getArticleList(articleCategory);
	}
	
	public List<Article> getArticleList(int firstResult, int maxResults) {
		return articleDao.getArticleList(firstResult, maxResults);
	}
	
	public List<Article> getArticleList(Date beginDate, Date endDate, int firstResult, int maxResults) {
		return articleDao.getArticleList(beginDate, endDate, firstResult, maxResults);
	}
	
	public List<Article> getArticleList(ArticleCategory articleCategory, int firstResult, int maxResults) {
		return articleDao.getArticleList(articleCategory, firstResult, maxResults);
	}
	
	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager) {
		return articleDao.getArticlePager(articleCategory, pager);
	}
	
	public List<Article> getRecommendArticleList(int maxResults) {
		return articleDao.getRecommendArticleList(maxResults);
	}

	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getRecommendArticleList(articleCategory, maxResults);
	}
	
	public List<Article> getHotArticleList(int maxResults) {
		return articleDao.getHotArticleList(maxResults);
	}

	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getHotArticleList(articleCategory, maxResults);
	}
	
	public List<Article> getNewArticleList(int maxResults) {
		return articleDao.getNewArticleList(maxResults);
	}

	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getNewArticleList(articleCategory, maxResults);
	}

	public Pager search(Pager pager) {
//		Compass compass = compassTemplate.getCompass();
//		CompassSession compassSession = compass.openSession();
//		CompassQueryBuilder compassQueryBuilder = compassSession.queryBuilder();
//		CompassBooleanQueryBuilder compassBooleanQueryBuilder = compassQueryBuilder.bool();
//
//		CompassQuery compassQuery = compassBooleanQueryBuilder.addMust(compassQueryBuilder.term("isPublication", true)).addMust(compassQueryBuilder.queryString("title:*" + pager.getKeyword() + "*").toQuery()).toQuery();
//		// 根据是否置顶排序(降序)
//		compassQuery.addSort("isTop", SortPropertyType.STRING, SortDirection.REVERSE)
//		// 根据点击量排序(降序)
//		.addSort("hits", SortPropertyType.STRING, SortDirection.REVERSE);
//
//		CompassHits compassHits = compassQuery.hits();

//		List<Article> articleList = new ArrayList<Article>();
//
//		int firstResult = (pager.getPageNumber() - 1) * pager.getPageSize();
//		int maxReasults = pager.getPageSize();
//		int totalCount = compassHits.length();
//
//		int end = Math.min(totalCount, firstResult + maxReasults);
//		for (int i = firstResult; i < end; i++) {
//			Article article = (Article) compassHits.data(i);
//			articleList.add(article);
//		}
//		compassSession.close();
//		pager.setList(articleList);
//		pager.setTotalCount(totalCount);
		return pager;
	}
	
	// 重写方法，删除对象的同时删除已生成的HTML静态文件
//	@Override
//	public void delete(Article article) {
//		List<String> htmlFilePathList = article.getHtmlFilePathList();
//		if (htmlFilePathList != null && htmlFilePathList.size() > 0) {
//			for (String htmlFilePath : htmlFilePathList) {
//				File htmlFile = new File(ServletActionContext.getServletContext().getRealPath(htmlFilePath));
//				if (htmlFile.exists()) {
//					htmlFile.delete();
//				}
//			}
//		}
//		articleDao.delete(article);
//	}

	// 重写方法，删除对象的同时删除已生成的HTML静态文件
	@Override
	public void delete(String id) {
		Article article = articleDao.load(id);
		this.delete(article);
	}

	// 重写方法，删除对象的同时删除已生成的HTML静态文件
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			this.delete(id);
		}
	}

	
	@Override
	public String save(Article article) {
		article.setPageCount(0);
		String id = articleDao.save(article);
		articleDao.flush();
		articleDao.evict(article);
		article = articleDao.load(id);
		return id;
	}

	@Override
	public List<Article> getHelpArticleList(int maxResults) {
		return articleDao.getHelpArticleList(maxResults);
	}
	
	
	// 重写方法，保存对象的同时生成HTML静态文件
//	@Override
//	public String save(Article article) {
//		article.setPageCount(0);
//		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ARTICLE_CONTENT);
//		String htmlFilePath = htmlConfig.getHtmlFilePath();
//		article.setHtmlFilePath(htmlFilePath);
//		String id = articleDao.save(article);
//		articleDao.flush();
//		articleDao.evict(article);
//		article = articleDao.load(id);
//		if (article.getIsPublication()) {
//			htmlService.articleContentBuildHtml(article);
//		}
//		return id;
//	}

	// 重写方法，更新对象的同时重新生成HTML静态文件
//	@Override
//	public void update(Article article) {
//		List<String> htmlFilePathList = article.getHtmlFilePathList();
//		if (htmlFilePathList != null && htmlFilePathList.size() > 0) {
//			for (String htmlFilePath : htmlFilePathList) {
//				File htmlFile = new File(ServletActionContext.getServletContext().getRealPath(htmlFilePath));
//				if (htmlFile.exists()) {
//					htmlFile.delete();
//				}
//			}
//		}
//		String id = article.getId();
//		articleDao.update(article);
//		articleDao.flush();
//		articleDao.evict(article);
//		article = articleDao.load(id);
//		if (article.getIsPublication()) {
//			htmlService.articleContentBuildHtml(article);
//		}
//	}
}

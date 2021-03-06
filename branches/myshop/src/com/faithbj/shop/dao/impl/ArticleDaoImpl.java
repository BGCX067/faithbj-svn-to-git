package com.faithbj.shop.dao.impl;

import java.util.Date;
import java.util.List;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.dao.ArticleDao;
import com.faithbj.shop.entity.Article;
import com.faithbj.shop.entity.ArticleCategory;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 文章
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article, String> implements ArticleDao {

	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(ArticleCategory articleCategory) {
		String hql = "from Article as article where article.isPublication = ? and article.articleCategory.path like ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory.getPath() + "%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(int firstResult, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(ArticleCategory articleCategory, int firstResult, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.articleCategory.path like ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory.getPath() + "%").setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticleList(Date beginDate, Date endDate, int firstResult, int maxResults) {
		if (beginDate != null && endDate == null) {
			String hql = "from Article as article where article.isPublication = ? and article.createDate >= ? order by article.isTop desc, article.createDate desc";
			return getSession().createQuery(hql).setParameter(0, true).setParameter(1, beginDate).setFirstResult(firstResult).setMaxResults(maxResults).list();
		} else if (endDate != null && beginDate == null) {
			String hql = "from Article as article where article.isPublication = ? and article.createDate <= ? order by article.isTop desc, article.createDate desc";
			return getSession().createQuery(hql).setParameter(0, true).setParameter(1, endDate).setFirstResult(firstResult).setMaxResults(maxResults).list();
		} else if (endDate != null && beginDate != null) {
			String hql = "from Article as article where article.isPublication = ? and article.createDate >= ? and article.createDate <= ? order by article.isTop desc, article.createDate desc";
			return getSession().createQuery(hql).setParameter(0, true).setParameter(1, beginDate).setParameter(2, endDate).setFirstResult(firstResult).setMaxResults(maxResults).list();
		} else {
			String hql = "from Article as article where article.isPublication = ? order by article.isTop desc, article.createDate desc";
			return getSession().createQuery(hql).setParameter(0, true).setFirstResult(firstResult).setMaxResults(maxResults).list();
		}
	}
	
	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		detachedCriteria.createAlias("articleCategory", "articleCategory");
		detachedCriteria.add(Restrictions.or(Restrictions.eq("articleCategory", articleCategory), Restrictions.like("articleCategory.path", articleCategory.getPath() + "%")));
		detachedCriteria.add(Restrictions.eq("isPublication", true));
		return super.findByPager(pager, detachedCriteria);
	}

	@SuppressWarnings("unchecked")
	public List<Article> getRecommendArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, true).list();
	}

	@SuppressWarnings("unchecked")
	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, true).setParameter(2, articleCategory).setParameter(3, articleCategory.getPath() + "%").list();
	}

	@SuppressWarnings("unchecked")
	public List<Article> getHotArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.hits desc, article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).list();
	}

	@SuppressWarnings("unchecked")
	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.hits desc, article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory).setParameter(2, articleCategory.getPath() + "%").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getNewArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).list();
	}

	@SuppressWarnings("unchecked")
	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory).setParameter(2, articleCategory.getPath() + "%").list();
	}
	
	// 根据isTop、createDate进行排序
	@Override
	@SuppressWarnings("unchecked")
	public List<Article> getAll() {
		String hql = "from Article as article order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).list();
	}

}

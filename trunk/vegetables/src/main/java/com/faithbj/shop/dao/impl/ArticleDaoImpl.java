package com.faithbj.shop.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.ArticleDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;

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

@SuppressWarnings("unchecked")
@Repository("articleDao")
public class ArticleDaoImpl extends BaseDaoImpl<Article, String> implements ArticleDao {

	public List<Article> getArticleList(ArticleCategory articleCategory) {
		String hql = "from Article as article where article.isPublication = ? and article.articleCategory.path like ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory.getPath() + "%").list();
	}
	
	public List<Article> getArticleList(int firstResult, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
	public List<Article> getArticleList(ArticleCategory articleCategory, int firstResult, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.articleCategory.path like ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory.getPath() + "%").setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
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

	public List<Article> getRecommendArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, true).list();
	}

	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, true).setParameter(2, articleCategory).setParameter(3, articleCategory.getPath() + "%").list();
	}

	public List<Article> getHotArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.hits desc, article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setFirstResult(0).setMaxResults(maxResults).list();
	}

	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.hits desc, article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory).setParameter(2, articleCategory.getPath() + "%").list();
	}
	
	public List<Article> getNewArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setFirstResult(0).setMaxResults(maxResults).list();
	}

	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articleCategory = ? or article.articleCategory.path like ?) order by article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setParameter(1, articleCategory).setParameter(2, articleCategory.getPath() + "%").setFirstResult(0).setMaxResults(maxResults).list();
	}
	
	// 根据isTop、createDate进行排序
	@Override
	public List<Article> getAll() {
		String hql = "from Article as article order by article.isTop desc, article.createDate desc";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Article> getHelpArticleList(int maxResults) {
		String hql = "from Article as article where article.isHelp = ? order by article.createDate desc";
		return getSession().createQuery(hql).setParameter(0, true).setFirstResult(0).setMaxResults(maxResults).list();
	}

}

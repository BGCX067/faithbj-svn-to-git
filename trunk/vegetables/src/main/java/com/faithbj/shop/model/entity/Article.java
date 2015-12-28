package com.faithbj.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

/**
 * 实体类 - 文章
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class Article extends BaseEntity {

	private static final long serialVersionUID = 1475773294701585482L;

	public static final int MAX_RECOMMEND_ARTICLE_LIST_COUNT = 20;// 推荐文章列表最大文章数
	public static final int MAX_HOT_ARTICLE_LIST_COUNT = 10;// 热点文章列表最大文章数
	public static final int MAX_NEW_ARTICLE_LIST_COUNT = 20;// 最新文章列表最大文章数
	public static final int MAX_NEW_NEWS_LIST_COUNT = 10;// 最新新闻列表最大文章数
	public static final int MAX_PAGE_CONTENT_COUNT = 2000;// 内容分页每页最大字数
	public static final int DEFAULT_ARTICLE_LIST_PAGE_SIZE = 20;// 文章列表默认每页显示数

	private String title;// 标题
	private String author;// 作者
	private String content;// 内容
	private String metaKeywords;// 页面关键词
	private String metaDescription;// 页面描述
	private Boolean isPublication;// 是否发布
	private Boolean isTop;// 是否置顶
	private Boolean isRecommend;// 是否为推荐文章
	/**
	 * 是否为帮助中心文章,true为是，false或null为普通新闻
	 */
	private Boolean isHelp;
	
	private Integer pageCount;// 文章页数
	private String htmlFilePath;// HTML静态文件路径（首页）
	private Integer hits;// 点击数
	
	private ArticleCategory articleCategory;// 文章分类

//	@SearchableProperty(store = Store.YES)
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}


	
//	@SearchableProperty(index = Index.NO, store = Store.YES)
	public String getAuthor() {
		return author;
	}

	
//	@SearchableProperty(store = Store.YES)
	@Column(length = 10000, nullable = false)
	public String getContent() {
		return content;
	}


	@Column(length = 5000)
	public String getMetaKeywords() {
		return metaKeywords;
	}


	@Column(length = 5000)
	public String getMetaDescription() {
		return metaDescription;
	}


//	@SearchableProperty(store = Store.NO)
	@Column
	public Boolean getIsPublication() {
		return isPublication;
	}


//	@SearchableProperty(store = Store.NO)
	@Column
	public Boolean getIsTop() {
		return isTop;
	}
	@Column
	public Boolean getIsHelp() {
		return isHelp;
	}
//	@SearchableProperty(store = Store.NO)
	@Column
	public Boolean getIsRecommend() {
		return isRecommend;
	}


	@Column(nullable = false)
	public Integer getPageCount() {
		return pageCount;
	}


//	@SearchableProperty(store = Store.YES)
	@JoinColumn(nullable = false, updatable = false)
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	
	@Column(nullable = false)
	public Integer getHits() {
		return hits;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public void setIsPublication(Boolean isPublication) {
//		if(null==isPublication)
//			isPublication=false;
		this.isPublication = isPublication;
	}
	public void setIsTop(Boolean isTop) {
//		if(null==isTop)
//			isTop=false;
		this.isTop = isTop;
	}
	public void setIsRecommend(Boolean isRecommend) {
//		if(null==isRecommend)
//			isRecommend=false;
		this.isRecommend = isRecommend;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public void setHtmlFilePath (String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	public void setIsHelp(Boolean isHelp) {
		this.isHelp = isHelp;
	}


	
	

	// 获取HTML静态文件路径
	@Transient
	public List<String> getHtmlFilePathList() {
		ArrayList<String> htmlFilePathList = new ArrayList<String>();
		String prefix = StringUtils.substringBeforeLast(htmlFilePath, ".");
		String extension = StringUtils.substringAfterLast(htmlFilePath, ".");
		htmlFilePathList.add(htmlFilePath);
		for (int i = 2; i <= pageCount; i++) {
			htmlFilePathList.add(prefix + "_" + i + "." + extension);
		}
		return htmlFilePathList;
	}

	// 获取文章内容文本（清除HTML标签后的文本内容）
	@Transient
	public String getContentText() {
		String result = "";
//		try {
//			Parser parser = Parser.createParser(content, "UTF-8");
//			TextExtractingVisitor textExtractingVisitor = new TextExtractingVisitor();
//			parser.visitAllNodesWith(textExtractingVisitor);
//			result = textExtractingVisitor.getExtractedText();
//		} catch (ParserException e) {
//			e.printStackTrace();
//		}
		return result;
	}

	// 获取文章分页内容
	@Transient
	public List<String> getPageContentList() {
		List<String> pageContentList = new ArrayList<String>();
		// 如果文章内容长度小于等于每页最大字符长度则直接返回所有字符
		if (content.length() <= Article.MAX_PAGE_CONTENT_COUNT) {
			pageContentList.add(content);
			return pageContentList;
		}
//		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);// TABLE
//		NodeFilter divFilter = new NodeClassFilter(Div.class);// DIV
//		NodeFilter paragraphFilter = new NodeClassFilter(ParagraphTag.class);// P
//		NodeFilter bulletListFilter = new NodeClassFilter(BulletList.class);// UL
//		NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);// LI
//		NodeFilter definitionListFilter = new NodeClassFilter(DefinitionList.class);// DL
//		NodeFilter DefinitionListBulletFilter = new NodeClassFilter(DefinitionListBullet.class);// DD
//
//		OrFilter orFilter = new OrFilter();
//		orFilter.setPredicates(new NodeFilter[] { paragraphFilter, divFilter, tableFilter, bulletListFilter, bulletFilter, definitionListFilter, DefinitionListBulletFilter });
//		List<Integer> indexList = new ArrayList<Integer>();
//		// 按每页最大字符长度分割文章内容
//		List<String> contentList = CommonUtil.splitString(content, Article.MAX_PAGE_CONTENT_COUNT);
//		for (int i = 0; i < contentList.size() - 1; i++) {
//			try {
//				String currentContent = contentList.get(i);
//				Parser parser = Parser.createParser(currentContent, "UTF-8");
//				NodeList nodeList = parser.parse(orFilter);
//				if (nodeList.size() > 0) {
//					// 若在此段内容中查找到相关标签，则记录最后一个标签的索引位置
//					Node node = nodeList.elementAt(nodeList.size() - 1);
//					indexList.add(i * Article.MAX_PAGE_CONTENT_COUNT + node.getStartPosition());
//				} else {
//					// 若在此段内容中未找到任何相关标签，则查找相关标点符号，并记录最后一个标点符号的索引位置
//					String regEx = "\\.|。|\\!|！|\\?|？";
//					Pattern pattern = Pattern.compile(regEx);
//					Matcher matcher = pattern.matcher(currentContent);
//					if (matcher.find()) {
//						int endIndex = 0;
//						while (matcher.find()) {
//							endIndex = matcher.end();
//						}
//						indexList.add(i * Article.MAX_PAGE_CONTENT_COUNT + endIndex);
//					} else {
//						indexList.add((i + 1) * Article.MAX_PAGE_CONTENT_COUNT);
//					}
//				}
//			} catch (ParserException e) {
//				e.printStackTrace();
//			}
//		}
//		for (int i = 0; i <= indexList.size(); i++) {
//			String pageContent = "";
//			if (i == 0) {
//				pageContent = content.substring(0, indexList.get(0));
//			} else if (i == indexList.size()) {
//				pageContent = content.substring(indexList.get(i - 1));
//			} else {
//				pageContent = content.substring(indexList.get(i - 1), indexList.get(i));
//			}
//			try {
//				// 对分割出的分页内容进行HTML标签补全
//				Parser parser = Parser.createParser(pageContent, "UTF-8");
//				NodeList nodeList = parser.parse(orFilter);
//				String contentResult = nodeList.toHtml();
//				if (StringUtils.isEmpty(contentResult)) {
//					contentResult = pageContent;
//				}
//				pageContentList.add(contentResult);
//			} catch (ParserException e) {
//				e.printStackTrace();
//			}
//		}
		return pageContentList;
	}

}

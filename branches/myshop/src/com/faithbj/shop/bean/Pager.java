package com.faithbj.shop.bean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Bean类 - 分页
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class Pager {
	
	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Integer pageNumber = 1;// 当前页码
	private Integer pageSize = 20;// 每页记录数
	private Integer totalCount = 0;// 总记录数
	private Integer pageCount = 0;// 总页数
	private String property;// 查找属性名称
	private String keyword;// 查找关键字
	
	private List<String> propertyList = null;
	private List<Object> keywordList = null;
	private List<String> compareTypeList = null;
	
	private String orderBy = "createDate";// 排序字段
	private OrderType orderType = OrderType.desc;// 排序方式
	private List list;// 数据List

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) throws UnsupportedEncodingException {
		if(keyword != null){
			this.keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
		}else{
			this.keyword = keyword;
		}
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<String> getPropertyList()
	{
		return propertyList;
	}

	public void setPropertyList(List<String> propertyList)
	{
		this.propertyList = propertyList;
	}

	public List<Object> getKeywordList()
	{
		return keywordList;
	}

	public void setKeywordList(List<Object> keywordList)
	{
		this.keywordList = keywordList;
	}

	public List<String> getCompareTypeList()
	{
		return compareTypeList;
	}

	public void setCompareTypeList(List<String> compareTypeList)
	{
		this.compareTypeList = compareTypeList;
	}

}

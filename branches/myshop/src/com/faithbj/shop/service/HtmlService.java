package com.faithbj.shop.service;

import java.util.Map;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.shop.entity.Article;
import com.faithbj.shop.entity.Product;

/**
 * Service接口 - 生成静态
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface HtmlService {
	
	/**
	 * 根据Freemarker模板文件路径、Map数据生成HTML静态文件
	 * 
	 * @param templateFilePath
	 *            Freemarker模板文件路径
	 *            
	 * @param htmlFilePath
	 *            生成HTML静态文件存放路径
	 * 
	 * @param data
	 *            Map数据
	 * 
	 */
	public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data);
	
	/**
	 * 生成baseJavascript文件
	 * 
	 */
	public void baseJavascriptBuildHtml();
	
	/**
	 * 生成首页HTML静态文件
	 * 
	 */
	public void indexBuildHtml();
	
	/**
	 * 生成登录HTML静态文件
	 * 
	 */
	public void loginBuildHtml();
	
	/**
	 * 根据Article对象生成文章内容HTML静态文件
	 * 
	 * @param article
	 *            文章
	 */
	public void articleContentBuildHtml(Article article);
	
	/**
	 * 根据Product对象生成产品内容HTML静态文件
	 * 
	 * @param product
	 *            商品
	 */
	public void productContentBuildHtml(Product product);
	
	/**
	 * 错误页HTML静态文件
	 */
	public void errorPageBuildHtml();
	
	/**
	 * 权限错误页HTML静态文件
	 */
	public void errorPageAccessDeniedBuildHtml();
	
	/**
	 * 错误页500 HTML静态文件
	 */
	public void errorPage500BuildHtml();
	
	/**
	 * 错误页404 HTML静态文件
	 */
	public void errorPage404BuildHtml();
	
	/**
	 * 错误页403 HTML静态文件
	 */
	public void errorPage403BuildHtml();

//	public void fieldBlockContentBuildHtml(FieldBlock fieldBlock);
	
}

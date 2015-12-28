package com.faithbj.shop.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.model.configuration.ProductImage;


/**
 * Service接口 - 商品图片
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface ProductImageService {
	
	/**
	 * 生成商品图片（包括原图、大图、小图、缩略图）
	 * 
	 * @param uploadProductImageFile
	 *            上传图片文件
	 * 
	 */
	public ProductImage buildProductImage(File uploadProductImageFile);
	
	/**
	 * 生成商品图片（包括原图、大图、小图、缩略图）
	 * 
	 * @param uploadProductImageFile
	 *            上传图片文件
	 * 
	 */
	public ProductImage buildProductImage(MultipartFile uploadProductImageFile);
	
	/**
	 * 根据ProductImage对象删除图片文件
	 * 
	 * @param productImage
	 *            ProductImage
	 * 
	 */
	public void deleteFile(ProductImage productImage);

}

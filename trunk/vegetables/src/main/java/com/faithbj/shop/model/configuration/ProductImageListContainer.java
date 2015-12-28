package com.faithbj.shop.model.configuration;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductImageListContainer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7163371357820354669L;
	
	private List<MultipartFile> productImageFileList;

	public List<MultipartFile> getProductImageFileList() {
		return productImageFileList;
	}

	public void setProductImageFileList(List<MultipartFile> productImageFileList) {
		this.productImageFileList = productImageFileList;
	} 
	
	
}

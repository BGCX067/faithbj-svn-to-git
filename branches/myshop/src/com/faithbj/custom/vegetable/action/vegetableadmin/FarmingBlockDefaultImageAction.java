package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.entity.FarmingBlockDefaultImage;
import com.faithbj.custom.vegetable.service.FarmingBlockDefaultImageService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.bean.SystemConfig;
import com.faithbj.shop.service.ProductImageService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 耕种地块默认图片Action
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-03
 * @version 1.0
 */

@ParentPackage("admin")
public class FarmingBlockDefaultImageAction extends BaseAdminAction
{
	private static final long serialVersionUID = 1L;

	@Resource
	private FarmingBlockDefaultImageService farmingBlockDefaultImageService = null;
	
	private FarmingBlockDefaultImage farmingBlockDefaultImage = null;

	private File[] productImages;
	private String[] productImagesFileName;
	private String[] productImageParameterTypes;
	private String[] productImageIds;
	
	@Resource
	private ProductImageService productImageService;

	//列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = this.farmingBlockDefaultImageService.findByPager(pager);
		return LIST;
	}

	// 编辑
	public String edit() {
		this.farmingBlockDefaultImage = this.farmingBlockDefaultImageService.load(id);
		return INPUT;
	}
	
	public String add() {
		return INPUT;
	}

	// 删除
	public String delete() {
		this.farmingBlockDefaultImageService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}
	
	//添加
	@InputConfig(resultName = "error")
	public String save() {
		if (productImages != null) {
			String allowedUploadImageExtension = getSystemConfig().getAllowedUploadImageExtension().toLowerCase();
			if (StringUtils.isEmpty(allowedUploadImageExtension)) {
				addActionError("不允许上传图片文件!");
				return ERROR;
			}
			for(int i = 0; i < productImages.length; i ++) {
				String productImageExtension =  StringUtils.substringAfterLast(productImagesFileName[i], ".").toLowerCase();
				String[] imageExtensionArray = allowedUploadImageExtension.split(SystemConfig.EXTENSION_SEPARATOR);
				if (!ArrayUtils.contains(imageExtensionArray, productImageExtension)) {
					addActionError("只允许上传图片文件类型: " + allowedUploadImageExtension + "!");
					return ERROR;
				}
				if (getSystemConfig().getUploadLimit() != 0 && productImages[i].length() > getSystemConfig().getUploadLimit() * 1024) {
					addActionError("此上传文件大小超出限制!");
					return ERROR;
				}
			}
		}
		List<ProductImage> productImageList = new ArrayList<ProductImage>();
		if (productImages != null && productImages.length > 0) {
			for(int i = 0; i < productImages.length; i ++) {
				ProductImage productImage = productImageService.buildProductImage(productImages[i]);
				productImageList.add(productImage);
			}
		}
		this.farmingBlockDefaultImage.setProductImageList(productImageList);
		
		farmingBlockDefaultImageService.save(farmingBlockDefaultImage);
		redirectionUrl = "farming_block_default_image!list.action";
		return SUCCESS;
	}

	//更新
	@InputConfig(resultName = "error")
	public String update() {
		
		FarmingBlockDefaultImage persistent = farmingBlockDefaultImageService.load(id);

		if (productImages != null) {
			String allowedUploadImageExtension = getSystemConfig().getAllowedUploadImageExtension().toLowerCase();
			if (StringUtils.isEmpty(allowedUploadImageExtension)) {
				addActionError("不允许上传图片文件!");
				return ERROR;
			}
			for(int i = 0; i < productImages.length; i ++) {
				String productImageExtension =  StringUtils.substringAfterLast(productImagesFileName[i], ".").toLowerCase();
				String[] imageExtensionArray = allowedUploadImageExtension.split(SystemConfig.EXTENSION_SEPARATOR);
				if (!ArrayUtils.contains(imageExtensionArray, productImageExtension)) {
					addActionError("只允许上传图片文件类型: " + allowedUploadImageExtension + "!");
					return ERROR;
				}
				if (getSystemConfig().getUploadLimit() != 0 && productImages[i].length() > getSystemConfig().getUploadLimit() * 1024) {
					addActionError("此上传文件大小超出限制!");
					return ERROR;
				}
			}
		}
		List<ProductImage> productImageList = new ArrayList<ProductImage>();
		if (productImageParameterTypes != null) {
			int index = 0;
			int productImageFileIndex = 0;
			int productImageIdIndex = 0;
			for (String parameterType : productImageParameterTypes) {
				if (StringUtils.equalsIgnoreCase(parameterType, "productImageFile")) {
					ProductImage destProductImage = productImageService.buildProductImage(productImages[productImageFileIndex]);
					productImageList.add(destProductImage);
					productImageFileIndex ++;
					index ++;
				} else if (StringUtils.equalsIgnoreCase(parameterType, "productImageId")) {
					ProductImage destProductImage = persistent.getProductImage(productImageIds[productImageIdIndex]);
					productImageList.add(destProductImage);
					productImageIdIndex ++;
					index ++;
				}
			}
		}
		
		List<ProductImage> persistentProductImageList = persistent.getProductImageList();
		if (persistentProductImageList != null) {
			for (ProductImage persistentProductImage : persistentProductImageList) {
				if (!productImageList.contains(persistentProductImage)) {
					productImageService.deleteFile(persistentProductImage);
				}
			}
		}
		
		farmingBlockDefaultImage.setProductImageList(productImageList);
		BeanUtils.copyProperties(farmingBlockDefaultImage, persistent, new String[] {"id", "createDate", "modifyDate"});		
		
		farmingBlockDefaultImageService.update(persistent);
		redirectionUrl = "farming_block_default_image!list.action";
		return SUCCESS;
	}
	
	public FarmingBlockDefaultImage getFarmingBlockDefaultImage()
	{
		return farmingBlockDefaultImage;
	}
	public void setFarmingBlockDefaultImage(FarmingBlockDefaultImage farmingBlockDefaultImage)
	{
		this.farmingBlockDefaultImage = farmingBlockDefaultImage;
	}

	public File[] getProductImages()
	{
		return productImages;
	}

	public void setProductImages(File[] productImages)
	{
		this.productImages = productImages;
	}

	public String[] getProductImagesFileName()
	{
		return productImagesFileName;
	}

	public void setProductImagesFileName(String[] productImagesFileName)
	{
		this.productImagesFileName = productImagesFileName;
	}

	public String[] getProductImageParameterTypes()
	{
		return productImageParameterTypes;
	}

	public void setProductImageParameterTypes(String[] productImageParameterTypes)
	{
		this.productImageParameterTypes = productImageParameterTypes;
	}

	public String[] getProductImageIds()
	{
		return productImageIds;
	}

	public void setProductImageIds(String[] productImageIds)
	{
		this.productImageIds = productImageIds;
	}
}

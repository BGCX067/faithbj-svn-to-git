package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.service.FarmingBlockService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.bean.SystemConfig;
import com.faithbj.shop.service.ProductImageService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 
 * <p>耕种块管理Action</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-27
 * @version 1.0
 */

@ParentPackage("admin")
public class FarmingBlockAction extends BaseAdminAction
{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private FarmingBlockService farmingBlockService = null;
	
	@Resource
	private ProductImageService productImageService;
	
	private FarmingBlock farmingBlock = null;
	
	private File[] productImages;
	private String[] productImagesFileName;
	private String[] productImageParameterTypes;
	private String[] productImageIds;

	private String farmingBlockStatus = null;


	//弹出
	@InputConfig(resultName = "error")
	public String popup() {
		pager = this.farmingBlockService.findByPager(pager);
		return "popup";
	}

	//列表
	@InputConfig(resultName = "error")
	public String list() {
		
		this.convertPager(pager);
		
		if (StringUtils.isNotBlank(this.farmingBlockStatus))
		{
			if (pager == null)
			{
				pager = new Pager();
			}
			
			List<String> propertyList = new ArrayList<String>(Arrays.asList("rendBlock.isTrusteeFeeEnabled"));
			List<Object> keyworkdList = new ArrayList<Object>(Arrays.<Object>asList(true));
			List<String> compareTypeList = new ArrayList<String>(Arrays.asList("eq"));
			
			propertyList.add("status");
			keyworkdList.add(this.farmingBlockStatus);
			compareTypeList.add("eq");
			
			pager.setPropertyList(propertyList);
			pager.setKeywordList(keyworkdList);
			pager.setCompareTypeList(compareTypeList);
		}
		
		pager = this.farmingBlockService.findByPager(pager);
		return LIST;
	}
	
	// 编辑
	public String edit() {
		this.farmingBlock = this.farmingBlockService.load(id);
		return INPUT;
	}

	// 删除
	public String delete() {
		this.farmingBlockService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}
	
	//添加
	public String add() {
		return INPUT;
	}
	
	//添加
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
		this.farmingBlock.setProductImageList(productImageList);
		
		this.farmingBlockService.save(this.farmingBlock);
		redirectionUrl = "farming_block!list.action";
		return SUCCESS;
	}
	
	//更新
	public String update() {
		FarmingBlock persistent = this.farmingBlockService.load(this.id);
		
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
		this.farmingBlock.setProductImageList(productImageList);
		
		if ("".equals(this.farmingBlock.getMember().getId()))
		{
			this.farmingBlock.setMember(null);
			persistent.setMember(null);
		}
		if ("".equals(this.farmingBlock.getSeed().getId()))
		{
			this.farmingBlock.setSeed(null);
			persistent.setSeed(null);
		}
		
		this.farmingBlock.setOperateTime(new Date());
		this.farmingBlock.setOperateUser(((String) getSession("SPRING_SECURITY_LAST_USERNAME")).toLowerCase());
		
		BeanUtils.copyProperties(this.farmingBlock, persistent, new String[] {"id", "createDate", "modifyDate"});
		
		this.farmingBlockService.update(persistent);
		
		redirectionUrl = "farming_block!list.action";
		return SUCCESS;
	}

	private void convertPager(Pager pager)
	{
		if (pager != null)
		{
			if ("status".equals(pager.getProperty()))
			{
				String keyword = pager.getKeyword();
				if (StringUtils.isNotBlank(keyword))
				{
					if ("空闲".equals(keyword))
					{
						keyword = "0";
					}
					else if ("播种".equals(keyword))
					{
						keyword = "2";
					}
					else if ("生长".equals(keyword))
					{
						keyword = "4";
					}
					else if ("收获".equals(keyword))
					{
						keyword = "6";
					}
					else if ("清理".equals(keyword))
					{
						keyword = "8";
					}
					try {
						pager.setKeyword(keyword);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public FarmingBlock getFarmingBlock()
	{
		return farmingBlock;
	}
	public void setFarmingBlock(FarmingBlock farmingBlock)
	{
		this.farmingBlock = farmingBlock;
	}

	public String[] getProductImagesFileName() {
		return productImagesFileName;
	}

	public void setProductImagesFileName(String[] productImagesFileName) {
		this.productImagesFileName = productImagesFileName;
	}

	public String[] getProductImageParameterTypes() {
		return productImageParameterTypes;
	}

	public void setProductImageParameterTypes(String[] productImageParameterTypes) {
		this.productImageParameterTypes = productImageParameterTypes;
	}

	public String[] getProductImageIds() {
		return productImageIds;
	}

	public void setProductImageIds(String[] productImageIds) {
		this.productImageIds = productImageIds;
	}

	public File[] getProductImages() {
		return productImages;
	}

	public void setProductImages(File[] productImages) {
		this.productImages = productImages;
	}

	public String getFarmingBlockStatus()
	{
		return farmingBlockStatus;
	}

	public void setFarmingBlockStatus(String farmingBlockStatus)
	{
		this.farmingBlockStatus = farmingBlockStatus;
	}

}

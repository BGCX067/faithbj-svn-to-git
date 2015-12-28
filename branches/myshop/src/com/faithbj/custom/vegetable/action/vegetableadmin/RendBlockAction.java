package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.constant.FieldConstants;
import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.entity.RendBlock;
import com.faithbj.custom.vegetable.enums.FarmingStatus;
import com.faithbj.custom.vegetable.service.RendBlockService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.bean.SystemConfig;
import com.faithbj.shop.service.ProductImageService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 前台Action类 - 土地
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-26
 * @version 1.0
 */

@ParentPackage("admin")
public class RendBlockAction extends BaseAdminAction{

	private static final long serialVersionUID = -4969421249817468001L;
	
	private File[] productImages;
	private String[] productImagesFileName;
	private String[] productImageParameterTypes;
	private String[] productImageIds;
	@Resource
	private ProductImageService productImageService;
	
	private RendBlock rendBlock;
	@Resource
	private RendBlockService rendBlockService;
	
	//添加
	public String add(){
		return INPUT;
	}
	
	/**
	 * 生成耕种块
	 * @return
	 */
	public String generateFarmingBlock()
	{
		String msg = null;
		
		if (StringUtils.isNotEmpty(this.id))
		{
			RendBlock rendBlock = this.rendBlockService.get(this.id);
			List<FarmingBlock> farmingBlockList = rendBlock.getFarmingBlockList();
			if (farmingBlockList != null && !farmingBlockList.isEmpty())
			{
				msg = "该租赁块下已存在耕种块，无法生成";
			}
			else
			{
				Integer area = rendBlock.getArea();
				if (area != null && area > 0)
				{
					if (area % FieldConstants.DefaultFarmingBlockArea == 0)
					{
						farmingBlockList = new ArrayList<FarmingBlock>();
						int size = area / FieldConstants.DefaultFarmingBlockArea;
						for (int i = 1; i <= size; i++)
						{
							FarmingBlock farmingBlock = new FarmingBlock();
							
							farmingBlock.setArea(FieldConstants.DefaultFarmingBlockArea);
							farmingBlock.setCode(rendBlock.getCode() + "_" + i);
							farmingBlock.setRendBlock(rendBlock);
							farmingBlock.setRendBlockCode(rendBlock.getCode());
							farmingBlock.setStatus(FarmingStatus.Free.getValue());
							
							farmingBlockList.add(farmingBlock);
						}
						
						rendBlock.setFarmingBlockList(farmingBlockList);
						
						this.rendBlockService.save(rendBlock);
						
						msg = "生成成功，共生成" + size + "个耕种块";
						
					}
					else
					{
						msg = "租赁块面积不是" + FieldConstants.DefaultFarmingBlockArea + "的整数倍，无法自动生成";
					}
				}
			}
		}
		
		return ajaxText(msg);
	}
	
	
	//列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = this.rendBlockService.findByPager(pager);
		return LIST;
	}
	
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "rendBlock.code", message = "租赁土地编号不允许为空!")
			}
		)
		@InputConfig(resultName = "error")
	public String save() {
		if (rendBlockService.isExist("code", rendBlock.getCode())) {
			addActionError("租赁土地编号重复,请重新输入!");
			return ERROR;
		}
		if(rendBlock.getName() == null || "".equals(rendBlock.getName())){
			rendBlock.setName(rendBlock.getCode());
		}else{
			rendBlock.setName(rendBlock.getName());
		}
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
		rendBlock.setRelativeAddress(rendBlock.getRelativeAddress());
		rendBlock.setCode(rendBlock.getCode());
		rendBlock.setProductImageList(productImageList);
		rendBlockService.save(rendBlock);
		redirectionUrl = "rend_block!list.action";
		
		return SUCCESS;
	}

	// 是否已存在 ajax验证
	public String checkCode() {
		String oldValue = getParameter("oldValue");
		String newValue;
		try {
				newValue = new String(this.rendBlock.getCode().getBytes("ISO-8859-1"),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			newValue = "";
			return ajaxText("false");
		}
		if (this.rendBlockService.isUnique("code", oldValue, newValue)) {
			return ajaxText("true");
		} else {
			return ajaxText("false");
		}
	}
	
	//更新
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "rendBlock.code", message = "土地编号不允许为空!")
			}
		)
		@InputConfig(resultName = "error")
	public String update() {
		
		RendBlock persistent = rendBlockService.load(id);
		
		if (!rendBlockService.isUnique("code", persistent.getCode(), rendBlock.getCode())) {
			addActionError("租赁土地编号重复,请重新输入!");
			return ERROR;
		}
		
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
		rendBlock.setProductImageList(productImageList);
		
		if (StringUtils.isNotEmpty(this.rendBlock.getUsername()) && !this.rendBlock.getUsername().equals(persistent.getUsername()))
		{
			List<FarmingBlock> farmingBlockList = persistent.getFarmingBlockList();
			if (farmingBlockList != null && !farmingBlockList.isEmpty())
			{
				for (FarmingBlock farmingBlock : farmingBlockList)
				{
					farmingBlock.setMember(this.rendBlock.getMember());
					farmingBlock.setUsername(this.rendBlock.getUsername());
				}
			}
		}
		
		if ("".equals(this.rendBlock.getMember().getId()))
		{
			this.rendBlock.setMember(null);
			persistent.setMember(null);
		}
		
		System.out.println("*******************************************");
		System.out.println(this.rendBlock.getIsDeliveryFeeEnabled());
		System.out.println(this.rendBlock.getIsMarketable());
		System.out.println(this.rendBlock.getIsTrusteeFeeEnabled());
		
		BeanUtils.copyProperties(rendBlock, persistent, new String[] {"id", "createDate","code", "farmingBlockList"});
		
		rendBlockService.update(persistent);
		redirectionUrl = "rend_block!list.action";
		return SUCCESS;
	}
	
	// 编辑
	public String edit() {
		rendBlock = rendBlockService.load(id);
		return INPUT;
	}
	
	// 删除
	public String delete() {
		this.rendBlockService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
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

	public void setRendBlock(RendBlock rendBlock) {
		this.rendBlock = rendBlock;
	}

	public RendBlock getRendBlock() {
		return rendBlock;
	}
	
}

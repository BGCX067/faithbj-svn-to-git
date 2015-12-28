package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.util.DateJsonValueProcessor;
import com.faithbj.custom.util.LoggerUtil;
import com.faithbj.custom.vegetable.constant.FieldConstants;
import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.entity.RendBlock;
import com.faithbj.custom.vegetable.enums.FarmingStatus;
import com.faithbj.custom.vegetable.service.FieldBlockService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.bean.SystemConfig;
import com.faithbj.shop.service.ProductImageService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * <p>土地块管理Action</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-04
 * @version 1.0
 */

@ParentPackage("admin")
public class FieldBlockAction extends BaseAdminAction
{
	private static final long serialVersionUID = -3550589296132171860L;

	@Resource
	private FieldBlockService fieldBlockService = null;
	
	private FieldBlock fieldBlock = null;
	private String fieldblockid;
	private File[] productImages;
	private String[] productImagesFileName;
	private String[] productImageParameterTypes;
	private String[] productImageIds;
	@Resource
	private ProductImageService productImageService;
	
	/**
	 * 生成租赁块
	 * @return
	 */
	public String generateRendBlock()
	{
		String msg = null;
		
		this.info("[土地模块]生成承包地块，土地id：" + this.fieldblockid + "，用户：" + this.getAdmin());
		
		if (StringUtils.isNotEmpty(this.fieldblockid))
		{
			FieldBlock fieldBlock = this.fieldBlockService.get(this.fieldblockid);
			
			if (fieldBlock != null)
			{
				this.info("[土地模块]生成承包地块，土地代码：" + fieldBlock.getCode());
				
				List<RendBlock> rendBlockList = fieldBlock.getRendBlockList();
				if (rendBlockList != null && !rendBlockList.isEmpty())
				{
					this.info("[土地模块]生成承包地块，土地代码：" + fieldBlock.getCode() + "，该土地块下已存在租赁块，无法生成");
					
					msg = "该土地块下已存在租赁块，无法生成";
				}
				else
				{
					Integer area = fieldBlock.getArea();
					if (area != null && area > 0)
					{
						if (area % FieldConstants.DefaultRendBlockArea == 0)
						{
							rendBlockList = new ArrayList<RendBlock>();
							int size = area / FieldConstants.DefaultRendBlockArea;
							for (int i = 1; i <= size; i++)
							{
								RendBlock rendBlock = new RendBlock();
								
								String name = "土地块[" + fieldBlock.getCode() + "]下的第" + i + "个租赁块";
								
								rendBlock.setArea(FieldConstants.DefaultRendBlockArea);
								rendBlock.setCode(fieldBlock.getCode() + "_" + i);
								rendBlock.setFieldBlock(fieldBlock);
								rendBlock.setIsDeliveryFeeEnabled(true);
								rendBlock.setIsMarketable(true);
								rendBlock.setIsTrusteeFeeEnabled(true);
								rendBlock.setName(name);
								rendBlock.setDescription(name);
								rendBlock.setRelativeAddress(String.valueOf(i));
								rendBlock.setFieldBlockCode(fieldBlock.getCode());
								rendBlock.setProductImageListStore(fieldBlock.getProductImageListStore());
								
								List<FarmingBlock> farmingBlockList = new ArrayList<FarmingBlock>();
								int farmingSize = FieldConstants.DefaultRendBlockArea / FieldConstants.DefaultFarmingBlockArea;
								for (int j = 1; j <= farmingSize; j++)
								{
									FarmingBlock farmingBlock = new FarmingBlock();
									farmingBlock.setRendBlock(rendBlock);
									farmingBlock.setCode(rendBlock.getCode() + "_" + j);
									farmingBlock.setStatus(FarmingStatus.Free.getValue());
									farmingBlock.setRendBlockCode(rendBlock.getCode());
									farmingBlock.setArea(FieldConstants.DefaultFarmingBlockArea);
									farmingBlockList.add(farmingBlock);
								}
								
								rendBlock.setFarmingBlockList(farmingBlockList);
								
								rendBlockList.add(rendBlock);
							}
							fieldBlock.setStore(size);
							
							fieldBlock.setRendBlockList(rendBlockList);
							this.fieldBlockService.save(fieldBlock);
							
							msg = "生成成功，共生成" + size + "个租赁块";
							
							this.info("[土地模块]生成承包地块，土地代码：" + fieldBlock.getCode() + "，生成成功，共生成" + size + "个租赁块");
							
						}
						else
						{
							msg = "土地面积不是" + FieldConstants.DefaultRendBlockArea + "的整数倍，无法自动生成";
							
							this.info("[土地模块]生成承包地块，土地代码：" + fieldBlock.getCode() + "，土地面积不是" + FieldConstants.DefaultRendBlockArea + "的整数倍，无法自动生成");
						}
					}
				}
			}
		}
		
		return ajaxText(msg);
	}
	
	//弹出
	@InputConfig(resultName = "error")
	public String popup() {
		pager = this.fieldBlockService.findByPager(pager);
		return "popup";
	}
	
	//列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = this.fieldBlockService.findByPager(pager);
		return LIST;
	}
	
	// 编辑
	public String edit() {
		this.fieldBlock = this.fieldBlockService.load(id);
		return INPUT;
	}
	// 根据fieldBlockId获取已启用的商品属性JSON数据
	public String ajaxFieldBlock() {
		FieldBlock fieldBlock = fieldBlockService.load(fieldblockid);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss")); 
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
			  public boolean apply(Object source, String name, Object value) {
				  if (name.equals("rendBlockList")) {
			  return true;
			  } else {
			  return false;
			  }
			  }
			   
			  });
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
		JSONArray jsonArray = JSONArray.fromObject(fieldBlock,jsonConfig);
		return ajaxJson(jsonArray.toString());
	}
	//添加土地块
	public String add() {
		return INPUT;
	}
	
	//添加
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "fieldBlock.code", message = "土地编号不允许为空!"),
				@RequiredStringValidator(fieldName = "fieldBlock.address", message = "土地详细地址不允许为空!")
			}, 
			requiredFields = {
				@RequiredFieldValidator(fieldName = "fieldBlock.rent", message = "土地年租金不允许为空!"),
				@RequiredFieldValidator(fieldName = "fieldBlock.trusteeFee", message = "土地年托管费用不允许为空!"),
				@RequiredFieldValidator(fieldName = "fieldBlock.deliveryFee", message = "土地年配送费用不允许为空!")
			}
		)
		@InputConfig(resultName = "error")
	public String save() {
		
		this.info("新增土地：" + fieldBlock.getCode() + "，用户：" + this.getAdmin());
		
		if (fieldBlockService.isExist("code", fieldBlock.getCode())) {
			addActionError("土地编号重复,请重新输入!");
			return ERROR;
		}
		if(fieldBlock.getRent().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年租金不允许小于0");
			return ERROR;
		}
		if(fieldBlock.getTrusteeFee().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年托管费用不允许小于0");
			return ERROR;
		}
		if(fieldBlock.getDeliveryFee().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年配送费用不允许小于0");
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
		if (productImages != null && productImages.length > 0) {
			for(int i = 0; i < productImages.length; i ++) {
				ProductImage productImage = productImageService.buildProductImage(productImages[i]);
				productImageList.add(productImage);
			}
		}
		fieldBlock.setAddress(fieldBlock.getAddress());
		fieldBlock.setCode(fieldBlock.getCode());
		fieldBlock.setFieldType(fieldBlock.getFieldType());
		fieldBlock.setLongitudeLatitude(fieldBlock.getLongitudeLatitude());
		fieldBlock.setProductImageList(productImageList);
		fieldBlock.setTrusteeFee(fieldBlock.getTrusteeFee());
		fieldBlock.setRent(fieldBlock.getRent());
		fieldBlock.setDeliveryFee(fieldBlock.getDeliveryFee());
		fieldBlockService.save(fieldBlock);
		redirectionUrl = "field_block!list.action";
		return SUCCESS;
	}

	//更新
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "fieldBlock.code", message = "土地编号不允许为空!"),
				@RequiredStringValidator(fieldName = "fieldBlock.address", message = "土地详细地址不允许为空!")
			}, 
			requiredFields = {
				@RequiredFieldValidator(fieldName = "fieldBlock.rent", message = "土地年租金不允许为空!"),
				@RequiredFieldValidator(fieldName = "fieldBlock.trusteeFee", message = "土地年托管费用不允许为空!"),
				@RequiredFieldValidator(fieldName = "fieldBlock.deliveryFee", message = "土地年配送费用不允许为空!")
			}
		)
		@InputConfig(resultName = "error")
	public String update() {

		this.info("更新土地：" + fieldBlock.getCode() + "，用户：" + this.getAdmin());
		
		FieldBlock persistent = fieldBlockService.load(id);

		fieldBlock.setAddress(fieldBlock.getAddress());
		fieldBlock.setLongitudeLatitude(fieldBlock.getLongitudeLatitude());
		if(fieldBlock.getRent().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年租金不允许小于0");
			return ERROR;
		}
		fieldBlock.setRent(fieldBlock.getRent());
		if(fieldBlock.getTrusteeFee().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年托管费用不允许小于0");
			return ERROR;
		}
		fieldBlock.setTrusteeFee(fieldBlock.getTrusteeFee());
		if(fieldBlock.getDeliveryFee().compareTo(new BigDecimal("0")) < 0){
			addActionError("土地年配送费用不允许小于0");
			return ERROR;
		}
		fieldBlock.setDeliveryFee(fieldBlock.getDeliveryFee());
		
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
		
		fieldBlock.setProductImageList(productImageList);
		
		if (!StringUtils.trimToEmpty(fieldBlock.getProductImageListStore()).equals(StringUtils.trimToEmpty(persistent.getProductImageListStore())))
		{
			List<RendBlock> rendBlockList = persistent.getRendBlockList();
			if (rendBlockList != null && !rendBlockList.isEmpty())
			{
				for (RendBlock rendBlock : rendBlockList)
				{
					if (StringUtils.trimToEmpty(rendBlock.getProductImageListStore()).equals(StringUtils.trimToEmpty(persistent.getProductImageListStore())))
					{
						rendBlock.setProductImageListStore(fieldBlock.getProductImageListStore());
					}
				}
			}
		}
		
		BeanUtils.copyProperties(fieldBlock, persistent, new String[] {"id", "createDate", "modifyDate","code", "rendBlockList"});		
		
		fieldBlockService.update(persistent);
		redirectionUrl = "field_block!list.action";
		return SUCCESS;
	}
	
	// 删除
	public String delete() {
		
		this.info("删除土地：" + ids + "，用户：" + this.getAdmin());
		
		this.fieldBlockService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}

	// 是否已存在 ajax验证
	public String checkCode() {
		String oldValue = getParameter("oldValue");
		String newValue;
		try {
				newValue = new String(this.fieldBlock.getCode().getBytes("ISO-8859-1"),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			newValue = "";
			return ajaxText("false");
		}
		if (this.fieldBlockService.isUnique("code", oldValue, newValue)) {
			return ajaxText("true");
		} else {
			return ajaxText("false");
		}
	}
	
	protected String getAdmin()
	{
		return ((String) getSession("SPRING_SECURITY_LAST_USERNAME")).toLowerCase();
	}
	protected void info(String msg)
	{
		LoggerUtil.fieldInfo(msg);
	}
	
	public File[] getProductImages() {
		return productImages;
	}

	public void setProductImages(File[] productImages) {
		this.productImages = productImages;
	}

	public FieldBlock getFieldBlock()
	{
		return fieldBlock;
	}
	public void setFieldBlock(FieldBlock fieldBlock)
	{
		this.fieldBlock = fieldBlock;
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
	public String getFieldblockid() {
		return fieldblockid;
	}

	public void setFieldblockid(String fieldblockid) {
		this.fieldblockid = fieldblockid;
	}
}

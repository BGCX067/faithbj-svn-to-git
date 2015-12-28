package com.faithbj.shop.web.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.ProductImage;
import com.faithbj.shop.model.configuration.ProductImageListContainer;
import com.faithbj.shop.model.configuration.ProductParameter;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductAttribute;
import com.faithbj.shop.model.entity.ProductType;
import com.faithbj.shop.service.BrandService;
import com.faithbj.shop.service.ProductAttributeService;
import com.faithbj.shop.service.ProductCategoryService;
import com.faithbj.shop.service.ProductImageService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.service.ProductTypeService;
import com.faithbj.shop.utils.JsonUtil;
/**
 * 后台Controller类 - 商品
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-01
 * @version 1.0
 */

@Controller
@RequestMapping("/faith/goods")
public class AdminProductController extends BaseAdminController {

	private static final long serialVersionUID = -4433964283757192334L;
	
	private final String OPERRATE_RETURN_URL="/faith/goods/list";

//	private File[] productImages;
//	private String[] productImagesFileName;
//	private String[] productImageParameterTypes;
//	private String[] productImageIds;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductCategoryService productCategoryService;
	
	@Resource
	private ProductAttributeService productAttributeService;
	
	@Resource
	private BrandService brandService;
	
	@Resource
	private ProductTypeService productTypeService;

	@Resource
	private ProductImageService productImageService;

	
	/**
	 * 商品列表
	 * @return
	 */
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public String list(ModelMap map) {
		Pager pager = productService.findByPager(super.pager);
		map.put("pager", pager);		
		return "admin/product_list";
	}	
	
	/**
	 * 土地列表
	 * @return
	 */
	@RequestMapping(value="/landlist", method= RequestMethod.GET)
	public String farmList(ModelMap map) {
		Pager pager = productService.findByPager(true,super.pager);
		map.put("pager", pager);		
		return "admin/product_land_list";
	}
	
	/**
	 * 添加商品
	 * @return
	 */
	@RequestMapping(value="/new", method= RequestMethod.GET)
	public String add(ModelMap map) {
		addSystemConfig(map);
		map.put("productCategoryTreeList", productCategoryService.getProductCategoryTreeList());
		map.put("allBrand", brandService.getAll());
		map.put("allProductTypeList", productTypeService.getAll());
		map.put("isAddAction", true);
		
		return "admin/product_input";
	}
	
	@RequestMapping(value="/ajaxGoodsAttribute", method= RequestMethod.POST)
	@ResponseBody
	public Set<ProductAttribute> ajaxProductAttribute(@RequestParam("id") String id)
	{
		ProductType localGoodsType = productTypeService.get(id);
		Set<ProductAttribute> localSet = localGoodsType.getProductAttributeSet();
	    return localSet;
	}
	
	@RequestMapping(value="/ajaxGoodsParameter", method= RequestMethod.POST)
	@ResponseBody
	public List<ProductParameter> ajaxGoodsParameter(@RequestParam("id") String id)
	{
		ProductType localGoodsType = productTypeService.load(id);
		List<ProductParameter>  localList = localGoodsType.getProductParameters();
	    return localList;
	}
	
	/**
	 *  保存
	 * @param product
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(ModelMap map,Product product,ProductImageListContainer productImageListContainer){
		
		List<ProductImage> images=new ArrayList<ProductImage>();
		for(MultipartFile files:productImageListContainer.getProductImageFileList()){
			ProductImage resultImage=productImageService.buildProductImage(files);
			if(!images.contains(resultImage))
				images.add(resultImage);
		}
		
		product.setProductImageList(images);
		product.setAttributeMapStore(JsonUtil.toJson(product.getGoodsAttributeValueMap()));
		product.setProductParameterValue(JsonUtil.toJson(product.getGoodsParameterValueMap()));

		productService.save(product);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}	


	// 编辑
	@RequestMapping(value="{id}/edit", method= RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") String id) {
		Product product= productService.load(id);
		addSystemConfig(map);
		
		map.put("productCategoryTreeList", productCategoryService.getProductCategoryTreeList());
		map.put("allBrand", brandService.getAll());
		map.put("allProductTypeList", productTypeService.getAll());
		map.put("isAddAction", false);
		
		map.put("product", product);	
		return "admin/product_input";
	}
	
	

	/**
	 * 生成土地商品，构造数据
	 * @param data
	 * @return
	 */
	@RequestMapping(value="generateland", method= RequestMethod.GET)
	public String generateLand(ModelMap map) {
		List<String> prefixs=new ArrayList<String>();
		prefixs.add("A");
		prefixs.add("B");
		prefixs.add("C");
		prefixs.add("D");
		prefixs.add("E");
		prefixs.add("F");
		
		
		for(String prefix:prefixs){
			for(int i=0;i<101;i++){
				Product product=new Product();
				product.setProductSn(prefix+String.format("%1$,03d", i));
				product.setName("待领养");
				product.setStore(1);
				
				product.setFreezeStore(0);
				product.setIsBest(false);
				product.setIsHot(false);//是否显示照片，默认不显示自拍照片，只是个默认照片
				product.setIsLand(true);
				product.setIsMarketable(true);
				product.setIsNew(true);//true代表没有人承包
				product.setMarketPrice(new BigDecimal(2500));
				product.setPoint(Integer.valueOf(1500));
				product.setPrice(new BigDecimal(2500));
				productService.save(product);
			}
		}

		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
//
//	// 删除
//	public String delete() throws Exception {
//		for (String id : ids) {
//			Product product = productService.load(id);
//			Set<OrderItem> orderItemSet = product.getOrderItemSet();
//			for (OrderItem orderItem : orderItemSet) {
//				if (orderItem.getOrder().getOrderStatus() != OrderStatus.completed && orderItem.getOrder().getOrderStatus() != OrderStatus.invalid) {
//					return ajaxJsonErrorMessage("商品[" + product.getName() + "]订单处理未完成，删除失败！");
//				}
//			}
//		}
//		productService.delete(ids);
//		flushCache();
//		return ajaxJsonSuccessMessage("删除成功！");
//	}
//
	
//
//	// 更新
//	@Validations(
//		requiredStrings = {
//			@RequiredStringValidator(fieldName = "product.name", message = "商品名称不允许为空!") 
//		}, 
//		requiredFields = {
//			@RequiredFieldValidator(fieldName = "product.price", message = "销售价不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.marketPrice", message = "市场价不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.weight", message = "商品重量不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.weightUnit", message = "商品重量单位不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.isMarketable", message = "是否上架不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.isBest", message = "是否精品不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.isNew", message = "是否新品不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.isHot", message = "是否热销不允许为空!"),
//			@RequiredFieldValidator(fieldName = "product.productCategory.id", message = "所属分类不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "product.point", min = "0", message = "积分必须为零或正整数!"),
//			@IntRangeFieldValidator(fieldName = "product.store", min = "0", message = "库存必须为零或正整数!")
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String update() throws Exception {
//		if (product.getPrice().compareTo(new BigDecimal("0")) < 0) {
//			addActionError("销售价不允许小于0");
//			return ERROR;
//		}
//		if (product.getMarketPrice().compareTo(new BigDecimal("0")) < 0) {
//			addActionError("市场价不允许小于0");
//			return ERROR;
//		}
//		if (product.getWeight() < 0) {
//			addActionError("商品重量只允许为正数或零!");
//			return ERROR;
//		}
//		Product persistent = productService.load(id);
//		if (StringUtils.isNotEmpty(product.getProductSn())) {
//			if (!productService.isUnique("productSn", persistent.getProductSn(), product.getProductSn())) {
//				addActionError("货号重复,请重新输入!");
//				return ERROR;
//			}
//		} else {
//			String productSn = SerialNumberUtil.buildProductSn();
//			product.setProductSn(productSn);
//		}
//		
//		ProductType productType = product.getProductType();
//		if (productType != null && StringUtils.isNotEmpty(productType.getId())) {
//			productType = productTypeService.load(productType.getId());
//		} else {
//			productType = null;
//		}
//		
//		if (productType != null) {
//			Map<ProductAttribute, List<String>> productAttributeMap = new HashMap<ProductAttribute, List<String>>();
//			List<ProductAttribute> enabledProductAttributeList = productAttributeService.getEnabledProductAttributeList(productType);
//			for (ProductAttribute productAttribute : enabledProductAttributeList) {
//				String[] parameterValues = getParameterValues(productAttribute.getId());
//				if (productAttribute.getIsRequired() && (parameterValues == null || parameterValues.length == 0 || StringUtils.isEmpty(parameterValues[0]))) {
//					addActionError(productAttribute.getName() + "不允许为空!");
//					return ERROR;
//				}
//				if (parameterValues != null && parameterValues.length > 0 && StringUtils.isNotEmpty(parameterValues[0])) {
//					if (productAttribute.getAttributeType() == AttributeType.number) {
//						Pattern pattern = Pattern.compile("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?");
//						Matcher matcher = pattern.matcher(parameterValues[0]);
//						if (!matcher.matches()) {
//							addActionError(productAttribute.getName() + "只允许输入数字!");
//							return ERROR;
//						}
//					}
//					if (productAttribute.getAttributeType() == AttributeType.alphaint) {
//						Pattern pattern = Pattern.compile("[a-zA-Z]+");
//						Matcher matcher = pattern.matcher(parameterValues[0]);
//						if (!matcher.matches()) {
//							addActionError(productAttribute.getName() + "只允许输入字母!");
//							return ERROR;
//						}
//					}
//					if (productAttribute.getAttributeType() == AttributeType.date) {
//						Pattern pattern = Pattern.compile("\\d{4}[\\/-]\\d{1,2}[\\/-]\\d{1,2}");
//						Matcher matcher = pattern.matcher(parameterValues[0]);
//						if (!matcher.matches()) {
//							addActionError(productAttribute.getName() + "日期格式错误!");
//							return ERROR;
//						}
//					}
//					if (productAttribute.getAttributeType() == AttributeType.select || productAttribute.getAttributeType() == AttributeType.checkbox) {
//						List<String> attributeOptionList = productAttribute.getAttributeOptionList();
//						for (String parameterValue : parameterValues) {
//							if (!attributeOptionList.contains(parameterValue)) {
//								addActionError("参数错误!");
//								return ERROR;
//							}
//						}
//					}
//					productAttributeMap.put(productAttribute, Arrays.asList(parameterValues));
//				}
//			}
//			product.setProductAttributeMap(productAttributeMap);
//		} else {
//			product.setProductAttributeMap(null);
//		}
//		product.setProductType(productType);
//		if (productImages != null) {
//			String allowedUploadImageExtension = getSystemConfig().getAllowedUploadImageExtension().toLowerCase();
//			if (StringUtils.isEmpty(allowedUploadImageExtension)) {
//				addActionError("不允许上传图片文件!");
//				return ERROR;
//			}
//			for(int i = 0; i < productImages.length; i ++) {
//				String productImageExtension =  StringUtils.substringAfterLast(productImagesFileName[i], ".").toLowerCase();
//				String[] imageExtensionArray = allowedUploadImageExtension.split(SystemConfig.EXTENSION_SEPARATOR);
//				if (!ArrayUtils.contains(imageExtensionArray, productImageExtension)) {
//					addActionError("只允许上传图片文件类型: " + allowedUploadImageExtension + "!");
//					return ERROR;
//				}
//				if (getSystemConfig().getUploadLimit() != 0 && productImages[i].length() > getSystemConfig().getUploadLimit() * 1024) {
//					addActionError("此上传文件大小超出限制!");
//					return ERROR;
//				}
//			}
//		}
//		
//		List<ProductImage> productImageList = new ArrayList<ProductImage>();
//		if (productImageParameterTypes != null) {
//			int index = 0;
//			int productImageFileIndex = 0;
//			int productImageIdIndex = 0;
//			for (String parameterType : productImageParameterTypes) {
//				if (StringUtils.equalsIgnoreCase(parameterType, "productImageFile")) {
//					ProductImage destProductImage = productImageService.buildProductImage(productImages[productImageFileIndex]);
//					productImageList.add(destProductImage);
//					productImageFileIndex ++;
//					index ++;
//				} else if (StringUtils.equalsIgnoreCase(parameterType, "productImageId")) {
//					ProductImage destProductImage = persistent.getProductImage(productImageIds[productImageIdIndex]);
//					productImageList.add(destProductImage);
//					productImageIdIndex ++;
//					index ++;
//				}
//			}
//		}
//		
//		if (StringUtils.isEmpty(product.getBrand().getId())) {
//			product.setBrand(null);
//		}
//		if (getSystemConfig().getPointType() == PointType.productSet) {
//			if (product.getPoint() == null) {
//				addActionError("积分不允许为空!");
//				return ERROR;
//			}
//		} else {
//			product.setPoint(0);
//		}
//		if (product.getStore() == null) {
//			product.setFreezeStore(0);
//		} else {
//			product.setFreezeStore(persistent.getFreezeStore());
//		}
//		
//		List<ProductImage> persistentProductImageList = persistent.getProductImageList();
//		if (persistentProductImageList != null) {
//			for (ProductImage persistentProductImage : persistentProductImageList) {
//				if (!productImageList.contains(persistentProductImage)) {
//					productImageService.deleteFile(persistentProductImage);
//				}
//			}
//		}
//		product.setProductImageList(productImageList);
//		BeanUtils.copyProperties(product, persistent, new String[] {"id", "createDate", "modifyDate", "htmlFilePath", "favoriteMemberSet", "cartItemSet", "orderItemSet", "deliveryItemSet"});
//		productService.update(persistent);
//		flushCache();
//		redirectionUrl = "product!list.action";
//		return SUCCESS;
//	}
//	
//	// 获取所有品牌
//	public List<Brand> getAllBrand() {
//		return brandService.getAll();
//	}
//
//	// 获取所有商品类型
//	public List<ProductType> getAllProductType() {
//		return productTypeService.getAll();
//	}
//
//	// 获取所有重量单位
//	public List<WeightUnit> getAllWeightUnit() {
//		List<WeightUnit> allWeightUnit = new ArrayList<WeightUnit>();
//		for (WeightUnit weightUnit : WeightUnit.values()) {
//			allWeightUnit.add(weightUnit);
//		}
//		return allWeightUnit;
//	}
//	
//	// 更新页面缓存
//	private void flushCache() {
//		Cache cache = ServletCacheAdministrator.getInstance(getRequest().getSession().getServletContext()).getCache(getRequest(), PageContext.APPLICATION_SCOPE); 
//		cache.flushAll(new Date());
//	}
//	
//	// 获取商品分类树
//	public List<ProductCategory> getProductCategoryTreeList() {
//		return productCategoryService.getProductCategoryTreeList();
//	}



}

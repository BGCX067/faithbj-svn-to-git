package com.faithbj.shop.web.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.ProductImage;
import com.faithbj.shop.model.configuration.ProductImageListContainer;
import com.faithbj.shop.model.entity.Farmland;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Seed;
import com.faithbj.shop.service.FarmLandService;
import com.faithbj.shop.service.ProductImageService;
import com.faithbj.shop.support.annotation.NeedNavigation;


@Controller
@RequestMapping("/faith/farmland")
public class AdminFarmLandController extends BaseAdminController  implements Serializable{

	private static final long serialVersionUID = -4969421249817468001L;

	
	@Resource
	private FarmLandService farmlandService;
	
	@Resource
	private ProductImageService productImageService;

	
	//耕种块土地列表
	@RequestMapping("list/{productid}")
	public String farmland(ModelMap map,Pager pager,@PathVariable String productid){
		pager = farmlandService.findByPager(productid,pager);
		map.put("pager", pager);
		map.put("productid", productid);
		return "admin/product_farmland_list";
	}
	
	//播种
	@RequestMapping("farming/{id}")
	public String farming(ModelMap map,Seed seed,@PathVariable String id){
		Farmland farmland = farmlandService.get(id);
		farmland.setSeed(seed);
		farmlandService.update(farmland);
		map.put("farmland", farmland);
		map.put("success", true);
		
		
		return "shop/my_farmland_list";
	}
	
	//清理
		@RequestMapping("clean/{id}")
		public String clean(ModelMap map,@PathVariable String id){
			Farmland farmland = farmlandService.get(id);
			farmland.setSeed(null);
			farmlandService.update(farmland);
			map.put("farmland", farmland);
			map.put("success", true);
			
			
			return "shop/my_farmland_list";
		}
		
		/**
		 *  保存耕地块图片
		 * @param product
		 * @param request
		 * @param map
		 * @return
		 */
		@RequestMapping(value="/saveFarmImages",method=RequestMethod.POST)
		public String saveFarmImages(ModelMap map,String farmlandid,ProductImageListContainer productImageListContainer){
			Farmland farm = farmlandService.get(farmlandid);
			List<ProductImage> images=farm.getProductImageList();
			List<MultipartFile> productImageFileList = productImageListContainer.getProductImageFileList();
			for(MultipartFile files:productImageFileList){
				if(files==null){
					continue;
				}
				ProductImage resultImage=productImageService.buildProductImage(files);
				if(!images.contains(resultImage))
					images.add(resultImage);
			}
			
			farm.setProductImageList(images);

			farmlandService.update(farm);
			map.put("redirectUrl", "/faith/admin");
			return SUCCESS;
		}	

		/**
		 *  上传耕地块图片
		 * @param product
		 * @param request
		 * @param map
		 * @return
		 */
		@RequestMapping(value="/{farmlandId}/upload")
		public String upload(ModelMap map,@PathVariable String farmlandId){
			Farmland farmland = farmlandService.get(farmlandId);
			map.put("farmland", farmland);
			return "admin/upload_farmimage";
		}
	
}

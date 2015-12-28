package com.faithbj.shop.web.controller.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.Brand;
import com.faithbj.shop.service.BrandService;
import com.faithbj.shop.support.fileupload.FileUploadHandler;
import com.faithbj.shop.support.fileupload.FileUploadHandlerSaveToLocalDisk;


@Controller
@RequestMapping("/faith/brand")
public class AdminBrandController extends BaseAdminController{
	
	private static final long serialVersionUID = -6698110596051395825L;
	
	private final String LIST="admin/brand_list";
	private final String INPUT="admin/brand_input";
	private final String OPERRATE_RETURN_URL="faith/brand/list";
	
	
	@Resource
	private BrandService brandService;
    @Resource
    private FileUploadHandler fileUploadHandlerSaveToLocalDisk;

	@RequestMapping("/add")
	public String add(ModelMap map)
	{
	  map.put("isAddAction", true);
	  return INPUT;
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(ModelMap map,@PathVariable String id)
	{
		Brand brand = brandService.load(id);
		map.put("brand", brand);
		 map.put("isAddAction", false);
	    return INPUT;
	}

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(ModelMap map) {
		if(pager == null) {
			pager = new Pager();
			pager.setOrderType(OrderType.asc);
			pager.setOrderBy("orderList");
		}
		pager = brandService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}
	
	@RequestMapping(value="/{id}/delete",method = RequestMethod.GET)
	public String delete(ModelMap map,@PathVariable String id)
	{
		brandService.delete(id);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
	    return SUCCESS;
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(Brand brand,@RequestParam("logoimage") MultipartFile logoimage,ModelMap map)
	{
		if (logoimage != null && !logoimage.isEmpty()) {
			Map<Integer,String> checkResult=fileUploadHandlerSaveToLocalDisk.checkFileExtention(logoimage);
			if(null!=checkResult.get(FileUploadHandlerSaveToLocalDisk.UPLOAD_PERMITION)){
				String filepath=fileUploadHandlerSaveToLocalDisk.handleUpload(logoimage, null,FileUploadHandlerSaveToLocalDisk.imageType.thumbnail.ordinal());
			    brand.setLogoPath(filepath);
				brandService.save(brand);
				
				map.put("redirectUrl", LIST);
				return SUCCESS;
			}
			else if(null!=checkResult.get(FileUploadHandlerSaveToLocalDisk.EXTENSIONS_NOT_ALLOWED)){
				map.put("errorMessages", checkResult.get(FileUploadHandlerSaveToLocalDisk.EXTENSIONS_NOT_ALLOWED));
			}
			else if(null!=checkResult.get(FileUploadHandlerSaveToLocalDisk.SIZE_NOT_ALLOWED)){
				map.put("errorMessages", checkResult.get(FileUploadHandlerSaveToLocalDisk.SIZE_NOT_ALLOWED));
			}
			else if(null!=checkResult.get(FileUploadHandlerSaveToLocalDisk.UPLOAD_NOT_ALLOWED)){
				map.put("errorMessages", checkResult.get(FileUploadHandlerSaveToLocalDisk.UPLOAD_NOT_ALLOWED));
			}
			return ERROR;
		}
		
		brandService.save(brand);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
		 
	@RequestMapping(value="/update",method = RequestMethod.POST)
    public String update(Brand brand,String id,ModelMap map)
	{
	    Brand localBrand = (Brand)brandService.load(id);
	    BeanUtils.copyProperties(brand, localBrand, new String[] { "id", "createDate", "modifyDate", "logoPath", "goodsSet", "goodsTypeSet" });
	    brandService.update(localBrand);
	    map.put("redirectUrl", OPERRATE_RETURN_URL);
	    return SUCCESS;
	}
}

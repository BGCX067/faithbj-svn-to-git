package com.faithbj.shop.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.model.configuration.ProductImage;
import com.faithbj.shop.service.ProductImageService;
import com.faithbj.shop.support.fileupload.FileUploadHandler;
import com.faithbj.shop.utils.CommonUtil;

/**
 * Service实现类 - 商品图片
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	@Resource
	private FileUploadHandler fileUploadHandlerSaveToLocalDisk;
	
    //图片文件根路径
    @Value("${shop.uploadImageDIR}")
    public String uploadImageDIR;    	
	
	public ProductImage buildProductImage(File uploadProductImageFile) {
//		SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();
//		String sourceProductImageFormatName = ImageUtil.getImageFormatName(uploadProductImageFile);
//		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
//		String dateString = simpleDateFormat.format(new Date());
//		String uuid = CommonUtil.getUUID();
//		
//		String sourceProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + "." + sourceProductImageFormatName;
//		String bigProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.BIG_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
//		String smallProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.SMALL_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
//		String thumbnailProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.THUMBNAIL_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
//
////		File sourceProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(sourceProductImagePath));
////		File bigProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(bigProductImagePath));
////		File smallProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(smallProductImagePath));
////		File thumbnailProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(thumbnailProductImagePath));
////		File watermarkImageFile = new File(ServletActionContext.getServletContext().getRealPath(systemConfig.getWatermarkImagePath()));
//
//		File sourceProductImageParentFile = sourceProductImageFile.getParentFile();
//		File bigProductImageParentFile = bigProductImageFile.getParentFile();
//		File smallProductImageParentFile = smallProductImageFile.getParentFile();
//		File thumbnailProductImageParentFile = thumbnailProductImageFile.getParentFile();
//
//		if (!sourceProductImageParentFile.exists()) {
//			sourceProductImageParentFile.mkdirs();
//		}
//		if (!bigProductImageParentFile.exists()) {
//			bigProductImageParentFile.mkdirs();
//		}
//		if (!smallProductImageParentFile.exists()) {
//			smallProductImageParentFile.mkdirs();
//		}
//		if (!thumbnailProductImageParentFile.exists()) {
//			thumbnailProductImageParentFile.mkdirs();
//		}
//
//		try {
//			BufferedImage srcBufferedImage = ImageIO.read(uploadProductImageFile);
//			// 将上传图片复制到原图片目录
//			FileUtils.copyFile(uploadProductImageFile, sourceProductImageFile);
//			// 商品图片（大）缩放、水印处理
//			ImageUtil.zoomAndWatermark(srcBufferedImage, bigProductImageFile, systemConfig.getBigProductImageHeight(), systemConfig.getBigProductImageWidth(), watermarkImageFile, systemConfig.getWatermarkPosition(), systemConfig.getWatermarkAlpha().intValue());
//			// 商品图片（小）缩放、水印处理
//			ImageUtil.zoomAndWatermark(srcBufferedImage, smallProductImageFile, systemConfig.getSmallProductImageHeight(), systemConfig.getSmallProductImageWidth(), watermarkImageFile, systemConfig.getWatermarkPosition(), systemConfig.getWatermarkAlpha().intValue());
//			// 商品图片缩略图处理
//			ImageUtil.zoom(srcBufferedImage, thumbnailProductImageFile, systemConfig.getThumbnailProductImageHeight(), systemConfig.getThumbnailProductImageWidth());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		ProductImage productImage = new ProductImage();
//		productImage.setId(uuid);
//		productImage.setSourceProductImagePath(sourceProductImagePath);
//		productImage.setBigProductImagePath(bigProductImagePath);
//		productImage.setSmallProductImagePath(smallProductImagePath);
//		productImage.setThumbnailProductImagePath(thumbnailProductImagePath);
//		return productImage;
		return null;
	}
	
	public void deleteFile(ProductImage productImage) {
//		File sourceProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getSourceProductImagePath()));
//		if (sourceProductImageFile.exists()) {
//			sourceProductImageFile.delete();
//		}
//		File bigProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getBigProductImagePath()));
//		if (bigProductImageFile.exists()) {
//			bigProductImageFile.delete();
//		}
//		File smallProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getSmallProductImagePath()));
//		if (smallProductImageFile.exists()) {
//			smallProductImageFile.delete();
//		}
//		File thumbnailProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getThumbnailProductImagePath()));
//		if (thumbnailProductImageFile.exists()) {
//			thumbnailProductImageFile.delete();
//		}
	}

	@Override
	public ProductImage buildProductImage(MultipartFile uploadProductImageFile) {
		    String str = CommonUtil.getUUID();
		    ProductImage localImage = new ProductImage();
		    localImage.setId(str);
		    localImage.setPath(uploadImageDIR);
		    String uploadFileName=uploadProductImageFile.getOriginalFilename();
		    int imageFormatIndex=uploadFileName.lastIndexOf(".");
		    String imageFormat=uploadFileName.substring(imageFormatIndex);
		    localImage.setSourceImageFormatName(imageFormat);
		    
		    String sourceImagePath=localImage.getSourceImagePath();
		    String bigImagePath=localImage.getBigImagePath();
		    String smallImagePath=localImage.getSmallImagePath();
		    String thumbnailImagePath=localImage.getThumbnailImagePath();
		    
		    fileUploadHandlerSaveToLocalDisk.uploadByFilePath(uploadProductImageFile, sourceImagePath);
		    
		    fileUploadHandlerSaveToLocalDisk.generateScaleImage(sourceImagePath, bigImagePath, 3);
		    fileUploadHandlerSaveToLocalDisk.generateScaleImage(sourceImagePath, smallImagePath, 2);
		    fileUploadHandlerSaveToLocalDisk.generateScaleImage(sourceImagePath, thumbnailImagePath, 1);
		    
		    return localImage;
	}

}

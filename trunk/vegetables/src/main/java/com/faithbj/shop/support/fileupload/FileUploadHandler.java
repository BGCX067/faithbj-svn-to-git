package com.faithbj.shop.support.fileupload;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadHandler {
	
	/**
	 * 使用指定目录处理文件上传.
	 * @param file 上传的文件
	 * @param isScaleImage 0原始文件尺寸，1缩略图，2小图，3大图
	 * @return 保存成功的文件名
	 * @throws java.io.IOException 有I/O错误发生
	 */
	String handleUpload(MultipartFile file,String fileType,int isScaleImage);
	
	/**
	 * 使用指定目录路径处理文件上传.
	 * @param file 上传的文件
	 * @param filepath 0原始文件尺寸，1缩略图，2小图，3大图
	 * @return 保存成功的文件名
	 * @throws java.io.IOException 有I/O错误发生
	 */
	String uploadByFilePath(MultipartFile file,String filepath);
	
	
	Map<Integer, String> checkFileExtention(MultipartFile logo);

	void generateScaleImage(String sourceImagePath, String bigImagePath, int scaleType);

}

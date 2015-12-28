package com.faithbj.shop.support.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Component("fileUploadHandlerSaveToLocalDisk")
public class FileUploadHandlerSaveToLocalDisk implements FileUploadHandler {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    public static final Integer UPLOAD_PERMITION=0;
    public static final Integer UPLOAD_NOT_ALLOWED=1;
    public static final Integer EXTENSIONS_NOT_ALLOWED=2;
    public static final Integer SIZE_NOT_ALLOWED=3;
    public static enum imageType{
    	original,thumbnail,small,big
    	
    }
    
    /**
     * 缩略图宽
     */
    @Value("${shop.thumbnailProductImageWidth}")
    private Integer thumbnailProductImageWidth;
    /**
     * 缩略图高
     */
    @Value("${shop.thumbnailProductImageHeight}")
    private Integer thumbnailProductImageHeight;
    
    /**
     * 小图宽
     */
    @Value("${shop.smallProductImageWidth}")
    private Integer smallProductImageWidth;
    /**
     * 小图高
     */
    @Value("${shop.smallProductImageHeight}")
    private Integer smallProductImageHeight;

    /**
     * 大图宽
     */
    @Value("${shop.bigProductImageWidth}")
    private Integer bigProductImageWidth;
    /**
     * 大图高
     */
    @Value("${shop.bigProductImageHeight}")
    private Integer bigProductImageHeight;

    
    @Resource(name = "fileNamingStrategyUUID")
    private FileNamingStragegy fileNamingStragegy;

    @Value("${shop.allowedUploadImageExtension}")
    private String allowedFileExtensions;
    
    @Value("${shop.uploadLimit}")
    public Integer uploadLimit;  

    private Set<String> allowedFileExtensionSet = new HashSet<String>();

    
    
    @PostConstruct
    public void init() {
        if (StringUtils.hasText(allowedFileExtensions)) {
            allowedFileExtensionSet.addAll(Arrays.asList(allowedFileExtensions.split(",")));
        }
    }

    /**
     * 如果上传成功，返回上传成功后的文件绝对路径
     */
    @Override
    public String handleUpload(MultipartFile file,String fileType,int isScaleImage) {
//        checkFileExtention(file.getOriginalFilename());
        String realpath = fileNamingStragegy.getFilePath(file,fileType);
        try {
            file.transferTo(new File(realpath));
        } catch (IOException ioe) {
            log.error("Fail to upload file: " + file.getOriginalFilename(), ioe);
        }
        generateScaleImage(file, realpath, isScaleImage);
        return realpath;
    }

	@Override
	public String uploadByFilePath(MultipartFile file, String filepath) {
		
		File localFile=new File(filepath);
		if(!localFile.getParentFile().isDirectory()){
			synchronized(FileUploadHandlerSaveToLocalDisk.class){
				localFile.mkdirs();
			}
		}
	    try {
	            file.transferTo(localFile);
	        } catch (IOException ioe) {
	            log.error("Fail to upload file: " + file.getOriginalFilename(), ioe);
	        }
	        return filepath;
	}

	/**
	 * 生成缩略图
	 * @param filepath
	 * @param isScaleImage
	 */
	private void generateScaleImage(MultipartFile file,String filepath,int isScaleImage) {
		if (isScaleImage>0)//如果要生成的是缩略图
		{
		    ScaleImage is = new ScaleImage();
		    switch(isScaleImage){
		    case 1:
		    	try 
		    	{
		    		is.saveImageAsPNG(filepath, filepath, thumbnailProductImageWidth, thumbnailProductImageHeight);
		        } catch (Exception e) {
		             log.error("Fail to scale file: " + file.getOriginalFilename(), e);
		         }
		    	break;
		    case 2:
		    	try 
		    	{
		    		is.saveImageAsPNG(filepath, filepath, smallProductImageWidth, smallProductImageHeight);
		        } catch (Exception e) {
		             log.error("Fail to scale file: " + file.getOriginalFilename(), e);
		         }
		    	break;
		    case 3:
		    	try 
		    	{
		    		is.saveImageAsPNG(filepath, filepath, bigProductImageWidth, bigProductImageHeight);
		        } catch (Exception e) {
		             log.error("Fail to scale file: " + file.getOriginalFilename(), e);
		         }
		    	break;
		    }
		}
	}
	/**
	 * 生成缩略图
	 * @param filepath
	 * @param isScaleImage
	 */
	public void generateScaleImage(String sourceFielPath,String targetFielPath,int isScaleImage) {
		File targetFile=new File(targetFielPath);
		if(!targetFile.getParentFile().isDirectory()){
			synchronized(FileUploadHandlerSaveToLocalDisk.class){
				targetFile.mkdirs();
			}
		}
		
		
		if (isScaleImage>0)//如果要生成的是缩略图
		{
			ScaleImage is = new ScaleImage();
			switch(isScaleImage){
			case 1:
				try 
				{
					is.saveImageAsPNG(sourceFielPath, targetFielPath, thumbnailProductImageWidth, thumbnailProductImageHeight);
				} catch (Exception e) {
					log.error("Fail to scale file: " + sourceFielPath, e);
				}
				break;
			case 2:
				try 
				{
					is.saveImageAsPNG(sourceFielPath, targetFielPath, smallProductImageWidth, smallProductImageHeight);
				} catch (Exception e) {
					log.error("Fail to scale file: " + sourceFielPath, e);
				}
				break;
			case 3:
				try 
				{
					is.saveImageAsPNG(sourceFielPath, targetFielPath,  bigProductImageWidth, bigProductImageHeight);
				} catch (Exception e) {
					log.error("Fail to scale file: " + sourceFielPath, e);
				}
				break;
			}
		}
	}
	
	
	
	
	
	
	
	protected void checkFileExtention(String fileName) {
		boolean allowed = false;
	    if (StringUtils.hasLength(fileName) && fileName.contains(".")) {
	    	String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
	        if (allowedFileExtensionSet.contains(extension.trim().toLowerCase())) {
	            allowed = true;
	        }
	     }
	     if (!allowed) {
	        throw new IllegalArgumentException("File extension not allowed for: " + fileName);
	     }
	}

	@Override
	public Map<Integer, String> checkFileExtention(MultipartFile logo) {
		Map<Integer, String> map=new HashMap<Integer, String>();
	
		if (!StringUtils.hasLength(allowedFileExtensions)){
			map.put(UPLOAD_NOT_ALLOWED, "不允许上传图片文件!");
			return map;
		}
		String fileName=logo.getOriginalFilename();
		if (!fileName.contains(".")) {
			map.put(EXTENSIONS_NOT_ALLOWED, "只允许上传图片文件类型:"+allowedFileExtensions);
			return map;
		}
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
		if (!allowedFileExtensionSet.contains(extension.trim().toLowerCase())) {
			map.put(EXTENSIONS_NOT_ALLOWED, "只允许上传图片文件类型:"+allowedFileExtensions);
			return map;
        }
		if (uploadLimit != 0 && logo.getSize() > uploadLimit) {
			map.put(SIZE_NOT_ALLOWED, "Logo文件大小超出限制!");
			return map;
		}
		map.put(UPLOAD_PERMITION, "可以上传文件!");
		return map;
	}

}

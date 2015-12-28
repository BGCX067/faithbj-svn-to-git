package com.faithbj.shop.support.fileupload;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.faithbj.shop.utils.CommonUtil;
import com.faithbj.shop.utils.DateUtils;


@Component("fileNamingStrategyUUID")
public class FileNamingStrategyUUID implements FileNamingStragegy {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * 图片文件上传根目录
     */
    @Value("${shop.uploadImageDIR}")
	private String uploadImageBaseDIR;

	
	@Override
	public String getFilePath(MultipartFile file,String fileType){
		
		if (!file.getOriginalFilename().contains(".")) { // 没有后缀名, 保存以后也无法读取(因为不认为是静态资源)
			log.error("Uploaded file {} does not include suffix.", file.getOriginalFilename());
			throw new IllegalArgumentException("Uploaded file must include suffix.");
		}
		
		String fileSuffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				
				
		//没有文件夹，新建一个
		File uploadBaseImageDir = new File(uploadImageBaseDIR);
		if (!uploadBaseImageDir.exists()) {
			uploadBaseImageDir.mkdir();
		}
		
		//获得当前年月作为分目录的标准
		String dateString = DateUtils.formateDate2String("yyyyMM",new Date());
		
		String uploadDestinationPath=uploadBaseImageDir+ "/" + dateString;//最终上传的目录
		File uploadDestinationImageDir = new File(uploadDestinationPath);
		if (!uploadDestinationImageDir.exists()) {
			uploadDestinationImageDir.mkdir();
		}
		//文件类型不空，按文件类型获取文件完整路径
		if(null!=fileType){
			String uploadDirPlusType=uploadDestinationPath+"/"+fileType;
			//又加了一层目录，目录名为文件类型（可以是大小，也可以是别的）
			File uploadDestinationPlusTypeDir=new File(uploadDirPlusType);
			if (!uploadDestinationPlusTypeDir.exists()) {
				uploadDestinationPlusTypeDir.mkdir();
			}
			String uploadImagePath = uploadDestinationPlusTypeDir + "/" + CommonUtil.getUUID()  + fileSuffix;
			return uploadImagePath;
		}
		
		String uploadImagePath = uploadDestinationPath + "/" + CommonUtil.getUUID()  + fileSuffix;
		
		return uploadImagePath;
	}



	
}

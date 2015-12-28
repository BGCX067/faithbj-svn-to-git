package com.faithbj.shop.support.fileupload;

import org.springframework.web.multipart.MultipartFile;

public interface FileNamingStragegy {
	
	/**
	 * 根据文件大小类型上传到不同的目录，如果filetype不是null，则file的上级目录为filetype
	 * @param file
	 * @param fileType file的上级目录名称，按文件类型分
	 * @return
	 */
	String getFilePath(MultipartFile file,String fileType);

}

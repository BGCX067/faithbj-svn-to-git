package com.faithbj.shop.support.filedownload;


/**
 * Support类 - 文件下载
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-07
 * @version 1.0
 */

public interface FileDownloadHandler {

	byte[] getFileBinary(String fileName, String dir);
	
	
	byte[] previewImage(String filename);
}

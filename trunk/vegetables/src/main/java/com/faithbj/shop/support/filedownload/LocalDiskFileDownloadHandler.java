package com.faithbj.shop.support.filedownload;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

@Component
public class LocalDiskFileDownloadHandler implements FileDownloadHandler {

	protected static final Logger log = LoggerFactory.getLogger(LocalDiskFileDownloadHandler.class);
	
	private final int IMAGE_SIZE=32;

	@Override
	public byte[] getFileBinary(String fileName, String dir) {
		File file = new File(dir + "/" + fileName);
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
		} catch (FileNotFoundException fnfe) {
			log.error("File not found: {}", file.getAbsolutePath());
		} catch (IOException ioe) {
			log.error("I/O Exception: {}", ioe.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ioe) {
				log.error("I/O Exception: {}", ioe.getMessage());
			}
		}
		return out != null ? out.toByteArray() : null;
	}
	
	
	public byte[] previewImage(String srcFile) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		File fi=null;
		
	        fi = new File(srcFile); // src
	        if (!fi.exists()) {  
	            return null;  
	        }   
	        BufferedImage bis = null;
			try {
				bis = ImageIO.read(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}

	        int w = bis.getWidth();
	        int h = bis.getHeight();
	        int nw = IMAGE_SIZE; // final int IMAGE_SIZE = 32;
	        int nh = (nw * h) / w;
	        if( nh>IMAGE_SIZE ) {
	            nh = IMAGE_SIZE;
	            nw = (nh * w) / h;
	        }
	        double sx = (double)nw / w;
	        double sy = (double)nh / h;

	        AffineTransform transform=new AffineTransform();
	        transform.setToScale(sx,sy);
	        AffineTransformOp ato = new AffineTransformOp(transform, null);
	        BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
	        ato.filter(bis,bid);
	        
	        try {
				ImageIO.write(bid, "P", out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return out.toByteArray();
	}
	
	
	
}

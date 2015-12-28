package com.faithbj.shop.model.configuration;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * Bean类 - 商品图片
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-16
 * @version 1.0
 */
@JsonIgnoreProperties({"sourceImagePath", "bigImagePath", "smallImagePath", "thumbnailImagePath","sourceImageView", "bigImageView", "smallImageView","thumbnailImageView"})
public class ProductImage implements Serializable,Comparable<ProductImage>{
	
	private static final long serialVersionUID = -8257115122679001541L;
	
	public static final String PRODUCT_IMAGE_FILE_EXTENSION = "png";// 商品图片文件名扩展名
	public static final String SOURCE_IMAGE_FILE_NAME_SUFFIX = "source";// 商品图片（原图）文件名后缀
	public static final String BIG_IMAGE_FILE_NAME_SUFFIX = "big";// 商品图片（大）文件名后缀
	public static final String SMALL_IMAGE_FILE_NAME_SUFFIX = "small";// 商品图片（小）文件名后缀
	public static final String THUMBNAIL_IMAGE_FILE_NAME_SUFFIX = "thumbnail";// 商品缩略图文件名后缀
	
	private String id;// ID
	private String path;// 商品图片（原）路径
	private String SourceImageFormatName;// 商品图片格式名
	private String description;// 商品图片（小）路径
	private Integer orderList;// 商品图片（缩略图）路径
	
	public ProductImage(){
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSourceImageFormatName() {
		return SourceImageFormatName;
	}

	public void setSourceImageFormatName(String sourceImageFormatName) {
		SourceImageFormatName = sourceImageFormatName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}
	
	/**
	 * 上传用的原图路径
	 * @return
	 */
	public String getSourceImagePath()
	  {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append(this.path);
	    localStringBuffer.append("/source/");
	    localStringBuffer.append(this.id);
	    localStringBuffer.append(this.SourceImageFormatName);
	    return localStringBuffer.toString();
	  }

	  public String getBigImagePath()
	  {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append(this.path);
	    localStringBuffer.append("/big/");
	    localStringBuffer.append(this.id);
	    localStringBuffer.append(this.SourceImageFormatName);
	    return localStringBuffer.toString();
	  }

	  public String getSmallImagePath()
	  {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append(this.path);
	    localStringBuffer.append("/small/");
	    localStringBuffer.append(this.id);
	    localStringBuffer.append(this.SourceImageFormatName);
	    return localStringBuffer.toString();
	  }

	  public String getThumbnailImagePath()
	  {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append(this.path);
	    localStringBuffer.append("/thumbnail/");
	    localStringBuffer.append(this.id);
	    localStringBuffer.append(this.SourceImageFormatName);
	    return localStringBuffer.toString();
	  }	
	
	  
	  
		/**
		 * 显示用的原图路径
		 * @return
		 */
		public String getSourceImageView()
		  {
		    StringBuffer localStringBuffer = new StringBuffer();
		    localStringBuffer.append("source/");
		    localStringBuffer.append(this.id);
		    localStringBuffer.append(this.SourceImageFormatName);
		    return localStringBuffer.toString();
		  }

		  public String getBigImageView()
		  {
		    StringBuffer localStringBuffer = new StringBuffer();
		    localStringBuffer.append("big/");
		    localStringBuffer.append(this.id);
		    localStringBuffer.append(this.SourceImageFormatName);
		    return localStringBuffer.toString();
		  }

		  public String getSmallImageView()
		  {
		    StringBuffer localStringBuffer = new StringBuffer();
		    localStringBuffer.append("small/");
		    localStringBuffer.append(this.id);
		    localStringBuffer.append(this.SourceImageFormatName);
		    return localStringBuffer.toString();
		  }

		  public String getThumbnailImageView()
		  {
		    StringBuffer localStringBuffer = new StringBuffer();
		    localStringBuffer.append("thumbnail/");
		    localStringBuffer.append(this.id);
		    localStringBuffer.append(this.SourceImageFormatName);
		    return localStringBuffer.toString();
		  }	
			  
	  
	  
	  
	  
	  
	@Override
	public boolean equals(Object paramObject) {
	   if (paramObject == null)
		      return false;
	   if ((paramObject instanceof ProductImage))
	   {
		   ProductImage localProductImage = (ProductImage)paramObject;
		      if ((getId() == null) || (localProductImage.getId() == null))
		        return false;
		   return getId().equals(localProductImage.getId());
	   }
	   return false;		
	}
	
	@Override
	public int hashCode() {
		return this.id == null ? System.identityHashCode(this) : (getClass().getName() + getId()).hashCode();
	}

	@Override
	public int compareTo(ProductImage productImage) {
		if (productImage.getOrderList() == null)
		      return 1;
		if (getOrderList() == null)
		      return -1;
		return getOrderList().compareTo(productImage.getOrderList());
	}

}

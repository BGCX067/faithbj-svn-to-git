package com.faithbj.custom.vegetable.entity;

import java.util.Date;

/**
 * 
 * <p>耕种作物</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */
@Deprecated
public class Plant 
{
    private String id;
    private Date createdate;
    private Date modifydate;
    private String name;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id == null ? null : id.trim();
	}

	public Date getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}
}
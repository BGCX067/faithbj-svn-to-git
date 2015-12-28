package com.faithbj.shop.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.AreaDao;
import com.faithbj.shop.model.entity.Area;

/**
 * Dao实现类 - 地区
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class AreaDaoImpl extends BaseDaoImpl<Area, String> implements AreaDao {
	
	@SuppressWarnings("unchecked")
	public List<Area> getRootAreaList() {
		String hql = "from Area area where area.parent is null";
		return getSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Area> getParentAreaList(Area area) {
		String hql = "from Area area where area != ? and area.id in(:ids)";
		String[] ids = area.getPath().split(Area.PATH_SEPARATOR);
		return getSession().createQuery(hql).setParameter(0, area).setParameterList("ids", ids).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Area> getChildrenAreaList(Area area) {
		String hql = "from Area area where area != ? and area.path like ?";
		return getSession().createQuery(hql).setParameter(0, area).setParameter(1, area.getPath() + "%").list();
	}

	public Boolean isNameUnique(String parentId, String oldName, String newName) {
		if (StringUtils.equalsIgnoreCase(newName, oldName)) {
			return true;
		}
		if (StringUtils.isEmpty(parentId)) {
			String hql = "from Area area where area.name = ? and area.parent is null";
			return getSession().createQuery(hql).setParameter(0, newName).uniqueResult() == null;
		} else {
			String hql = "from Area area where area.name = ? and area.parent.id = ?";
			return getSession().createQuery(hql).setParameter(0, newName).setParameter(1, parentId).uniqueResult() == null;
		}
	}
	
	private void setAreaDisplayName(Area area)
	  {
	    Area localArea1 = area.getParent();
	    if (localArea1 == null)
	      area.setPath(area.getId());
	    else
	      area.setPath(localArea1.getPath() + "," + area.getId());
	    area.setGrade(Integer.valueOf(area.getPath().split(",").length - 1));
	    StringBuilder localStringBuilder = new StringBuilder();
	    List localList = getParentAreaList(area);
	    if (localList != null)
	    {
	      Iterator localIterator = localList.iterator();
	      while (localIterator.hasNext())
	      {
	        Area localArea2 = (Area)localIterator.next();
	        localStringBuilder.append(localArea2.getName());
	      }
	    }
	    localStringBuilder.append(area.getName());
	    area.setDisplayName(localStringBuilder.toString());
	  }

	// 设置path值
	@Override
	public String save(Area area) {
		area.setDisplayName(area.getName());
	    area.setPath(area.getName());
	    area.setGrade(Integer.valueOf(0));
	    super.save(area);
	    setAreaDisplayName(area);
	    super.update(area);
	    return area.getId();
	    
//		String id = super.save(area);
//		Area parent = area.getParent();
//		if (parent != null) {
//			String parentPath = parent.getPath();
//			area.setPath(parentPath + Area.PATH_SEPARATOR + id);
//		} else {
//			area.setPath(id);
//		}
//		super.update(area);
//		return id;
	}
	
	// 设置path值
	@Override
	public void update(Area area) {
		setAreaDisplayName(area);
	    super.update(area);
	    List localList = getChildrenAreaList(area);
	    if (localList != null)
	      for (int i = 0; i < localList.size(); i++)
	      {
	        Area localArea = (Area)localList.get(i);
	        setAreaDisplayName(localArea);
	        super.update(localArea);
	        if (i % 20 != 0)
	          continue;
	        flush();
	        clear();
	      }
//		Area parent = area.getParent();
//		if (parent != null) {
//			String parentPath = parent.getPath();
//			area.setPath(parentPath + Area.PATH_SEPARATOR + area.getId());
//		} else {
//			area.setPath(area.getId());
//		}
//		super.update(area);
	}

}

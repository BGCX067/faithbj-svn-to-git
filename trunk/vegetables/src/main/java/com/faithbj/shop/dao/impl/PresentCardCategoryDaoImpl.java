package com.faithbj.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.PresentCardCategoryDao;
import com.faithbj.shop.dao.PresentCardDao;
import com.faithbj.shop.model.entity.PresentCard;
import com.faithbj.shop.model.entity.PresentCardCategory;

@Repository("presentCardCategoryDao")
public class PresentCardCategoryDaoImpl extends BaseDaoImpl<PresentCardCategory,String> implements PresentCardCategoryDao {

}
package com.faithbj.custom.vegetable.action.fieldmember;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.service.FieldBlockService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 前台Action类 - 土地收藏夹
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-02-02
 * @version 1.0
 */

@ParentPackage("member")
public class FieldFavoriteAction extends BaseShopAction
{
	private static final long serialVersionUID = 1L;

	private static final Integer pageSize = 10;// 商品收藏每页显示数
	
	@Resource
	private FieldBlockService fieldBlockService = null;

	public String list() {
		Member loginMember = getLoginMember();
		if (pager == null) {
			pager = new Pager();
		}
		pager.setPageSize(pageSize);
		pager = fieldBlockService.findFavoriteFieldBlockByPager(loginMember, pager);
		return LIST;
	}

	public String delete () {
		FieldBlock fieldBlock = fieldBlockService.load(id);
		Member loginMember = getLoginMember();
		Set<FieldBlock> favoriteFieldBlockSet = loginMember.getFavoriteFieldBlockSet();
		if (!favoriteFieldBlockSet.contains(fieldBlock)) {
			addActionError("参数错误!");
			return ERROR;
		}
		favoriteFieldBlockSet.remove(fieldBlock);
		memberService.update(loginMember);
		redirectionUrl = "favorite!list.action";
		return list();
	}

	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "id", message = "ID不允许为空!")
		}
	)
	@InputConfig(resultName = "error")
	public String ajaxAdd() {
		FieldBlock fieldBlock = fieldBlockService.load(id);
		if (!fieldBlock.getIsMarketable()) {
			return ajaxJsonErrorMessage("此承包土地已下架!");
		}
		Member loginMember = getLoginMember();
		Set<FieldBlock> favoriteFieldBlockSet = (loginMember.getFavoriteFieldBlockSet() == null 
				? new HashSet<FieldBlock>() : loginMember.getFavoriteFieldBlockSet());
		if (favoriteFieldBlockSet.contains(fieldBlock)) {
			return ajaxJsonWarnMessage("您已经收藏过此承包土地!");
		} else {
			favoriteFieldBlockSet.add(fieldBlock);
			memberService.update(loginMember);
			return ajaxJsonSuccessMessage("承包土地收藏成功!");
		}
	}

}

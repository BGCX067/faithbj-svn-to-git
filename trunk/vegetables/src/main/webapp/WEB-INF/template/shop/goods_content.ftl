<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${goods.name}<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<#if (goods.metaKeywords)! != ""><meta name="keywords" content="${goods.metaKeywords}" /></#if>
<#if (goods.metaDescription)! != ""><meta name="description" content="${goods.metaDescription}" /></#if>
<#include "/common/common_css.ftl">
<link href="${base}/vegetables/common/css/jquery.zoomimage.css" rel="stylesheet" type="text/css" />
<#include "/common/common_js.ftl">
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.zoomimage.js"></script>
<script type="text/javascript">
$(function() {
	// Tab效果
    $("ul.goodsParameterTab").tabs(".detailtab");
});
</script>
</head>
<body id="goodsContent" class="goodsContent">
	<#include "/shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="goodsCategory">
            	<div class="top">商品分类</div>
            	<div class="middle">
            		<ul id="goodsCategoryMenu" class="menu">
	            			<#list goodsCategoryTree as firstGoodsCategory>
	            				<li class="mainCategory">
									<a href="${base}/${firstGoodsCategory.url}">${firstGoodsCategory.name}</a>
								</li>
								<#if (firstGoodsCategory.children?? && firstGoodsCategory.children?size > 0)>
									<#list firstGoodsCategory.children as secondGoodsCategory>
										<li>
											<a href="${base}${secondGoodsCategory.url}">
												<span class="icon">&nbsp;</span>${secondGoodsCategory.name}
											</a>
										</li>
										<#if secondGoodsCategory_index + 1 == 5>
											<#break />
										</#if>
									</#list>
								</#if>
								<#if firstGoodsCategory_index + 1 == 3>
									<#break />
								</#if>
	            			</#list>
					</ul>
            	</div>
                <div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="hotGoods">
				<div class="top">热销排行</div>
				<div class="middle">
					<ul>
						<#list goodsList as goods>
							<li class="number${goods_index + 1}">
								<span class="icon">&nbsp;</span>
								<a href="${base}${goods.htmlPath}" title="${goods.name}"><#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..24]}...</#if></a>
							</li>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div id="goodsHistory" class="goodsHistory">
				<div class="top">浏览记录</div>
				<div class="middle">
					<ul id="goodsHistoryListDetail"></ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
						<#list pathList as path>
							<a href="${base}${path.url}">${path.name}</a> &gt;
						</#list>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="goodsTop">
				<div class="goodsTopLeft">
					<div class="goodsImage">
						<#if goods.productImageList??>
		                	<#list goods.productImageList as goodsImage>
		                		<a href="${base}/vegetables/inventory/${goodsImage.bigImageView}" class="tabContent zoom<#if (goodsImage_index > 0)> nonFirst</#if>">
									<img src="${base}/vegetables/inventory/${goodsImage.smallImageView}" alt="点击放大" />
								</a>
							</#list>
						<#else>
	            			<a href="${base}/vegetables/shop/images/${setting.defaultBigProductImagePath}" class="zoom">
								<img src="${base}/vegetables/shop/images/${setting.defaultSmallProductImagePath}" alt="点击放大" />
							</a>
	                	</#if>
                	</div>
					<div class="thumbnailGoodsImage">
						<a class="prev browse" href="javascript: void(0);" hidefocus></a>
						<div id="thumbnailGoodsImageScrollable" class="scrollable">
							<ul id="goodsImageTab" class="items goodsImageTab">
								<#if goods.productImageList??>
									<#list goods.productImageList as goodsImage>
										<li><img src="${base}/vegetables/inventory/${goodsImage.thumbnailImageView}" alt="${goodsImage.description}" /></li>
									</#list>
								<#else>
									<li><img src="${base}/vegetables/shop/images/${setting.defaultSmallProductImagePath}" /></li>
	                        	</#if>
							</ul>
						</div>
						<a class="next browse" href="javascript: void(0);" hidefocus></a>
					</div>
				</div>
				<div class="goodsTopRight">
					<h1 class="title"><#if goods.name?length lt 50>${goods.name}<#else>${goods.name[0..50]}...</#if></h1>
					<ul class="goodsAttribute">
						<li>商品编号: ${goods.productSn}</li>
						<#--<li>货品编号: <span id="productSn">${goods.defaultProduct.productSn}</span></li>-->
						<#list (goods.productType.productAttributeSet)! as goodsAttribute>
							<#if goods.getProductAttributeMapValue(goodsAttribute.id)?? && goods.getProductAttributeMapValue(goodsAttribute.id) != "">
	                    		<li>${goodsAttribute.name}: ${goods.getProductAttributeMapValue(goodsAttribute.id)}</li>
							</#if>
						</#list>
					</ul>
					<div class="blank"></div>
					<div class="goodsPrice">
						<div class="left"></div>
						<div class="right">
							<div class="top">
								销 售 价:
								<span id="price" class="price">${goods.price?string(currencyFormat)}</span>
							</div>
							<div class="bottom">
								市 场 价:
								<#if setting.isShowMarketPrice>
									<span id="marketPrice" class="marketPrice">${goods.marketPrice?string(currencyFormat)}</span>
								<#else>
									-
								</#if>
							</div>
						</div>
					</div>
					<div class="blank"></div>
					<table id="buyInfo" class="buyInfo">
						<tr>
							<th>购买数量:</th>
							<td>
								<input type="text" id="quantity" value="1" />
								<#if setting.scoreType == "goodsSet" && goods.score != 0>
									&nbsp;&nbsp;( 所得积分: ${goods.score} )
								</#if>
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td>
								<#if !goods.isSpecificationEnabled && goods.isOutOfStock>
									<input type="button" id="goodsButton" class="goodsNotifyButton" value="" hidefocus />
								<#else>
									<input type="button" id="goodsButton" class="addCartItemButton" value="" hidefocus />
								</#if>
								 <input type="button" id="addFavorite" class="addFavoriteButton" goodsId="${goods.id}" hidefocus />
								 <input type="button" value="喜欢" id="addFavor" class="addFavorButton" goodsId="${goods.id}" hidefocus />
								 <input type="button" value="忌讳" id="addHate" class="addHateButton" goodsId="${goods.id}" hidefocus />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="blank"></div>
			<div class="goodsBottom">
				<ul id="goodsParameterTab" class="goodsParameterTab">
					<li>
						<a href="javascript: void(0);" class="current" hidefocus>商品介绍</a>
					</li>
					<li>
						<a href="javascript: void(0);" name="goodsAttribute" hidefocus>商品参数</a>
					</li>
					<#if setting.isCommentEnabled>
						<li>
							<a href="javascript: void(0);" hidefocus>商品评论</a>
						</li>
					</#if>
				</ul>
				<div class="tabContent goodsIntroduction">
					${goods.introduction}
				</div>
				<div class="tabContent goodsAttribute">
					<table class="goodsParameterTable">
						<#list (goods.goodsType.goodsParameterList)! as goodsParameter>
							<#if goods.goodsParameterValueMap.get(goodsParameter.id)?? && goods.goodsParameterValueMap.get(goodsParameter.id) != "">
								<tr>
									<th>
										${goodsParameter.name}
									</th>
									<td>
										${(goods.goodsParameterValueMap.get(goodsParameter.id))!}
									</td>
								</tr>
							</#if>
						</#list>
					</table>
				</div>
				<#if setting.isCommentEnabled>
					<div id="comment" class="tabContent comment">
						<@comment_list goods_id=goods.id count=5; commentList>
							<#list commentList as comment>
								<#assign isHasComment = true />
								<div class="commentItem" id="commentItem${comment.id}">
									<p><span class="red">${(comment.username)!"游客"}</span> ${comment.createDate?string("yyyy-MM-dd HH: mm")} <a href="#commentForm" class="commentReply" forCommentId="${comment.id}">[回复此评论]</a></p>
									<p><pre>${comment.content}</pre></p>
									<#list comment.replyCommentSet as replyComment>
										<#if replyComment.isShow>
											<div class="reply">
												<p><span class="red"><#if replyComment.isAdminReply>管理员<#else>${(replyComment.username)!"游客"}</#if></span> ${replyComment.createDate?string("yyyy-MM-dd HH: mm")}</p>
												<p><pre>${replyComment.content}</pre></p>
											</div>
										</#if>
									</#list>
								</div>
								<#if comment_has_next>
									<div class="blank"></div>
								</#if>
							</#list>
							<#if (commentList?size > 0)>
								<div class="info">
									<a href="${base}/shop/comment_list/${goods.id}.htm">查看所有评论&gt;&gt;</a>
								</div>
							</#if>
						</@comment_list>
						<form id="commentForm" name="commentForm" method="post">
							<input type="hidden" name="comment.goods.id" value="${goods.id}" />
							<input type="hidden" id="forCommentId" name="forCommentId" />
							<table class="sendTable">
								<tr class="title">
									<td width="100">
										<span id="sendTitle">发表评论</span>
									</td>
									<td>
										<a href="javascript: void(0);" id="sendComment" class="sendComment">切换到发表新评论&gt;&gt;</a>
									</td>
								</tr>
								<tr>
									<th>
										评论内容: 
									</th>
									<td>
										<textarea id="commentContent" name="comment.content" class="formTextarea"></textarea>
									</td>
								</tr>
								<tr>
									<th>
										联系方式: 
									</th>
									<td>
										<input type="text" name="comment.contact" class="formText" />
									</td>
								</tr>
								<#if setting.isCommentCaptchaEnabled>
									<tr>
					                	<th>
					                		验证码: 
					                	</th>
					                    <td>
					                    	<input type="text" id="commentCaptcha" name="j_captcha" class="formText captcha" />
					                    	<img id="commentCaptchaImage" class="captchaImage" src="${base}/captcha.jpeg" alt="换一张" />
					                    </td>
					                </tr>
				                </#if>
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="formButton" value="提交评论" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</#if>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">

	<script type="text/javascript">
	$().ready( function() {
	
		$productSn = $("#productSn");
		$price = $("#price");
		$marketPrice = $("#marketPrice");
		$buyInfo = $("#buyInfo");
		$tipsTitle = $("#tipsTitle");
		$tipsContent = $("#tipsContent");
		$closeHighlight = $("#closeHighlight");
		$specificationValue = $("#buyInfo li");
		$quantity = $("#quantity");
		$goodsButton = $("#goodsButton");
	
		// 添加商品浏览记录
		$.addGoodsHistory("<#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..24]}...</#if>", "${base}/product/${goods.id}");
		
		var selectedProductId = "${goods.id}";
			
			// 添加商品至购物车/到货通知
			$goodsButton.click(function () {
				var $this = $(this);
				if ($this.hasClass("addCartItemButton")) {
					$.addCartItem(selectedProductId, $quantity.val());
				} else {
					location.href='${base}/shop/goods_notify/' + selectedProductId;
				}
			});
	
	})
	</script>
</body>
</html>
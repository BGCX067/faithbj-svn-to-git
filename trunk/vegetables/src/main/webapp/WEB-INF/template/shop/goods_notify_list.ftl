<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>缺货登记<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/shop.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/template/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
<script type="text/javascript">
$().ready( function() {
	
	var $deleteGoodsNotify = $("#goodsNotifyTable .deleteGoodsNotify");
	
	// 到货通知删除
	$deleteGoodsNotify.click( function() {
		var $this = $(this);
		var goodsNotifyId = $this.attr("goodsNotifyId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteGoodsNotify});
		function deleteGoodsNotify() {
			$.ajax({
				url: "goods_notify!ajaxDelete.action",
				data: {id: goodsNotifyId},
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					$.message({type: data.status, content: data.message});
					$this.parent().parent().remove();
				}
			});
		}
		return false;
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body goodsNotifyList">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">缺货登记</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="goodsNotifyTable" class="listTable">
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品价格</th>
							<th>操作</th>
						</tr>
						<#list (pager.result)! as goodsNotify>
							<#assign product = goodsNotify.product />
							<#assign goods = goodsNotify.product.goods />
							<tr>
								<td>
									<a href="${base}${goods.htmlPath}" class="goodsImage" target="_blank">
										<img src="${base}${goods.defaultThumbnailGoodsImagePath}" />
									</a>
								</td>
								<td>
									<a href="${base}${goods.htmlPath}" target="_blank">
										${product.name}
									</a>
								</td>
								<td>
									${product.price?string(currencyFormat)}
								</td>
								<td>
									<a href="#" class="deleteGoodsNotify" goodsNotifyId="${goodsNotify.id}">删除</a>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         				<#include "/shop/pager.ftl">
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">
</body>
</html>
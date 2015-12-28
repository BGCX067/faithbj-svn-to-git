<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/common/common_css.ftl">
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	var $deleteFavor = $("#favorTable .deleteFavor");
	
	// 删除收藏
	$deleteFavor.click( function() {
		var $this = $(this);
		var favorId = $this.attr("favorId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteFavorGoods});
		function deleteFavorGoods() {
			$.ajax({
				url: "/cjlhome/profile/deletefavor",
				data: {id: favorId},
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
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
	<div class="body favorList">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">商品收藏</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="favorTable" class="listTable">
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品价格</th>
							<th>操作</th>
						</tr>
						<#list pager.result as favorGoods>
							<tr>
								<td>
									<a href="${base}${favorGoods.htmlPath}" class="goodsImage" target="_blank">
										<img src="${base}${favorGoods.defaultThumbnailGoodsImagePath}" />
									</a>
								</td>
								<td>
									<a href="${base}${favorGoods.htmlPath}" target="_blank">${favorGoods.name}</a>
								</td>
								<td>
									${favorGoods.price?string(currencyFormat)}
								</td>
								<td>
									<a href="#" class="deleteFavorite" favorId="${favorGoods.id}" title="删 除">删 除</a>
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
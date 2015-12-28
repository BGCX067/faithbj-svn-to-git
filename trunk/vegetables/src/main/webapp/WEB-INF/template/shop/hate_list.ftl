<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品收藏<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
	
	var $deleteHate = $("#hateTable .deleteHate");
	
	// 删除收藏
	$deleteHate.click( function() {
		var $this = $(this);
		var hateId = $this.attr("hateId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteHateGoods});
		function deleteHateGoods() {
			$.ajax({
				url: "/cjlhome/profile/deletehate",
				data: {id: hateId},
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
	<div class="body hateList">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">商品收藏</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="hateTable" class="listTable">
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品价格</th>
							<th>操作</th>
						</tr>
						<#list pager.result as hateGoods>
							<tr>
								<td>
									<a href="${base}${hateGoods.htmlPath}" class="goodsImage" target="_blank">
										<img src="${base}${hateGoods.defaultThumbnailGoodsImagePath}" />
									</a>
								</td>
								<td>
									<a href="${base}${hateGoods.htmlPath}" target="_blank">${hateGoods.name}</a>
								</td>
								<td>
									${hateGoods.price?string(currencyFormat)}
								</td>
								<td>
									<a href="#" class="deleteFavorite" hateId="${hateGoods.id}" title="删 除">删 除</a>
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
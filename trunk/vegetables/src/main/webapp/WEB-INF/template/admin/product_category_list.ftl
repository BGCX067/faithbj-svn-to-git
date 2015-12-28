<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品分类列表 - Powered By <#--${systemConfig.systemName}--></title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $deleteGoodsCategory = $("#listTable .deleteGoodsCategory");
	var $goodsCategoryName = $("#listTable .goodsCategoryName");
	
	// 删除商品分类
	$deleteGoodsCategory.click( function() {
		if (confirm("您确定要删除此商品分类吗?") == false) {
			return false;
		}
	});
	
	// 树折叠
	$goodsCategoryName.click( function() {
		var grade = $(this).parent().attr("grade");
		var isHide;
		$(this).parent().nextAll("tr").each(function(){
			var thisLevel = $(this).attr("grade");
			if(thisLevel <=  grade) {
				return false;
			}
			if(isHide == null) {
				if($(this).is(":hidden")){
					isHide = true;
				} else {
					isHide = false;
				}
			}
			if( isHide) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	});

})
</script>
</head>
<body class="list goodsCategory">
	<div class="bar">
		商品分类列表&nbsp;<span class="pageInfo">总记录数: ${productCategoryTreeList?size}
	</div>
	<div class="body">
		<form id="listForm" action="list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='add'" value="添加分类" hidefocus />
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>分类名称</span>
					</th>
					<th>
						<span>排序</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list productCategoryTreeList as goodsCategory>
					<tr level="${(goodsCategory.level)!}">
						<td class="goodsCategoryName">
							<#if goodsCategory.level == 0>
								<span class="pointer firstCategory" style="margin-left: ${goodsCategory.level * 20}px;">
									${goodsCategory.name}
								</span>
							<#else>
								<span class="pointer category" style="margin-left: ${goodsCategory.level * 20}px;">
									${goodsCategory.name}
								</span>
							</#if>
						</td>
						<td>
							${goodsCategory.orderList}
						</td>
						<td>
							<a href="${base}/shop/product/list?id=${goodsCategory.id}" target="_blank" title="查看">[查看]</a>
							<#if (goodsCategory.children?size > 0)>
								<span title="无法删除">[删除]</span>
							<#else>
								<a href="${base}/faith/goods_category/${goodsCategory.id}/delete" class="deleteGoodsCategory" title="删除">[删除]</a>
							</#if>
							<a href="${base}/faith/goods_category/edit?id=${goodsCategory.id}" title="编辑">[编辑]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if productCategoryTreeList?size == 0>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>
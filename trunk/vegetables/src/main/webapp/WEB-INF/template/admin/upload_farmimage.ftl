<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>上传耕地块图片</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>

<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.cn.js"></script>

<script type="text/javascript" src="${base}/vegetables/common/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>

<script>
$(function() {
  // Tab效果
  $("ul.tab").tabs(".tabContent");
  
  var $goodsImageTable = $("#goodsImageTable");
  var $addGoodsImageButton = $("#addGoodsImageButton");
	
	
	// 增加商品图片
	var goodsImageIndex = ${(farmland.productImageList?size)!0};
	$addGoodsImageButton.click( function() {
		
		<@compress single_line = true>
			var goodsImageTrHtml = 
			'<tr class="goodsImageTr">
				<td>
					<input type="file" name="productImageFileList[' + goodsImageIndex + ']" class="goodsImageFileList" />
				</td>
				<td>
					<input type="text" name="productImageList[' + goodsImageIndex + '].description" class="formText" />
				</td>
				<td>
					<input type="text" name="productImageList[' + goodsImageIndex + '].orderList" class="formText goodsImageOrderList" style="width: 50px;" />
				</td>
				<td>
					<span class="deleteIcon deleteGoodsImage" title="删 除">&nbsp;</span>
				</td>
			</tr>';
		</@compress>
		
		$goodsImageTable.append(goodsImageTrHtml);
		goodsImageIndex ++;
	});
	
	// 删除商品图片
	$("#goodsImageTable .deleteGoodsImage").live("click", function() {
		var $this = $(this);
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteGoodsImage});
		function deleteGoodsImage() {
			$this.parent().parent().remove();
		}
	});
	
	
});
</script>
<#if (goods.isSpecificationEnabled)!>
	<style type="text/css">
		.specificationDisabledInfo {
			display: none;
		}
	</style>
</#if>
</head>
<body class="input goods">
	<div class="bar">
		上传耕地块图片
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="goodsForm" action="/faith/farmland/saveFarmImages"  enctype="multipart/form-data" method="post">
		<input type="hidden" name="farmlandid" value="${(farmland.id)!}" />
			<table id="goodsImageTable" class="inputTable">
				<tr class="noneHover">
					<td colspan="5">
						<input type="button" id="addGoodsImageButton" class="formButton" value="增加图片" hidefocus />
					</td>
				</tr>
				<tr class="title">
					<th>
						上传图片
					</th>
					<th>
						图片描述
					</th>
					<th>
						排序
					</th>
					<th>
						删除
					</th>
				</tr>
				<#list (farmland.productImageList)! as goodsImage>
					<tr class="goodsImageTr">
						<td>
							<input type="hidden" name="productImageList[${goodsImage_index}].id" value="${goodsImage.id}" />
							<a href="${base}/vegetables/inventory/${goodsImage.thumbnailImageView}" title="点击查看" target="_blank">
								<img src="${base}/vegetables/inventory/${(goodsImage.thumbnailImageView)!defaultpbg.png}" style="width: 50px; height: 50px;" />
							</a>
						</td>
						<td>
							<input type="text" name="productImageList[${goodsImage_index}].description" class="formText" value="${goodsImage.description}" />
						</td>
						<td>
							<input type="text" name="productImageList[${goodsImage_index}].orderList" class="formText goodsImageOrderList" value="${goodsImage.orderList}" style="width: 50px;" />
						</td>
						<td>
							<span class="deleteIcon deleteGoodsImage" title="删 除">&nbsp;</span>
						</td>
					</tr>
				</#list>
			</table>
		
			
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>
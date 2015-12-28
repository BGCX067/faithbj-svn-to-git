<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地会员中心 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product_content.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/product.js"></script>
<script type='text/javascript'>
$().ready(function() {
	
	document.getElementById('farmingBlock.status').value = '${(farmingBlock.status)!}';
})
	
	function chooseSeed()
	{
		var data = window.showModalDialog('${base}/fieldmember/seed!popup.action?t=' + new Date().getTime(), '', 'dialogWidth:800px; dialogHeight:600px; center:yes');
		if (data)
		{
			var obj = eval('(' + data + ')');
			document.listForm['farmingBlock.seedName'].value = obj.name;
			document.listForm['farmingBlock.seed.id'].value = obj.id;
		}
	}
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body memberCenterIndex productContent">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
		
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="home"><span class="icon">&nbsp;</span>首页</a>>
						<a href="${base}/fieldmember/my_rend_block!list.action" class="home"><span class="icon">&nbsp;</span>我的承包地块</a>>
						<a href="${base}/fieldmember/my_farming_block!list.action" class="home"><span class="icon">&nbsp;</span>我的耕种地块</a>>
						<a href='#'>详细信息</a>>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="productTop">
				<div class="productTopLeft">
					<div class="productImage">
	                	<#list farmingBlock.productImageList as list>
	                		<a href="${base}${list.bigProductImagePath}" class="tabContent zoom<#if (list_index > 0)> nonFirst</#if>">
								<img src="${base}${list.smallProductImagePath}" />
							</a>
						</#list>
						<#if farmingBlock.productImageList == null>
	            			<a href="${base}${systemConfig.defaultBigProductImagePath}" class="zoom">
								<img src="${base}${systemConfig.defaultSmallProductImagePath}" />
							</a>
	                	</#if>
                	</div>
					<div class="thumbnailProductImage">
						<a class="prev browse" href="javascript:void(0);" hidefocus="true"></a>
						<div class="scrollable">
							<ul class="items productImageTab">
								<#if (farmingBlock.productImageList == null)!>
									<li>
										<img src="${base}${systemConfig.defaultThumbnailProductImagePath}" />
									</li>
	                        	</#if>
	                        	<#list (farmingBlock.productImageList)! as list>
									<li>
										<img src="${base}${list.thumbnailProductImagePath}" />
									</li>
								</#list>
							</ul>
						</div>
						<a class="next browse" href="javascript:void(0);" hidefocus="true"></a>
					</div>
				</div>
				<div class="productTopRight">
					<h1>
					</h1>
					<ul class="productAttribute">
					</ul>
					<div class="blank"></div>
				</div>
			</div>
			<div class="blank"></div>
			
			<form id="inputForm" name='listForm' class="validate" action="my_farming_block!update.action" method="post">
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="farmingBlock.member.id" value="${(farmingBlock.member.id)!}" />
				<input type="hidden" name="farmingBlock.seed.id" value="${(farmingBlock.seed.id)!}" />
				<input type="hidden" name="farmingBlock.rendBlock.id" value="${(farmingBlock.rendBlock.id)!}" />
				<div class="blank"></div>
				</ul>
				<table class="inputTable tabContent">
	
					<tr>
						<th>
							耕种地块代码:
						</th>
						<td>
								${farmingBlock.code}
								<input type="hidden" name="farmingBlock.code" value="${(farmingBlock.code)!}" />
						</td>
					</tr>
					<tr>
						<th>
							承包地块代码:
						</th>
						<td>
							${farmingBlock.rendBlockCode}
							<input type="hidden" name="farmingBlock.rendBlockCode" value="${(farmingBlock.rendBlockCode)!}" />
						</td>
					</tr>
					<tr>
						<th>
							面积（平方米）:
						</th>
						<td>
							${farmingBlock.area}
							<input type="hidden" name="farmingBlock.area" value="${(farmingBlock.area)!}" />
						</td>
					</tr>
					<tr>
						<th>
							耕种状态:
						</th>
						<td>
							<select name="farmingBlock.status" id='farmingBlock.status'>
								<option value='0'>空闲</option>
								<option value='2'>播种</option>
								<option value='4'>生长</option>
								<option value='6'>收获</option>
								<option value='8'>清理</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							种子:
						</th>
						<td>
							<input type="text" name="farmingBlock.seedName" readonly=true class="formText {required: false, name: true, maxlength : 255}", value="${(farmingBlock.seedName)!}"/>
							<a href='#' onclick='chooseSeed();return false;'>点此选择种子</a>
						</td>
					</tr>
					<tr>
						<th>
							操作人:
						</th>
						<td>
							${(farmingBlock.operateUser)!}
						</td>
					</tr>
					<tr>
						<th>
							操作时间:
						</th>
						<td>
							${(farmingBlock.operateTime?string('yyyy-MM-dd HH:mm:ss'))!}
						</td>
					</tr>
				</table>
			
				<div class="buttonArea">
					<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
				</div>
				
		</form>
			
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>

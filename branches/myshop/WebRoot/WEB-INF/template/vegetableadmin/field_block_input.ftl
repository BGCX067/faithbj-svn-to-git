<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑土地 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {

	
	// 商品图片预览滚动栏
	$(".productImageArea .scrollable").scrollable({
		speed: 600
	});
	
	// 显示商品图片预览操作层
	$(".productImageArea li").livequery("mouseover", function() {
		$(this).find(".productImageOperate").show();
	});
	
	// 隐藏商品图片预览操作层
	$(".productImageArea li").livequery("mouseout", function() {
		$(this).find(".productImageOperate").hide();
	});
	
	// 商品图片左移
	$(".left").livequery("click", function() {
		var $productImageLi = $(this).parent().parent().parent();
		var $productImagePrevLi = $productImageLi.prev("li");
		if ($productImagePrevLi.length > 0) {
			$productImagePrevLi.insertAfter($productImageLi);
		}
	});
	
	// 商品图片右移
	$(".right").livequery("click", function() {
		var $productImageLi = $(this).parent().parent().parent();
		var $productImageNextLi = $productImageLi.next("li");
		if ($productImageNextLi.length > 0) {
			$productImageNextLi.insertBefore($productImageLi);
		}
	});
	
	// 商品图片删除
	$(".delete").livequery("click", function() {
		var $productImageLi = $(this).parent().parent().parent();
		var $productImagePreview = $productImageLi.find(".productImagePreview");
		var $productImageIds = $productImageLi.find("input[name='productImageIds']");
		var $productImageFiles = $productImageLi.find("input[name='productImages']");
		var $productImageParameterTypes = $productImageLi.find("input[name='productImageParameterTypes']");
		$productImageIds.remove();
		$productImageFiles.after('<input type="file" name="productImages" hidefocus="true" />');
		$productImageFiles.remove();
		$productImageParameterTypes.remove();
		
		$productImagePreview.html("暂无图片");
		$productImagePreview.removeAttr("title");
		if ($.browser.msie) {
			if(window.XMLHttpRequest) {
				$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='')";
			}
		}
	});
	
	// 商品图片选择预览
	var $productImageScrollable = $(".productImageArea .scrollable").scrollable();
	var productImageLiHtml = '<li><div class="productImageBox"><div class="productImagePreview">暂无图片</div><div class="productImageOperate"><a class="left" href="javascript: void(0);" alt="左移" hidefocus="true"></a><a class="right" href="javascript: void(0);" title="右移" hidefocus="true"></a><a class="delete" href="javascript: void(0);" title="删除" hidefocus="true"></a></div><a class="productImageUploadButton" href="javascript: void(0);"><input type="file" name="productImages" hidefocus="true" /><div>上传新图片</div></a></div></li>';
	$(".productImageUploadButton input").livequery("change", function() {
		var $this = $(this);
		var $productImageLi = $this.parent().parent().parent();
		var $productImagePreview = $productImageLi.find(".productImagePreview");
		var fileName = $this.val().substr($this.val().lastIndexOf("\\") + 1);
		if (/(<#list systemConfig.allowedUploadImageExtension?split(stack.findValue("@com.faithbj.shop.bean.SystemConfig@EXTENSION_SEPARATOR")) as list><#if list_has_next>.${list}|<#else>.${list}</#if></#list>)$/i.test($this.val()) == false) {
			$.message("您选择的文件格式错误！");
			return false;
		}
		$productImagePreview.empty();
		$productImagePreview.attr("title", fileName);
		if ($.browser.msie) {
			if(!window.XMLHttpRequest) {
				$productImagePreview.html('<img src="' + $this.val() + '" />');
			} else {
				$this[0].select();
				var imgSrc = document.selection.createRange().text;
				$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='" + imgSrc + "')";
			}
		} else if ($.browser.mozilla) {
			//alert('<img src="' + $this[0].files[0].getAsDataURL() + '" />');
		
			$productImagePreview.html('<img src="' + $this[0].files[0].getAsDataURL() + '" />');
		} else {
			$productImagePreview.html(fileName);
		}
		if ($productImageLi.next().length == 0) {
			$productImageLi.after(productImageLiHtml);
			if ($productImageScrollable.getSize() > 5) {
				$productImageScrollable.next();
			}
		}
		var $productImageIds = $productImageLi.find("input[name='productImageIds']");
		var $productImageParameterTypes = $productImageLi.find("input[name='productImageParameterTypes']");
		var $productImageUploadButton = $productImageLi.find(".productImageUploadButton");
		$productImageIds.remove();
		if ($productImageParameterTypes.length > 0) {
			$productImageParameterTypes.val("productImageFile");
		} else {
			$productImageUploadButton.append('<input type="hidden" name="productImageParameterTypes" value="productImageFile" />');
		}
	});
	
	document.getElementById('fieldBlock.isMarketable').value = '${(fieldBlock.isMarketable)!}' == 'true' ? 'true' : 'false';

})
</script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加土地<#else>编辑土地</#if></h1>
		</div>
		<div class="blank"></div>
		<form id="inputForm" class="validate" action="<#if isAdd??>field_block!save.action<#else>field_block!update.action</#if>" method="post" enctype="multipart/form-data" >
<input type="hidden" name="id" value="${id}" />
			<div class="blank"></div>
			</ul>
			<table class="inputTable tabContent">

				<tr>
					<th>
						土地代码:
					</th>
					<td>
						<#if isAdd??>
							<input type="text" name="fieldBlock.code" class="formText {required: true, name: true, remote: 'field_block!checkCode.action?oldValue=${(fieldBlock.code?url)!}', minlength: 2, maxlength: 20, messages: {remote: '土地代码已存在,请重新输入!'}}" title="土地代码不能为空" value="${(fieldBlock.code)!}"/>
							<label class="requireField">*</label>
						<#else>
							${fieldBlock.code}
							<input type="hidden" name="fieldBlock.code" value="${(fieldBlock.code)!}" />
						</#if>
					</td>
				</tr>
				<tr>
					<th>
						名称:
					</th>
					<td>
						<input type="text" name="fieldBlock.name" class="formText {required: true, name: true, maxlength: 255}", title="土地名称不能为空" value="${(fieldBlock.name)!}"/>
							<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						面积（平方米）:
					</th>
					<td>
						<input type="text" name="fieldBlock.area" class="formText {required: true, name: true, digits:true}", title="面积不能为空" value="${(fieldBlock.area)!}"/>
							<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<textarea name="fieldBlock.description" rows=5 cols=100>${(fieldBlock.description)!}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						积分:
					</th>
					<td>
						<input type="text" name="fieldBlock.point" class="formText {required: true, name: true, digits:true}", title="土地积分不能为空" value="${(fieldBlock.point)!}"/>
							<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						详细地址:
					</th>
					<td>
						
							<input type="text" name="fieldBlock.address" class="formText {required: true, name: true, maxlength: 255}", title="土地详细地址不能为空" value="${(fieldBlock.address)!}"/>
							<label class="requireField">*</label>
						
					</td>
				</tr>
				<tr>
					<th>
						经纬度:
					</th>
					<td>
						
							<input type="text" name="fieldBlock.longitudeLatitude" class="formText {required: false, name: true, maxlength: 255}", value="${(fieldBlock.longitudeLatitude)!}"/>
						
					</td>
				</tr>
				<tr>
					<th>
						年租金:
					</th>
					<td>
						
							<input type="text" name="fieldBlock.rent" class="formText {required: true, name: false, number:true}", value="${(fieldBlock.rent)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						年托管费用:
					</th>
					<td>
						
							<input type="text" name="fieldBlock.trusteeFee" class="formText {required: true, name: false, number:true}", value="${(fieldBlock.trusteeFee)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						年配送费用:
					</th>
					<td>
						
							<input type="text" name="fieldBlock.deliveryFee" class="formText {required: true, name: false, number:true}", value="${(fieldBlock.deliveryFee)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						上架:
					</th>
					<td>
						
							<select name="fieldBlock.isMarketable" id='fieldBlock.isMarketable'>
								<option value='true'>上架</option>
								<option value='false'>下架</option>
							</select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						上传土地图片
					</th>
					<td>
						<div class="productImageArea">
							<div class="example"></div>
							<a class="prev browse" href="javascript:void(0);" hidefocus="true"></a>
							<div class="scrollable">
								<ul class="items">
									<#list (fieldBlock.productImageList)! as list>
										<li>
											<div class="productImageBox">
												<div class="productImagePreview png">
													<img src="${base}${list.thumbnailProductImagePath}" >
												</div>
												<div class="productImageOperate">
													<a class="left" href="javascript: void(0);" alt="左移" hidefocus="true"></a>
													<a class="right" href="javascript: void(0);" title="右移" hidefocus="true"></a>
													<a class="delete" href="javascript: void(0);" title="删除" hidefocus="true"></a>
												</div>
												<a class="productImageUploadButton" href="javascript: void(0);">
													<input type="hidden" name="productImageIds" value="${list.id}" />
													<input type="hidden" name="productImageParameterTypes" value="productImageId" />
													<#if systemConfig.allowedUploadImageExtension != "">
														<input type="file" name="productImages" hidefocus="true" />
														<div>上传新图片</div>
													<#else>
														<div>不允许上传</div>
													</#if>
												</a>
											</div>
										</li>
									</#list>
									<li>
										<div class="productImageBox">
											<div class="productImagePreview png">暂无图片</div>
											<div class="productImageOperate">
												<a class="left" href="javascript: void(0);" alt="左移" hidefocus="true"></a>
												<a class="right" href="javascript: void(0);" title="右移" hidefocus="true"></a>
												<a class="delete" href="javascript: void(0);" title="删除" hidefocus="true"></a>
											</div>
											<a class="productImageUploadButton" href="javascript: void(0);">
												<#if systemConfig.allowedUploadImageExtension != "">
													<input type="file" name="productImages" hidefocus="true" />
													<div>上传新图片</div>
												<#else>
													<div>不允许上传</div>
												</#if>
											</a>
										</div>
									</li>
								</ul>
							</div>
							<a class="next browse" href="javascript:void(0);" hidefocus="true"></a>
							<div class="blank"></div>
							<#if systemConfig.allowedUploadImageExtension != "">
								<span class="warnInfo"><span class="icon">&nbsp;</span><#if (systemConfig.uploadLimit) != 0 && (systemConfig.uploadLimit < 1024)>小于${systemConfig.uploadLimit}KB<#elseif (systemConfig.uploadLimit >= 1024)>小于${systemConfig.uploadLimit / 1024}MB</#if> (<#list systemConfig.allowedUploadImageExtension?split(stack.findValue("@com.faithbj.shop.bean.SystemConfig@EXTENSION_SEPARATOR")) as list><#if list_has_next>*.${list};<#else>*.${list}</#if></#list>)</span>
							<#else>
								<span class="warnInfo"><span class="icon">&nbsp;</span>系统设置不允许上传图片文件!</span>
							</#if>
						</div>
					</td>
				</tr>
			
			</table>
		
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑商品分类 - Powered By faithbj</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.translate.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	
	var $validateForm = $("#validateForm");
	var $goodsCategoryName = $("#goodsCategoryName");
	var $goodsCategorySign = $("#goodsCategorySign");
	var $goodsCategorySignLoadingIcon = $("#goodsCategorySignLoadingIcon");

	$goodsCategoryName.change( function() {
		var $this = $(this);
		$this.translate("zh", "en", {
			data: true,
			replace: false,
			start: function() {
				$goodsCategorySignLoadingIcon.show();
			},
			complete: function() {
				var goodsCategorySignValue = $.trim($this.data("translation").en.value.toLowerCase()).replace(/\s+/g, "_").replace(/[^\w]+/g, "");
				$goodsCategorySign.val(goodsCategorySignValue);
				$goodsCategorySignLoadingIcon.hide();
			},
			error: function() {
				$goodsCategorySignLoadingIcon.hide();
			}
		});

	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"goodsCategory.name": "required",
			
			"goodsCategory.orderList": "digits"
		},
		messages: {
			"goodsCategory.name": "请填写分类名称",
			"goodsCategory.sign": {
				required: "请填写标识",
				alphanumeric: "标识只允许包含英文、数字和下划线",
				remote: "标识已存在"
			},
			"goodsCategory.orderList": "排序必须为零或正整数"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="bar">
		<#if isAdd>添加商品分类<#else>编辑商品分类</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAdd>save<#else>update</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						分类名称: 
					</th>
					<td>
						<input type="text" id="goodsCategoryName" name="name" class="formText" value="${(productCategory.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						上级分类: 
					</th>
					<td>
						<#if isAdd>
							<select name="parentId">
								<option value="">顶级分类</option>
								<#list productCategoryTreeList as goodsCategoryTree>
									<option value="${goodsCategoryTree.id}">
										<#if goodsCategoryTree.level != 0>
											<#list 1..goodsCategoryTree.level as i>
												&nbsp;&nbsp;
											</#list>
										</#if>
										${goodsCategoryTree.name}
									</option>
								</#list>
							</select>
						<#else>
							${(goodsCategory.parent.name)!'顶级分类'}
						</#if>
					</td>
				</tr>
				<tr>
					<th>
						排序: 
					</th>
					<td>
						<input type="text" name="orderList" class="formText" value="${(goodsCategory.orderList)!}" title="只允许输入零或正整数" />
					</td>
				</tr>
				<tr>
					<th>
						页面关键词: 
					</th>
					<td>
						<input type="text" name="metaKeywords" class="formText" value="${(goodsCategory.metaKeywords)!}" />
					</td>
				</tr>
				<tr>
					<th>
						页面描述: 
					</th>
					<td>
						<textarea name="metaDescription" class="formTextarea">${(goodsCategory.metaDescription)!}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="warnInfo"><span class="icon">&nbsp;</span>页面关键词、页面描述可以更好的使用户通过搜索引擎搜索到站点</span>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>
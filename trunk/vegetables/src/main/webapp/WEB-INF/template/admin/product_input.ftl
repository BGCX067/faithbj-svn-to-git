<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑商品</title>
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
  //编辑器
  var editor = KindEditor.create('textarea[name="introduction"]');
  
  var $goodsImageTable = $("#goodsImageTable");
  var $addGoodsImageButton = $("#addGoodsImageButton");
	
	var $goodsTypeId = $("#goodsTypeId");
	var $goodsTypeLoadingIcon = $("#goodsTypeLoadingIcon");
	var $goodsAttributeTable = $("#goodsAttributeTable");
	var $goodsParameterTable = $("#goodsParameterTable");
	
	
	// 增加商品图片
	var goodsImageIndex = ${(product.productImageList?size)!0};
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
	
    // 修改商品类型
	var previousGoodsTypeId = "${(goods.goodsType.id)!}";
	$goodsTypeId.change( function() {
		if (previousGoodsTypeId != "") {
			$.dialog({type: "warn", content: "修改商品类型后当前“属性参数”数据将会丢失,是否继续!", width: 450, ok: "确 定", cancel: "取 消", modal: true, okCallback: goodsTypeChange, cancelCallback: goodsTypeReset});
		} else {
			goodsTypeChange();
		}
		
		function goodsTypeChange() {
			previousGoodsTypeId = $goodsTypeId.val();
			
			if ($goodsTypeId.val() == "") {
				$goodsAttributeTable.hide();
				$goodsParameterTable.hide();
				return;
			}
			
			$.ajax({
				url: "/faith/goods/ajaxGoodsAttribute",
				data: {id: $goodsTypeId.val()},
				type: "POST",
				dataType: "json",
				async: false,
				cache: false,
				beforeSend: function() {
					$goodsTypeLoadingIcon.show();
					$("#goodsAttributeTable .goodsAttributeTr").remove();
				},
				success: function(data) {
					if (data != null) {
						var goodsAttributeTrHtml = "";
						$.each(data, function(i) {
							if (data[i].attributeType == "filter") {
								var optionHtml = '<option value="">请选择...</option>';
								$.each(data[i].optionList, function(j) {
									<@compress single_line = true>
										optionHtml += 
										'<option value="' + data[i].optionList[j] + '">'
											 + data[i].optionList[j] + 
										'</option>';
									</@compress>
								});
								<@compress single_line = true>
									goodsAttributeTrHtml += 
									'<tr class="goodsAttributeTr">
										<th>' + data[i].name + ': </th>
										<td>
											<select name="goodsAttributeValueMap[\'' + data[i].id + '\']">
												' + optionHtml + '
											</select>
										</td>
									</tr>';
								</@compress>
							} else {
								<@compress single_line = true>
									goodsAttributeTrHtml += 
									'<tr class="goodsAttributeTr">
										<th>' + data[i].name + ': </th>
										<td>
											<input type="text" name="goodsAttributeValueMap[\''+ data[i].id + '\']" class="formText" />
										</td>
									</tr>';
								</@compress>
							}
						});
						$goodsAttributeTable.append(goodsAttributeTrHtml);
						$goodsAttributeTable.show();
					} else {
						$goodsAttributeTable.hide();
					}
				},
				complete: function() {
					$goodsTypeLoadingIcon.hide();
				}
			});
			
			$.ajax({
				url: "/faith/goods/ajaxGoodsParameter",
				data: {id: $goodsTypeId.val()},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$goodsTypeLoadingIcon.show();
					$("#goodsParameterTable .goodsParameterTr").remove();
				},
				success: function(data) {
					if (data != null) {
						var goodsParameterTrHtml = "";
						$.each(data, function(i) {
							<@compress single_line = true>
								goodsParameterTrHtml += 
								'<tr class="goodsParameterTr">
									<th>' + data[i].name + ': </th>
									<td>
										<input type="text" name="goodsParameterValueMap[\'' + data[i].id + '\']" class="formText" />
									</td>
								</tr>';
							</@compress>
						});
						$goodsParameterTable.append(goodsParameterTrHtml);
						$goodsParameterTable.show();
					} else {
						$goodsParameterTable.hide();
					}
				},
				complete: function() {
					$goodsTypeLoadingIcon.hide();
				}
			});
		}
		
		function goodsTypeReset() {
			$goodsTypeId.val(previousGoodsTypeId);
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
		<#if isAddAction>添加商品<#else>编辑商品</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="goodsForm" action="<#if isAddAction>/faith/goods/save<#else>/faith/goods/save</#if>" <#if isAddAction>method="post"<#else>method="put"</#if>  enctype="multipart/form-data">

			<input type="hidden" name="id" value="${id}" />
			<#if isAddAction==false><input type="hidden" name="_method" value="put" /></#if>
			
			<ul id="tabs" class="tab">
				<li><input type="button" value="基本信息" hidefocus /></li>
				<li><input type="button" value="商品描述" hidefocus /></li>
				<li><input type="button" value="商品图片" hidefocus /></li>
				<li><input type="button" value="属性参数" hidefocus /></li>
			</ul>
			<table id="infoTable" class="inputTable tabContent">
				<tr>
					<th>
						商品分类: 
					</th>
					<td>
				        <select name="productCategory.id" class="{required: true}">
							<option value="">请选择...</option>
							<#list productCategoryTreeList as list>
								<option value="${list.id}"<#if (list.id == product.productCategory.id)!> selected</#if>>
									<#if list.level != 0>
										<#list 1..list.level as i>&nbsp;&nbsp;</#list>
									</#if>
									${list.name}
								</option>
						    </#list>
						</select>						
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品名称: 
					</th>
					<td>
						<input type="text" name="name" class="formText" value="${(product.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						货号: 
					</th>
					<td>
						<input type="text" name="productSn" class="formText" value="${(product.productSn)!}" title="若留空则由系统自动生成" />
					</td>
				</tr>
				<tr>
					<th>
						品牌: 
					</th>
					<td>
					 <select name="brand.id">
						   <option value="">请选择...</option>
						   <#list allBrand as list>
								<option value="${list.id}"<#if (list.id == product.brand.id)!> selected </#if>>
									${list.name}
								</option>
						    </#list>
					   </select>
					</td>
				</tr>
				<#if setting.PointType == "productSet">
					<tr>
						<th>
							积分: 
						</th>
						<td>
							<input type="text" name="point" class="formText" value="${(product.point)!"0"}" />
						</td>
					</tr>
				</#if>
				<tr class="specificationDisabledInfo">
					<th>
						销售价: 
					</th>
					<td>
						<input type="text" name="price" class="formText" value="${(product.price)!"0"}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr class="specificationDisabledInfo">
					<th>
						成本价: 
					</th>
					<td>
						<input type="text" name="cost" class="formText" value="${(product.cost)!"0"}" title="前台不会显示,仅供后台使用" />
					</td>
				</tr>
				<tr class="specificationDisabledInfo">
					<th>
						市场价: 
					</th>
					<td>
						<input type="text" name="marketPrice" class="formText" value="${(product.marketPrice)!"0"}" title="若为空则系统自动计算市场价" />
					</td>
				</tr>
				<tr class="specificationDisabledInfo">
					<th>
						重量: 
					</th>
					<td>
						<input type="text" name="weight" class="formText" value="${(product.weight)!}" title="单位: 克" />
					</td>
				</tr>
				<tr class="specificationDisabledInfo">
					<th>
						库存: 
					</th>
					<td>
						<input type="text" name="store" class="formText" value="${(product.store)!}" title="只允许输入零或正整数,为空表示不计库存" />
					</td>
				</tr>
				<tr class="specificationDisabledInfo">
					<th>
						货位: 
					</th>
					<td>
						<input type="text" name="storePlace" class="formText" value="${(product.storePlace)!}" title="用于记录商品所在的具体仓库位置,便于检索" />				 						
					</td>
				</tr>
				<tr>
					<th>
						设置: 
					</th>
					<td>
						<label>
						    <input name="isMarketable" type="checkbox" <#if (product.isMarketable)!true==true>checked="checked"</#if> />上架
						</label>
						<label>
							<input name="isBest" type="checkbox" <#if (product.isBest)!false==true>checked="checked"</#if>  />精品
						</label>
						<label>
							<input name="isNew" type="checkbox" <#if (product.isNew)!false==true>checked="checked"</#if>  />新品
						</label>
						<label>
							<input name="isHot" type="checkbox" <#if (product.isHot)!false==true>checked="checked"</#if>  />热销
						</label>
						<label>
							<input name="isLand" type="checkbox" <#if (product.isLand)!false==true>checked="checked"</#if>  />是否是土地
						</label>
					</td>
				</tr>
				<tr>
					<th>
						页面关键词: 
					</th>
					<td>
						<input type="text" name="metaKeywords" class="formText" value="${(product.metaKeywords)!}" />
					</td>
				</tr>
				<tr>
					<th>
						页面描述: 
					</th>
					<td>
						<textarea name="metaDescription" class="formTextarea">${(product.metaDescription)!}</textarea>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<td>
						<textarea id="editor" name="introduction" class="editor" style="width: 100%; height:500px;">${(product.introduction)!}</textarea>
					</td>
				</tr>
			</table>
			<table id="goodsImageTable" class="inputTable tabContent">
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
				<#list (product.productImageList)! as goodsImage>
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
		
			<div class="tabContent">
				<div class="tableItem">
					<table class="inputTable">
						<tr class="title">
							<th>
								请选择商品类型: 
							</th>
							<td>
								<select id="goodsTypeId" name="productType.id">
									<option value="">请选择...</option>
									<#list allProductTypeList as goodsType>
										<option value="${goodsType.id}"<#if (goodsType == product.productType)!> selected</#if>>${goodsType.name}</option>
									</#list>
								</select>
								<span id="goodsTypeLoadingIcon" class="loadingIcon hidden">&nbsp;</span>
							</td>
						</tr>
					</table>
					<div class="blank"></div>
					<table id="goodsAttributeTable" class="inputTable<#if (isAddAction || product.productType == null)!> hidden</#if>">
						<tr class="title">
							<th>
								商品属性
							</th>
							<td>
								&nbsp;
							</td>
						</tr>
						<#list (product.productType.productAttributeMapStore)! as goodsAttribute>
							<tr class="goodsAttributeTr">
								<th>${goodsAttribute.name}: </th>
								<td>
									<#if goodsAttribute.attributeType == "filter">
										<select name="goodsAttributeValueMap['${goodsAttribute.id}']">
											<option value="">请选择...</option>
											<#list goodsAttribute.optionList as option>
												<option value="${option}"<#if option == goods.getGoodsAttributeValue(goodsAttribute)> selected</#if>>
													${option}
												</option>
											</#list>
										</select>
									<#else>
										<input type="text" name="goodsAttributeValueMap['${goodsAttribute.id}']" class="formText" value="${(goods.getGoodsAttributeValue(goodsAttribute))!}" />
									</#if>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
					<table id="goodsParameterTable" class="inputTable<#if (isAddAction || product.productType == null)!true> hidden</#if>">
						<tr class="title">
							<th>
								商品参数
							</th>
							<td>
								&nbsp;
							</td>
						</tr>
						<#assign goodsParameterValueMap = (goods.goodsParameterValueMap)! />
						<#list (product.productType.goodsParameterList)! as goodsParameter>
							<tr class="goodsParameterTr">
								<th>${goodsParameter.name}: </th>
								<td>
									<input type="text" name="goodsParameterValueMap['${goodsParameter.id}']" class="formText" value="${(goodsParameterValueMap.get(goodsParameter.id))!}" />
								</td>
							</tr>
						</#list>
					</table>
				</div>
			</div>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑商品类型</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>

<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>

<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script>
$(function() {
  // Tab效果
  $("ul.tab").tabs(".tabContent");
  
  	var $goodsAttributeTable = $("#goodsAttributeTable");
	var $addGoodsAttributeButton = $("#addGoodsAttributeButton");
	var $goodsParameterTable = $("#goodsParameterTable");
	var $addGoodsParameterButton = $("#addGoodsParameterButton");
  
  // 增加商品属性
	var goodsAttributeIndex = ${(productType.productAttributeList?size)!0};
	$addGoodsAttributeButton.click( function() {
	   <@compress single_line = true>
			var goodsAttributeTrHtml = 
			'<tr class="goodsAttributeTr">
				<td>
					<input type="text" name="productAttributeList[' + goodsAttributeIndex + '].name" class="formText goodsAttributeListName" />
				</td>
				<td>
				
					<select name="productAttributeList[' + goodsAttributeIndex + '].attributeType" class="attributeType">
						<#list attributeTypeList as attributeType>
							<option value="${attributeType}"><@spring.message "AttributeType.${attributeType}"/></option>
						</#list>
					</select>
				</td>
				<td>
					<input type="text" name="productAttributeList[' + goodsAttributeIndex + '].optionText" class="formText optionText goodsAttributeListOptionText" title="多个可选值.请使用“,”分隔" />
				</td>
				<td>
					<input type="text" name="productAttributeList[' + goodsAttributeIndex + '].orderList" class="formText goodsAttributeListOrderList" style="width: 30px;" />
				</td>
				<td>
					<span class="deleteIcon deleteGoodsAttributeIcon" title="删 除">&nbsp;</span>
				</td>
			</tr>';
		</@compress>
		
		if ($goodsAttributeTable.find(".goodsAttributeTr").length >= 20) {
			$.dialog({type: "warn", content: "商品属性个数超出限制!", modal: true, autoCloseTime: 3000});
		} else {
			$goodsAttributeTable.append(goodsAttributeTrHtml);
			goodsAttributeIndex ++;
		}
	});
	
	// 修改商品属性类型
	$("#goodsAttributeTable .attributeType").live("click", function() {
		var $this = $(this);
		var $optionText = $this.parent().parent().find(".optionText")
		if ($this.val() == "filter") {
			$optionText.attr("disabled", false).show();
		} else {
			$optionText.attr("disabled", true).hide();
		}
	});
	
	// 删除商品属性
	$("#goodsAttributeTable .deleteGoodsAttributeIcon").live("click", function() {
		$(this).parent().parent().remove();
	});	
	
// 增加商品参数
	var goodsParameterIndex = ${(productType.productParameterList?size)!0};
	$addGoodsParameterButton.click( function() {
		
		<@compress single_line = true>
			var goodsParameterTrHtml = 
			'<tr class="goodsParameterTr">
				<td>
					<input type="text" name="productParameterList[' + goodsParameterIndex + '].name" class="formText goodsParameterListName" />
				</td>
				<td>
					<input type="text" name="productParameterList[' + goodsParameterIndex + '].orderList" class="formText goodsParameterListOrderList" style="width: 30px;" />
				</td>
				<td>
					<span class="deleteIcon deleteGoodsParameterIcon" title="删 除">&nbsp;</span>
				</td>
			</tr>';
		</@compress>
		
		$goodsParameterTable.append(goodsParameterTrHtml);
		goodsParameterIndex ++;
	});
	
	// 删除商品参数
	$("#goodsParameterTable .deleteGoodsParameterIcon").live("click", function() {
		var $this = $(this);
		$this.parent().parent().remove();
	});	
	
	
});
</script>
</head>
<body class="input goodsType">
	<div class="bar">
		<#if isAddAction>添加商品类型<#else>编辑商品类型</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>/faith/goods_type/save<#else>/faith/goods_type/update</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" hidefocus />
				</li>
				<li>
					<input type="button" value="商品属性" hidefocus />
				</li>
				<li>
					<input type="button" value="商品参数" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						类型名称: 
					</th>
					<td>
						<input type="text" name="name" class="formText" value="${(productType.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<table id="goodsAttributeTable" class="inputTable tabContent">
				<tr class="noneHover">
					<td colspan="5">
						<input type="button" id="addGoodsAttributeButton" class="formButton" value="增加属性" hidefocus />
					</td>
				</tr>
				<tr class="title">
					<th>
						属性名称
					</th>
					<th>
						属性类型
					</th>
					<th>
						可选项
					</th>
					<th>
						排序
					</th>
					<th>
						删除
					</th>
				</tr>
				<#list (productType.productAttributeSet)! as goodsAttribute>
					<tr class="goodsAttributeTr">
						<td>
							<input type="hidden" name="productAttributeList[${goodsAttribute_index}].id" value="${goodsAttribute.id}" />
							<input type="text" name="productAttributeList[${goodsAttribute_index}].name" class="formText goodsAttributeListName" value="${goodsAttribute.name}" />
						</td>
						<td>
							<select name="productAttributeList[${goodsAttribute_index}].attributeType" class="attributeType">
								<#list attributeTypeList as attributeType>
									<option value="${attributeType}"<#if (attributeType == goodsAttribute.attributeType)!> selected</#if>>
										<@spring.message "AttributeType.${attributeType}"/>
									</option>
								</#list>
							</select>
						</td>
						<td>
							<#if goodsAttribute.attributeType == "filter">
								<input type="text" name="productAttributeList[${goodsAttribute_index}].optionText" class="formText optionText goodsAttributeListOptionText" value="${goodsAttribute.optionText}" title="多个可选值请使用“,”分隔" />
							<#else>
								<input type="text" name="productAttributeList[${goodsAttribute_index}].optionText" class="formText hidden optionText goodsAttributeListOptionText" title="多个可选值请使用“,”分隔" disabled />
							</#if>
						</td>
						<td>
							<input type="text" name="productAttributeList[${goodsAttribute_index}].orderList" class="formText goodsAttributeListOrderList" value="${goodsAttribute.orderList}" style="width: 30px;" />
						</td>
						<td>
							<span class="deleteIcon deleteGoodsAttributeIcon" title="删 除">&nbsp;</span>
						</td>
					</tr>
				</#list>
			</table>
			<table id="goodsParameterTable" class="inputTable tabContent">
				<tr class="noneHover">
					<td colspan="3">
						<input type="button" id="addGoodsParameterButton" class="formButton" value="增加参数" hidefocus />
					</td>
				</tr>
				<tr class="title">
					<th>
						参数名称
					</th>
					<th>
						排序
					</th>
					<th>
						删除
					</th>
				</tr>
				<#list (productType.productParameters)! as goodsParameter>
					<tr class="goodsParameterTr">
						<td>
							<input type="hidden" name="productParameterList[${goodsParameter_index}].id" value="${goodsParameter.id}" />
							<input type="text" name="productParameterList[${goodsParameter_index}].name" class="formText goodsParameterListName" value="${goodsParameter.name}" />
						</td>
						<td>
							<input type="text" name="productParameterList[${goodsParameter_index}].orderList" class="formText goodsParameterListOrderList" value="${goodsParameter.orderList}" style="width: 30px;" />
						</td>
						<td>
							<span class="deleteIcon deleteGoodsParameterIcon" title="删 除">&nbsp;</span>
						</td>
					</tr>
				</#list>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />
				&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>
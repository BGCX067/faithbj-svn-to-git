<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
<script type="text/javascript">
	function generateRendBlock(fieldblockid)
	{
		$.ajax({
		   type: "POST",
		   url: "${base}/vegetableadmin/field_block!generateRendBlock.action",
		   data: "fieldblockid=" + fieldblockid,
		   success: function(msg){
		     alert( msg );
		   }
		});
	}
</script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>土地列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount} (共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="field_block!list.action" method="post">
			<div class="operateBar">
				<input type="button" class="addButton" onclick="location.href='field_block!add.action'" value="添加土地" />
				<label>查找:</label>
				<select name="pager.property">
					
					<option value="code" <#if pager.property == "code">selected="selected" </#if>>
						土地代码
					</option> 
					<option value="name" <#if pager.property == "name">selected="selected" </#if>>
						土地名称
					</option> 
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
			</div>
			<table class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="code">土地代码</span>
					</th>
					<th>
						<span class="sort" name="name">名称</span>
					</th>
					<th>
						<span class="sort" name="area">面积</span>
					</th>
					<th>
						<span class="sort" name="point">积分</span>
					</th>
					<th>
						<span class="sort" name="isMarketable">上架</span>
					</th>
					<th>
						<span class="sort" name="address">详细地址</span>
					</th>
					<th>
						<span class="sort" name="longitudeLatitude">经纬度</span>
					</th>
					<th>
						<span class="sort" name="rent">年租金</span>
					</th>
					<th>
						<span class="sort" name="trusteeFee">年托管费用</span>
					</th>
					<th>
						<span class="sort" name="deliveryFee">年配送费用</span>
					</th>
					<th>
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${list.id}" />
						</td>
						<td>
							${list.code}
						</td>
						<td>
							${list.name}
						</td>
						<td>
							${list.area}
						</td>
						<td>
							${list.point}
						</td>
						<td>
							<#if list.isMarketable == true>
								<img src="${base}/template/admin/images/list_true_icon.gif" />
							<#else>
								<img src="${base}/template/admin/images/list_false_icon.gif" />
							</#if>
						</td>
						<td>
							${list.address}
						</td>
						<td>
							${list.longitudeLatitude}
						</td>
						<td>
							${list.rent}
						</td>
						<td>
							${list.trusteeFee}
						</td>
						<td>
							${list.deliveryFee}
						</td>
						<td>
							<a href="field_block!edit.action?id=${list.id}" title="编辑">[编辑]</a>
							<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" target='_blank' title="浏览">[浏览]</a>
							<a href="#" onclick='generateRendBlock("${list.id}");return false;' title="生成承包地块">[生成承包地块]</a>
							<a href="${base}/vegetableadmin/rend_block!list.action?pager.property=code&pager.keyword=${list.code}" target="_blank" title="查看承包地块">[查看承包地块]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<input type="button" class="deleteButton" url="field_block!delete.action" value="删 除" disabled hidefocus="true" />
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
	</div>
	
</body>
</html>

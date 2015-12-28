<#import "/spring.ftl" as spring/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>配送方式列表 - Powered By <#--${systemConfig.systemName}--></title>

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">
		配送方式管理&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='new'" value="添加方式" hidefocus />
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<a href="#" class="sort" name="name" hidefocus>配送方式名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="deliveryMethod" hidefocus>配送类型</a>
					</th>
					<th>
						<a href="#" class="sort" name="firstWeightPrice" hidefocus>首重价格</a>
					</th>
					<th>
						<a href="#" class="sort" name="continueWeightPrice" hidefocus>续重价格</a>
					</th>
					<th>
						<a href="#" class="sort" name="orderList" hidefocus>排序</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as deliveryType>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${deliveryType.id}" />
						</td>
						<td>
							${deliveryType.name}
						</td>
						<td>
							<@spring.message "DeliveryMethod.${deliveryType.deliveryMethod}"/>
						</td>
						<td>
							${deliveryType.firstWeightPrice}
						</td>
						<td>
							${deliveryType.continueWeightPrice}
						</td>
						<td>
							${deliveryType.orderList}
						</td>
						<td>
							<a href="${deliveryType.id}/edit" title="编辑">[编辑]</a>
							<a href="${deliveryType.id}/delete" title="删 除">[删 除]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					
					<div class="pager">
						<#include "admin/pager.ftl" />
					</div>
				<div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>
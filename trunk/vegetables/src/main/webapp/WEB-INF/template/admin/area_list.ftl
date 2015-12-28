<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>地区列表 - Powered By <#--${systemConfig.systemName}--></title>

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $deleteArea = $("#listTable .deleteArea");
	
	// 地区删除
	$deleteArea.click( function() {
		var $this = $(this);
		$.dialog({type: "warn", content: "您确定删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteArea});
		function deleteArea() {
			var id = $this.attr("areaId");
			$.ajax({
				url: "area!delete.action",
				data: {"id": id},
				type: "POST",
				dataType: "json",
				success: function(data) {
					if (data.status == "success") {
						$this.parent().html("&nbsp;");
					}
					$.message({type: data.status, content: data.message});
				}
			});
		}
		return false;
	});
	
})
</script>
</head>
<body class="list">
	<div class="bar">
		地区管理&nbsp;总记录数: ${areaList?size}
	</div>
	<div class="body">
		<form id="listForm" action="list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='${base}/faith/area/new/<#if parent??>${(parentId)!0}</#if>'" value="添加地区" hidefocus />
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th colspan="5" class="green" style="text-align: center;">
						<#if parent??>上级地区 - [${(parent.name)!}]<#else>顶级地区</#if>
					</th>
				</tr>
				<#list areaList as area>
					<#if (area_index + 1) == 1>
						<tr>
					</#if>
					<td>
						<a href="${base}/faith/area/list/${area.id}" title="查看下级地区">${area.name}</a>
						<a href="${base}/faith/area/${area.id}/edit" title="编辑">[编辑]</a>
						<a href="${base}/faith/area/${area.id}/delete" title="删除">[删除]</a>
					</td>
					<#if (area_index + 1) % 5 == 0 && area_has_next>
						</tr>
						<tr>
					</#if>
					<#if (area_index + 1) % 5 == 0 && !area_has_next>
						</tr>
					</#if>
					<#if (area_index + 1) % 5 != 0 && !area_has_next>
							<td colspan="${5 - areaList?size % 5}">&nbsp;</td>
						</tr>
					</#if>
				</#list>
				<#if areaList?size == 0>
					<tr>
						<td colspan="5" style="text-align: center; color: red;">
							无下级地区! <a href="${base}/faith/area/new/<#if parent??>/${parentId}</#if>" style="color: gray">点击添加</a>
						</td>
					</tr>
				</#if>
			</table>
			<#if parent??>
				<div class="blank"></div>
				<#if parent.parent??>
					<input type="button" class="formButton" onclick="location.href='${base}/faith/area/list/${parent.parent.id}'" value="上级地区" hidefocus />
				<#else>
					<input type="button" class="formButton" onclick="location.href='${base}/faith/area/list'" value="上级地区" hidefocus />
				</#if>
			</#if>
		</form>
	</div>
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑收获记录 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {
	
	$(function() {
		$("#harvestTime").datepicker({dateFormat: 'yy-mm-dd'});
	});
	
	//document.getElementById('rendBlock.isMarketable').value = '${(rendBlock.isMarketable)!}';
})
	
	function chooseFarmingBlock()
	{
		var data = window.showModalDialog('${base}/vegetableadmin/farming_block!popup.action?t=' + new Date().getTime(), '', 'dialogWidth:800px; dialogHeight:600px; center:yes');
		if (data)
		{
			var obj = eval('(' + data + ')');
			document.listForm['harvestRecord.farmingBlockCode'].value = obj.code;
			document.listForm['harvestRecord.farmingBlock.id'].value = obj.id;
			document.listForm['harvestRecord.seedName'].value = obj.seedname;
			document.listForm['harvestRecord.seed.id'].value = obj.seedid;
		}
	}
	function chooseMember()
	{
		var data = window.showModalDialog('${base}/vegetableadmin/vegetable_member!list.action?t=' + new Date().getTime(), '', 'dialogWidth:800px; dialogHeight:600px; center:yes');
		if (data)
		{
			var obj = eval('(' + data + ')');
			document.listForm['harvestRecord.username'].value = obj.username;
		}
	}
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
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加收获记录<#else>编辑收获记录</#if></h1>
		</div>
		<div class="blank"></div>
		<form id="inputForm" name='listForm' class="validate" action="<#if isAdd??>harvest_record!save.action<#else>harvest_record!update.action</#if>" method="post" >
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="harvestRecord.farmingBlock.id" value="${(harvestRecord.farmingBlock.id)!}" />
			<input type="hidden" name="harvestRecord.seed.id" value="${(harvestRecord.seed.id)!}" />
			<div class="blank"></div>
			</ul>
			<table class="inputTable tabContent">
				
				<tr>
					<th>
						收获时间:
					</th>
					<td>
						<input type="text" id='harvestTime' name="harvestRecord.harvestTime" class="formText {required: false, name: true}", value="${(harvestRecord.harvestTime)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						收获耕种地块:
					</th>
					<td>
						<input type="text" name="harvestRecord.farmingBlockCode" readonly=true class="formText {required: true, name: true, maxlength: 255}", title="耕种地块不能为空" value="${(harvestRecord.farmingBlockCode)!}"/>
						<a href='#' onclick='chooseFarmingBlock();return false;'>点此选择耕种地块</a>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						收获作物:
					</th>
					<td>
						<input type="text" name="harvestRecord.seedName" readonly=true class="formText {required: true, name: true}", title="收获作物不能为空" value="${(harvestRecord.seedName)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						收获数量:
					</th>
					<td>
						<input type="text" name="harvestRecord.amount" class="formText {required: true, name: true, digits:true}", title="收获数量不能为空", value="${(harvestRecord.amount)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						收获单位:
					</th>
					<td>
						<input type="text" name="harvestRecord.unit" class="formText {required: true, name: true}", title="收获单位不能为空" value="${(harvestRecord.unit)!}"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						会员:
					</th>
					<td>
						<input type="text" name="harvestRecord.username" class="formText {required: true, name: true}", title="会员不能为空" value="${(harvestRecord.username)!}"/>
						<a href='#' onclick='chooseMember();return false;'>点此选择会员</a>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						操作人:
					</th>
					<td>
						${(harvestRecord.operateUser)!}
					</td>
				</tr>
			
				<tr>
					<th>
						操作时间:
					</th>
					<td>
						${(harvestRecord.operateTime?string('yyyy-MM-dd HH:mm:ss'))!}
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

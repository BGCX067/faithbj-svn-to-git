<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑在线问答 - Powered By <#--${systemConfig.systemName}--></title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>编辑在线管理</h1>
		</div>
		<form id="inputForm" class="validate" action="online!update.action" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						主题:
					</th>
					<td>
						<input type="hidden" name="online.title" class="formText {required: true}" value="${(online.title)!}" />
						${online.title}
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						内容:
					</th>
					<td>
						<input type="hidden" name="online.question" class="formText {required: true}" value="${(online.question)!}" />
						${online.question}
						<label class="requireField">*</label>
					</td>
				</tr>
				
				<tr>
					<th>
						在线回答:
					</th>
					<td>
						<textarea name="online.answer" class="formTextarea">${(online.answer)!}</textarea>
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

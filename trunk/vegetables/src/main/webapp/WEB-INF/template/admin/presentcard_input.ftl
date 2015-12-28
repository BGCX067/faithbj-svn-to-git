<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加礼品卡</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
</head>
<body class="input admin">
	<div class="bar">
		生成礼品卡
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="/faith/presentcard/generateCards" method="post">
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="礼品卡基本信息" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						礼品卡面额: 
					</th>
					<td>
						<input type="text" name="denomination" class="formText" title="礼品卡面额" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
				<th>
						礼品卡中文名: 
					</th>
					<td>
						<input type="text" name="alias" id="password" class="formText" title="长度只允许在4-20之间" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						礼品卡数量: 
					</th>
					<td>
						<input type="text" name="amount" class="formText" />
						<label class="requireField">*</label>
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
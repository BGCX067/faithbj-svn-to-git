<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单 - 土地承包</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		<@sec.authorize ifAnyGranted="ROLE_GOODS,ROLE_GOODS_NOTIFY">
			<dl>
				<dt>
					<span>种子管理&nbsp;</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_GOODS">
					<dd>
						<a href="/faith/seed/list" target="mainFrame">种子列表</a>
					</dd>
					<dd>
						<a href="/faith/seed/add" target="mainFrame">添加种子</a>
					</dd>
				</@sec.authorize>
			</dl>
			<dl>
				<dt>
					<span>土地承包管理&nbsp;</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_GOODS">
					<dd>
						<a href="/faith/goods/landlist/1" target="mainFrame">耕地列表</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>
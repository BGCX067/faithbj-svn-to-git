<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>内容管理</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		<@sec.authorize ifAnyGranted="ROLE_NAVIGATION,ROLE_ARTICLEE,ROLE_ARTICLE_CATEGORY,ROLE_FRIEND_LINK">
			<dl>
				<dt>
					<span>内容管理</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_ARTICLEE">
					<dd>
						<a href="/faith/article/list" target="mainFrame">文章管理</a>
					</dd>
				</@sec.authorize>
				<@sec.authorize ifAnyGranted="ROLE_ARTICLEE">
					<dd>
						<a href="/faith/article_category/list" target="mainFrame">文章分类</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>
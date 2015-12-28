<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单 - Powered By faithby</title>
<meta name="Author" content="faithby Team" />
<meta name="Copyright" content="faithby" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		<dl>
			<dt>
				<span>会员管理</span>
			</dt>
			<@sec.authorize ifAnyGranted="ROLE_MEMBER">
				<dd>
					<a href="list" target="mainFrame">会员列表</a>
				</dd>
			</@sec.authorize>
			<@sec.authorize ifAnyGranted="ROLE_MEMBER_RANK">
				<dd>
					<a href="/faith/member_rank/list" target="mainFrame">会员等级</a>
				</dd>
			</@sec.authorize>
			<@sec.authorize ifAnyGranted="ROLE_MEMBER_ATTRIBUTE">
				<dd>
					<a href="/faith/member_attribute/list" target="mainFrame">会员注册项</a>
				</dd>
			</@sec.authorize>
		</dl>
		<@sec.authorize ifAnyGranted="ROLE_COMMENT">
			<dl>
				<dt>
					<span>商品评论</span>
				</dt>
				<dd>
					<a href="comment/list" target="mainFrame">评论列表</a>
				</dd>
				<dd>
					<a href="comment/setting" target="mainFrame">评论设置</a>
				</dd>
			</dl>
		</@sec.authorize>
		<@sec.authorize ifAnyGranted="ROLE_LEAVE_MESSAGE">
			<dl>
				<dt>
					<span>在线留言</span>
				</dt>
				<dd>
					<a href="leave_message/list" target="mainFrame">留言列表</a>
				</dd>
				<dd>
					<a href="leave_message/setting" target="mainFrame">留言设置</a>
				</dd>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>
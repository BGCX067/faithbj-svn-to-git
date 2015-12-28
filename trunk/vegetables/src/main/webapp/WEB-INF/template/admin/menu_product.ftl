<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		<@sec.authorize ifAnyGranted="ROLE_GOODS,ROLE_GOODS_NOTIFY">
			<dl>
				<dt>
					<span>商品管理&nbsp;</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_GOODS">
					<dd>
						<a href="/faith/goods/list" target="mainFrame">商品列表</a>
					</dd>
					<dd>
						<a href="/faith/goods/new" target="mainFrame">添加商品</a>
					</dd>
				</@sec.authorize>
				<@sec.authorize ifAnyGranted=('ROLE_GOODS_NOTIFY')>
					<dd>
						<a href="goods_notify!list.action" target="mainFrame">到货通知</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
		<@sec.authorize ifAnyGranted="ROLE_GOODS_CATEGORY">
			<dl>
				<dt>
					<span>商品分类管理&nbsp;</span>
				</dt>
				<dd>
					<a href="${base}/faith/goods_category/list" target="mainFrame">分类列表</a>
				</dd>
				<dd>
					<a href="${base}/faith/goods_category/add" target="mainFrame">添加分类</a>
				</dd>
			</dl>
		</@sec.authorize>
		<@sec.authorize ifAnyGranted="ROLE_GOODS_TYPE,ROLE_GOODS_ATTRIBUTE">
			<dl>
				<dt>
					<span>商品类型管理&nbsp;</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_GOODS_TYPE">
					<dd>
						<a href="/faith/goods_type/list" target="mainFrame">类型列表</a>
					</dd>
					<dd>
						<a href="/faith/goods_type/new" target="mainFrame">添加类型</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
		<@sec.authorize ifAnyGranted="ROLE_BRAND">
			<dl>
				<dt>
					<span>品牌管理&nbsp;</span>
				</dt>
				<dd>
					<a href="/faith/brand/list" target="mainFrame">品牌列表</a>
				</dd>
				<dd>
					<a href="/faith/brand/add" target="mainFrame">添加品牌</a>
				</dd>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>
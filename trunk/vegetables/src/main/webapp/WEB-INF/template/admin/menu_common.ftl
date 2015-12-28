<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/common/include.ftl">
<link href="${base}/vegetables/admin/css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="menuContent">
		<dl>
			<dt>
				<span>商品管理</span>
			</dt>
			<dd>
				<a href="${base}/adminProduct/list" target="mainFrame">商品列表</a>
			</dd>
			<dd>
				<a href="${base}/adminProduct/add" target="mainFrame">添加商品</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>新闻管理</span>
			</dt>
			<dd>
				<a href="${base}/article/list" target="mainFrame">新闻列表</a>
			</dd>
			<dd>
				<a href="${base}/article/add" target="mainFrame">添加新闻</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>订单管理</span>
			</dt>
			<dd>
				<a href="${base}/order/list" target="mainFrame">配送单列表</a>
			</dd>
		</dl> 
		<dl>
			<dt>
				<span>礼品卡管理</span>
			</dt>
			<dd>
				<a href="${base}/deposit/createCard" target="mainFrame">创建礼品卡</a>
			</dd>
			<dd>
				<a href="${base}/deposit/manageCard" target="mainFrame">管理礼品卡</a>
			</dd>
			<dd>
				<a href="${base}/deposit/list" target="mainFrame">下发礼品卡</a>
			</dd>
		</dl>
	</div>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/menu.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="menuContent">
		<dl>
			<dt>
				<span>土地管理</span>
			</dt>
			<dd>
				<a href="field_block!list.action" target="mainFrame">土地列表</a>
			</dd>
			<dd>
				<a href="rend_block!list.action" target="mainFrame">承包地块列表</a>
			</dd>
			<dd>
				<a href="farming_block!list.action" target="mainFrame">耕种地块列表</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>收获记录管理</span>
			</dt>
			<dd>
				<a href="harvest_record!list.action" target="mainFrame">收获记录列表</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>默认图片管理</span>
			</dt>
			<dd>
				<a href="farming_block_default_image!list.action" target="mainFrame">耕种地块默认图片管理</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>种子管理</span>
			</dt>
			<dd>
				<a href="seed!list.action" target="mainFrame">种子列表</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>待处理耕种地块</span>
			</dt>
			<dd>
				<a href="farming_block!list.action?farmingBlockStatus=2" target="mainFrame">待播种耕种地块</a>
			</dd>
			<dd>
				<a href="farming_block!list.action?farmingBlockStatus=8" target="mainFrame">待清理耕种地块</a>
			</dd>
		</dl>
		<dl>
			<dt>
				<span>日志</span>
			</dt>
			<dd>
				<a href="rend_log!list.action" target="mainFrame">承包日志</a>
			</dd>
			<dd>
				<a href="farming_log!list.action" target="mainFrame">耕种日志</a>
			</dd>
		</dl>
	</div>
</body>
</html>

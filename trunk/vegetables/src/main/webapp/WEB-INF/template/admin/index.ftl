<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 - Powered By faithbj</title>
<meta name="Author" content="faithbj" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
</head>
<body class="index">
	<div class="bar">
		欢迎使用菜精灵网店管理系统！
	</div>
	<div class="body">
		<div class="bodyLeft">
			<table class="listTable">
				<tr>
					<th colspan="2">
						软件信息
					</th>
				</tr>
				<tr>
					<td width="110">
						系统名称: 
					</td>
					<td>
						菜精灵网
					</td>
				</tr>
				<tr>
					<td>
						系统版本: 
					</td>
					<td>
						1.0.0
					</td>
				</tr>
				<tr>
					<td>
						官方网站: 
					</td>
					<td>
						<a href="http://www.caijingling.com.cn">http://www.caijingling.com.cn</a>
					</td>
				</tr>
				<tr>
					<td>
						交流论坛: 
					</td>
					<td>
						<a href="http://bbs.faithbj.com">http://bbs.faithbj.com</a>
					</td>
				</tr>
				<tr>
					<td>
						BUG反馈邮箱: 
					</td>
					<td>
						<a href="mailto:faithbj@163.com">faithbj@163.com</a>
					</td>
				</tr>
				<tr>
					<td>
						商业授权: 
					</td>
					<td>
						未取得精诚软件工作室授权之前,您不能将本软件应用于其他商业用途
					</td>
				</tr>
			</table>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">
						待处理事务
					</th>
				</tr>
				<tr>
					<td width="110">
						未处理订单: 
					</td>
					<td>
						${unprocessedOrderCount} <a href="order/list">[订单列表]</a>
					</td>
				</tr>
				<tr>
					<td width="110">
						等待发货订单数: 
					</td>
					<td>
						${paidUnshippedOrderCount} <a href="order/list">[订单列表]</a>
					</td>
				</tr>
				<tr>
					<td>
						未读消息: 
					</td>
					<td>
						${unreadMessageCount} <a href="message/inbox">[收件箱]</a>
					</td>
				</tr>
				<tr>
					<td>
						未处理缺货登记数: 
					</td>
					<td>
						${unprocessedGoodsNotifyCount} <a href="goods_notify/list">[到货通知]</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="bodyRight">
			<table class="listTable">
				<tr>
					<th colspan="2">
						系统信息
					</th>
				</tr>
				<tr>
					<td width="110">
						Java版本: 
					</td>
					<td>
						${javaVersion}
					</td>
				</tr>
				<tr>
					<td>
						操作系统名称: 
					</td>
					<td>
						${osName}
					</td>
				</tr>
				<tr>
					<td>
						操作系统构架: 
					</td>
					<td>
						${osArch}
					</td>
				</tr>
				<tr>
					<td>
						操作系统版本: 
					</td>
					<td>
						${osVersion}
					</td>
				</tr>
				<tr>
					<td>
						Server信息: 
					</td>
					<td>
						${serverInfo}
					</td>
				</tr>
				<tr>
					<td>
						Servlet版本: 
					</td>
					<td>
						${servletVersion}
					</td>
				</tr>
			</table>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">
						信息统计
					</th>
				</tr>
				<tr>
					<td width="110">
						已上架商品: 
					</td>
					<td>
						${marketableGoodsCount}
					</td>
				</tr>
				<tr>
					<td>
						已下架商品: 
					</td>
					<td>
						${unMarketableGoodsCount}
					</td>
				</tr>
				<tr>
					<td>
						会员总数: 
					</td>
					<td>
						${memberTotalCount}
					</td>
				</tr>
				<tr>
					<td>
						文章总数: 
					</td>
					<td>
						${articleTotalCount}
					</td>
				</tr>
			</table>
		</div>
		<p class="copyright">Copyright © 2012-2014 Caijingling.com.cn All Rights Reserved.</p>
	</div>
</body>
</html>
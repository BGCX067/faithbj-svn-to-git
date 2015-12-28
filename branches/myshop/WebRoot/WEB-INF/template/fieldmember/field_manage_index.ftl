<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地会员中心 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body memberCenterIndex">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">
					<strong>${loginMember.username}的自留地
				</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<td colspan="4">
								您目前是[${loginMember.memberRank.name}]
								<#if loginMember.memberRank.preferentialScale != 100>
									<span class="red">[优惠百分比：${loginMember.memberRank.preferentialScale}%]</span>
								</#if>
							</td>
						</tr>
						<tr>
							<th>我的承包地块总数量</th>
							<td>
								${rendBlockSize}&nbsp;&nbsp;
								<a href="order!list.action">[查看我的承包地块]</a>
							</td>
						</tr>
						<tr>
							<th>[空闲]耕种地块</th>
							<td>
								${freeSize}&nbsp;&nbsp;
								<a href="my_farming_block!list.action?farmingBlockStatus=0">[查看空闲耕种地块]</a>
							</td>
							<th>[播种]耕种地块</th>
							<td>
								${seedingSize}&nbsp;&nbsp;
								<a href="my_farming_block!list.action?farmingBlockStatus=2">[查看播种耕种地块]</a>
							</td>
						</tr>
						<tr>
							<th>[生长]耕种地块</th>
							<td>
								${growingSize}&nbsp;&nbsp;
								<a href="my_farming_block!list.action?farmingBlockStatus=4">[查看生长耕种地块]</a>
							</td>
							<th>[收获]耕种地块</th>
							<td>
								${harvestSize}&nbsp;&nbsp;
								<a href="my_farming_block!list.action?farmingBlockStatus=6">[查看收获耕种地块]</a>
							</td>
						</tr>
						<tr>
							<th>[清理]耕种地块</th>
							<td>
								${cleanSize}&nbsp;&nbsp;
								<a href="my_farming_block!list.action?farmingBlockStatus=8">[查看清理耕种地块]</a>
							</td>
						</tr>
					</table>
					
					<table class="listTable" style='margin-top:5px;'>
						<tr>
							<th>我的收获记录</th>
							<td>
								${rendBlockSize}&nbsp;&nbsp;
								<a href="my_harvest_record!list.action">[查看我的收获记录]</a>
							</td>
						</tr>
					</table>
					
					<table class="listTable" style='margin-top:5px;'>
						<tr>
							<th>我的承包地块收藏</th>
							<td>
								${favoriteSize}&nbsp;&nbsp;
								<a href="field_favorite!list.action">[查看我的承包地块收藏]</a>
							</td>
						</tr>
					</table>
					
					<table class="listTable" style='margin-top:5px;'>
						<tr>
							<th>我的购物车中的承包地块</th>
							<td>
								${fieldBlockCartItemSize}&nbsp;&nbsp;
								<a href="field_favorite!list.action">[查看购物车中承包地块]</a>
							</td>
						</tr>
					</table>
					
					<div class="blank"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>

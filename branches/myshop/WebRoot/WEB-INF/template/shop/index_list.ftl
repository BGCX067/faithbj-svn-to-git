<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜精灵网 - Powered By Vegetable Fairy</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<meta name="keywords" content="菜精灵" />
<meta name="description" content="Fairy" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="/template/common/css/jquery.datepicker.css" rel="stylesheet" type="text/css" />
<link href="/template/common/css/jquery.zoomimage.css" rel="stylesheet" type="text/css" />
<link href="/template/common/css/ui.all.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/template/common/js/jquery.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.form.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.metadata.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.validate.cn.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.jqDnR.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.jqModal.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.qtip.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.livequery.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.superfish.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.hoverIntent.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.datepicker.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/template/common/js/jquery.zoomimage.js"></script>
<script type="text/javascript" src="/template/common/tiny_mce/jquery.tinymce.js"></script>

<script type="text/javascript" src="/template/common/js/ui.core.js"></script>
<script type="text/javascript" src="/template/common/js/ui.datepicker.js"></script>

<!--[if lte IE 6]>
<script type="text/javascript" src="/template/common/js/belatedPNG.js"></script>
<![endif]-->
<script type="text/javascript" src="/template/common/js/base.js"></script><link href="/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="/template/shop/css/index.css" rel="stylesheet" type="text/css" />
<link href="/template/shop/css/product.css" rel="stylesheet" type="text/css" />
<link href="/template/shop/css/article.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/template/shop/js/login.js"></script>
<script type="text/javascript" src="/template/shop/js/register.js"></script>
<script type="text/javascript" src="/template/shop/js/index.js"></script>
<script type="text/javascript" src="/template/shop/js/product.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$(".slider .scrollable").scrollable({
		circular: true,
		speed: 500
	}).autoscroll({
		autoplay: true,
		interval: 4000
	}).navigator();
	
	$(".hotProduct .scrollable").scrollable({
		circular: true,
		speed: 500
	});
	
	$(".newProduct ul.newProductTab").tabs(".newProduct .newProductTabContent", {
		effect: "fade",// 逐渐显示动画
		fadeInSpeed: 500,// 动画显示速度
		event: "mouseover"// 触发tab切换的事件
	});

})
</script>
</head>
<body class="index">
<script type="text/javascript" src="/template/shop/js/header.js"></script>
<link href="/template/shop/css/header.css" rel="stylesheet" type="text/css" />
<div class="header png">
	<div class="headerTop png">
		<div class="headerTopContent">
			<div class="headerLoginInfo">
				您好<span id="headerLoginMemberUsername"></span>，欢迎来到菜精灵网！
				<a href="#" id="headerShowLoginWindow" class="showLoginWindow">登录</a>
				<a href="/shop/member_center!index.action" id="headerMemberCenter">会员中心</a>
				<a href="#" id="headerShowRegisterWindow" class="showRegisterWindow">注册</a>
				<a href="/shop/member!logout.action" id="headerLogout">[退出]</a>
			</div>
			<div class="headerTopNav">
					<a href="javascript: addFavorite('http://www.faithbj.com','菜精灵网')">收藏本站</a>
					|
					<a href="/shop/article!list.action?id=402881882ba8455f012ba86db8560006">帮助中心</a>
					|
					<a href="/html/article_content/201010/5010509a555548939840a3103d3cea71.html">联系我们</a>
					
			</div>
		</div>
	</div>
	<div class="headerMiddle">
		<div class="headerInfo">
			7×24小时服务热线：<strong>010-8888888</strong>
		</div>
		<div class="headerLogo">
			<a href="/"><img class="png" src="/upload/image/logo.png" title="菜精灵网" /></a>
		</div>
		<div class="headerSearch png">
			<form id="productSearchForm" action="/shop/product!search.action" method="get">
				<div class="headerSearchText">
					<input type="text" id="productSearchKeyword" name="pager.keyword" value="请输入关键词..."}" />
				</div>
				<input type="submit" class="headerSearchButton" value="" />
				<div class="hotKeyword">
					热门关键词: 
						<a href="/shop/product!search.action?pager.keyword=%E8%8B%B9%E6%9E%9C">苹果</a>
						<a href="/shop/product!search.action?pager.keyword=%E6%9C%89%E6%9C%BA%E8%94%AC%E8%8F%9C">有机蔬菜</a>
						<a href="/shop/product!search.action?pager.keyword=%E5%A4%A7%E6%A3%9A%E8%94%AC%E8%8F%9C">大棚蔬菜</a>
				</div>
			</form>
		</div>
	</div>
	<div class="headerBottom">
		<input type="button" class="cartItemListButton showCartItemList" value="" onclick="window.open('/shop/cart_item!list.action')" />
		<ul id="cartItemListDetail"></ul>
		<input type="button" class="orderButton" value="" onclick="window.open('/shop/cart_item!list.action')" />
		<div class="headerMiddleNav">
			<div class="headerMiddleNavLeft png"></div>
			<ul class="headerMiddleNavContent png">
					<li>
						<a href="/">首页</a>
					</li>
					<li>
						<a href="/shop/article!list.action?id=402881882ba8455f012ba86db8560006">新闻区</a>
					</li>
					<li>
						<a href="/fieldmember/field_manage!index.action">土地承包</a>
					</li>
					<li>
						<a href="/shop/product!list.action?id=402881882ba8753a012ba95d56ae01cc">定期配送</a>
					</li>
					<li>
						<a href="/shop/product!list.action?id=402881882ba8753a012ba955ca2001c4">零售用户</a>
					</li>
					<li>
						<a href="/shop/product!list.action?id=402881882ba8753a012ba954f95301c2">礼品卡</a>
					</li>
			</ul>
			<div class="headerMiddleNavRight png"></div>
		</div>
	</div>
</div>	<div class="body">
		<div class="bodyLeft">
			<div class="productCategory">
            	<div class="top">商品分类</div>
            	<div class="middle clearfix">
            		<ul class="menu">
            				<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba9545eb901c1">蔬菜类</a>
							</li>
									<li>
										<a href="/shop/product!list.action?id=402881882ba8753a012ba95b38d001ca"><span class="icon">&nbsp;</span>根菜类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=402881882bb2c00c012bb2ddb237000e"><span class="icon">&nbsp;</span>葱蒜类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cf97190134cfcb882a0002"><span class="icon">&nbsp;</span>叶菜类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cf97190134cfcbd7350003"><span class="icon">&nbsp;</span>瓜菜类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cf97190134cfcc363d0004"><span class="icon">&nbsp;</span>茄果类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cf97190134cfcc88c20005"><span class="icon">&nbsp;</span>芽苗菜</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cf97190134cfcd2dbc0007"><span class="icon">&nbsp;</span>豆类</a>
									</li>
									<li>
										<a href="/shop/product!list.action?id=8ac7440334cfdbf80134d17cb51c0019"><span class="icon">&nbsp;</span>菌类</a>
									</li>
            				<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba952154601c0">禽蛋类</a>
							</li>
									
            				<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba95d56ae01cc">肉类</a>
							</li>
							
							<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba955ca2001c4">水果类</a>
							</li>
								
            				<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba95d56ae01cc">肉类</a>
							</li>
            				<li class="mainCategory">
								<a href="/shop/product!list.action?id=402881882ba8753a012ba955ca2001c4">水果</a>
							</li>
					</ul>
            	</div>
                <div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="slider">
				<div class="scrollable">
					<div class="items">
						<div>
							<img src="/upload/image/banner2.jpg" />
						</div>
						<div>
							<img src="/upload/image/banner3.jpg" />
						</div>
						<div>
							<img src="/upload/image/大白菜.jpg" />
						</div>
						<div>
							<img src="/upload/image/baicai.jpg" />
						</div>
					</div>
					<div class="navi"></div>
					<div class="prevNext">
						<a class="prev browse left"></a>
						<a class="next browse right"></a>
					</div>
				</div>
			</div>
			<div class="blank"></div>
			<div class="hotProduct">
				<div class="title">
					<strong>热卖商品</strong>HOT
				</div>
				<a class="prev browse"></a>
				<div class="scrollable">
					<div class="items">
								<div>
								<ul>
							<li>
								<a href="/html/product_content/201201/993203b89d2147c99d94429e31e1cbd5.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="山药" />
										<p title="山药">姜</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/74b138d706f6496b94e4856d6cadbf55.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="姜" />
										<p title="姜">山药</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/1504775c86a740eeb7f4e319e383c234.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="马铃薯" />
										<p title="马铃薯">马铃薯</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/e55e51e03eee4b3aa2361584a9498311.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="甘蓝" />
										<p title="甘蓝">甘蓝</p>
								</a>
							</li>
								</ul>
								</div>
								<div>
								<ul>
							<li>
								<a href="/html/product_content/201201/1baa80d5932b4a69a1db425c8f4f45ff.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="香菇" />
										<p title="香菇">香菇</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/865c24fac0ae442997f3e30ad5178e50.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="金针菇" />
										<p title="金针菇">金针菇</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/fe0ed12ba2234665af30cb679a39a998.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="木耳" />
										<p title="木耳">木耳</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/c7e2a83cb50d40c8bbc776dddded25ba.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="毛豆" />
										<p title="毛豆">毛豆</p>
								</a>
							</li>
								</ul>
								</div>
								<div>
								<ul>
							<li>
								<a href="/html/product_content/201201/1b2b2caf53964df58c7a0d0fe2b08223.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="长豇豆" />
										<p title="长豇豆">长豇豆</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/93d23133f29a4576aacbe26ae030acd6.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="四季豆" />
										<p title="四季豆">四季豆</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/c2d01bfa721440e08708bb6183921097.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="香椿芽" />
										<p title="香椿芽">香椿芽</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/44ea28c273754c8da3a57b6cbd8f8873.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="黄豆芽" />
										<p title="黄豆芽">黄豆芽</p>
								</a>
							</li>
								</ul>
								</div>
					</div>
				</div>
				<a class="next browse"></a>
			</div>
		</div>
		<div class="blank"></div>
		<img src="/upload/image/banner4.jpg" />
		<div class="blank"></div>
		<div class="newProduct">
			<div class="left">
				<ul class="newProductTab">
						<li>
							蔬菜类
						</li>
						<li>
							禽蛋类
						</li>
						<li>
							肉类
						</li>
						<li>
							水果
						</li>
				</ul>
			</div>
			<div class="right">
					<ul class="newProductTabContent">
							<li>
								<a href="/html/product_content/201201/993203b89d2147c99d94429e31e1cbd5.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="山药" />
										<p title="山药">山药</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/74b138d706f6496b94e4856d6cadbf55.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="姜" />
										<p title="姜">姜</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/1504775c86a740eeb7f4e319e383c234.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="马铃薯" />
										<p title="马铃薯">马铃薯</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/e55e51e03eee4b3aa2361584a9498311.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="甘蓝" />
										<p title="甘蓝">甘蓝</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/05e935be0ddb4449b316fd3e14fac19e.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="水萝卜" />
										<p title="水萝卜">水萝卜</p>
								</a>
							</li>
					</ul>
					<ul class="newProductTabContent">
					</ul>
					<ul class="newProductTabContent">
					</ul>
					<ul class="newProductTabContent">
					</ul>
			</div>
		</div>
		<div class="blank"></div>
		<div class="bodyLeft">
			<div class="hotProduct">
				<div class="top">热销排行</div>
				<div class="middle clearfix">
					<ul>
							<li class="number1">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/993203b89d2147c99d94429e31e1cbd5.html" title="山药">山药</a>
							</li>
							<li class="number2">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/74b138d706f6496b94e4856d6cadbf55.html" title="姜">姜</a>
							</li>
							<li class="number3">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/1504775c86a740eeb7f4e319e383c234.html" title="马铃薯">马铃薯</a>
							</li>
							<li class="number4">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/e55e51e03eee4b3aa2361584a9498311.html" title="甘蓝">甘蓝</a>
							</li>
							<li class="number5">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/1baa80d5932b4a69a1db425c8f4f45ff.html" title="香菇">香菇</a>
							</li>
							<li class="number6">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/865c24fac0ae442997f3e30ad5178e50.html" title="金针菇">金针菇</a>
							</li>
							<li class="number7">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/fe0ed12ba2234665af30cb679a39a998.html" title="木耳">木耳</a>
							</li>
							<li class="number8">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/c7e2a83cb50d40c8bbc776dddded25ba.html" title="毛豆">毛豆</a>
							</li>
							<li class="number9">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/1b2b2caf53964df58c7a0d0fe2b08223.html" title="长豇豆">长豇豆</a>
							</li>
							<li class="number10">
									<span class="icon">&nbsp;</span><a href="/html/product_content/201201/93d23133f29a4576aacbe26ae030acd6.html" title="四季豆">四季豆</a>
							</li>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="hotArticle">
				<div class="top">热点新闻</div>
				<div class="middle clearfix">
					<ul>
						<#list (hotArticleList)! as list>
							<li class="number${list_index + 1}">
								<#if (list.title?length < 15)>
									<span class="icon">&nbsp;</span><a href="${base}${list.htmlFilePath}" title="${list.title}">${list.title}</a>
								<#else>
									<span class="icon">&nbsp;</span><a href="${base}${list.htmlFilePath}" title="${list.title}">${list.title[0..12]}...</a>
								</#if>
							</li>
							<#if list_index + 1 == 10>
								<#break/>
							</#if>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="bestProduct">
				<div class="top">
					<strong>精品推荐</strong>BEST
				</div>
				<div class="middle">
					<ul>
							<li>
								<a href="/html/product_content/201201/993203b89d2147c99d94429e31e1cbd5.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="山药" />
										<p title="山药">山药</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/74b138d706f6496b94e4856d6cadbf55.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="姜" />
										<p title="姜">姜</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/1504775c86a740eeb7f4e319e383c234.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="马铃薯" />
										<p title="马铃薯">马铃薯</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/e55e51e03eee4b3aa2361584a9498311.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="甘蓝" />
										<p title="甘蓝">甘蓝</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/1baa80d5932b4a69a1db425c8f4f45ff.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="香菇" />
										<p title="香菇">香菇</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/865c24fac0ae442997f3e30ad5178e50.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="金针菇" />
										<p title="金针菇">金针菇</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/fe0ed12ba2234665af30cb679a39a998.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="木耳" />
										<p title="木耳">木耳</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/c7e2a83cb50d40c8bbc776dddded25ba.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="毛豆" />
										<p title="毛豆">毛豆</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/1b2b2caf53964df58c7a0d0fe2b08223.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="长豇豆" />
										<p title="长豇豆">长豇豆</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/93d23133f29a4576aacbe26ae030acd6.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="四季豆" />
										<p title="四季豆">四季豆</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/c2d01bfa721440e08708bb6183921097.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="香椿芽" />
										<p title="香椿芽">香椿芽</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
							<li>
								<a href="/html/product_content/201201/44ea28c273754c8da3a57b6cbd8f8873.html">
									<img src="/upload/image/default_thumbnail_product_image.jpg" alt="黄豆芽" />
										<p title="黄豆芽">测试下</p>
									<p class="red">￥0.00</p>
								</a>
							</li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
<link href="/template/shop/css/friend_link.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/template/shop/js/friend_link.js"></script>
<div class="friendLink">
	<div class="pictureFriendLink">
		<div class="left prev"></div>
		<div class="middle scrollable">
			<ul class="items">
					<li>
						<a href="http://www.boc.cn" target="_blank" title="中国银行">
							<img src="/upload/image/d7f330b36cc1468e978a83b5edeb010e.gif">
						</a>
					</li>
					<li>
						<a href="http://cmpay.10086.cn/" target="_blank" title="手机支付">
							<img src="/upload/image/b798c45e177e4486b3d91df376ffa605.gif">
						</a>
					</li>
					<li>
						<a href="https://www.alipay.com" target="_blank" title="支付宝">
							<img src="/upload/image/351cf1e552314fa187c8cd2d4b1b28cb.png">
						</a>
					</li>
					<li>
						<a href="https://www.tenpay.com" target="_blank" title="财付通">
							<img src="/upload/image/fc36b46c50a6485ca2b8e535d63c6a21.png">
						</a>
					</li>
					<li>
						<a href="https://www.99bill.com" target="_blank" title="快钱">
							<img src="/upload/image/3f9825f3d5d84676865c4d5d6f67abaa.png">
						</a>
					</li>
					<li>
						<a href="http://www.icbc.com.cn" target="_blank" title="工商银行">
							<img src="/upload/image/d8c231353b594de89ab0e9954ced0335.png">
						</a>
					</li>
					<li>
						<a href="http://www.ccb.com/cn/home/index.html" target="_blank" title="建设银行">
							<img src="/upload/image/e7b2b595a2724b87aa4fdecd34c75c79.png">
						</a>
					</li>
					<li>
						<a href="http://www.abchina.com/cn/default.htm" target="_blank" title="农业银行">
							<img src="/upload/image/e11494cddc0a4d66ad4a90588168fa75.png">
						</a>
					</li>
			</ul>
		</div>
		<div class="right next"></div>
	</div>
	<div class="textFriendLink">
		<div class="left"></div>
		<div class="middle">
			<ul>
					<li>
						<a href="https://www.alipay.com" target="_blank" title="支付宝">支付宝</a>
					</li>
					<li>
						<a href="https://www.tenpay.com" target="_blank" title="财付通">财付通</a>
					</li>
					<li>
						<a href="https://www.99bill.com" target="_blank" title="快钱">快钱</a>
					</li>
					<li>
						<a href="http://www.baidu.com" target="_blank" title="百度">百度</a>
					</li>
					<li>
						<a href="http://www.google.com.hk" target="_blank" title="Google">Google</a>
					</li>
			</ul>
		</div>
		<div class="right"></div>
	</div>
</div>	</div>
	<div class="blank"></div>
<link href="/template/shop/css/footer.css" type="text/css" rel="stylesheet" />
<div class="footer">
	<div class="bottomNavigation">
				<dl>
				<dd>
					<a href="/html/article_content/201010/9102600e47b7401b843ad3722e1e9b70.html">购物流程</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/90eb7634990341909027a5e20245b3e1.html">网站制度</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/50588403d9dd494c9035fa763e49e112.html">订单查询</a>
				</dd>
				</dl>
				<dl>
				<dd>
					<a href="/html/article_content/201010/390aed8de096473aa3fd928c25bf9fb9.html">积分说明</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/c55109104bb241c7a51605602b1531df.html">会员注册协议</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/0340b92488ca464aa8d545fb899dcd25.html">会员等级</a>
				</dd>
				</dl>
				<dl>
				<dd>
					<a href="/html/article_content/201010/fd97e448663c4f5a8f033743328a8038.html">网上支付</a>
				</dd>
				<dd>
					<a href="/shopxx/html/article_content/201010/fd97e448663c4f5a8f033743328a8038.html">邮局汇款</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/ac6f0b7c351c41058dc800ea0091e26c.html">退货政策</a>
				</dd>
				</dl>
				<dl>
				<dd>
					<a href="/html/article_content/201010/070088f2df794b26b03bdf022565f1f2.html">关于我们</a>
				</dd>
				<dd>
					<a href="../shop/online!list.action">在线问答</a>
				</dd>
				<dd>
					<a href="/html/article_content/201010/cb4b35cc2fd14ddbb20b49d7637fab2f.html">常见问题</a>
				</dd>
				</dl>
	</div>
	<div class="footerInfo">
<ul>
<li><a>关于商城</a>|</li>
<li><a>帮助中心</a>|</li>
<li><a>网站地图</a>|</li>
<li><a>诚聘英才</a>|</li>
<li><a>联系我们</a>|</li>
<li><a>版权说明</a></li>
</ul>
<ul>
<p>Copyright &copy; 2012 MyShop. All rights reserved. Peking Faithbg.com</p>
<p>Powered by <a class="systemName" href="http://www.faithbj.com" target="_blank">MySHOP V2012</a></p>
</ul>	</div>
</div></body>
</html>

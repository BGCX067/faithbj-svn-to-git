<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<#import "/spring.ftl" as spring/>
<div id="header" class="header belatedPNG">
	<div class="headerTop belatedPNG">
		<div class="headerTopContent">
			<div class="headerLoginInfo">
				您好<span id="headerLoginMemberUsername"></span>, 欢迎来到菜精灵!
<#--				
<#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    <@spring.message "Gender.male"/>
</#if>

<sec:authentication property='name' />
	<@sec.authorize ifAnyGranted="ROLE_MEMBER,ROLE_RENT,ROLE_DELIVERY">
				哈哈哈
				</@sec.authorize>
-->			
				<a href="javascript: void(0);" id="headerShowLoginWindow">登录</a>
				<a href="${base}/cjlhome/member_center/index" id="headerMemberCenter">会员中心</a>
				<a href="javascript: void(0);" id="headerShowRegisterWindow">注册</a>
				<a href="/cjlhome/logout" id="headerLogout">[退出]</a>
			</div>
			<div class="headerTopNav">
					<#list navigationList as navigation>
						<a href='<@navigation.url?interpret />' <#if navigation.isBlankTarget> target="_blank"</#if>>${navigation.navigationName}
						</a>
						<#if navigation_has_next>|</#if>
					</#list>
			</div>
		</div>
	</div>
	<div class="headerMiddle">
		<div class="headerInfo">
			<#if (setting.phone?? && setting.phone != "")!>
				7×24小时服务热线: <strong>${setting.phone}</strong>
			</#if>
		</div>
		<div class="headerLogo">
			<a href="${base}/"><img class="belatedPNG" src="${base}/${(setting.shopLogo)!}" alt="${(setting.shopName)!}" /></a>
		</div>
		<div class="headerSearch belatedPNG">
			<form id="goodsSearchForm" action="${base}/shop/goods!search.action" method="post">
				<div class="headerSearchText">
					<input type="text" id="goodsSearchKeyword" name="pager.keyword" value="<#if (pager.keyword)??>${pager.keyword}<#else>请输入关键词...</#if>" />
				</div>
				<input type="submit" class="headerSearchButton" value="" />
				<div class="hotKeyword">
					热门关键词: 
					<#--
					<#list setting.hotSearchList as hotSearch>
						<a href="${base}/shop/product!search/pager.keyword=${hotSearch?url}">${hotSearch}</a>
					</#list>
					-->
				</div>
			</form>
		</div>
	</div>
	<div class="headerBottom">
		<input type="button" class="cartItemListButton" value="" onclick="window.open('${base}/product/caritem/list')" />
		<input type="button" class="orderButton" value="" onclick="window.open('${base}/product/caritem/list')" />
		<div class="headerMiddleNav">
			<div class="headerMiddleNavLeft belatedPNG"></div>
			<ul class="headerMiddleNavContent belatedPNG">
				
					<#list middleNavigationList as navigation>
						<li>
							<a href="<@navigation.url?interpret />"
								<#if navigation.isBlankTarget> target="_blank"</#if>>${navigation.navigationName}
							</a>
						</li>
					</#list>
			</ul>
			<div class="headerMiddleNavRight belatedPNG"></div>
		</div>
	</div>
</div>
<div class="footer">
	<div class="bottomNavigation">
			<#list bottomNavigationList as navigation>
				<#if (navigation_index + 1) == 1>
					<dl>
				</#if>
					<dd>
						<a href="<@navigation.url?interpret />"<#if navigation.isBlankTarget> target="_blank"</#if>>${navigation.name}</a>
					</dd>
				<#if (navigation_index + 1) % 3 == 0 && navigation_has_next>
					</dl>
					<dl>
				</#if>
				<#if !navigation_has_next>
					</dl>
				</#if>
			</#list>
	</div>
	<div class="footerInfo">
		<ul>
			<li><a href="#">关于商城</a>|</li>
			<li><a href="#">帮助中心</a>|</li>
			<li><a href="#">网站地图</a>|</li>
			<li><a href="#">诚聘英才</a>|</li>
			<li><a href="#">联系我们</a>|</li>
			<li><a href="#">版权说明</a></li>
		</ul>
		<p>Copyright &copy; 2012 caijingling.com.cn. All rights reserved. 菜精灵</p>
			<p>Powered by <a class="systemName" href="http://www.faithbj.com" target="_blank">精诚软件工作室</a></p>
	</div>
</div>
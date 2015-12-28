<div id="friendLink" class="friendLink">
	<div class="pictureFriendLink">
		<div class="left"></div>
		<div class="middle">
			<ul>
				<#list friendLinkList as friendLink>
					<li>
						<a href="${friendLink.url}" title="${friendLink.name}" target="_blank">
							<img src="${base}/vegetables/${friendLink.logo}">
						</a>
					</li>
				</#list>
			</ul>
		</div>
		<div class="right"></div>
	</div>
	<div class="textFriendLink">
		<div class="left"></div>
		<div class="middle">
			<ul>
				<#list friendLinkList! as friendLink>
					<#if !(friendLink.logo)??>
						<li>
							<a href="${friendLink.url}" title="${friendLink.name}" target="_blank">${friendLink.name}</a>
						</li>
					</#if>
				</#list>
			</ul>
		</div>
		<div class="right"></div>
	</div>
</div>
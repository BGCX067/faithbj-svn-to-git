<div class="bodyLeft">
			<div class="memberInfo">
				<div class="top"></div>
				<div class="middle">
					<p>欢迎您！<span class="username">${loginMember.username}</span> [<a class="userLogout" href="member!logout.action"">退出</a>]</p>
					<p>会员等级:<span class="red"> ${loginMember.memberRank.name}</span></p>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="memberMenu">
				<div class="top">
					<a href="field_manage!index.action"><strong>${loginMember.username}的自留地</strong></a>
				</div>
				<div class="middle">
					<ul>
	                	<li class="order">
	                    	<ul>
	                        	<li><a href="field_block!list.action">承包土地</a></li>
	                        </ul>
	                    </li>
	                    <li class="order">
	                    	<ul>
	                        	<li><a href="my_rend_block!list.action">我的承包地块</a></li>
	                        	<li><a href="my_farming_block!manage.action">批量管理托管耕种地块</a></li>
	                        	<li><a href="my_harvest_record!list.action">我的收获记录</a></li>
	                        </ul>
	                    </li>
	                    <li class="category favorite">
	                    	<ul>
	                        	<li><a href="field_favorite!list.action">我的承包地块收藏</a></li>
	                        </ul>
	                    </li>
	                     <li class="order">
	                    	<ul>
	                        	<li><a href="favorite!list.action">帮助</a></li>
	                        </ul>
	                    </li>
	                    <li class="order">
	                    	<ul>
	                        	<li><a href="${base}/shop/member_center!index.action">会员中心</a></li>
	                        </ul>
	                    </li>
	                </ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
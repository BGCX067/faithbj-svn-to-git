<div class="bodyLeft">
	<div class="memberInfo">
		<div class="top"></div>
		<div class="middle">
			<p>欢迎您!&nbsp;&nbsp;<span class="username">${loginMember.username}</span>&nbsp;&nbsp;[<a class="userLogout" href="/cjlhome/logout">退出</a>]</p>
			<p>会员等级: <span class="red"> ${loginMember.memberRank.name}</span></p>
		</div>
		<div class="bottom"></div>
	</div>
	<div class="blank"></div>
	<div class="memberMenu">
		<div class="top">
			<a href="/cjlhome/member_center/index">会员中心首页</a>
		</div>
		<div class="middle">
			<ul>
            	<li class="order">
                	<ul>
                    	<li class="current"><a href="/cjlhome/order/list">我的订单</a></li>
                    </ul>
                </li>
                <li class="category land">
                	<ul>
                    	<li><a href="/cjlhome/order/myLandList">土地管理</a></li>
                    	<li><a href="/cjlhome/account/list">记账本</a></li>
                    </ul>
                </li>
                <li class="category favorite">
                	<ul>
                    	<li><a href="/cjlhome/favorite/list">商品收藏</a></li>
                    </ul>
                </li>
                
                
              	<li class="message">
                	<ul>
                    	<li><a href="/cjlhome/message/send">发送消息</a></li>
                        <li><a href="/cjlhome/message/inbox">收件箱</a></li>
                        <li><a href="/cjlhome/message/draftbox">草稿箱</a></li>
                        <li><a href="/cjlhome/message/outbox">发件箱</a></li>
                    </ul>
                </li>
                <li class="profile">
                	<ul>
                    	<li><a href="/cjlhome/profile/edit">个人信息</a></li>
                        <li><a href="/cjlhome/password/edit">修改密码</a></li>
                        <li><a href="/cjlhome/receiver/list">收货地址</a></li>
                      	<li><a href="/cjlhome/profile/favorlist">个人偏好</a></li>
                      	<li><a href="/cjlhome/profile/hatelist">忌讳列表 </a></li>
                    </ul>
                </li>
                <li class="deposit">
                	<ul>
                		<li><a href="/cjlhome/deposit/list">我的预存款</a></li>
                    	<li><a href="/cjlhome/deposit/recharge">预存款充值</a></li>
                    </ul>
                </li>
            </ul>
		</div>
		<div class="bottom"></div>
	</div>
</div>
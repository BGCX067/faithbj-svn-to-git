<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理登录 - </title>
<meta name="Baiduspider" content="nofollow">
<meta name="robots" content="nofollow">
<link rel="icon" href="favicon.ico" type="image/x-icon" />

<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $loginForm = $("#loginForm");
	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isRememberUsername = $("#isRememberUsername");

	// 判断"记住用户名"功能是否默认选中,并自动填充登录用户名
	if(getCookie("adminUsername") != null) {
		$isRememberUsername.attr("checked", true);
		$username.val(getCookie("adminUsername"));
		$password.focus();
	} else {
		$isRememberUsername.attr("checked", false);
		$username.focus();
	}

	// 提交表单验证,记住登录用户名
	$loginForm.submit( function() {
		if ($username.val() == "") {
			$.dialog({type: "warn", content: "请输入您的用户名!", modal: true, autoCloseTime: 3000});
			return false;
		}
		if ($password.val() == "") {
			$.dialog({type: "warn", content: "请输入您的密码!", modal: true, autoCloseTime: 3000});
			return false;
		}
		if ($captcha.val() == "") {
			$.dialog({type: "warn", content: "请输入您的验证码!", modal: true, autoCloseTime: 3000});
			return false;
		}
		
		if ($isRememberUsername.attr("checked") == true) {
			var expires = new Date();
			expires.setTime(expires.getTime() + 1000 * 60 * 60 * 24 * 7);
			setCookie("adminUsername", $username.val(), expires);
		} else {
			removeCookie("adminUsername");
		}
		
	});

	// 刷新验证码
	$captchaImage.click( function() {
		var timestamp = (new Date()).valueOf();
		var imageSrc = $captchaImage.attr("src");
		if(imageSrc.indexOf("?") >= 0) {
			imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
		}
		imageSrc = imageSrc + "?timestamp=" + timestamp;
		$captchaImage.attr("src", imageSrc);
	});

	<%if (message != null) {%>
		$.dialog({type: "error", content: "<%=message%>", modal: true, autoCloseTime: 3000});
	<%}%>

});
</script>
</head>
<body class="login">
	<script type="text/javascript">

		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		};

	</script>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="blank"></div>
	<div class="body">
		
	      <form id="loginForm" action="/faith/login" method="post"> 

            <table class="loginTable">
            	<tr>
            		<td rowspan="3">
            			<img src="${base}/vegetables/admin/images/logo_large.png" alt="菜精灵" />
            		</td>
                    <th>
                    	用户名:
                    </th>
					<td>
                    	<input type="text" id="username" name="j_username" class="formText" />
                    </td>
                </tr>
                <tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
                    <td>
                    	<input type="password" id="password" name="j_password" class="formText" />
                    </td>
                </tr>
                <tr>
                	<th>
                		验证码:
                	</th>
                    <td>
                    	<input type="text" id="captcha" name="j_captcha" class="formText captcha" />
                   	    <img id="captchaImage" class="captchaImage" src="${base}/captcha" alt="换一张" />
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                    	<label>
                    		<input type="checkbox" id="isRememberUsername" />&nbsp;记住用户名
                    	</label>
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                        <input type="button" class="homeButton" value="" onclick="window.open('${base}/')" hidefocus /><input type="submit" class="submitButton" value="登 录" hidefocus />
                    </td>
                </tr>
            </table>
            <div class="powered">
            	COPYRIGHT &copy; 2011-2013 CAIJINGLING ALL RIGHTS RESERVED.
            </div>
            <div class="link">
            	<a href="${base}/index">前台首页</a> |
				<a href="http://www.faithbj.com">官方网站</a>|
				<a href="http://about.faithbj.com">关于我们</a>|
				<a href="http://contact.faithbj.com">联系我们</a>|
            </div>
        </form>
	</div>
</body>




</html>

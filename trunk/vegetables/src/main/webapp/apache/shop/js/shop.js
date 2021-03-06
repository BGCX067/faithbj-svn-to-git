faithbj = {
	base: "/vegetables",
	currencySign: "￥",
	currencyUnit: "元",
	priceScale: "2",
	priceRoundType: "roundHalfUp"
};

// 货币格式化
function currencyFormat(price) {
	price = setScale(price, faithbj.priceScale, faithbj.priceRoundType);
	return faithbj.currencySign + price + faithbj.currencyUnit;
}

$().ready( function() {

	var $body = $("body");
	
	/* ---------- MemberVerify ---------- */
	
	$.memberVerify = function () {
		if(getCookie("memberUsername") != null) {
			var isMemberLogin = false;
			$.ajax({
				url: faithbj.base + "/ajaxMemberVerify",
				type: "POST",
				dataType: "json",
				async: false,
				cache: false,
				success: function(data) {
					if (data.status=="success") {
						isMemberLogin = true;
					}
				}
			});
			return isMemberLogin;
		} else {
			return false;
		}
	};
	
	/* ---------- Header ---------- */
	
	var $header = $("#header");
	if ($header.size() > 0) {
		var $headerShowLoginWindow = $("#headerShowLoginWindow");
		var $headerShowRegisterWindow = $("#headerShowRegisterWindow");
		var $headerLoginMemberUsername = $("#headerLoginMemberUsername");
		var $headerMemberCenter = $("#headerMemberCenter");
		var $headerLogout = $("#headerLogout");
		var $goodsSearchForm = $("#goodsSearchForm");
		var $goodsSearchKeyword = $("#goodsSearchKeyword");
		
		$.flushHeaderInfo = function () {
			if(getCookie("memberUsername") != null) {
				$headerLoginMemberUsername.text(getCookie("memberUsername"));
				$headerMemberCenter.show();
				$headerLogout.show();
				$headerShowLoginWindow.hide();
				$headerShowRegisterWindow.hide();
			} else {
				$headerLoginMemberUsername.text("");
				$headerShowLoginWindow.show();
				$headerShowRegisterWindow.show();
				$headerMemberCenter.hide();
				$headerLogout.hide();
			}
		};
		
		$.flushHeaderInfo();
		
		$.showLoginWindow = function (redirectUrl) {
			
var loginWindowHtml = '<form id="loginWindowForm"> <table> <tr> <th>用户名: </th> <td> <input type="text" id="loginWindowMemberUsername" name="j_username" class="formText" /> </td> </tr> <tr> <th>密&nbsp;&nbsp;&nbsp;码: </th> <td> <input type="password" id="loginWindowMemberPassword" name="j_password" class="formText" /> </td> </tr> <tr> <th>验证码: </th> <td> <input type="text" id="loginWindowCaptcha" name="j_captcha" class="formText loginWindowCaptcha" /> <img id="loginWindowCaptchaImage" class="loginWindowCaptchaImage" src="' + faithbj.base + '/captcha.jpeg?timestamp=' + (new Date()).valueOf() + '" alt="换一张" /> </td> </tr> <tr> <th>&nbsp;</th> <td> <span class="warnIcon">&nbsp;</span> <a href="' + faithbj.base + '/shop/member/passwordRecover">忘记了密码? 点击找回!</a> </td> </tr> </table> </form>';			
			$.dialog({title: "会员登录", content: loginWindowHtml, ok: "登 录", cancel: "取 消", id: "loginWindow", className: "loginWindow", width: 420, okCallback: login, modal: true});
			
			var $loginWindowForm = $("#loginWindowForm");
			var $loginWindowMemberUsername = $("#loginWindowMemberUsername");
			var $loginWindowMemberPassword = $("#loginWindowMemberPassword");
			var $loginWindowCaptcha = $("#loginWindowCaptcha");
			var $loginWindowCaptchaImage = $("#loginWindowCaptchaImage");
			
			function loginWindowCaptchaImageRefresh() {
				$loginWindowCaptchaImage.attr("src", faithbj.base + "/captcha.jpeg?timestamp=" + (new Date()).valueOf());
			}
			
			$loginWindowCaptchaImage.click( function() {
				loginWindowCaptchaImageRefresh();
			});
			
			function login() {
				if ($.trim($loginWindowMemberUsername.val()) == "") {
					$loginWindowMemberUsername.focus();
					$.message({type: "warn", content: "请输入用户名!"});
					return false;
				}
				if ($.trim($loginWindowMemberPassword.val()) == "") {
					$loginWindowMemberPassword.focus();
					$.message({type: "warn", content: "请输入密码!"});
					return false;
				}
//				if ($.trim($loginWindowCaptcha.val()) == "") {
//					$loginWindowCaptcha.focus();
//					$.message({type: "warn", content: "请输入验证码!"});
//					return false; 	
//				}
//				
				$.ajax({
					url: faithbj.base + "/cjlhome/login",
					data: $loginWindowForm.serialize(),
					type: "POST",
//					dataType: "json",
					cache: false,
					beforeSend: function() {
						$loginWindowForm.find("button").attr("disabled", true);
					},
					success: function(response) {
//						if (data.status == success) {
							$.flushHeaderInfo();
//							$headerLoginMemberUsername.text(getCookie("memberUsername"));
							$.closeDialog("loginWindow");
							
							setCookie("memberUsername",$loginWindowMemberUsername.val());
//						}
//						$.message({type: data.status, content: data.message});
						if(redirectUrl != null) {
							location.href = redirectUrl;
						}else{
							location.href = faithbj.base+"/index";
						}
					},
					complete: function() {
						$loginWindowForm.find("button").attr("disabled", false);
						$loginWindowCaptcha.val("");
						loginWindowCaptchaImageRefresh();
					}
				});
				return false;
			}
		};
		
		$.showRegisterWindow = function () {
			
var registerWindowHtml = 
	' <form id="registerWindowForm"> '+
	'<table> <tr> <th>用户名: </th> <td> <input type="text" id="registerWindowMemberUsername" name="username" class="formText" title="用户名只允许包含中文、英文、数字和下划线!" /> </td> </tr>'+ 
	'<tr> <th>密&nbsp;&nbsp;&nbsp;码: </th> <td> <input type="password" id="registerWindowMemberPassword" name="password" class="formText" /> </td> </tr> '+
	'<tr> <th>重复密码: </th> <td> <input type="password" id="registerWindowReMemberPassword" name="reMemberPassword" class="formText" title="密码长度只允许在4-20之间!" /> </td> </tr> '+
	'<tr> <th>E-mail: </th> <td> <input type="text" id="registerWindowMemberEmail" name="email" class="formText" /> </td> </tr>'+
	' <tr> <th>验证码: </th> <td> <input type="text" id="registerWindowCaptcha" name="j_captcha" class="formText registerWindowCaptcha" /> <img id="registerWindowCaptchaImage" class="registerWindowCaptchaImage" src="' + faithbj.base + '/captcha.jpeg?timestamp=' + (new Date()).valueOf() + '" alt="换一张" /> </td> </tr> '+
	'<tr> <th>&nbsp;</th> <td> <label> <input type="checkbox" id="registerWindowIsAgreeAgreement" name="isAgreeAgreement" value="true" checked />已阅读并同意 <a id="registerWindowShowAgreementWindow" class="showAgreementWindow" href="javascript: void(0);" title="查看会员注册协议">《会员注册协议》</a> </label> </td> </tr> </table> </form>';			
			$.dialog({title: "会员注册", content: registerWindowHtml, ok: "注 册", cancel: "取 消", id: "registerWindow", className: "registerWindow", width: 480, okCallback: register, modal: true});
			
			var $registerWindowForm = $("#registerWindowForm");
			var $registerWindowMemberUsername = $("#registerWindowMemberUsername");
			var $registerWindowMemberPassword = $("#registerWindowMemberPassword");
			var $registerWindowReMemberPassword = $("#registerWindowReMemberPassword");
			var $registerWindowMemberEmail = $("#registerWindowMemberEmail");
			var $registerWindowCaptcha = $("#registerWindowCaptcha");
			var $registerWindowCaptchaImage = $("#registerWindowCaptchaImage");
			var $registerWindowIsAgreeAgreement = $("#registerWindowIsAgreeAgreement");
			var $registerWindowShowAgreementWindow = $("#registerWindowShowAgreementWindow");
			
			function registerWindowCaptchaImageRefresh() {
				$registerWindowCaptchaImage.attr("src", faithbj.base + "/captcha.jpeg?timestamp=" + (new Date()).valueOf());
			}
			
			$registerWindowCaptchaImage.click( function() {
				registerWindowCaptchaImageRefresh();
			});
			
			$registerWindowShowAgreementWindow.click( function() {
				$.showAgreementWindow();
			});
			
			function register() {
				if ($.trim($registerWindowMemberUsername.val()) == "") {
					$registerWindowMemberUsername.focus();
					$.message({type: "warn", content: "请输入用户名!"});
					return false;
				}
				if (!/^[\u0391-\uFFE5\w]+$/.test($registerWindowMemberUsername.val())) {
					$registerWindowMemberUsername.focus();
					$.message({type: "warn", content: "用户名只允许包含中文、英文、数字和下划线!"});
					return false;
				}
				if ($.trim($registerWindowMemberUsername.val()).length < 2 || $.trim($registerWindowMemberUsername.val()).length > 20) {
					$registerWindowMemberUsername.focus();
					$.message({type: "warn", content: "用户名长度只允许在2-20之间!"});
					return false;
				}
				if ($.trim($registerWindowMemberPassword.val()) == "") {
					$registerWindowMemberPassword.focus();
					$.message({type: "warn", content: "请输入密码!"});
					return false;
				}
				if ($.trim($registerWindowMemberPassword.val()).length < 4 || $.trim($registerWindowMemberPassword.val()).length > 20) {
					$registerWindowMemberPassword.focus();
					$.message({type: "warn", content: "密码长度只允许在4-20之间!"});
					return false;
				}
				if ($.trim($registerWindowReMemberPassword.val()) == "") {
					$registerWindowReMemberPassword.focus();
					$.message({type: "warn", content: "请输入重复密码!"});
					return false;
				}
				if ($.trim($registerWindowReMemberPassword.val()) != $.trim($registerWindowMemberPassword.val())) {
					$registerWindowReMemberPassword.focus();
					$.message({type: "warn", content: "两次密码输入不相同!"});
					return false;
				}
				if ($.trim($registerWindowMemberEmail.val()) == "") {
					$registerWindowMemberEmail.focus();
					$.message({type: "warn", content: "请输入E-mail!"});
					return false;
				}
				if (!/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test($registerWindowMemberEmail.val())) {
					$registerWindowMemberEmail.focus();
					$.message({type: "warn", content: "E-mail格式错误!"});
					return false;
				}
//				if ($.trim($registerWindowCaptcha.val()) == "") {
//					$registerWindowCaptcha.focus();
//					$.message({type: "warn", content: "请输入验证码!"});
//					return false;
//				}
				if (!$registerWindowIsAgreeAgreement.attr("checked")) {
					$registerWindowIsAgreeAgreement.focus();
					$.message({type: "warn", content: "注册前必须阅读并同意《注册协议》!"});
					return false;
				}
				
//				$.ajax({
//					url: faithbj.base + "/cjlhome/checkUsername",
//					data: {"username": $registerWindowMemberUsername.val()},
//					type: "POST",
//					cache: false,
//					beforeSend: function() {
//						$registerWindowForm.find("button").attr("disabled", true);
//					},
//					success: function() {
//						alert(data);
//						if (data == "true") {
							$.ajax({
								url: faithbj.base + "/ajaxRegister",
								data: $registerWindowForm.serialize(),
								type: "POST",
//								dataType: "json",
//								cache: false,
								success: function() {
//									if (data.status == "success") {
										$.closeDialog("registerWindow");
										$.flushHeaderInfo();
//									}
										$.message({type: "success", content: "注册成功!"});
										if(redirectUrl != null) {
											alert("redirectUrl != null"+redirectUrl);
											location.href = redirectUrl;
										}else{
											alert("redirectUrl == null");
											location.href = faithbj.base+"/index";
										}
									
//									$.message({type: data.status, content: data.message});
								},
								complete: function() {
									$registerWindowForm.find("button").attr("disabled", false);
									$registerWindowCaptcha.val("");
									registerWindowCaptchaImageRefresh();
								}
							});
//						},failture:function() {
//							$registerWindowMemberUsername.focus();
//							$.message({type: "warn", content: "用户名已存在,请重新输入!"});
//							return false;
//						}
////					}
//				});
			}
		};
		
		$.showAgreementWindow = function() {
			if ($("#agreementWindow").length == 0) {
				var agreementWindowHtml = '<div id="agreementWindowContent" class="agreementWindowContent"></div>';
				
				function agreeAgreement() {
					var $registerWindowIsAgreeAgreement = $("#registerWindowIsAgreeAgreement");
					$registerWindowIsAgreeAgreement.attr("checked", true);
				}
			
				$.dialog({title: "注册协议", content: agreementWindowHtml, ok: "同 意", cancel: "取 消", id: "agreementWindow", className: "agreementWindow", width: 520, okCallback: agreeAgreement});
				
				var $agreementWindowContent = $("#agreementWindowContent");
				
				$.ajax({
					url: faithbj.base + "/html/register_agreement.html",
					datatype: "html",
					beforeSend: function(data) {
						$agreementWindowContent.html('<span class="loadingIcon">&nbsp;</span> 加载中...');
					},
					success: function(data) {
						$agreementWindowContent.html(data);
					}
				});
			}
		};
		
		$headerShowLoginWindow.click( function() {
			$.showLoginWindow();
			return false;
		});
		
		$headerShowRegisterWindow.click( function() {
			$.showRegisterWindow();
			return false;
		});
		
		goodsSearchKeywordDefaultValue = $goodsSearchKeyword.val();
		$goodsSearchKeyword.focus( function() {
			if ($goodsSearchKeyword.val() == goodsSearchKeywordDefaultValue) {
				$goodsSearchKeyword.val("");
			}
		});
		
		$goodsSearchKeyword.blur( function() {
			if ($goodsSearchKeyword.val() == "") {
				$goodsSearchKeyword.val(goodsSearchKeywordDefaultValue);
			}
		});
		
		$goodsSearchForm.submit( function() {
			if ($.trim($goodsSearchKeyword.val()) == "" || $.trim($goodsSearchKeyword.val()) == goodsSearchKeywordDefaultValue) {
				return false;
			}
		});

	}
	
	/* ---------- SliderScrollable ---------- */
	
	var $sliderScrollable = $("#sliderScrollable");
	if ($sliderScrollable.size() > 0) {
		$sliderScrollable.scrollable({
			circular: true,
			speed: 500
		}).autoscroll({
			autoplay: true,
			interval: 4000
		}).navigator();
	}
	
	/* ---------- HotGoodsScrollable ---------- */
	
	var $hotGoodsScrollable = $("#hotGoodsScrollable");
	if ($hotGoodsScrollable.size() > 0) {
		$hotGoodsScrollable.scrollable({
			circular: true,
			speed: 500
		});
	}
	
	/* ---------- NewGoodsTab ---------- */
	
	var $newGoodsTab = $("#newGoodsTab");
	if ($newGoodsTab.size() > 0) {
		$newGoodsTab.tabs(".newGoodsTabContent", {
			event: "mouseover"
		});
	}
	
	/* ---------- AddFavorite ---------- */
	
	$.addFavorite = function(id) {
		if (!$.memberVerify()) {
			$.showLoginWindow();
			return false; 
		}
		$.ajax({
			url: faithbj.base + "/cjlhome/favorite/ajaxAdd",
			data: {"id": id},
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
			}
		});
		return false;
	};
	
	/* ---------- AddFavor ---------- */
	
	$.addFavor = function(id) {
		if (!$.memberVerify()) {
			$.showLoginWindow();
			return false; 
		}
		$.ajax({
			url: faithbj.base + "/cjlhome/profile/addfavor",
			data: {"id": id},
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
			}
		});
		return false;
	};
	
	/* ---------- AddHate ---------- */
	
	$.addHate = function(id) {
		if (!$.memberVerify()) {
			$.showLoginWindow();
			return false; 
		}
		$.ajax({
			url: faithbj.base + "/cjlhome/profile/addhate",
			data: {"id": id},
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
			}
		});
		return false;
	};
	
	/* ---------- AddCartItem ---------- */
	
	$.addCartItem = function(id, quantity) {
		if ($.trim(id) == "") {
			$.dialog({type: "warn", content: "请选择购买商品!", modal: true, autoCloseTime: 3000});
			return false;
		}
		if (!/^[0-9]*[1-9][0-9]*$/.test($.trim(quantity))) {
			$.dialog({type: "warn", content: "商品品数量必须为正整数!", modal: true, autoCloseTime: 3000});
			return false;
		}

		$.ajax({
			url: faithbj.base + "/product/caritem/ajaxAdd",
			data: {"id": id, "quantity": quantity},
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					$.dialog({type: "success", content: "<span class=\"red\">" + data.message + "</span><br />商品总计: " + data.totalProductQuantity + " 件, 总金额: " + currencyFormat(data.totalProductPrice), width: 360, modal: true, autoCloseTime: 3000});
				} else {
					$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	};
	/* ---------- AddLandCartItem ---------- */
	
	$.addLandCartItem = function(id, quantity) {
		if ($.trim(id) == "") {
			$.dialog({type: "warn", content: "请选择购买商品!", modal: true, autoCloseTime: 3000});
			return false;
		}
		$.ajax({
			url: faithbj.base + "/product/caritem/ajaxAdd",
			data: {"id": id, "quantity": 1},
			type: "POST",
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.status == "success") {
					$.dialog({type: "success", content: "<span class=\"red\">" + data.message + "</span><br />商品总计: " + data.totalProductQuantity + " 件, 总金额: " + currencyFormat(data.totalProductPrice), width: 360, modal: true, autoCloseTime: 3000});
				} else {
					$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
				}
			}
		});
	};
	
	/* ---------- GoodsList ---------- */
	
	var $goodsListForm = $("#goodsListForm");
	if ($goodsListForm.size() > 0) {
		var $brandId = $("#brandId");
		var $brand = $("#filter .brand");
		var $goodsAttributeOption = $("#filter .goodsAttributeOption");
		var $pageNumber = $("#pageNumber");
		var $pageSize = $("#pageSize");
		var $orderType = $("#orderType");
		var $viewType = $("#viewType");
		var $tableType = $("#tableType");
		var $pictureType = $("#pictureType");
		var $addFavorite = $("input.addFavorite");
		
		$brand.click( function() {
			var $this = $(this);
			if ($this.hasClass("all")) {
				$brandId.val("");
			} else {
				$brandId.val($this.attr("brandId"));
			}
			$goodsListForm.submit();
			return false;
		});
		
		$goodsAttributeOption.click( function() {
			var $this = $(this);
			$this.parent().parent().find(":hidden").attr("disabled", true);
			if (!$this.hasClass("all")) {
				$this.prev(":hidden").attr("disabled", false);
			}
			$goodsListForm.submit();
			return false;
		});
		
		$pageSize.change( function() {
			$pageNumber.val("1");
			$goodsListForm.submit();
		});
		
		$orderType.change( function() {
			$pageNumber.val("1");
			$goodsListForm.submit();
		});
		
		$tableType.click( function() {
			$viewType.val("tableType");
			$goodsListForm.submit();
			return false;
		});
		
		$pictureType.click( function() {
			$viewType.val("pictureType");
			$goodsListForm.submit();
			return false;
		});
		
		$addFavorite.click( function() {
			var $this = $(this);
			$.addFavorite($this.attr("goodsId"));
		});
	
	}
	
	/* ---------- GoodsContent ---------- */
	
	var $goodsContent = $("#goodsContent");
	if ($goodsContent.size() > 0) {
		var $zoom = $("a.zoom");
		var $thumbnailGoodsImageScrollable = $("#thumbnailGoodsImageScrollable");
		var $goodsImageTab = $("#goodsImageTab");
		var $goodsParameterTab = $("#goodsParameterTab");
		var $addFavorite = $("#addFavorite");
		var $addFavor = $("#addFavor");
		var $addHate = $("#addHate");
		
		$thumbnailGoodsImageScrollable.scrollable({
			speed: 600
		});
		
		$goodsImageTab.tabs(".goodsImage .tabContent", {
			event: "mouseover"
		});
		
		$zoom.zoomimage({
			opacity: 0.6,
			controlsTrigger: "mouseover",
			controls: true,
			centered: true,
			hideSource: true
		});
		
		$goodsParameterTab.tabs(".goodsBottom .tabContent", {
			event: "mouseover"
		});
		
		$addFavorite.click( function() {
			var $this = $(this);
			$.addFavorite($this.attr("goodsId"));
		});
		
		$addFavor.click( function() {
			var $this = $(this);
			$.addFavor($this.attr("goodsId"));
		});
		
		$addHate.click( function() {
			var $this = $(this);
			$.addHate($this.attr("goodsId"));
		});
	}
	
	/* ---------- Comment ---------- */
	
	var $comment = $("#comment");
	if ($comment.size() > 0) {
		var $commentReply = $(".commentReply");
		var $sendTitle = $("#sendTitle");
		var $sendComment = $("#sendComment");
		var $commentForm = $("#commentForm");
		var $forCommentId = $("#forCommentId");
		var $commentContent = $("#commentContent");
		var $commentCaptcha = $("#commentCaptcha");
		var $commentCaptchaImage = $("#commentCaptchaImage");
		var forCommentId = null;
		var ajaxUrl = faithbj.base + "/shop/comment!ajaxSave.action";
		
		$commentReply.click( function() {
			var $this = $(this);
			forCommentId = $this.attr("forCommentId");
			$forCommentId.val(forCommentId);
			ajaxUrl = faithbj.base + "/shop/comment!ajaxReply.action";
			$sendTitle.html("回复评论");
			$sendComment.show();
		});
		
		$sendComment.click( function() {
			forCommentId = null;
			$forCommentId.val("");
			ajaxUrl = faithbj.base + "/shop/comment!ajaxSave.action";
			$sendTitle.html("发表评论");
			$sendComment.hide();
		});
		
		function commentCaptchaImageRefresh() {
			$commentCaptchaImage.attr("src", faithbj.base + "/captcha.jpeg?timestamp=" + (new Date()).valueOf());
		}
	
		$commentCaptchaImage.click( function() {
			commentCaptchaImageRefresh();
		});
		
		$commentForm.submit( function() {
				if (!$.memberVerify()) {
					$.showLoginWindow();
					return false; 
				}
			if ($.trim($commentContent.val()) == "") {
				$.dialog({type: "warn", content: "请输入评论内容!", modal: true, autoCloseTime: 3000});
				return false;
			}
				if ($.trim($commentCaptcha.val()) == "") {
					$.dialog({type: "warn", content: "请输入验证码!", modal: true, autoCloseTime: 3000});
					return false;
				}
			
			$.ajax({
				url: ajaxUrl,
				data: $commentForm.serialize(),
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$commentForm.find("submit").attr("disabled", true);
				},
				success: function(data) {
					$.dialog({type: data.status, content: data.message, modal: true, autoCloseTime: 3000});
					if (data.status == "success") {
							var username = getCookie("memberUsername");
							if (username == null) {
								username = "游客";
							}
							if (forCommentId == null) {
								var commentItemHtml = '<div class="commentItem"><p><span class="red">' + username + '</span> ' + new Date().toLocaleDateString() + '</p><p><pre>' + htmlEscape($commentContent.val()) + '</pre></p></div><div class="blank"></div>';
								$comment.prepend(commentItemHtml);
							} else {
								var replyHtml = '<div class="reply"><p><span class="red">' + username + '</span> ' + new Date().toLocaleDateString() + '</p><p><pre>' + htmlEscape($commentContent.val()) + '</pre></p></div>';
								$("#commentItem" + forCommentId).append(replyHtml);
							}
						$commentContent.val("");
					}
				},
				complete: function() {
					$commentForm.find("submit").attr("disabled", false);
					$commentCaptcha.val("");
					commentCaptchaImageRefresh();
				}
			});
			return false;
		});
		
	}
	
	/* ---------- GoodsHistory ---------- */
	
	var $goodsHistory = $("#goodsHistory");
	if ($goodsHistory.size() > 0) {
		var $goodsHistoryListDetail = $("#goodsHistoryListDetail");
		var maxGoodsHistoryListCount = 5;
		$.addGoodsHistory = function(name, htmlPath) {
			var goodsHistory = {
				name: name,
				htmlPath: htmlPath
			};
			var goodsHistoryArray = new Array();
			var goodsHistoryListCookie = getCookie("goodsHistoryList");
			if(goodsHistoryListCookie) {
				goodsHistoryArray = eval(goodsHistoryListCookie);
			}
			var goodsHistoryListHtml = "";
			for (var i in goodsHistoryArray) {
				goodsHistoryListHtml += '<li><a href="' + goodsHistoryArray[i].htmlPath + '">' + goodsHistoryArray[i].name + '</a></li>';
			}
			for (var i in goodsHistoryArray) {
				if(goodsHistoryArray[i].htmlPath == htmlPath) {
					return;
				}
			}
			if(goodsHistoryArray.length >= maxGoodsHistoryListCount) {
				goodsHistoryArray.shift();
			}
			goodsHistoryArray.push(goodsHistory);
			var newGoodsHistoryCookieString = "";
			for (var i in goodsHistoryArray) {
				newGoodsHistoryCookieString += ',{name: "' + goodsHistoryArray[i].name + '", htmlPath: "' + goodsHistoryArray[i].htmlPath + '"}';
			}
			newGoodsHistoryCookieString = "[" + newGoodsHistoryCookieString.substring(1, newGoodsHistoryCookieString.length) + "]";
			setCookie("goodsHistoryList", newGoodsHistoryCookieString);
		};
		
		var goodsHistoryArray = new Array();
		var goodsHistoryListCookie = getCookie("goodsHistoryList");
		if(goodsHistoryListCookie) {
			goodsHistoryArray = eval(goodsHistoryListCookie);
		}
		var goodsHistoryListHtml = "";
		for (var i in goodsHistoryArray) {
			goodsHistoryListHtml += '<li><span class="icon">&nbsp;</span><a href="' + goodsHistoryArray[i].htmlPath + '">' + goodsHistoryArray[i].name + '</a></li>';
		}
		$goodsHistoryListDetail.html(goodsHistoryListHtml);
	}
	
	/* ---------- ArticleSearch ---------- */
	
	var $articleSearch = $("#articleSearch");
	if ($articleSearch.size() > 0) {
		$articleSearchForm = $("#articleSearchForm");
		$articleSearchKeyword = $("#articleSearchKeyword");
		
		articleSearchKeywordDefaultValue = $("#articleSearchKeyword").val();
		$articleSearchKeyword.focus( function() {
			if ($articleSearchKeyword.val() == articleSearchKeywordDefaultValue) {
				$articleSearchKeyword.val("");
			}
		});
		
		$articleSearchKeyword.blur( function() {
			if ($articleSearchKeyword.val() == "") {
				$articleSearchKeyword.val(articleSearchKeywordDefaultValue);
			}
		});
		
		$articleSearchForm.submit( function() {
			if ($.trim($articleSearchKeyword.val()) == "" || $.trim($articleSearchKeyword.val()) == articleSearchKeywordDefaultValue) {
				return false;
			}
		});
	}
	
	/* ---------- ArticleContent ---------- */
	
	var $articleContent = $("#articleContent");
	if ($articleContent.size() > 0) {
		$changeBigFontSize = $("#changeBigFontSize");
		$changeNormalFontSize = $("#changeNormalFontSize");
		$changeSmallFontSize = $("#changeSmallFontSize");
		
		$changeBigFontSize.click(function () {
			$articleContent.css({"font-size": "16px"});
		});
		
		$changeNormalFontSize.click(function () {
			$articleContent.css({"font-size": "14px"});
		});
		
		$changeSmallFontSize.click(function () {
			$articleContent.css({"font-size": "12px"});
		});
	}
	
	/* ---------- InstantMessaging ---------- */
	
	
var instantMessagingHtml = ' <div id="instantMessagingLayer" class="instantMessagingLayer rightInstantMessagingLayer"> <div id="instantMessagingMin" class="instantMessagingMin"></div> <div id="instantMessagingMain" class="instantMessagingMain"> <div class="top"></div> <div class="middle"> <dl> <dt>7 × 24小时服务</dt> <dd> <a href="http://wpa.qq.com/msgrd?v=3&uin=2606473036&site=caijingling.com.cn&menu=yes" target="_blank"> <img src="http://wpa.qq.com/pa?p=2:2606473036:45" alt="QQ在线客服" align="absmiddle" />在线客服1 </a> </dd> <dd> <a href="http://wpa.qq.com/msgrd?v=3&uin=在线客服2&site=faithbj&menu=yes" target="_blank"> <img src="http://wpa.qq.com/pa?p=2:在线客服2:45" alt="QQ在线客服" align="absmiddle" /> 在线客服2 </a> </dd> <dd> <a href="http://wpa.qq.com/msgrd?v=3&uin=在线客服3&site=faithbj&menu=yes" target="_blank"> <img src="http://wpa.qq.com/pa?p=2:在线客服3:45" alt="QQ在线客服" align="absmiddle" /> 在线客服3 </a> </dd> <dd> <a href="msnim:chat?contact=在线客服4" target="_blank"> <img src="/vegetables/shop/images/instant_messaging_msn.gif" alt="MSN在线客服" align="absmiddle" /> 在线客服4 </a> </dd> <dd> <a href="msnim:chat?contact=在线客服5" target="_blank"> <img src="/vegetables/shop/images/instant_messaging_msn.gif" alt="MSN在线客服" align="absmiddle" /> 在线客服5 </a> </dd> <dd> <a href="http://amos1.taobao.com/msg.ww?v=2&uid=huilianjie87&s=2" target="_blank"> <img src="/vegetables/shop/images/instant_messaging_wangwang.gif" alt="旺旺在线客服" align="absmiddle" /> 在线客服6 </a> </dd> <dd> <a href="callto://在线客服7" target="_blank"> <img src="/vegetables/shop/images/instant_messaging_skype.gif" alt="Skype在线客服" align="absmiddle" /> 在线客服7 </a> </dd> </dl> </div> <div id="closeInstantMessagingMain" class="bottom" title="关 闭"></div> </div> </div>';		
		$body.append(instantMessagingHtml);
		
		var $instantMessagingLayer = $("#instantMessagingLayer");
		var $instantMessagingMin = $("#instantMessagingMin");
		var $instantMessagingMain = $("#instantMessagingMain");
		var $closeInstantMessagingMain = $("#closeInstantMessagingMain");
		
		var lastScrollY = 60;
		
		$instantMessagingLayer.show();
		
		function moveInstantMessagingLayer() {
			var diffY;
			if (document.documentElement && document.documentElement.scrollTop) {
				diffY = document.documentElement.scrollTop;
			} else if (document.body) {
				diffY = document.body.scrollTop;
			}
			
			percent = .1 * (diffY - lastScrollY);
			if(percent > 0) {
				percent = Math.ceil(percent);
			} else {
				percent = Math.floor(percent);
			}
			$instantMessagingLayer.css("top", (isNaN(parseInt($instantMessagingLayer.css("top"))) ? 0 : parseInt($instantMessagingLayer.css("top"))) + percent);
			lastScrollY = lastScrollY + percent;
		}
		
		window.setInterval(moveInstantMessagingLayer, 20);
		
		$instantMessagingMin.mouseover( function() {
			$instantMessagingMin.hide();
			$instantMessagingMain.show();
		});
		
		$closeInstantMessagingMain.click( function() {
			$instantMessagingMain.hide();
			$instantMessagingMin.show();
		});

});
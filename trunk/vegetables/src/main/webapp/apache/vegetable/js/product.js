
$().ready( function() {
	
	$addCartItemField = $(".addCartItemField");
	$addCartItemTip = $("#addCartItemTip");
	$tipClose = $("#addCartItemTip .tipClose");
	$addCartItemTipMessageIcon = $("#addCartItemTipMessageIcon");
	$addCartItemTipMessage = $("#addCartItemTipMessage");
	$addCartItemTipInfo = $("#addCartItemTipInfo");
	
	$isTrusteeFeeEnabled = $("#isTrusteeFeeEnabled");
	$isDeliveryFeeEnabled = $("#isDeliveryFeeEnabled");
	$farmingBlockStatus = $("#farmingBlockStatus");
	
	$addFavoriteField = $(".addFavoriteField");
	
	var $idsCheck = $(".list input[name='ids']");// ID复选框
	$seedingButton = $(".seedingButton");
	$cleanButton = $(".cleanButton");

	if (document.getElementById('isTrusteeFeeEnabled'))
	{
		document.getElementById('isTrusteeFeeEnabled').onclick = function()
		{
			if (document.getElementById('isTrusteeFeeEnabled').checked)
			{
				$(".price").text('￥' + (parseInt($(".price").text().substring(1)) + trusteeFee));
			}
			else
			{
				$(".price").text('￥' + (parseInt($(".price").text().substring(1)) - trusteeFee));
			}
		};
		document.getElementById('isDeliveryFeeEnabled').onclick = function()
		{
			if (document.getElementById('isDeliveryFeeEnabled').checked)
			{
				$(".price").text('￥' + (parseInt($(".price").text().substring(1)) + deliveryFee));
			}
			else
			{
				$(".price").text('￥' + (parseInt($(".price").text().substring(1)) - deliveryFee));
			}
		};
	}

	$isTrusteeFeeEnabled.change( function() {
		$pageNumber.val("1");
		$productListForm.submit();
	});

	$isDeliveryFeeEnabled.change( function() {
		$pageNumber.val("1");
		$productListForm.submit();
	});
	
	$farmingBlockStatus.change( function() {
		$pageNumber.val("1");
		$productListForm.submit();
	});
	
	$addCartItemField.click( function() {
		
		var $this = $(this);
		var id = $this.metadata().id;
		
		var x = $this.offset().left - 50;
		var y = $this.offset().top + $this.height() + 6;
		$addCartItemTip.css({"left" :x, "top" :y});
		
		var quantity = $("#quantity").val();
		if (quantity == null) {
			quantity = 1;
		}
		
		var isTrusteeFeeEnabled = document.getElementById('isTrusteeFeeEnabled').checked;
		var isDeliveryFeeEnabled = document.getElementById('isDeliveryFeeEnabled').checked;
		
		var reg = /^[0-9]*[1-9][0-9]*$/;
		if (!reg.test(quantity)) {
			$addCartItemTipMessageIcon.removeClass("successIcon").addClass("errorIcon");
			$addCartItemTipMessage.text("加入购物车失败！");
			$addCartItemTipInfo.text("承包数量必须为正整数！");
			$addCartItemTip.fadeIn();
			return false;
		}
		
		$.ajax({
			url: "/fieldmember/field_block_cart_item!ajaxAdd.action",
			data: {"id": id, "quantity": quantity, "isTrusteeFeeEnabled": isTrusteeFeeEnabled, "isDeliveryFeeEnabled": isDeliveryFeeEnabled},
			dataType: "json",
			beforeSend: function() {
				$this.attr("disabled", true);
			},
			success: function(data) {
				$.flushCartItemList();
				if (data.status == "success") {
					$addCartItemTipMessageIcon.removeClass("errorIcon").addClass("successIcon");
					$addCartItemTipMessage.text(data.message);
					$addCartItemTipInfo.text("共计商品：" + data.totalQuantity + "件，总计金额：" + data.totalPrice);
				} else if (data.status == "error") {
					$addCartItemTipMessageIcon.removeClass("successIcon").addClass("errorIcon");
					$addCartItemTipMessage.text(data.message);
					$addCartItemTipInfo.empty();
				}
				$addCartItemTip.fadeIn();
				$this.attr("disabled", false);
			}
		});
	});
	
	
	$addFavoriteField.click( function() {
		var $this = $(this);
		if ($.cookie("loginMemberUsername") == null) {
			$.flushHeaderInfo();
			$.loginWindowShow();
			return false; 
		} else {
			var id = $(this).metadata().id;
			$.ajax({
				url: "/fieldmember/field_favorite!ajaxAdd.action",
				data: {"id": id},
				dataType: "json",
				beforeSend: function() {
					$this.attr("disabled", true);
				},
				success: function(data) {
					$.tip(data.status, data.message);
					$this.attr("disabled", false);
				},
				error: function(data) {
					if ($.cookie("loginMemberUsername") == null) {
						$.flushHeaderInfo();
						$.loginWindowShow();
						return false;
					}
					$this.attr("disabled", false);
				}
			});
		}
	});
	
	
	// 无复选框被选中时,播种清理按钮不可用
	$idsCheck.click( function() {
		var $idsChecked = $(".list input[name='ids']:checked");
		if ($idsChecked.size() > 0) {
			$seedingButton.attr("disabled", false);
			$cleanButton.attr("disabled", false);
		} else {
			$seedingButton.attr("disabled", true);
			$cleanButton.attr("disabled", true);
		}
	});
	
	
	// 批量播种
	$seedingButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		
		var data = window.showModalDialog('/fieldmember/seed!popup.action?t=' + new Date().getTime(), '', 'dialogWidth:800px; dialogHeight:600px; center:yes');
		if (!data)
		{
			return;
		}
		
		var obj = eval('(' + data + ')');
		if (confirm("您确定要在选中耕种地块播种：" + obj.name + " 吗？（播种操作只能在空闲状态的耕种地块上进行）") == true) {
			$.ajax({
				url: url + '?seedid=' + obj.id,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$seedingButton.attr("disabled", true);
				},
				success: function(data) {
					$seedingButton.attr("disabled", false);
					if (data.status == "success") {
						$idsCheckedCheck.parent().parent().remove();
					}
					$.message(data.status, data.message);
					window.location.href = '/fieldmember/my_farming_block!manage.action';
				}
			});
		}
	});
	
	// 批量清理
	$cleanButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		if (confirm("您确定要在选中耕种地块清理吗？（清理会使耕种地块恢复空闲状态）") == true) {
			$.ajax({
				url: url,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$cleanButton.attr("disabled", true);
				},
				success: function(data) {
					$cleanButton.attr("disabled", false);
					if (data.status == "success") {
						$idsCheckedCheck.parent().parent().remove();
					}
					$.message(data.status, data.message);
					window.location.href = '/fieldmember/my_farming_block!manage.action';
				}
			});
		}
	});

	
});
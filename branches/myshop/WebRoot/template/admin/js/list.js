/***
 *	CAIJINGLING Register JavaScript
 *
 *	http://www.shopxx.net
 *
 *	Copyright (c) 2008 CAIJINGLING. All rights reserved.
 **/

$().ready( function() {

	var $allCheck = $(".list input.allCheck");// 全选复选框
	var $idsCheck = $(".list input[name='ids']");// ID复选框
	var $memberidsCheck = $(".list input[name='memberids']");// 礼品下发中memberID复选框
	var $deleteButton = $(".list input.deleteButton");// 删除按钮
	var $releaseButton = $(".list input.releaseButton");// 删除按钮
	
	var $listForm = $(".list form");// 列表表单
	var $searchButton =  $("#searchButton");// 查询按钮
	var $pageNumber = $("#pageNumber");// 当前页码
	var $pageSize = $("#pageSize");// 每页显示数
	var $sort = $(".list .sort");// 排序
	var $orderBy = $("#orderBy");// 排序方式
	var $order = $("#order");// 排序字段
	
	
	$allCheck.click( function() {
		if ($(this).attr("checked") == true) {
			$idsCheck.attr("checked", true);
			$releaseButton.attr("disabled", false);
		} else {
			$idsCheck.attr("checked", false);
			$releaseButton.attr("disabled", true);
		}
	});
	
	
	// 无复选框被选中时,删除按钮不可用
	$idsCheck.click( function() {
		var $idsChecked = $(".list input[name='ids']:checked");
		if ($idsChecked.size() > 0) {
			$deleteButton.attr("disabled", false);
		} else {
			$deleteButton.attr("disabled", true);
		}
	});
		
	// 礼品卡下发中全选
	$allCheck.click( function() {
		if ($(this).attr("checked") == true) {
			$memberidsCheck.attr("checked", true);
			$releaseButton.attr("disabled", false);
		} else {
			$memberidsCheck.attr("checked", false);
			$releaseButton.attr("disabled", true);
		}
	});
	
	// 礼品卡无复选框被选中时,下发按钮不可用
	$memberidsCheck.click( function() {
		var $memberidsCheck = $(".list input[name='memberids']:checked");
		if ($memberidsCheck.size() > 0) {
			$releaseButton.attr("disabled", false);
		} else {
			$releaseButton.attr("disabled", true);
		}
	});
	
	
	// 礼品卡批量下发礼品卡码 
	$releaseButton.click( function() {
		var url = $(this).attr("url");
		var eventid = $("#eventid").val();
		var $memberidsCheck = $(".list input[name='memberids']:checked");
		var memberids =$memberidsCheck.serialize();
		if (confirm("您确定要下发吗？") == true) {
			$.ajax({
				url: url,
				data: {"memberids": memberids, "eventid": eventid},
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$releaseButton.attr("disabled", true);
				},
				success: function(data) {
					$releaseButton.attr("disabled", false);
					if (data.status == "success") {
						$memberidsCheck.parent().parent().remove();
					}
					$.message(data.status, data.message);
				}
			});
		}
	});
	
	
	// 批量删除
	$deleteButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		
		if (confirm("您确定要删除吗？") == true) {
			$.ajax({
				url: url,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$deleteButton.attr("disabled", true);
				},
				success: function(data) {
					$deleteButton.attr("disabled", false);
					if (data.status == "success") {
						$idsCheckedCheck.parent().parent().remove();
					}
					$.message(data.status, data.message);
				}
			});
		}
	});

	// 查找
	$searchButton.click( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 每页显示数
	$pageSize.change( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序
	$sort.click( function() {
		var $currentOrderBy = $(this).attr("name");
		if ($orderBy.val() == $currentOrderBy) {
			if ($order.val() == "") {
				$order.val("asc");
			} else if ($order.val() == "desc") {
				$order.val("asc");
			} else if ($order.val() == "asc") {
				$order.val("desc");
			}
		} else {
			$orderBy.val($currentOrderBy);
			$order.val("asc");
		}
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序图标效果
	sortStyle();
	function sortStyle() {
		var orderByValue = $orderBy.val();
		var orderValue = $order.val();
		if (orderByValue != "" && orderValue != "") {
			$(".sort[name='" + orderByValue + "']").after('<span class="' + orderValue + 'Sort">&nbsp;</span>');
		}
	}
	
	// 页码跳转
	$.gotoPage = function(id) {
		$pageNumber.val(id);
		$listForm.submit();
	};
	
});
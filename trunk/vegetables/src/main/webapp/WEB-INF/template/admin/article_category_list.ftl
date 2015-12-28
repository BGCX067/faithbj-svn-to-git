<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>文章分类列表</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var $deleteArticleCategory = $("#listTable .deleteArticleCategory");
	var $articleCategoryName = $("#listTable .articleCategoryName");

	// 删除商品分类
	$deleteArticleCategory.click( function() {
		if (confirm("您确定要删除此文章分类吗?") == false) {
			return false;
		}
	});

	// 树折叠
	$articleCategoryName.click( function() {
		var $this = $(this);
		var level = $this.parent().attr("level");
		var isHide;
		$this.parent().nextAll("tr").each(function(){
			$this = $(this);
			var thisLevel = $this.attr("level");
			if(thisLevel <=  level) {
				return false;
			}
			if(isHide == null) {
				if($this.is(":hidden")){
					isHide = true;
				} else {
					isHide = false;
				}
			}
			if(isHide) {
				$this.show();
			} else {
				$this.hide();
			}
		});
	});	
})
</script>
</head>
<body class="list">
	<div class="bar">
		文章分类列表&nbsp;总记录数: ${articleCategoryTreeList?size}
	</div>
	<div class="body articleCategory">
		<form id="listForm" action="article_category!list.action" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='/faith/article_category/add'" value="添加分类" hidefocus />
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th>
						<span>分类名称</span>
					</th>
					<th>
						<span>排序</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list articleCategoryTreeList as articleCategory>
					<tr level="${articleCategory.level}">
						<td class="articleCategoryName">
							<#if articleCategory.level == 0>
								<span class="pointer firstCategory" style="margin-left: ${articleCategory.level * 20}px;">
									${articleCategory.name}
								</span>
							<#else>
								<span class="pointer category" style="margin-left: ${articleCategory.level * 20}px;">
									${articleCategory.name}
								</span>
							</#if>
						</td>
						<td>
							${articleCategory.orderList}
						</td>
						<td>
							<a href="${base}/${articleCategory.url}" target="_blank" title="浏览">[浏览]</a>
							<#if (articleCategory.children?size > 0)>
								<span title="无法删除">[删除]</span>
							<#else>
								<a href="/faith/article_category/${articleCategory.id}/delete" class="deleteArticleCategory" title="删除">[删除]</a>
							</#if>
							<a href="/faith/article_category/${articleCategory.id}/edit" title="编辑">[编辑]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if articleCategoryTreeList?size == 0>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>
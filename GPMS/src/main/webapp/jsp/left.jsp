<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script src="${rootPath }/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>

</head>

<body>
		<div class="lefttop">
			<span></span>系统菜单
		</div>

		<dl class="leftmenu">
			<c:forEach var="menu1" items="${sessionScope.menuTreeList}"
				varStatus="i">
				<dd>
					<div class="title">
						<span></span><i class="fa fa-cog"></i>${menu1.menuNm }</div>
					<c:if test="${!empty menu1.children}">
						<ul class="menuson">
							<c:forEach var="menu2" items="${menu1.children}" varStatus="i">
								<li><cite></cite><a href="${menu2.jspUrl }"
									target="mainFrame">${menu2.menuNm}</a><i></i></li>
							</c:forEach>
						</ul>
					</c:if>
				</dd>
			</c:forEach>
		</dl>
</body>
</html>
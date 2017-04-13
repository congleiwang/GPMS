<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="${rootPath }/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body>

	<div class="topleft">
		<a href="main.html" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
	</div>

	<div class="topright">
		<ul>
			<li><span><img src="images/help.png" title="帮助"
					class="helpimg" /></span><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
			<li><a href="login.html" target="_parent">退出</a></li>
		</ul>

		<div class="user">
			<span>admin</span> <i>消息</i> <b>5</b>
		</div>

	</div>

</body>
</html>
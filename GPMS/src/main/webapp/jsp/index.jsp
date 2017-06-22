<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.2/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/syUtil.js"></script>
<%
	String easyuiThemeName = "default";
	Cookie cookies[] = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("easyuiThemeName")) {
				easyuiThemeName = cookie.getValue();
				break;
			}
		}
	}
%>
<link id="easyuiTheme"  rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/<%=easyuiThemeName%>/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/icon.css" type="text/css"></link>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;">
		<jsp:include page="layout/north.jsp"></jsp:include>
	</div>
	<div data-options="region:'south'" style="height:0px;"></div>
	<div data-options="region:'west'" style="width:200px;">
		<jsp:include page="layout/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'">
		<jsp:include page="layout/center.jsp"></jsp:include>
	</div>
</body>
</html>

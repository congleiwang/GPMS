<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>管理系统</title>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="${rootPath }/jsp/top.jsp" name="topFrame" scrolling="No" noresize="noresize"
		id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="${rootPath }/jsp/left.jsp" name="leftFrame" scrolling="No"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${rootPath }/jsp/main.jsp" name="mainFrame" id="mainFrame"
			title="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
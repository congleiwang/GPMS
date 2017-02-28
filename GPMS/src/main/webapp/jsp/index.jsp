<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("ctxBase", path);
	String currentName = (String )request.getAttribute("current");
%>
	<head>
		<title>论文管理系统</title>
		<script type="text/javascript">
	        var user = {};
			user.userName = '${SPRING_SECURITY_CONTEXT.authentication.principal.userNm}';
			user.userId = '${SPRING_SECURITY_CONTEXT.authentication.principal.userId}';
			user.roles = '${SPRING_SECURITY_CONTEXT.authentication.principal.roleIds}';
			user.orgId = '${SPRING_SECURITY_CONTEXT.authentication.principal.orgId}';
        </script>
              
        <script type="text/javascript">
        	user.jsessionId = getSessionId();        	 
              //当浏览器窗口大小改变时，设置显示内容的高度  
            window.onresize=function(){  
                   changeDivHeight();  
            }  
            var ctx  = '<%=basePath.substring(0, basePath.length() - 1)%>';
            var basePath = ctx;
        </script>        
	</head>
	<body>
</body>
  
</html>
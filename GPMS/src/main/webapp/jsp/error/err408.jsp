<!DOCTYPE>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.sunyard.frame.base.service.EhcacheSysParService" %>
<%@ page import="com.sunyard.frame.base.service.impl.EhcacheSysParServiceImpl" %>
<%@ page import="com.sunyard.frame.base.constant.ItemConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
//    String msg = (String)request.getSession().getAttribute("msg");
    ApplicationContext context = WebApplicationContextUtils. getWebApplicationContext(application);
    EhcacheSysParService service = (EhcacheSysParServiceImpl)context.getBean("ehcacheSysParService");
    String title = (String)service.getSysParamValueList(ItemConst.DEFAULT_SYSNAME,"System","SysName").getCparamvalue();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><%=title%></title>
    <meta http-equiv="refresh" content="3; url=<%=basePath%>/jsp/login.jsp">
    <!-- content="3，即3秒后返回主页，可根据需要修改或者删除这段代码 -->
    <link href="<%=basePath%>/res/css/408.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div>
    <div class="bg">
        <input type="button" class="input" onClick="location.href='<%=basePath%>/jsp/login.jsp'">
    </div>
</div>

<!-- 代码 开始 -->
<%--<div id="container" style="background-image:url(<%=basePath%>/res/img/error.png)">
    <pre style="margin-top:80px;margin-left:80px;"><%=msg==null?"信雅达风险加权资产管理系统":msg %></pre>
</div>--%>
<!-- 代码 结束 -->

</body>
</html>
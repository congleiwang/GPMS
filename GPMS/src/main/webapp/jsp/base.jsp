<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- core 为JSTL的核心库，包含循环，判断，set赋值标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- fmt 为JSTL的格式化库，用于格式化字符串，日期，金额 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- fn 为JSTL的函数库，包含操作字符串的一些函数，一般用于判断长度 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set scope="page" var="rootPath" value="${pageContext.request.contextPath }"></c:set>
<c:if test="${not empty sessionScope.result && sessionScope.result == 1}">
	<font color="red">操作成功</font>
	<c:remove var="result" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.result && sessionScope.result == 0}">
	<font color="red">操作失败</font>
	<c:remove var="result" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.msg}">
	<font color="red">${msg }</font>
	<c:remove var="msg" scope="session"/>
</c:if>
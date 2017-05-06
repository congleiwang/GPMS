<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-layout" data-options="fit:true">
	<form id="userInfo" method="post">
		<input type="hidden" name="userId" value=""/>
		<table>
			<tr>
				<th>所属机构</th>
				<td><input name="orgNm" type="text" value="${sessionScope.user.orgNm}" /></td>
				<th>登陆名</th>
				<td><input name="loginNm" type="text" value="${sessionScope.user.loginNm}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th>用户名</th>
				<td><input name="userNm" type="text" value="${sessionScope.user.userNm}" readonly="readonly" /></td>
				<th>电话</th>
				<td><input name="phoneNum" type="text" value="${sessionScope.user.phoneNum}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input name="address" type="text" value="${sessionScope.user.address}" readonly="readonly" /></td>
				<th>邮箱</th>
				<td><input name="email" type="email" value="${sessionScope.user.email}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th>注册时间</th>
				<td><input name="signAt" type="text" value="${sessionScope.user.signAt}" readonly="readonly" /></td>
				<th>最后登陆</th>
				<td><input name="lastLoginAt" type="text" value="${sessionScope.user.lastLoginAt}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th>最后修改</th>
				<td><input name="modAt" type="text" value="${sessionScope.user.modAt}" readonly="readonly" /></td>
				<th>修改次数</th>
				<td><input name="modCount" type="text" value="${sessionScope.user.modCount}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="3"><textarea name='remark' rows="3" cols="60">${sessionScope.user.remark}</textarea></td>
			</tr>
		</table>
	</form>
</div>
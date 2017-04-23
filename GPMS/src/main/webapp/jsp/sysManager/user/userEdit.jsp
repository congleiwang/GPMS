<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<from>
<form id="user_editForm" method="post">
	<input type="hidden" name="userId"/>
	<table>
		<tr>
			<th>所属机构</th>
			<td><select class="easyui-combotree"
				url="${pageContext.request.contextPath}/org/getPermOrgTree"
				name="orgId"></select></td>
			<th>登录名</th>
			<td><input name="loginNm" class="easyui-validatebox"
				data-options="required:true" /></td>
		</tr>
		<tr>
			<th>用户名</th>
			<td><input name="userNm" type="text" class="easyui-validatebox"
				data-options="required:true" /></td>
			<th>密码</th>
			<td><input name="passwd" type="password" /><font color='red'>不修改请留空</font></td>
		</tr>
		<tr>
			<th>电话</th>
			<td><input name="phoneNum" type="text" /></td>
			<th>地址</th>
			<td><input name="address" type="text" /></td>
		</tr>
		<tr>
			<th>邮箱</th>
			<td><input name="email" type="email" /></td>
			<th>是否锁定</th>
			<td><select name="isLock">
					<option value="1" selected="selected">是</option>
					<option value="0">否</option>
			</select></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><textarea name='remark' rows="3" cols="60"></textarea></td>
		</tr>
	</table>
</form>
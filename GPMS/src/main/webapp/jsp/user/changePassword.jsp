<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<form id="changePasswordForm" method="post">
	<table>
		<tr>
			<th>原密码：</th>
			<td><input name="oldPasswd" type="password"
				class="easyui-validatebox"
				data-options="required:true,missingMessage:'原密码必填'" /></td>
		</tr>
		<tr>
			<th>新密码</th>
			<td><input name="newPasswd" type="password"
				class="easyui-validatebox"
				data-options="required:true,missingMessage:'新密码必填'" /></td>
		</tr>
		<tr>
			<th>重复新密码</th>
			<td><input name="rePwd" type="password"
				class="easyui-validatebox"
				data-options="required:true,validType:'eqPwd[\'#changePasswordForm input[name=newPasswd]\']'" />
			</td>
		</tr>
	</table>
</form>

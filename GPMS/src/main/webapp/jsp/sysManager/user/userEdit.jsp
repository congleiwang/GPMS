<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<from>
<form id="user_editForm" method="post">
	<input type="hidden" name="userId"/>
	<table>
		<tr>
			<th>所属机构</th>
			<td><select class="easyui-combotree" style="width:200px;"
				url="${pageContext.request.contextPath}/org/getPermOrgTree"
				name="orgId"></select></td>
			<th>登录名</th>
			<td><input name="loginNm" class="easyui-validatebox"
				data-options="required:true,validType:'username'" /></td>
		</tr>
		<tr>
			<th>用户名</th>
			<td><input name="userNm" type="text" class="easyui-validatebox"
				data-options="required:true,validType:'minLength[2]'" /></td>
			<th>密码</th>
			<td><input name="passwd" type="password" class="easyui-validatebox" data-options="validType:'minLength[6]'" /><font color='red'>不修改请留空</font></td>
		</tr>
		<tr>
			<th>确认密码</th>
				<td><input type="password" class="easyui-validatebox" data-options="validType:'eqPwd[\'#user_editForm input[name=passwd]\']'" /></td>
			<th>电话</th>
			<td><input name="phoneNum" type="text" /></td>
		</tr>
		<tr>
			<th>地址</th>
			<td><input name="address" type="text" /></td>
			<th>邮箱</th>
			<td><input name="email" type="email" class="easyui-validatebox" data-options="required:true,validType:'email'"  /></td>
		</tr>
		<tr>
			<th>是否锁定</th>
			<td>
				<select id="userEditIsLock" name="isLock" class="easyui-combobox" style="width: 100px">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><textarea name='remark' rows="3" cols="60"></textarea></td>
		</tr>
	</table>
</form>
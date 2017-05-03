<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/applyMentor/getMyMentor',
			type:'post',
			dataType : 'json',
			success : function(d) {
                $('#myMentor_Form').form('load',{
                	orgNm:d.orgNm,
    				loginNm:d.loginNm,
    				userNm:d.userNm,
    				phoneNum:d.phoneNum,
    				address:d.address,
    				email:d.email,
    				isLock:d.isLock,
    				signAt:timeToString(d.signAt),
    				lastLoginAt:timeToString(d.lastLoginAt),
    				modAt:timeToString(d.modAt),
    				modCount:d.modCount,
    				remark:d.remark,
                });
			}
           });
	});
</script>
<form id="myMentor_Form" method="post">
	<table>
		<tr>
			<th>所属机构</th>
			<td><input name="orgNm" type="text" readonly="readonly" /></td>
			<th>登陆名</th>
			<td><input name="loginNm" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>用户名</th>
			<td><input name="userNm" type="text" readonly="readonly" /></td>
			<th>电话</th>
			<td><input name="phoneNum" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>地址</th>
			<td><input name="address" type="text" readonly="readonly" /></td>
			<th>邮箱</th>
			<td><input name="email" type="email" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>是否锁定</th>
			<td><select name="isLock" draggable="false">
					<option value="1">是</option>
					<option value="0">否</option>
			</select></td>
			<th>注册时间</th>
			<td><input name="signAt" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>最后登陆</th>
			<td><input name="lastLoginAt" type="text" readonly="readonly" /></td>
			<th>最后修改</th>
			<td><input name="modAt" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>修改次数</th>
			<td><input name="modCount" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><textarea name='remark' rows="3" cols="60" readonly="readonly"></textarea></td>
		</tr>
	</table>
</form>
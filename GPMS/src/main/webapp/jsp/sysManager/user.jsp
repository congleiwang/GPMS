<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${rootPath }/css/model.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
</script>
</head>
<body class="body">
	<form action="">
	 	<fieldset>
    		<legend class="input-sm">查询</legend>
    			<p>
				<label for="userNm">用户名：</label>
				<input type="text" name="userNm" id="userNm" />
				<label for="loginNm">登陆名：</label>
				<input type="text" name="loginNm" id="loginNm" />
				<label for="address">地址：</label>
				<input type="text" name="address" id="address" />
				</p>
				<label for="signAtStart">注册时间：</label>
				<input name="signAtStart" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
				<label for="signAtEnd">至</label>
				<input name="signAtEnd" onfocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
				<label for="isLock">是否锁定：</label>
				<select name="isLock" id="isLock">
					<option value="" disabled="disabled" selected="selected">--请选择--</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<button type="submit" >查询</button>
		</fieldset>
	</form>
	<input type="button" value="新增" />
	<input type="button" value="删除" />
	<input type="button" value="修改" />
	<input type="button" value="锁定用户" />
	<table class="table">
	  <thead>
	    <tr>
	      <th>用户名</th>
	      <th>机构名</th>
	      <th>登陆名</th>
	      <th>电话</th>
	      <th>地址</th>
	      <th>邮箱</th>
	      <th>是否锁定</th>
	      <th>是否锁定</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td>Tanmay</td>
	      <td>Bangalore</td>
	    </tr>
	    <tr>
	      <td>Sachin</td>
	      <td>Mumbai</td>
	    </tr>
	  </tbody>
	</table>
</body>
</html>
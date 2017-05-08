<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<img alt="" height="55px" width="350px" src="${pageContext.request.contextPath}/images/index_logo.jpg">
</div>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionScope.user != null}">
		[<strong>${sessionScope.user.userNm}</strong>]，欢迎你！您使用[<strong>${sessionScope.user.ipAddr}</strong>]IP登录！
	</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#controlPanel',iconCls:'icon-help'">控制面板</a>
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#logout',iconCls:'icon-back'">注销</a>
</div>
<div id="controlPanel" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div onclick="changePassword();">修改密码</div>
	<div class="menu-sep"></div>
	<c:forEach items="${sessionScope.user.roles}" var="role">
		<c:if test="${role.roleId==2}">
			<div onclick="canApplySetting();">可申请开关</div>
			<div class="menu-sep"></div>
		</c:if>
	</c:forEach>
	<div data-options="iconCls:'icon-edit'">  
        <span>更换皮肤</span>
        <div style="width:150px;">
	        <div onclick="changeTheme('default');">默认</div>
			<div onclick="changeTheme('black');">黑色</div>
			<div onclick="changeTheme('bootstrap');">亮灰</div>
			<div onclick="changeTheme('gray');">灰色</div>
			<div onclick="changeTheme('material');">淡灰</div>
			<div onclick="changeTheme('metro');">纯白</div>
		</div>
	</div>
</div>
<div id="logout" style="width: 100px; display: none;">
	<div onclick="logout();">注销</div>
</div>
<script type="text/javascript" charset="utf-8">
	function changeTheme(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});
	}

	function logout() {
		$('#sessionInfoDiv').html('');
		$.post('${pageContext.request.contextPath}/j_spring_security_logout', function() {
			location.replace('${pageContext.request.contextPath}/');
		});
		$.ajax({
			url : '${pageContext.request.contextPath}/j_spring_security_logout',
			type:'post',
			dataType : 'json',
			success : function(d) {
				$('#sessionInfoDiv').html('');
				location.replace('${pageContext.request.contextPath}/');
			}
		});
	}
	function showUserInfo() {
		var p = $('<div/>').dialog({
			title : '用户信息',
			href : '${pageContext.request.contextPath}/jsp/user/userInfo.jsp',
			width : 700,
			height : 400,
			buttons : [ {
				text : '修改信息',
				handler : function() {
					if($("#userInfo").form('validate')){
						var phoneNum=$("#userInfo input[name=phoneNum]").val();
						var email=$("#userInfo input[name=email]").val();
						$.ajax({
							url : '${pageContext.request.contextPath}/user/upMyInfo',
							data :"phoneNum="+phoneNum+"&email="+email,
							type:'post',
							dataType : 'json',
							success : function(d) {
								$.messager.alert({
									title : '提示',
									msg : d.msg
								});
								if(d.success){
									p.dialog('close');
								}
							}
						});
					}
				}
			} ]
		});
	}
	function changePassword(){
		var p = $('<div/>').dialog({
			title : '用户信息',
			href : '${pageContext.request.contextPath}/jsp/user/changePassword.jsp',
			width : 400,
			modal:true,
			height :170,
			buttons : [ {
				text : '修改密码',
				handler : function() {
					if($("#changePasswordForm").form('validate')){
						var oldPasswd=$("#changePasswordForm input[name=oldPasswd]").val();
						var newPasswd=$("#changePasswordForm input[name=newPasswd]").val();
						$.ajax({
							url : '${pageContext.request.contextPath}/user/changePasswd',
							data :"oldPasswd="+oldPasswd+"&newPasswd="+newPasswd,
							type:'post',
							dataType : 'json',
							success : function(d) {
								$.messager.alert({
									title : '提示',
									msg : d.msg
								});
								if(d.success){
									setTimeout('logout()',1000); 
								}
							}
						});
					}
				}
			} ]
		});
	}
	function canApplySetting(){
		var p = $('<div/>').dialog({
			title : '可申请设置',
			href : '${pageContext.request.contextPath}/jsp/user/canApply.jsp',
			width : 400,
			modal:true,
			height :170,
			buttons : [ {
				text : '保存',
				handler : function() {
					$('#canApplyForm').form('submit',{
					    url : '${pageContext.request.contextPath}/user/canApply',
					    success:function(r){
					        obj=$.parseJSON(r);
					        if(obj.success){
						       p.dialog('destroy');
					        }
					        $.messager.show({title:'提示',msg:obj.msg});
					    }
				    });
				}
			} ]
		});
	}
</script>
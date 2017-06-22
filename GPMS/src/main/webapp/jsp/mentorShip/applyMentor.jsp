<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/applyMentor/getApply',
		dataType : 'json',
		success : function(data) {
			if(data){
				$('#applyMentor_Form').form('load',{
					receiver:data.receiver,
					state:data.state,
					receiverNm:data.receiverNm,
					applySbjt:data.applySbjt,
					applyCont:data.applyCont
	            });
				if(data.state && data.state!='1'){ //2发送 或 3同意
					$("#applyMentor_Form textarea").attr("readonly","readonly");
					$("#applyMentor_Form input").attr("disabled","disabled");
					if(data.state=='2'){
						$("#applyMentor_Tip").html('已发送请等待通知');
					}else{
						$("#applyMentor_Tip").html('申请成功，请在我的导师菜单查看导师信息！');
					}
				}
			}
		}
	});
});
function amSelectMentor(){
	var selectMentor=$('<div/>').dialog({
		title : '选择导师',
		href : '${pageContext.request.contextPath}/jsp/mentorShip/selectMentor.jsp',
		width : 900,
		height : 600,
		modal : true,
		resizable :true,
		buttons : [ {
			text : '选择',
			handler : function() {
				var row = $('#applyMentor_datagrid').datagrid('getSelected');
				$("#applyMentor_Form input[name='receiver']").val(row.userId);
				$("#applyMentor_Form input[name='receiverNm']").val(row.userNm);
				$('#applyMentor_datagrid').datagrid('unselectAll');
				selectMentor.dialog('destroy');
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		},
	});
}
function applyMentorSave(){
	var state=$('#applyMentor_Form input[name=state]').val();
	var url;
	if(state==1 || state=='1'){
		url='${pageContext.request.contextPath}/applyMentor/update';
	}else{
		url='${pageContext.request.contextPath}/applyMentor/add';
	}
	$('#applyMentor_Form').form('submit',{
		url:url,
		success:function(data){
			var obj=$.parseJSON(data);
            $.messager.alert('提示', obj.msg);
            if(data.success){
            	var tab = $('#layout_center_centerTabs').tabs('getSelected');  // 获取选择的面板
            	$('#layout_center_centerTabs').tabs('update', {
            		tab: tab,
            		options: {
            			href: '/jsp/mentorShip/applyMentor.jsp'  // 新内容的URL
            		}
            	});

            }
		}
	});
}
function applyMentorSend(){
	$.messager.confirm('请确认',"提交后将不可修改，确认提交？",
			function(r) {
				if (r) {
					$('#applyMentor_Form').form('submit',{
						url:'${pageContext.request.contextPath}/applyMentor/sendApply',
						success:function(data){
							var obj=$.parseJSON(data);
				            $.messager.alert('提示', obj.msg);
				            if(obj.success){
					            $("#applyMentor_Form textarea").attr("readonly","readonly");
								$("#applyMentor_Form input").attr("disabled","disabled");
				            }
						}
					});
				}
	});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<form id="applyMentor_Form" method="post">
		<input type="hidden" name="receiver"/>
		<input type="hidden" name="state"/>
		<table class="tableForm" style="width: 100%; height: 100%;">
			<tr><td colspan="2"><span id="applyMentor_Tip"></span></td></tr>
			<tr>
				<th>导师姓名：</th>
				<td><input type="text" name="receiverNm" />
					<input type="button" onclick="amSelectMentor();" value="选择导师"></td>
			</tr>
			<tr>
				<th>申请标题：</th>
				<td><input name="applySbjt" type="text" style="width: 250px" /></td>
			</tr>
			<tr>
				<th>申请正文：</th>
				<td><textarea name="applyCont" rows="6" cols="50"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" value="保存" onclick="applyMentorSave();" />
					<input type="reset" value="重置" />
					<input type="button" value="提交申请" onclick="applyMentorSend();" />
				</td>
			</tr>
		</table>
	</form>
</div>
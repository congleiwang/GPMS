<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
#msg_datagrid_wrap .datagrid-header {
	position: absolute; 
	display: none;     
}
</style>
<div id="msg_datagrid_wrap" data-options="region:'east',title:'我的消息',split:true,tools:[{
	                iconCls:'icon-reload',
	                handler:function(){
	                    $('#msg_datagrid').datagrid('load');
	                }
	            }]"
	style="width: 350px;">
	<table id="msg_datagrid" ></table>
</div>
<div id="msg_detailDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'消息通知',resizable :true,
	buttons : [ {
		text : '关闭',
		handler : function() {
			$('#msg_datagrid').datagrid('unselectAll');
			$('#msg_detailDialog').dialog('close');
		}
	}]">
	<form id="msg_detailForm">
		<table class="tableForm" style="width: 100%; height: 100%;">
			<tr>
				<th>发送人：</th>
				<td><input type="text" name="senderNm" /></td>
			</tr>
			<tr>
				<th>发送时间：</th>
				<td><input type="text" name="sendAt" /></td>
			</tr>
			<tr>
				<th>消息标题：</th>
				<td><input name="msgTitle" type="text" style="width: 250px" /></td>
			</tr>
			<tr>
				<th>消息内容：</th>
				<td><textarea name="msgCont" rows="6" cols="50"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
var msgDg = $('#msg_datagrid');
$(function() {
	msgDg.datagrid({
				fit : true,
				url : '${pageContext.request.contextPath}/msg/list',
				idField : 'msgId',
				pagePosition : 'bottom',
				singleSelect:true,
				pageSize:5,
				pageList:[5,10],
				pagination : true,
				loadMsg : '正在加载数据当中....',
				columns : [ [  {
					title:"所有消息：",
					field : 'msgTitle',
					width:350,
					formatter : function(value, rowData, rowIndex) {
						var sender;
						if(rowData.sender==0){
							rowData.senderNm="系统通知"
						}
						if(rowData.msgState=='1'){
							return timeToString(rowData.sendAt)+"&nbsp;&nbsp;&nbsp;"+rowData.senderNm+":"+"<p>"+value+"</p>"+"<p>"+rowData.msgCont+"</p>";
						}else{
							return "<font color='red'>"+timeToString(rowData.sendAt)+
									"&nbsp;&nbsp;&nbsp;"+rowData.senderNm+":"+"<p>"+value+"</p>"+"<p>"+rowData.msgCont+"</p></font>";							
						}
					}
				} ] ],
				toolbar : [ {
					text : '设为已读',
					iconCls : 'icon-add',
					handler : function() {
						setRead();
					}
				},'-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						msgDel();
					}
				}],
				onDblClickRow:function(index, row){
		                $('#msg_detailForm').form('load',{
		                	senderNm:row.senderNm,
		                	sendAt:timeToString(row.sendAt),
		                	msgTitle:row.msgTitle,
		                	msgCont:row.msgCont
		                });
		                $('#msg_detailDialog').dialog('open');
		                $("#msg_detailForm textarea").attr("readonly","readonly");
						$("#msg_detailForm input").attr("readonly","readonly");
						
						if(row && row.msgState!='1'){
							$.ajax({
								url : '${pageContext.request.contextPath}/msg/readMsg',
								data :"msgId="+row.msgId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									if(d.success){
										msgDg.datagrid('reload');
									}
								}
							});
						}
				}
			});
	var pager = $('#msg_datagrid').datagrid('getPager');
	pager.pagination({  
	    showPageList:false,
	    showRefresh:false
	}); 
	//定时获取消息
	setInterval(function() {
	    $.get('${pageContext.request.contextPath}/msg/unreadCount',function(count){
	        if(count>0){
                $.messager.show({
	                title:'提示',
	                msg:'你有'+count+'条未阅读的短消息，<a href="#" onclick="msgLook()">点击查看</a>',
	                timeout:5000,
	                showType:'slide'
                });
	        }
	    });
	}, 30000);
});
function setRead(){
	var row=msgDg.datagrid("getSelected");
	if(row && row.msgState!='1'){
		$.ajax({
			url : '${pageContext.request.contextPath}/msg/readMsg',
			data :"msgId="+row.msgId,
			type:'post',
			dataType : 'json',
			success : function(d) {
				if(d.success){
					msgDg.datagrid('reload');
				}
			}
		});
	}else{
		 $.messager.alert('提示', "请选择一条未读消息！");
	}
}
function msgDel(){
	var row=msgDg.datagrid("getSelected");
	if(row){
		$.ajax({
			url : '${pageContext.request.contextPath}/msg/del',
			data :"id="+row.msgId,
			type:'post',
			dataType : 'json',
			success : function(d) {
				if(d.success){
					msgDg.datagrid('reload');
				}
				$.messager.show({
					title : '提示',
					msg : d.msg
				});
			}
		});
	}else{
		$.messager.alert('提示', "请选择一条消息！");
	}
}
function msgLook(){
	$('#layout_center_centerTabs').tabs('select',0);
}
</script>
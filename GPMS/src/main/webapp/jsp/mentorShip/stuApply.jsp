<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var stuApplyDg = $('#stuApply_datagrid');
	$(function() {
		stuApplyDg.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					singleSelect:true,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/applyMentor/stuApplyList',
					idField : 'sender',
					pagePosition : 'bottom',
					pagination : true,
					loadMsg : '正在加载数据当中....',
					frozenColumns : [ [ {
						field : 'senderNm',
						title : '申请人',
						width : 100
					}] ],
					columns : [ [ {
						field : 'createAt',
						title : '申请时间',
						width : 150,
						formatter : function(value, rowData, rowIndex) {
							return timeToString(value);
						}
					} , {
						field : 'applySbjt',
						title : '标题',
						width : 250
					} ,  {
						field : 'applyCont',
						title : '正文',
						width : 630
					} ] ],
					onDblClickRow:function(){
						var row=stuApplyDg.datagrid("getSelected");
						if(row){
			                $('#stuApply_detailForm').form('load',{
			                	senderNm:row.senderNm,
			                	createAt:timeToString(row.createAt),
			                	applySbjt:row.applySbjt,
			                	applyCont:row.applyCont
			                });
			                $('#stuApply_detailDialog').dialog('open');
			                $("#stuApply_detailForm textarea").attr("readonly","readonly");
							$("#stuApply_detailForm input").attr("readonly","readonly");
						}
					},
					toolbar : [ {
						text : '同意',
						iconCls : 'icon-ok',
						handler : function() {
							stuApplyAllow();
						}
					}, '-', {
						text : '拒绝',
						iconCls : 'icon-edit',
						handler : function() {
							stuApplyReject();
						}
					},  '-', {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							stuApplyDg.datagrid('rejectChanges');
							stuApplyDg.datagrid('unselectAll');
						}
					}]
				});
	});
	function stuApplySearchs() {
		var searchForm = $('#stuApply_searchForm').form();
		stuApplyDg.datagrid('load', serializeObject(searchForm));
	}
	function stuApplyCleanSearch() {
		stuApplyDg.datagrid('load', {});
		$('#stuApply_searchForm').form('clear');
	}
	
	function stuApplyAllow() {
		var row=stuApplyDg.datagrid("getSelected");
		if(!row){
			$.messager.show({title : '提示',msg : '请选择一条数据！'});
			return;
		}
		$.messager.confirm('请确认','确认同意指导该学生论文？',
			function(r) {
				if (r) {
					$.ajax({
						url : '${pageContext.request.contextPath}/applyMentor/stuApplyAllow',
						data :"sender="+row.sender,
						type:'post',
						dataType : 'json',
						success : function(d) {
							if(d.success){
								stuApplyDg.datagrid('load');
								stuApplyDg.datagrid('unselectAll');
							}
							$.messager.show({title : '提示',msg : d.msg});
						}
					});
				}
			});
	}
	
	function stuApplyReject() {
		var row=stuApplyDg.datagrid("getSelected");
		if(!row){
			$.messager.show({title : '提示',msg : '请选择一条数据！'});
			return;
		}
		$.messager.confirm('请确认','确认拒绝指导该学生论文？',
			function(r) {
				if (r) {
					$.ajax({
						url : '${pageContext.request.contextPath}/applyMentor/stuApplyReject',
						data :"sender="+row.sender,
						type:'post',
						dataType : 'json',
						success : function(d) {
							if(d.success){
								stuApplyDg.datagrid('load');
								stuApplyDg.datagrid('unselectAll');
							}
							$.messager.show({title : '提示',msg : d.msg});
						}
					});
				}
			});
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="stuApply_toolbar">
			<form id="stuApply_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>学生姓名</th>
						<td><input name="senderNm"  /></td>
						<th>申请时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="stuApplySearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="stuApplyCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="stuApply_datagrid"></table>
	</div>
</div>

<div id="stuApply_detailDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'学生申请',resizable :true,
	buttons : [ {
		text : '关闭',
		handler : function() {
			$('#stuApply_datagrid').datagrid('unselectAll');
			$('#stuApply_detailDialog').dialog('close');
		}
	}]">
	<form id="stuApply_detailForm">
		<table class="tableForm" style="width: 100%; height: 100%;">
			<tr>
				<th>学生姓名：</th>
				<td><input type="text" name="senderNm" /></td>
			</tr>
			<tr>
				<th>申请时间：</th>
				<td><input type="text" name="createAt" /></td>
			</tr>
			<tr>
				<th>申请标题：</th>
				<td><input name="applySbjt" type="text" style="width: 250px" /></td>
			</tr>
			<tr>
				<th>申请正文：</th>
				<td><textarea name="applyCont" rows="6" cols="50"></textarea></td>
			</tr>
		</table>
	</form>
</div>
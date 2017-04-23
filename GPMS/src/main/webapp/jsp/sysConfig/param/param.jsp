<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var paramDatagrid = $('#param_datagrid');
	$(function() {
		paramDatagrid.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/sysParam/list',
					idField : 'paramKey',
					pagePosition : 'bottom',
					pagination : true,
					loadMsg : '正在加载数据当中....',
					columns : [ [ {
						field : 'sysNm',
						title : '系统',
						width : 100
					} , {
						field : 'paramKey',
						title : '参数键',
						width : 150
					} , {
						field : 'paramValue',
						title : '参数值',
						width : 100
					}, {
						field : 'type',
						title : '参数类型',
						width : 150
					}, {
						field : 'valueBound',
						title : '取值范围',
						width : 100
					},{
						field : 'orderBy',
						hidden:true
					},{
						field : 'remark',
						hidden:true
					}] ],
					toolbar : [ {
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							paramAdd();
						}
					}, '-', {
						text : '修改',
						iconCls : 'icon-edit',
						handler : function() {
							paramUpdate();
						}
					}, '-', {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							paramDatagrid.datagrid('rejectChanges');
							paramDatagrid.datagrid('unselectAll');
						}
					}]
				});
	});
	function paramSearchs() {
		var searchForm = $('#param_searchForm').form();
		paramDatagrid.datagrid('load', serializeObject(searchForm));
	}
	function paramCleanSearch() {
		paramDatagrid.datagrid('load', {});
		$('#param_searchForm').form('clear');
	}
	function paramAdd() {
		$('#param_updateDialog').dialog('open');
		$("#param_updateDialog").dialog('setTitle','添加参数');
		$('#param_updateForm').form('clear');
		$('.sysNm').css('visibility','visible');
		$('#param_updateForm [name=paramKey]').removeAttr('readonly');
		$('#param_isAdd').val(1);
	}
	function paramSave(){
		var isAdd=$('#param_isAdd').val();
		var url;
		if(isAdd==1 || isAdd=='1'){
			url='${pageContext.request.contextPath}/sysParam/add';
		}else{
			url='${pageContext.request.contextPath}/sysParam/update';
		}
		$('#param_updateForm').form('submit',{
			onSubmit:function(){
				return $(this).form('validate');
			},
			url:url,
            success:function(r){
            	obj=$.parseJSON(r);
                 if(obj.success){
                	$('#param_updateDialog').dialog('close');
                	$('#param_datagrid').datagrid('unselectAll');
                	paramDatagrid.datagrid('load');
                 }
                 $.messager.alert('提示', obj.msg);
		    }
		});
	}
	function update() {
		var rows = dg.datagrid('getSelections');
		if (rows.length == 1) {
			$('#param_updateDialog').dialog('open');
			$("#param_updateDialog").dialog('setTitle','修改参数');
			$('#param_isAdd').val(0);
			$('#param_updateForm').form('load',{
				sysNm:rows[0].sysNm,
				paramKey:rows[0].paramKey,
				paramValue:rows[0].paramValue,
				type:rows[0].type,
				valueBound:rows[0].valueBound,
				orderBy:rows[0].orderBy,
				paramDesc:rows[0].paramDesc
	         });
			$('.sysNm').css('visibility','hidden');
			$('#param_updateForm [name=paramKey]').attr('readonly','readonly');
		} else {
			$.messager.alert('提示', '请勾选一条要编辑的数据');
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="param_toolbar">
			<form id="param_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>所属系统</th>
						<td><select name="sysNm" draggable="false" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=system" valueField="keyValue" textField="caption">
							</select>
						</td>
						<th>参数键</th>
						<td><input name="paramKey"  /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="paramSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="paramCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="param_datagrid"></table>
	</div>
</div>
<div id="param_updateDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:' ',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			paramSave();
	   }
	}]">
	<form id="param_updateForm" method="post">
		<input type="hidden" id="param_isAdd" /> 
		<table>
			<tr>
				<th>参数键</th>
				<td><input name="paramKey" class="easyui-validatebox" data-options="required:true" /></td>
				<th class="sysNm">所属系统</th>
				<td class="sysNm">
					<select name="sysNm" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=system" valueField="keyValue" textField="caption">
					</select>
				</td>
			</tr>
			<tr>
				<th>参数值</th>
				<td><input name="paramValue" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				<th>参数类型</th>
				<td><select name="type" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=paramType" valueField="keyValue" textField="caption">
					</select></td>
			</tr>
			<tr>
				<th>取值范围</th>
				<td><input name="valueBound" type="text" /></td>
				<th>排序</th>
				<td><input name="orderBy" type="text" class="easyui-numberbox" data-option="min:0,precision:0"/></td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="3"><textarea name='paramDesc' rows="3" cols="60"></textarea></td>
			</tr>
		</table>
	</form>
</div>
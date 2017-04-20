<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var datagrid = $('#role_datagrid');
	var ru_datagrid = $('#role_user_datagrid');
	$(function() {
		datagrid.datagrid({
			url : '${pageContext.request.contextPath}/role/list',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			singleSelect:true,
			nowrap : false,
			rownumbers:true,
			border : false,
			idField : 'roleId',
			checkOnSelect : true,
			selectOnCheck : true,
			columns : [ [ {
				title : '角色名称',
				field : 'roleNm',
				width : 100
			}, {
				title : '角色类型',
				field : 'roleType',
				width : 100
			},{
				title : '是否启用',
				field : 'isUse',
				width : 100,
				formatter : function(value, rowData, rowIndex) {
					if(value==1 || value=='1'){
						return '是';
					}
					return '否';
				}
			} , {
				title : '备注',
				field : 'remark',
				width : 434
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
				}
			} ],
			onClickRow:function(index,data){
				var roots=$('#menuTree').tree('getRoots');
				$(roots).each(function(i,root){
				   $('#menuTree').tree('uncheck',root.target);
				});
				$.ajax({
					url : '${pageContext.request.contextPath}/menu/getMenuCdsByRoleId',
					data :'roleId='+data.roleId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						$(d).each(function(i,menuCd){
							var node=$('#menuTree').tree('find',menuCd);
							$('#menuTree').tree('check',node.target);
						});
					}
				});/* 
				$.ajax({
					url : '${pageContext.request.contextPath}/user/getUsersByRoleId',
					data :'roleId='+data.roleId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						$(d).each(function(i,menuCd){
							var node=$('#menuTree').tree('find',menuCd);
							$('#menuTree').tree('check',node.target);
						});
					}
				});  */
			}
		});
		ru_datagrid.datagrid({
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			singleSelect:true,
			nowrap : false,
			rownumbers:true,
			border : false,
			idField : 'userId',
			frozenColumns : [ [ {
				field : 'loginNm',
				title : '登陆名',
				width : 100
			}] ],
			columns : [ [ {
				title : '角色名称',
				field : 'roleNm',
				width : 100
			}, {}]]
		});
	});
	function del(){
		var row = dg.datagrid('getSelected');
		if(row){
			$.ajax({
				url : '${pageContext.request.contextPath}/user/getUsersByRoleId',
				data :'roleId='+row.roleId,
				type:'post',
				async:false,
				dataType : 'json',
				success : function(d) {
					if(d.length>0){
						$.messager.show({title : '提示',msg :'该角色含有用户，请先移除用户'});
						return ;
					}
				}
			});
			$.messager.confirm('请确认','确认删除？',
					function(r) {
						if (r) {
							$.ajax({
								url : '${pageContext.request.contextPath}/role/del',
								data :'roleId='+row.roleId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									$('#role_datagrid').datagrid('load');
									$('#role_datagrid').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : d.msg
									});
								}
							});
						}
					});
		}else{
			$.messager.show({title : '提示',msg :'请先选择一条数据'});
		}
	}
	function add(){
		$('#role_updateForm').form('clear');
		$('#role_updateDialog').dialog('open');
	}
	function update(){
		var row = dg.datagrid('getSelected');
		if(row){
			$('#role_updateDialog').dialog('open');
			$('#role_updateForm').form('load',{
				roleId:rows.roleId,
				roleNm:rows.roleNm,
				roleType:rows.roleType,
				isUse:rows.isUse,
				remark:rows.remark
	        });
		}else{
			$.messager.show({title : '提示',msg :'请先选择一条数据'});
		}
	}
	function save(){
		var url;
		var roleId=$("#role_updateForm [name=roleId]").val();
		if(roleId && roleId!=''){
			url='${pageContext.request.contextPath}/role/update';
		}else{
			url='${pageContext.request.contextPath}/role/add';			
		}
		$('#role_updateForm').form('submit',{
			onSubmit:function(){
				return $(this).form('validate');
			},
			url:url,
            success:function(r){
            	obj=$.parseJSON(r);
                 if(obj.success){
                	$('#role_updateDialog').dialog('destroy');
                	$('#role_datagrid').datagrid('unselectAll');
                 	searchs();
                 }
                 $.messager.alert('提示', obj.msg);
		    }
		});
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="width:600px;">
		<table id="role_datagrid"></table>
	</div>
	<div data-options="region:'east',border:false" style="width:400px;height: 100%">
		<div class="easyui-tabs">
			<div title="角色权限" style="padding:10px;">
				<ul class="easyui-tree" id="menuTree"
					data-options="
						url:'${pageContext.request.contextPath}/menu/getAllMenuTree',
						parentField:'parentId',
						lines:true,
						checkbox:true,
						border:false"
				</ul>
			</div>
			<div title="角色用户" style="padding:10px;">
				<table id="role_user_datagrid"></table>
			</div>
		</div>
	</div>
</div>
<div id="role_updateDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加权限',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			save();
	   }
	}]">
	<form id="role_updateForm" method="post">
		<input type="hidden" name="roleId"/>
		<table>
			<tr>
				<th>角色名称</th>
				<td><input name="roleNm" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>角色类型</th>
				<td><select name="roleType" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=roleType" valueField="keyValue" textField="caption">
					</select></td>
			</tr>
			<tr>
				<th>是否启用</th>
				<td><select name="isUse">
					<option value="1">是</option>
					<option value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="2"><textarea name='remark' rows="4" cols="40"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var roleDatagrid = $('#role_datagrid');
	var ru_datagrid = $('#roleUser_datagrid');
	$(function() {
		roleDatagrid.datagrid({
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
			columns : [ [ {
				title : '角色名称',
				field : 'roleNm',
				width : 100
			}, {
				title : '角色类型',
				field : 'roleType',
				width : 70
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
				width : 332
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					roleAdd();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					roleDel();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					roleUpdate();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
				}
			} ],
			onClickRow:function(index,data){
				var roots=$('#role_menuTree').tree('getRoots');
				$(roots).each(function(i,root){
				   $('#role_menuTree').tree('uncheck',root.target);
				});
				$.ajax({
					url : '${pageContext.request.contextPath}/menu/getMenuCdsByRoleId',
					data :'roleId='+data.roleId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						$(d).each(function(i,menuCd){
							var node=$('#role_menuTree').tree('find',menuCd);
							var isLeaf=$('#role_menuTree').tree('isLeaf', node.target);
							if(isLeaf){
								$('#role_menuTree').tree('check',node.target);
							}
						});
					}
				});
				ru_datagrid.datagrid({
					url : '${pageContext.request.contextPath}/user/getUsersByRoleId?roleId='+data.roleId
				})
			}
		});
		ru_datagrid.datagrid({
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			nowrap : false,
			rownumbers:true,
			border : false,
			idField : 'userId',
			frozenColumns : [ [ {
				field : 'loginNm',
				title : '登陆名',
				width : 90
			}] ],
			columns : [ [ {
				field : 'userNm',
				title : '用户名',
				width : 100
			} , {
				field : 'orgNm',
				title : '所属机构',
				width : 120
			} , {
				field : 'phoneNum',
				title : '电话',
				width : 90
			} , {
				field : 'email',
				title : '邮箱',
				width : 90
			}]],
			toolbar : [ {
				text : '加入',
				iconCls : 'icon-add',
				handler : function() {
					rolePutUser();
				}
			}, '-', {
				text : '移除',
				iconCls : 'icon-remove',
				handler : function() {
					roleRemoveUser();
				}
			}, '-', {
				text : '全选',
				iconCls : 'icon-ok',
				handler : function() {
					ru_datagrid.datagrid('selectAll');
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					ru_datagrid.datagrid('unselectAll');
				}
			} ]
		});
	});
	//删除某一角色
	function roleDel(){
		var row = $('#role_datagrid').datagrid('getSelected');
		if(row){
			var flag=false;
			$.ajax({
				url : '${pageContext.request.contextPath}/user/getUsersByRoleId',
				data :'roleId='+row.roleId,
				type:'post',
				async:false,
				dataType : 'json',
				success : function(d) {
					if(d.length>0){
						$.messager.show({title : '提示',msg :'该角色含有用户，请先移除用户'});
						flag=true;
					}
				}
			});
			if(flag){
				return;
			}
			$.messager.confirm('请确认','确认删除？',
					function(r) {
						if (r) {
							$.ajax({
								url : '${pageContext.request.contextPath}/role/del',
								data :'id='+row.roleId,
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
	//打开添加角色对话框
	function roleAdd(){
		$('#role_updateDialog').dialog('open');
		$('#role_updateForm').form('clear');
		$("#role_updateDialog").dialog('setTitle','添加角色');
	}
	//打开修改角色对话框
	function roleUpdate(){
		var row = roleDatagrid.datagrid('getSelected');
		if(row){
			$('#role_updateDialog').dialog('open');
			$("#role_updateDialog").dialog('setTitle','修改角色');
			$('#role_updateForm').form('load',{
				roleId:row.roleId,
				roleNm:row.roleNm,
				roleType:row.roleType,
				isUse:row.isUse,
				remark:row.remark
	        });
		}else{
			$.messager.show({title : '提示',msg :'请先选择一条数据'});
		}
	}
	//保存添加或修改对话框中信息
	function roleSave(){
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
                	$('#role_updateDialog').dialog('close');
                	$('#role_datagrid').datagrid('unselectAll');
                	$('#role_datagrid').datagrid('load', {});
                 }
                 $.messager.alert('提示', obj.msg);
		    }
		});
	}
	//打开向角色中添加用户对话框
	function rolePutUser(){
		var roleRow = roleDatagrid.datagrid('getSelected');
		if(roleRow){
			var authAdd=$('<div/>').dialog({
				title : '为用户设置角色',
				href : '${pageContext.request.contextPath}/jsp/sysManager/auth/authAdd.jsp',
				width : 900,
				height : 600,
				modal : true,
				resizable :true,
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad:function(){
					$('#roleUserAdd_datagrid').datagrid({
						url : '${pageContext.request.contextPath}/user/getUsersExRoleId?roleId='+roleRow.roleId
					})
				},
				buttons : [ {
					text : "加入到 <font color='red'>"+roleRow.roleNm+"</font>",
					handler : function() {
						var msg;
						var userRows = $('#roleUserAdd_datagrid').datagrid('getSelections');
						if (!userRows || userRows.length == 0) {
							$.messager.alert('提示', '请选择一条数据！');
							return;
						}
						if(userRows.length==1){
							msg='确认将该用户加入？'
						}else{
							msg='确认将选中的多个用户加入？'
						}
						$.messager.confirm('请确认',msg,
								function(r) {
									if (r) {
										var userIds=[];
										for ( var i = 0; i < userRows.length; i++) {
											userIds.push(userRows[i].userId);
										}
										$.ajax({
											url : '${pageContext.request.contextPath}/role/putUsers',
											data : {"userIds":userIds.join(','),"roleId":roleRow.roleId},
											type:'post',
											dataType : 'json',
											success : function(d) {
												$('#roleUserAdd_datagrid').datagrid('load');
												$('#roleUser_datagrid').datagrid('load');
												$('#roleUserAdd_datagrid').datagrid('unselectAll');
												$.messager.show({
													title : '提示',
													msg : d.msg
												});
											}
										});
									}
							});
					}
				} ]
			});
		}else{
			$.messager.show({title : '提示',msg :'请先选择一种角色'});
		}
	}
	//将角色中的某些用户移除掉
	function roleRemoveUser(){
		var roleRow = roleDatagrid.datagrid('getSelected');
		var userRows = $('#roleUser_datagrid').datagrid('getSelections');
		if (!userRows || userRows.length == 0) {
			$.messager.alert('提示', '请选择一条数据！');
			return;
		}
		if(userRows.length==1){
			msg='确认将该用户移除？'
		}else{
			msg='确认将选中的多个用户移除？'
		}
		$.messager.confirm('请确认',msg,
				function(r) {
					if (r) {
						var userIds=[];
						for ( var i = 0; i < userRows.length; i++) {
							userIds.push(userRows[i].userId);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/role/removeUsers',
							data : {"userIds":userIds.join(','),"roleId":roleRow.roleId},
							type:'post',
							dataType : 'json',
							success : function(d) {
								$('#roleUser_datagrid').datagrid('load');
								$('#roleUser_datagrid').datagrid('unselectAll');
								$.messager.show({
									title : '提示',
									msg : d.msg
								});
							}
						});
					}
			});
	}
	
	//保存角色权限
	function saveRoleAtuth(){
		var roleRow = roleDatagrid.datagrid('getSelected');
		var nodes = $('#role_menuTree').tree('getChecked', ['checked','indeterminate']);
		if(!roleRow){
			$.messager.show({title : '提示',msg :'请选择目标角色'});
			return ;
		}
		$.messager.confirm('请确认','确认保存？',
				function(r) {
					if (r) {
						var menuCds=[];
						for ( var i = 0,len=nodes.length; i < len; i++) {
							menuCds.push(nodes[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/role/putAtuth',
							data : {"menuCds":menuCds.join(','),"roleId":roleRow.roleId},
							type:'post',
							dataType : 'json',
							success : function(d) {
								$('#role_menuTree').tree("reload");
								$('#role_datagrid').datagrid('unselectAll');
								$.messager.show({
									title : '提示',
									msg : d.msg
								});
							}
						});
					}
			});
	}
	
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<table id="role_datagrid"></table>
	</div>
	<div data-options="region:'east',border:false" style="width:530px;height: 100%">
		<div class="easyui-tabs" style="height: 543px">
			<div title="角色权限">
				<div class="datagrid-toolbar">
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-top: 3px" onclick="saveRoleAtuth();">保存权限</a>
				</div>
				<ul class="easyui-tree" id="role_menuTree"
					data-options="
						url:'${pageContext.request.contextPath}/menu/getAllMenuTree',
						parentField:'parentId',
						lines:true,
						checkbox:true,
						border:false",
				</ul>
			</div>
			<div title="角色用户" style="height: 100%">
				<table id="roleUser_datagrid" style="height: 100%"></table>
			</div>
		</div>
	</div>
</div>
<div id="role_updateDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加角色',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			roleSave();
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
				<td><select name="roleType" style="width:150px;" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=roleType" valueField="keyValue" textField="caption">
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
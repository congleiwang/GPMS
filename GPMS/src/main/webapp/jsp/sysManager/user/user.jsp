<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var userDatagrid = $('#user_datagrid');
	$(function() {
		userDatagrid.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					sortName : 'userNm',
					sortOrder : 'desc',
					pageSize : 10,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/user/list',
					idField : 'userId',
					pagePosition : 'bottom',
					pagination : true,
					loadMsg : '正在加载数据当中....',
					frozenColumns : [ [ {
						field : 'loginNm',
						title : '登陆名',
						width : 100
					}] ],
					columns : [ [ {
						field : 'userNm',
						title : '用户名',
						width : 100,
						sortable : true
					} , {
						field : 'orgNm',
						title : '所属机构',
						width : 150
					} , {
						field : 'orgId',
						hidden:true
					} , {
						field : 'phoneNum',
						title : '电话',
						width : 100
					}, {
						field : 'address',
						title : '地址',
						width : 150
					}, {
						field : 'email',
						title : '邮箱',
						width : 100
					},{
						title : '是否锁定',
						field : 'isLock',
						width : 60,
						formatter : function(value, rowData, rowIndex) {
							if(value==1 || value=='1'){
								return '是';
							}
							return '否';
						}
					}, {
						title : '注册时间',
						field : 'signAt',
						sortable : true,
						width : 150,
						formatter : function(value, rowData, rowIndex) {
							return timeToString(value);
						}
					},{
						field : 'remark',
						hidden:true
					}] ],
					onDblClickRow:function(){
						var row=userDatagrid.datagrid("getSelected");
						if(row){
							$.ajax({
								url : '${pageContext.request.contextPath}/user/selectById',
								data :"id="+row.userId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									var userDetail=$('<div/>').dialog({
										title : '查看用户',
										href : '${pageContext.request.contextPath}/jsp/sysManager/user/userDetail.jsp',
										width : 600,
										height : 400,
										modal : true,
										resizable :true,
										buttons : [ {
											text : '关闭',
											handler : function() {
												userDatagrid.datagrid('unselectAll');
												userDetail.dialog("destroy");
											}
										} ],
										onClose : function() {
											userDatagrid.datagrid('unselectAll');
											$(this).dialog('destroy');
										},
										onLoad : function() {
							                $('#user_detailForm').form('load',{
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
								}
							});
						}
					},
					toolbar : [ {
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							userAdd();
						}
					}, '-', {
						text : '修改',
						iconCls : 'icon-edit',
						handler : function() {
							userUpdate();
						}
					}, '-', {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							userDel();
						}
					}, '-', {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							userDatagrid.datagrid('rejectChanges');
							userDatagrid.datagrid('unselectAll');
						}
					}]
				});
	});
	function userSearchs() {
		var searchForm = $('#user_searchForm').form();
		userDatagrid.datagrid('load', serializeObject(searchForm));
	}
	function userCleanSearch() {
		userDatagrid.datagrid('load', {});
		$('#user_searchForm').form('clear');
	}
	function userDel() {
		var ids = [];
		var rows = userDatagrid.datagrid('getSelections');
		var msg;
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请勾选要删除的数据！');
			return;
		}
		if (rows.length == 1) {
			msg="确认删除该数据？";
		} else {
			msg="确认删除多条数据？";
		}
		$.messager.confirm('请确认',msg,
				function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].userId);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/user/deleteBatchIds',
							data :JSON.stringify(ids),
							type:'post',
							contentType:'application/json;UTF-8',
							dataType : 'json',
							success : function(d) {
								userDatagrid.datagrid('load');
								userDatagrid.datagrid('unselectAll');
								$.messager.show({
									title : '提示',
									msg : d.msg
								});
							}
						});
					}
				});
	}
	function userAdd() {
		$('#user_addDialog').dialog('open');
		$('#user_addForm').form('clear');
	}
	function userUpdate() {
		var rows = userDatagrid.datagrid('getSelections');
		if (rows.length == 1) {
			var userUpdate=$('<div/>').dialog({
				title : '修改用户',
				href : '${pageContext.request.contextPath}/jsp/sysManager/user/userEdit.jsp',
				width : 600,
				height : 300,
				modal : true,
				resizable :true,
				buttons : [ {
					text : '保存',
					handler : function() {
						$('#user_editForm').form('submit',{
							onSubmit:function(){
								return $(this).form('validate');
							},
							url:'${pageContext.request.contextPath}/user/update',
				            success:function(r){
				            	obj=$.parseJSON(r);
				                 if(obj.success){
				                	userUpdate.dialog('destroy');
				                 	userDatagrid.datagrid('unselectAll');
				                 	userDatagrid.datagrid('load');
				                 }
				                 $.messager.alert('提示', obj.msg);
						    }
						});
					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
	                $('#user_editForm').form('load',{
	                	userId:rows[0].userId,
	    				orgId:rows[0].orgId,
	    				loginNm:rows[0].loginNm,
	    				userNm:rows[0].userNm,
	    				passwd:'',
	    				phoneNum:rows[0].phoneNum,
	    				address:rows[0].address,
	    				email:rows[0].email,
	    				isLock:rows[0].isLock,
	    				remark:rows[0].remark,
	                });
				}
            });
		} else {
			$.messager.alert('提示', '请勾选一条要编辑的数据');
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 85px;overflow: hidden;" align="left">
		<div id="user_toolbar">
			<form id="user_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>所属机构</th>
						 <td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<th>用户名</th>
						<td><input name="userNm"  /></td>
						<th>登陆名</th>
						<td><input name="loginNm"  /></td>
					</tr>
					<tr>
						<th>注册时间</th>
						<td><input name="signAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="signAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>地址</th>
						<td><input name="address" /></td>
						<th>是否锁定</th>
						<td>
							<select name="isLock">
								<option value="" disabled="disabled" selected="selected">--请选择--</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="userSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="userCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="user_datagrid"></table>
	</div>
</div>
<div id="user_addDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加用户',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			$('#user_addForm').form('submit',{
				onSubmit:function(){
					return $(this).form('validate');
				},
			    url : '${pageContext.request.contextPath}/user/add',
			     success:function(r){
			        obj=$.parseJSON(r);
			        if(obj.success){
			           userDatagrid.datagrid('load');
				       $('#user_addDialog').dialog('close');
			        }
			        $.messager.show({title:'提示',msg:obj.msg});
			    }
		    });
	   }
	}]">
	<form id="user_addForm" method="post">
		<table>
			<tr>
				<th>所属机构</th>
				 <td>
					<select class="easyui-combotree easyui-validatebox" style="width:200px;" data-options="required:true" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
				</td>
				<th>登录名</th>
				<td><input name="loginNm" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>用户名</th>
				<td><input name="userNm" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				<th>密码</th>
				<td><input name="passwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>确认密码</th>
				<td><input type="password" class="easyui-validatebox" data-options="required:true" /></td>
				<th>电话</th>
				<td><input name="phoneNum" type="text"/></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input name="address" type="text" /></td>
				<th>邮箱</th>
				<td><input name="email" type="email"/></td>
			</tr>
			<tr>
				<th>是否锁定</th>
				<td>
					<select name="isLock">
						<option value="1" selected="selected">是</option>
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
</div>

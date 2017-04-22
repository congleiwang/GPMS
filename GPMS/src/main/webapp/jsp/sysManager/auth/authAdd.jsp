<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var user_dg = $('#user_datagrid');
	$(function() {
		user_dg.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
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
						var row=user_dg.datagrid("getSelected");
						if(row){
							$.ajax({
								url : '${pageContext.request.contextPath}/user/selectById',
								data :"id="+row.userId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									var dd=$('<div/>').dialog({
										title : '查看用户',
										href : '${pageContext.request.contextPath}/jsp/sysManager/user/userDetail.jsp',
										width : 600,
										height : 400,
										modal : true,
										resizable :true,
										buttons : [ {
											text : '关闭',
											handler : function() {
												user_dg.datagrid('unselectAll');
												dd.dialog("destroy");
											}
										} ],
										onClose : function() {
											user_dg.datagrid('unselectAll');
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
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							user_dg.datagrid('rejectChanges');
							dg.datagrid('unselectAll');
						}
					}]
				});
	});
	function searchs() {
		var searchForm = $('#user_searchForm').form();
		user_dg.datagrid('load', serializeObject(searchForm));
	}
	function cleanSearch() {
		user_dg.datagrid('load', {});
		$('#user_searchForm input').val('');
	}
	function timeToString(v){
		if(v){
			return new Date(parseInt(v)).toLocaleString()
		}
		return '';
	}
</script>
<div class="easyui-layout" data-options="fit:true" style="height: 100%">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="user_toolbar">
			<form id="user_searchForm">
				<input name="isLock" value="0" type="hidden"/>
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>所属机构</th>
						 <td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<th>用户名</th>
						<td><input name="userNm" type="text" /></td>
						<th>登陆名</th>
						<td><input name="loginNm" type="text" /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">重置</a>
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
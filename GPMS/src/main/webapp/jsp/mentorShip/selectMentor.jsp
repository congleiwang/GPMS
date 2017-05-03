<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var amDatagrid = $('#applyMentor_datagrid');
	$(function() {
		amDatagrid.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					rownumbers:true,
					singleSelect:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/applyMentor/selectMentor',
					idField : 'userId',
					pagePosition : 'bottom',
					pagination : true,
					loadMsg : '正在加载数据当中....',
					frozenColumns : [ [ {
						field : 'userNm',
						title : '用户名',
						width : 100
					}] ],
					columns : [ [ {
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
						field : 'remark',
						hidden:true
					}] ],
					onDblClickRow:function(){
						var row=amDatagrid.datagrid("getSelected");
						if(row){
							$.ajax({
								url : '${pageContext.request.contextPath}/user/selectById',
								data :"id="+row.userId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									var userDetail=$('<div/>').dialog({
										title : '查看导师信息',
										href : '${pageContext.request.contextPath}/jsp/sysManager/user/userDetail.jsp',
										width : 600,
										height : 400,
										modal : true,
										resizable :true,
										buttons : [ {
											text : '关闭',
											handler : function() {
												amDatagrid.datagrid('unselectAll');
												userDetail.dialog("destroy");
											}
										} ],
										onClose : function() {
											amDatagrid.datagrid('unselectAll');
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
						text : '发出申请',
						iconCls : 'icon-add',
						handler : function() {
							amSendApply();
						}
					}, '-', {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							amDatagrid.datagrid('rejectChanges');
							amDatagrid.datagrid('unselectAll');
						}
					}]
				});
	});
	function applyMentorSearchs() {
		var searchForm = $('#applyMentor_searchForm').form();
		amDatagrid.datagrid('load', serializeObject(searchForm));
	}
	function applyMentorCleanSearch() {
		amDatagrid.datagrid('load', {});
		$('#applyMentor_searchForm').form('clear');
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="applyMentor_toolbar">
			<form id="applyMentor_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>所属机构</th>
						 <td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getAllOrgTree" name="orgId"></select>
						</td>
						<th>教师姓名</th>
						<td><input name="userNm"  /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="applyMentorSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="applyMentorCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="applyMentor_datagrid"></table>
	</div>
</div>
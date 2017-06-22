<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var myStuDg = $('#myStu_datagrid');
	$(function() {
		myStuDg.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					singleSelect:true,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/applyMentor/getMyStuList',
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
						var row=myStuDg.datagrid("getSelected");
						if(row){
							$.ajax({
								url : '${pageContext.request.contextPath}/user/selectById',
								data :"id="+row.userId,
								type:'post',
								dataType : 'json',
								success : function(d) {
									var userDetail=$('<div/>').dialog({
										title : '查看学生',
										href : '${pageContext.request.contextPath}/jsp/sysManager/user/userDetail.jsp',
										width : 600,
										height : 400,
										modal : true,
										resizable :true,
										buttons : [ {
											text : '关闭',
											handler : function() {
												myStuDg.datagrid('unselectAll');
												userDetail.dialog("destroy");
											}
										} ],
										onClose : function() {
											myStuDg.datagrid('unselectAll');
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
					}
				});
	});
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="myStu_datagrid"></table>
	</div>
</div>
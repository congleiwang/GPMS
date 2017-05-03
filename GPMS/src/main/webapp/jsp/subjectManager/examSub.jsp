<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var examSubDg = $('#examSub_datagrid');
$(function() {
	examSubDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subject/examList',
				idField : 'subId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'createNm',
					title : '提交人',
					width : 250
				} ] ],
				columns : [ [  {
					field : 'title',
					title : '标题',
					width : 250
				} ,{
					field : 'createAt',
					title : '提交时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'fileUrl',
					title : '附件',
					width : 220,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ] ],
				toolbar : [{
					text : '同意',
					iconCls : 'icon-edit',
					handler : function() {
						examSubAllow();
					}
				},{
					text : '拒绝',
					iconCls : 'icon-remove',
					handler : function() {
						examSubReject();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						examSubDg.datagrid('unselectAll');
					}
				}],
				onDblClickRow:function(index,row){
					var datailSub=$('<div/>').dialog({
						title : '查看课题',
						href : '${pageContext.request.contextPath}/jsp/subjectManager/datailSub.jsp',
						width : 600,
						height : 400,
						modal : true,
						resizable :true,
						buttons : [ {
							text : '关闭',
							handler : function() {
								examSubDg.datagrid('unselectAll');
								datailSub.dialog("destroy");
							}
						} ],
						onClose : function() {
							examSubDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#detailSubFileUrl').html(row.fileUrl);
							$('#detailSubFileUrl').attr('href',"file/download?fileName="+row.fileUrl);
			                $('#detailSub_Form').form('load',{
			                	title:row.title,
								createAt:timeToString(row.createAt),
								createNm:row.createNm,
								doerNm:row.doerNm,
								doAt:timeToString(row.doAt),
								finishAT:timeToString(row.finishAT),
								moderNm:row.moderNm,
								modAt:timeToString(row.modAt),
								require:row.require,
								intro:row.intro,
								applySbjt:row.applySbjt,
								applyCont:row.applyCont
			                });
						}
		            });
				}
			});
});
function examSubSearchs() {
	var searchForm = $('#examSub_searchForm').form();
	examSubDg.datagrid('load', serializeObject(searchForm));
}
function examSubCleanSearch() {
	examSubDg.datagrid('load', {});
	$('#examSub_searchForm').form('clear');
}
function examSubAllow() {
	var row = examSubDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要审批的数据！');
		return;
	}
	$.messager.confirm('请确认','审批后不可修改，确认？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subject/examSubAllow',
					data :"subId="+row.subId+"&creator="+row.creator+"&title="+row.title,
					type:'post',
					dataType : 'json',
					success : function(d) {
						examSubDg.datagrid('load');
						examSubDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function examSubReject(){
	var row = examSubDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要审批的数据！');
		return;
	}
	$.messager.confirm('请确认','审批后不可修改，确认？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subject/examSubReject',
					data :"subId="+row.subId+"&creator="+row.creator+"&title="+row.title,
					type:'post',
					dataType : 'json',
					success : function(d) {
						examSubDg.datagrid('load');
						examSubDg.datagrid('unselectAll');
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
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="examSub_toolbar">
			<form id="examSub_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>提交人</th>
						<td><input name="createNm"/></td>
						<th>标题</th>
						<td><input name="title"/></td>
						<th>提交时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examSubSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examSubCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="examSub_datagrid" ></table>
	</div>
</div>

<div id="examSub_Dialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			examSubSave();
	   }
	}]">
	<form id="examSub_updateForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="subId"/>
		<table>
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="title" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>要求：</th>
				<td>
					<textarea name="require" rows="4" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<th>简介</th>
				<td>
					<textarea name="intro" rows="5" cols="60"></textarea>
				</td>
			</tr>
			<tr>
				<th>附件</th>
				<td>
					<input type="file" name="file" class="easyui-filebox" />
				</td>
			</tr>
		</table>
	</form>
</div>
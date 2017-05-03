<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var examSubRepDg = $('#examSubRep_datagrid');
$(function() {
	examSubRepDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subRep/examList',
				idField : 'srId',
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
					field : 'sendAt',
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
					text : '审批',
					iconCls : 'icon-remove',
					handler : function() {
						examSubRep();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						examSubRepDg.datagrid('unselectAll');
					}
				}],
				onDblClickRow:function(index,row){
					var datailSubRep=$('<div/>').dialog({
						title : '查看课题报告',
						href : '${pageContext.request.contextPath}/jsp/subjectReport/datailSubRep.jsp',
						width : 600,
						height : 400,
						modal : true,
						resizable :true,
						buttons : [ {
							text : '关闭',
							handler : function() {
								subRepDg.datagrid('unselectAll');
								datailSubRep.dialog("destroy");
							}
						} ],
						onClose : function() {
							subRepDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#detailSubRepFileUrl').html(row.fileUrl);
							$('#detailSubRepFileUrl').attr('href',"file/download?fileName="+row.fileUrl);
			                $('#detailSubRep_Form').form('load',{
			                	title:row.title,
			                	state:row.state,
								createAt:timeToString(row.createAt),
								sendAt:timeToString(row.sendAt),
								remark:row.remark
			                });
						}
		            });
				}
			});
});
function examSubRepSearchs() {
	var searchForm = $('#examSubRep_searchForm').form();
	examSubRepDg.datagrid('load', serializeObject(searchForm));
}
function examSubRepCleanSearch() {
	examSubRepDg.datagrid('load', {});
	$('#examSubRep_searchForm').form('clear');
}
function examSubRep(){
	var row = examSubRepDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要审批的数据！');
		return;
	}
	$('#examSubRep_examDialog').dialog('open');
	$('#examSubRep_examForm').form('clear');
}
function examSubRepAllow() {
	$.messager.confirm('请确认','通过后将进入提交论文阶段，确认？',
		function(r) {
			if (r) {
				var row = examSubRepDg.datagrid('getSelected');
				$('#examSubRep_examForm input[name=examTarget]').val(row.srId);
				$('#examSubRep_examForm').form('submit',{
					onSubmit:function(){
						return $(this).form('validate');
					},
					url:'${pageContext.request.contextPath}/subRep/examSubRepAllow',
			        success:function(r){
			        	var obj=$.parseJSON(r);
			            if(obj.success){
			            	$('#examSubRep_examForm').form('clear');
			            	$("#examSubRep_examDialog").dialog("close");
			            	examSubRepDg.datagrid("reload");
			            }
			            $.messager.alert('提示', obj.msg);
				    }
				});
			}
		});
}
function examSubRepReject(){
	$.messager.confirm('请确认','审批后不可修改，确认？',
		function(r) {
			if (r) {
				var row = examSubRepDg.datagrid('getSelected');
				$('#examSubRep_examForm input[name=examTarget]').val(row.srId);
				$('#examSubRep_examForm').form('submit',{
					onSubmit:function(){
						return $(this).form('validate');
					},
					url:'${pageContext.request.contextPath}/subRep/examSubRepReject',
			        success:function(r){
			        	var obj=$.parseJSON(r);
			            if(obj.success){
			            	$('#examSubRep_examForm').form('clear');
			            	$("#examSubRep_examDialog").dialog("close");
			            	examSubRepDg.datagrid("reload");
			            }
			            $.messager.alert('提示', obj.msg);
				    }
				});
			}
		});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="examSubRep_toolbar">
			<form id="examSubRep_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>提交人</th>
						<td><input name="createNm"/></td>
						<th>标题</th>
						<td><input name="title"/></td>
						<th>创建时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examSubRepSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examSubRepCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="examSubRep_datagrid" ></table>
	</div>
</div>

<div id="examSubRep_examDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '同意',
		iconCls : 'icon-ok',
		handler :  function() {
			examSubRepAllow();
	   }
	},{
		text : '拒绝',
		iconCls : 'icon-no',
		handler :  function() {
			examSubRepReject();
	   }
	}]">
	<form id="examSubRep_examForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="examTarget"/>
		<table>
			<tr>
				<th>审批意见</th>
				<td>
					<textarea name="remark" rows="7" cols="60"></textarea>
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
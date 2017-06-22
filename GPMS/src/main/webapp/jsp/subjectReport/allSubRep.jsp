<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var allSubRepDg = $('#allSubRep_datagrid');
$(function() {
	allSubRepDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subRep/list',
				idField : 'srId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'createNm',
					title : '学生',
					width : 100
				} ] ],
				columns : [ [  {
					field : 'title',
					title : '标题',
					width : 250
				} ,{
					field : 'createAt',
					title : '创建时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'sendAt',
					title : '提交时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'examorNm',
					title : '教师',
					width : 100
				} ,{
					field : 'examAt',
					title : '审批时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'srFileUrl',
					title : '开题报告附件',
					width : 220,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ] ],
				toolbar : [{
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						allSubRepDg.datagrid('unselectAll');
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
								allSubRepDg.datagrid('unselectAll');
								datailSubRep.dialog("destroy");
							}
						} ],
						onClose : function() {
							allSubRepDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#detailSubRepSrFileUrl').html(row.srFileUrl);
							$('#detailSubRepSrFileUrl').attr('href',"file/download?fileName="+row.srFileUrl);
							if(row.state=="3" ||row.state=="4" ){
								$('#detailSubRepExamRemark').html(row.examFileUrl);
								$('#detailSubRepExamRemark').attr('href',"file/download?fileName="+row.examFileUrl);
							}
			                $('#detailSubRep_Form').form('load',{
			                	title:row.title,
								srRemark:row.srRemark,
			                	state:row.state,
								createAt:timeToString(row.createAt),
								createNm:row.createNm,
								sendAt:timeToString(row.sendAt),
								examorNm:row.examorNm,
								examAt:timeToString(row.examAt),
								examRemark:row.examRemark,
			                });
						}
		            });
				}
			});
});
function allSubRepSearchs() {
	var searchForm = $('#allSubRep_searchForm').form();
	allSubRepDg.datagrid('load', serializeObject(searchForm));
}
function allSubRepCleanSearch() {
	allSubRepDg.datagrid('load', {});
	$('#allSubRep_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 85px;overflow: hidden;" align="left">
		<div id="allSubRep_toolbar">
			<form id="allSubRep_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>学生</th>
						<td><input name="createNm"/></td>
						<th>教师</th>
						<td><input name="examorNm"/></td>
						<th>标题</th>
						<td><input name="title"/></td>
					</tr>
					<tr>
						<th>提交时间</th>
						<td><input name="sendAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="sendAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>审批时间</th>
						<td colspan="2"><input name="examAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="examAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allSubRepSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allSubRepCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="allSubRep_datagrid" ></table>
	</div>
</div>
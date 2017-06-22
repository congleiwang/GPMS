<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var allPaperDg = $('#allPaper_datagrid');
$(function() {
	allPaperDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/paper/list',
				idField : 'paperId',
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
					field : 'pfileUrl',
					title : '论文附件',
					width : 250,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ] ],
				toolbar : [{
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						allPaperDg.datagrid('unselectAll');
					}
				}],
				onDblClickRow:function(index,row){
					var datailPaper=$('<div/>').dialog({
						title : '查看论文',
						href : '${pageContext.request.contextPath}/jsp/paper/datailPaper.jsp',
						width : 600,
						height : 400,
						modal : true,
						resizable :true,
						buttons : [ {
							text : '关闭',
							handler : function() {
								paperDg.datagrid('unselectAll');
								datailPaper.dialog("destroy");
							}
						} ],
						onClose : function() {
							paperDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#datailPaperPfileUrl').html(row.pfileUrl);
							$('#datailPaperPfileUrl').attr('href',"file/download?fileName="+row.pfileUrl);
							if(row.state=="3" ||row.state=="4" ){
								$('#datailPaperExamFileUrl').html(row.examFileUrl);
								$('#datailPaperExamFileUrl').attr('href',"file/download?fileName="+row.examFileUrl);
							}
			                $('#detailPaper_Form').form('load',{
			                	title:row.title,
			                	abs:row.abs,
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
function allPaperSearchs() {
	var searchForm = $('#allPaper_searchForm').form();
	allPaperDg.datagrid('load', serializeObject(searchForm));
}
function allPaperCleanSearch() {
	allPaperDg.datagrid('load', {});
	$('#allPaper_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 85px;overflow: hidden;" align="left">
		<div id="allPaper_toolbar">
			<form id="allPaper_searchForm">
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
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allPaperSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allPaperCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="allPaper_datagrid" ></table>
	</div>
</div>
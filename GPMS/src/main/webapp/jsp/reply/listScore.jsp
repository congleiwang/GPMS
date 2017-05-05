<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var listScoreDg = $('#listScore_datagrid');
$(function() {
	listScoreDg.datagrid({
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
			field : 'orgNm',
			title : '专业',
			width : 250
		},{
			field : 'createNm',
			title : '学生',
			width : 150
		} ] ],
		columns : [ [  {
			field : 'title',
			title : '标题',
			width : 300
		} ,{
			field : 'examorNm',
			title : '指导老师',
			width : 150
		} ,{
			field : 'score',
			title : '成绩',
			width : 200
		}] ]
	});
});
function listScoreSearchs() {
	var searchForm = $('#listScore_searchForm').form();
	listScoreDg.datagrid('load', serializeObject(searchForm));
}
function listScoreCleanSearch() {
	listScoreDg.datagrid('load', {});
	$('#listScore_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="listScore_toolbar">
			<form id="listScore_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>机构</th>
						<td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="listScoreSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="listScoreCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="listScore_datagrid" ></table>
	</div>
</div>
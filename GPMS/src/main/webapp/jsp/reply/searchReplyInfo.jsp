<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var searchReplyInfoDg = $('#searchReplyInfo_datagrid');
$(function() {
	searchReplyInfoDg.treegrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/reply/list',
				idField : 'orgId',
				treeField:'orgNm',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'orgNm',
					title : '机构',
					width : 250
				} ] ],
				columns : [ [  {
					field : 'addr',
					title : '答辩地址',
					width : 250
				} ,{
					field : 'timeStart',
					title : '开始时间',
					width : 150
				} ,{
					field : 'timeEnd',
					title : '结束时间',
					width : 150
				} ,{
					field : 'contacts',
					title : '联系人',
					width : 100
				} ,{
					field : 'phone',
					title : '电话',
					width : 150
				} ] ],
			});
});
function searchReplyInfoSearchs() {
	var searchForm = $('#searchReplyInfo_searchForm').form();
	$.ajax({
		url : '${pageContext.request.contextPath}/reply/list',
		data :serializeObject(searchForm),
		type:'post',
		dataType : 'json',
		success : function(d) {
			searchReplyInfoDg.treegrid('loadData',d);
		}
	});
}
function searchReplyInfoCleanSearch() {
	$.ajax({
		url : '${pageContext.request.contextPath}/reply/list',
		type:'post',
		dataType : 'json',
		success : function(d) {
			searchReplyInfoDg.treegrid('loadData',d);
		}
	});
	$('#searchReplyInfo_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="searchReplyInfo_toolbar">
			<form id="searchReplyInfo_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>机构</th>
						<td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchReplyInfoSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchReplyInfoCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="searchReplyInfo_datagrid" ></table>
	</div>
</div>
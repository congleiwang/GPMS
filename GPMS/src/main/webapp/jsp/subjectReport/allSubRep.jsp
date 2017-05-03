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
						<th>提交时间</th>
						<td><input name="sendAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="sendAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>学生</th>
						<td><input name="createNm"/></td>
						<th>状态</th>
						<td><select name="state" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
					</tr>
					<tr>
						<th>审批时间</th>
						<td><input name="examAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="examAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>教师</th>
						<td><input name="examorNm"/></td>
						<th>标题</th>
						<td  colspan="3"><input name="title"/></td>
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
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
	var allMentorShipDg = $('#allMentorShip_datagrid');
	$(function() {
		allMentorShipDg.datagrid({
					fitColum : true,
					fit : true,
					nowarp : true,
					pageSize : 10,
					singleSelect:true,
					rownumbers:true,
					pageList : [ 10, 20, 30, 40 ],
					url : '${pageContext.request.contextPath}/applyMentor/list',
					idField : 'sender',
					pagePosition : 'bottom',
					pagination : true,
					loadMsg : '正在加载数据当中....',
					frozenColumns : [ [ {
						field : 'receiverNm',
						title : '教师',
						width : 100
					}] ],
					columns : [ [ {
						field : 'senderNm',
						title : '学生',
						width : 150
					} ,{
						field : 'createAt',
						title : '申请时间',
						width : 150,
						formatter : function(value, rowData, rowIndex) {
							return timeToString(value);
						}
					} ,{
						field : 'state',
						title : '状态',
						width : 150
					},{
						field : 'updateAt',
						title : '处理时间',
						width : 150,
						formatter : function(value, rowData, rowIndex) {
							return timeToString(value);
						}
					}] ]
				});
	});
	function allMentorShipSearchs() {
		var searchForm = $('#allMentorShip_searchForm').form();
		allMentorShipDg.datagrid('load', serializeObject(searchForm));
	}
	function allMentorShipCleanSearch() {
		allMentorShipDg.datagrid('load', {});
		$('#allMentorShip_searchForm').form('clear');
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 85px;overflow: hidden;" align="left">
		<div id="allMentorShip_toolbar">
			<form id="allMentorShip_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>申请时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>申请状态</th>
						<td><select name="state" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
					</tr>
					<tr>
						<th>教师姓名</th>
						<td><input name="receiverNm" /></td>
						<th>学生姓名</th>
						<td><input name="senderNm" /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allMentorShipSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allMentorShipCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="allMentorShip_datagrid"></table>
	</div>
</div>

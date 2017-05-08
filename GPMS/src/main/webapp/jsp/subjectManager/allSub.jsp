<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var allSubDg = $('#allSub_datagrid');
$(function() {
	allSubDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subject/list',
				idField : 'subId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'title',
					title : '标题',
					width : 250
				} ] ],
				columns : [ [  {
					field : 'createNm',
					title : '提交人',
					width : 100
				} ,{
					field : 'createAt',
					title : '提交时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'doerNm',
					title : '认领人',
					width : 100
				} ,{
					field : 'doAt',
					title : '认领时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				},{
					field : 'fileUrl',
					title : '附件',
					width : 220,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ] ],
				toolbar : [{
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						allSubDg.datagrid('unselectAll');
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
								allSubDg.datagrid('unselectAll');
								datailSub.dialog("destroy");
							}
						} ],
						onClose : function() {
							allSubDg.datagrid('unselectAll');
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
function allSubSearchs() {
	var searchForm = $('#allSub_searchForm').form();
	allSubDg.datagrid('load', serializeObject(searchForm));
}
function allSubCleanSearch() {
	allSubDg.datagrid('load', {});
	$('#allSub_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 85px;overflow: hidden;" align="left">
		<div id="allSub_toolbar">
			<form id="allSub_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>提交人</th>
						<td><input name="createNm"/></td>
						<th>认领人</th>
						<td colspan="2"><input name="doerNm"/></td>
						<th>状态</th>
						<td><select name="state" style="width:150px;" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
					</tr>
					<tr>
						<th>标题</th>
						<td><input name="title"/></td>
						<th>提交时间</th>
						<td colspan="2"><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td colspan="2">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allSubSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="allSubCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="allSub_datagrid" ></table>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var relScoreDg = $('#relScore_datagrid');
var relScoreEditId = undefined;
$(function() {
	if(relScoreEditId != undefined){
		relScoreEditId = undefined;
	}
	relScoreDg.datagrid({
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
					width : 200,
					editor:'numberbox',
					formatter : function(value, rowData, rowIndex) {
						return value==0?'':value;
					}
				}] ],
				toolbar : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						relScoreSave();
					}
				},  '-', {
					text : '撤回',
					iconCls : 'icon-undo',
					handler : function() {
						relScoreCancel();
					}
				}],
				onClickRow:function (index){
					if (relScoreEditId == undefined){
						relScoreDg.datagrid('beginEdit', index);  
						var ed = relScoreDg.datagrid('getEditor', {index:index,field:'score'});
						$(ed.target).focus(); 
						relScoreEditId = index;
					}else{
						if (relScoreEditId != index){//重新点了一行
							if (relScoreDg.datagrid('validateRow', relScoreEditId)){
								relScoreDg.datagrid('beginEdit', index);
								relScoreDg.datagrid('endEdit', relScoreEditId);
								var ed = relScoreDg.datagrid('getEditor', {index:index,field:'score'});
								$(ed.target).focus();
								relScoreEditId = index;
							} else {
								relScoreDg.datagrid('selectRow', relScoreEditId);
							}
						}
					}
				}
			});
});
//取消修改
function relScoreCancel(){
	relScoreDg.datagrid('rejectChanges');
	relScoreDg.datagrid('unselectAll');
	relScoreEditId = undefined;
}
//保存
function relScoreSave(){
	if (relScoreEditId != undefined){
		$.messager.confirm('请确认','保存后无法撤销！',
			function(r) {
				if (r) {
					relScoreDg.datagrid('endEdit', relScoreEditId);
					var rows = relScoreDg.datagrid('getChanges');
					$.ajax({
						url : '${pageContext.request.contextPath}/paper/saveScore',
						data :JSON.stringify(rows),
						type:'post',
						contentType:'application/json;UTF-8',
						dataType : 'json',
						success : function(d) {
							if(d.success){
								relScoreDg.datagrid('acceptChanges');
							}
							relScoreDg.datagrid('load');
							relScoreDg.datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : d.msg
							});
						}
					});
				}
		});
	}else{
		$.messager.alert('提示', '请先编辑数据！');
	}
}
function relScoreSearchs() {
	var searchForm = $('#relScore_searchForm').form();
	relScoreDg.datagrid('load', serializeObject(searchForm));
}
function relScoreCleanSearch() {
	relScoreDg.datagrid('load', {});
	$('#relScore_searchForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="relScore_toolbar">
			<form id="relScore_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>学生所属机构</th>
						<td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<th>学生</th>
						<td><input name="createNm"/></td>
						<th>教师</th>
						<td><input name="examorNm"/></td>
						<th>标题</th>
						<td><input name="title"/></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="relScoreSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="relScoreCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="relScore_datagrid" ></table>
	</div>
</div>
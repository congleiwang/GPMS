<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var relReplyInfoTg = $('#releaseReplyInfo_treegrid');
var relInfoEditingId;
$(function() {
	if (relInfoEditingId != undefined){
		relInfoEditingId=undefined;
	}
	relReplyInfoTg.treegrid({
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
					width : 250,
					editor:'text'
				} ,{
					field : 'timeStart',
					title : '开始时间',
					width : 150,
					editor:'datetimebox'
				} ,{
					field : 'timeEnd',
					title : '结束时间',
					width : 150,
					editor:'datetimebox'
				} ,{
					field : 'contacts',
					title : '联系人',
					width : 100,
					editor:'text'
				} ,{
					field : 'phone',
					title : '电话',
					width : 150,
					editor:'text'
				} ] ],
				toolbar : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						replyInfoEdit();
					}
				}, '-', {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						replyInfoSave();
					}
				},  '-', {
					text : '取消编辑',
					iconCls : 'icon-undo',
					handler : function() {
						replyInfoCancel();
					}
				}]
			});
});
function replyInfoEdit(){
	if (relInfoEditingId != undefined){
		relReplyInfoTg.treegrid('select', relInfoEditingId);
		return;
	}
	var row = relReplyInfoTg.treegrid('getSelected');
	if (row){
		relInfoEditingId = row.orgId
		relReplyInfoTg.treegrid('beginEdit', relInfoEditingId);
	}else{
		$.messager.show({title : '提示',msg : '请先选中要编辑的数据'});
	}
}
function replyInfoSave(){
	if (relInfoEditingId != undefined){
		relReplyInfoTg.treegrid('endEdit', relInfoEditingId);
		relReplyInfoTg.treegrid('select', relInfoEditingId);
        var row = relReplyInfoTg.treegrid('getSelected');
        row.children=null;
        $.ajax({
    		url : '${pageContext.request.contextPath}/reply/save',
    		data :JSON.stringify(row),
    		type:'post',
    		contentType:'application/json;UTF-8',
    		dataType : 'json',
    		success : function(d) {
    			if(d.success){
    				relReplyInfoSearchs();
    			}
    			$.messager.show({title : '提示',msg : d.msg});
    		}
    	});
	}else{
		$.messager.show({title : '提示',msg : '请选编辑数据！'});
	}
}
function replyInfoCancel(){
	if (relInfoEditingId != undefined){
		relReplyInfoTg.treegrid('cancelEdit', relInfoEditingId);
		relInfoEditingId = undefined;
	}
}
function relReplyInfoSearchs() {
	var searchForm = $('#relReplyInfo_searchForm').form();
	$.ajax({
		url : '${pageContext.request.contextPath}/reply/list',
		data :serializeObject(searchForm),
		type:'post',
		dataType : 'json',
		success : function(d) {
			replyInfoCancel();
			relReplyInfoTg.treegrid("unselectAll");
			relReplyInfoTg.treegrid('loadData',d);
		}
	});
}
function relReplyInfoCleanSearch() {
	$.ajax({
		url : '${pageContext.request.contextPath}/reply/list',
		type:'post',
		dataType : 'json',
		success : function(d) {
			replyInfoCancel();
			relReplyInfoTg.treegrid("unselectAll");
			relReplyInfoTg.treegrid('loadData',d);
			$('#relReplyInfo_searchForm').form('clear');
		}
	});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="relReplyInfo_toolbar">
			<form id="relReplyInfo_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>机构</th>
						<td>
							<select class="easyui-combotree" style="width:240px;" url="${pageContext.request.contextPath}/org/getPermOrgTree" name="orgId"></select>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="relReplyInfoSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="relReplyInfoCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="releaseReplyInfo_treegrid" ></table>
	</div>
</div>
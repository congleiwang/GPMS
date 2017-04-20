<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var dg = $('#log_datagrid');
$(function() {
	dg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/loginRecord/list',
				idField : 'recordId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'loginNm',
					title : '登陆名',
					width : 100
				}] ],
				columns : [ [ {
					field : 'createAt',
					title : '创建时间',
					width : 200,
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} , {
					field : 'loginState',
					title : '登陆状态',
					width : 80,
					formatter : function(value, rowData, rowIndex) {
						if(value=='1' || value==1){
							return '成功';
						}
						return '失败';
					}
				} , {
					field : 'ipAddr',
					title : 'IP地址',
					width : 200
				}] ],
				toolbar : [ {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						del();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						dg.datagrid('rejectChanges');
						dg.datagrid('unselectAll');
					}
				}]
			});
});
function searchs() {
	var searchForm = $('#log_searchForm').form();
	dg.datagrid('load', serializeObject(searchForm));
}
function cleanSearch() {
	dg.datagrid('load', {});
	$('#log_searchForm').form('clear');
}
function del() {
	var ids = [];
	var rows = dg.datagrid('getSelections');
	var msg;
	if (rows.length == 0) {
		$.messager.alert('提示', '请勾选要删除的数据！');
		return;
	}
	if (rows.length == 1) {
		msg="确认删除该数据？";
	} else {
		msg="确认删除多条数据？";
	}
	$.messager.confirm('请确认',msg,
		function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].recordId);
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/loginRecord/deleteBatchIds',
					data :JSON.stringify(ids),
					type:'post',
					contentType:'application/json;UTF-8',
					dataType : 'json',
					success : function(d) {
						dg.datagrid('load');
						dg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}

function timeToString(v){
	if(v){
		return new Date(parseInt(v)).toLocaleString()
	}
	return '';
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="log_toolbar">
			<form id="log_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>登陆名</th>
						<td><input name="loginNm"  /></td>
						<th>登陆结果</th>
						<td>
							<select name="loginState">
								<option value="" disabled="disabled" selected="selected">--请选择--</option>
								<option value="0">失败</option>
								<option value="1">成功</option>
							</select>
						</td>
						<th>登陆时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="log_datagrid" ></table>
	</div>
</div>

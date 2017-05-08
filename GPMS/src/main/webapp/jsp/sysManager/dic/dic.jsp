<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var dicDatagrid = $('#dic_datagrid');
$(function() {
	dicDatagrid.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/dictionary/list',
				idField : 'keyCd',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'keyCd',
					title : '键值码',
					width : 80
				} , {
					field : 'keyValue',
					title : '键值',
					width : 200
				} , {
					field : 'caption',
					title : '中文名称',
					width : 200
				}] ],
				columns : [ [ {
					field : 'sysNm',
					title : '系统名',
					width : 200
				} , {
					field : 'isMod',
					title : '是否可修改',
					width : 200,
					formatter : function(value, rowData, rowIndex) {
						if(value==1 || value=='1'){
							return '是';
						}
						return '否';
					}
				} , {
					field : 'remark',
					title : '备注',
					width : 200
				} , {
					field : 'isUse',
					title : '是否启用',
					width : 200
				} , {
					field : 'english',
					title : '英文名称',
					width : 200
				}] ],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						dicAdd();
					}
				},{
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						dicUpdate();
					}
				},{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						dicDel();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						dicDatagrid.datagrid('rejectChanges');
						dicDatagrid.datagrid('unselectAll');
					}
				}]
			});
});
function dicSearchs() {
	var searchForm = $('#dic_searchForm').form();
	dicDatagrid.datagrid('load', serializeObject(searchForm));
}
function dicCleanSearch() {
	dicDatagrid.datagrid('load', {});
	$('#dic_searchForm').form('clear');
}
function dicDel() {
	var row = dicDatagrid.datagrid('getSelected');
	var msg;
	if (!row) {
		$.messager.alert('提示', '请勾选要删除的数据！');
		return;
	}
	$.messager.confirm('请确认','确认删除选中的数据？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/dictionary/del',
					data :"id="+row.keyCd,
					type:'post',
					dataType : 'json',
					success : function(d) {
						dicDatagrid.datagrid('load');
						dicDatagrid.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function dicAdd(){
	$('#dic_updateDialog').dialog('open');
	$('#dic_updateForm').form('clear');
	$("#dic_isAdd").val(1);
	$('.sysNm').css('visibility','visible');
	$('#dic_updateForm [name=keyCd]').removeAttr('readonly');
	$("#dic_updateDialog").dialog('setTitle','添加字典');
}
function dicUpdate(){
	var row = dicDatagrid.datagrid('getSelected');
	var msg;
	if (!row) {
		$.messager.alert('提示', '请选择要修改的数据！');
		return;
	}
	if(row.isMod ==1 || row.isMod =='1'){//可修改
		$('#dic_updateDialog').dialog('open');
		$("#dic_updateDialog").dialog('setTitle','修改字典');
		$("#dic_isAdd").val(0);
		$('.sysNm').css('visibility','hidden');
		$('#dic_updateForm [name=keyCd]').attr('readonly','readonly');
		$('#dic_updateForm').form('load',{
			keyCd:row.keyCd,
			sysNm:row.sysNm,
			keyValue:row.keyValue,
			caption:row.caption,
			isMod:row.isMod,
			english:row.english,
			isUse:row.isUse,
			orderBy:row.orderBy,
			remark:row.remark
	     });
	}else{
		var dicDetail=$('<div/>').dialog({
			title : '修改字典<font color=red>（不可修改）</font>',
			href : '${pageContext.request.contextPath}/jsp/sysManager/dic/dicDetail.jsp',
			width : 500,
			height : 260,
			modal : true,
			resizable :true,
			buttons : [ {
				text : '关闭',
				handler : function() {
					dicDatagrid.datagrid('unselectAll');
					dicDetail.dialog("destroy");
				}
			} ],
			onClose : function() {
				dicDatagrid.datagrid('unselectAll');
				$(this).dialog('destroy');
			},
			onLoad : function() {
                $('#dic_detailForm').form('load',{
                	keyCd:row.keyCd,
        			sysNm:row.sysNm,
        			keyValue:row.keyValue,
        			caption:row.caption,
        			isMod:row.isMod==1?'是':'否',
        			english:row.english,
        			isUse:row.isUse==1?'是':'否',
        			orderBy:row.orderBy,
        			remark:row.remark
                });
			}
        });
	}
}
function dicSave(){
	var flag=$("#dic_isAdd").val();
	var url;
	if(flag==1||flag=='1'){
		url='${pageContext.request.contextPath}/dictionary/add';
	}
	if(!url || url==''){
		url='${pageContext.request.contextPath}/dictionary/update';
	}
	$('#dic_updateForm').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$("#dic_updateDialog").dialog("close");
            	$('#dic_datagrid').datagrid("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="dic_toolbar">
			<form id="dic_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>系统</th>
						<td><select name="sysNm" style="width:150px;" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=system" valueField="keyValue" textField="caption">
							</select>
						</td>
						<th>键值码</th>
						<td>
							<input name="keyCd" type="text"/>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="dicSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="dicCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="dic_datagrid" ></table>
	</div>
</div>

<div id="dic_updateDialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加字典',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			dicSave();
	   }
	}]">
	<form id="dic_updateForm" method="post">
		<input type="hidden" id="dic_isAdd"/>
		<table>
			<tr>
				<th>键值码</th>
				<td>
					<input type="text" name="keyCd" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th class='sysNm'>系统</th>
				<td class='sysNm'>
					<select name="sysNm" style="width:150px;" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=system" valueField="keyValue" textField="caption"></select>
				</td>
			</tr>
			<tr>
				<th>键值</th>
				<td>
					<input type="text" name="keyValue" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>中文名称</th>
				<td>
					<input type="text" name="caption" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>可否更改</th>
				<td><select name="isMod">
					<option value="1">是</option>
					<option value="0">否</option>
				</select></td>
				<th>英文名称</th>
				<td>
					<input type="text" name="english" />
				</td>
			</tr>
			<tr>
				<th>是否启用</th>
				<td>
					<select name="isUse">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
				<th>排序</th>
				<td>
					<input type="text" name="orderBy" />
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="4"><textarea name='remark' rows="3" cols="50"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var subRepDg = $('#subRep_datagrid');
var subRepFlag=true;
$(function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/subRep/getAllowSubRep',
		type:'post',
		dataType : 'json',
		success : function(d) {
			if(d && d.srId){
				$('#subRepSend').linkbutton("disable");
				subRepFlag=false;
			}
		}
	});
	subRepDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subRep/mySubRepList',
				idField : 'srId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'title',
					title : '标题',
					width : 250
				} ] ],
				columns : [ [  {
					field : 'state',
					title : '状态',
					width : 100
				} ,{
					field : 'createAt',
					title : '创建时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'fileUrl',
					title : '附件',
					width : 220,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ,{
					field : 'remark',
					title : '备注',
					width : 250
				}] ],
				toolbar : [{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						subRepAdd();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					id:'subRepUpdate',
					handler : function() {
						subRepUpdate();
					}
				}, '-', {
					text : '删除',
					id:'subRepDel',
					iconCls : 'icon-remove',
					handler : function() {
						subRepDel();
					}
				}, '-',{
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						subRepDg.datagrid('unselectAll');
					}
				},'-', {
					text : '提交审批',
					id:'subRepSend',
					iconCls : 'icon-tip',
					handler : function() {
						subRepSend();
					}
				}],
				onClickRow:function(index, row){
					if(row.state=='1'){
						if(subRepFlag){
							$('#subRepSend').linkbutton("enable");							
						}
						$('#subRepUpdate').linkbutton("enable");
						$('#subRepDel').linkbutton("enable");
					}else{
						$('#subRepSend').linkbutton("disable");
						$('#subRepUpdate').linkbutton("disable");
						$('#subRepDel').linkbutton("disable");
					}
				},
				onDblClickRow:function(index,row){
					var datailSubRep=$('<div/>').dialog({
						title : '查看课题报告',
						href : '${pageContext.request.contextPath}/jsp/subjectReport/datailSubRep.jsp',
						width : 600,
						height : 400,
						modal : true,
						resizable :true,
						buttons : [ {
							text : '关闭',
							handler : function() {
								subRepDg.datagrid('unselectAll');
								datailSubRep.dialog("destroy");
							}
						} ],
						onClose : function() {
							subRepDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#detailSubRepFileUrl').html(row.fileUrl);
							$('#detailSubRepFileUrl').attr('href',"file/download?fileName="+row.fileUrl);
			                $('#detailSubRep_Form').form('load',{
			                	title:row.title,
			                	state:row.state,
								createAt:timeToString(row.createAt),
								sendAt:timeToString(row.sendAt),
								remark:row.remark
			                });
						}
		            });
				}
			});
});
function subRepAdd(){
	$('#subRep_Dialog').dialog('open');
	$('#subRep_updateForm').form('clear');
	$("#subRep_Dialog").dialog('setTitle','新增课题报告');
}
function subRepDel() {
	var row = subRepDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要删除的数据！');
		return;
	}
	$.messager.confirm('请确认','确认删除选中的数据？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subRep/del',
					data :"id="+row.srId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						subRepDg.datagrid('load');
						subRepDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function subRepUpdate(){
	var row = subRepDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请选择要修改的数据！');
		return;
	}
	$('#subRep_Dialog').dialog('open');
	$('#subRep_updateForm').form('clear');
	$("#subRep_Dialog").dialog('setTitle','修改课题报告');
	$('#subRep_updateForm').form('load',{
		srId:row.srId,
    	title:row.title,
    	remark:row.remark
    });
}
function subRepSave(){
	var srId=$("#subRep_updateForm input[name=srId]").val();
	var url;
	if(srId){
		url='${pageContext.request.contextPath}/subRep/update';
	}else{
		url='${pageContext.request.contextPath}/subRep/add';
	}
	$('#subRep_updateForm').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$('#subRep_updateForm').form('clear');
            	$("#subRep_Dialog").dialog("close");
            	subRepDg.datagrid("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
function subRepSend(){
	var row = subRepDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要提交的数据！');
		return;
	}
	$.messager.confirm('请确认','提交后将不可修改，确认提交？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subRep/subRepSend',
					data :"srId="+row.srId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						subRepDg.datagrid('load');
						subRepDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function subRepSearchs() {
	var searchForm = $('#subRep_searchForm').form();
	subRepDg.datagrid('load', serializeObject(searchForm));
}
function subRepCleanSearch() {
	subRepDg.datagrid('load', {});
	$('#subRep_searchForm').form('clear');
}

</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="subRep_toolbar">
			<form id="subRep_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>状态</th>
						<td><select name="state" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
						<th>标题</th>
						<td><input name="title"/></td>
						<td colspan="2">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="subRepSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="subRepCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="subRep_datagrid" ></table>
	</div>
</div>

<div id="subRep_Dialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			subRepSave();
	   }
	}]">
	<form id="subRep_updateForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="srId"/>
		<table>
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="title" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea name="remark" rows="4" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<th>附件</th>
				<td>
					<input type="file" name="file" class="easyui-filebox" />
				</td>
			</tr>
		</table>
	</form>
</div>
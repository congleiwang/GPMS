<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var releaseSubDg = $('#releaseSub_datagrid');
$(function() {
	releaseSubDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/subject/releaseList',
				idField : 'subId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'title',
					title : '标题',
					width : 250
				} ] ],
				columns : [ [ {
					field : 'state',
					title : '状态',
					width : 100
				} , {
					field : 'createAt',
					title : '提交时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} , {
					field : 'doerNm',
					title : '完成者',
					width : 100
				} , {
					field : 'doAt',
					title : '认领时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,  {
					field : 'finishAT',
					title : '完成时间',
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
				} ] ],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						rSubAdd();
					}
				},{
					text : '修改',
					iconCls : 'icon-edit',
					id:'rSubUpdate',
					handler : function() {
						rSubUpdate();
					}
				},{
					text : '删除',
					iconCls : 'icon-remove',
					id:'rSubDel',
					handler : function() {
						rSubDel();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						releaseSubDg.datagrid('unselectAll');
					}
				}, '-', {
					text : '提交审批',
					id:'rSubSend',
					iconCls : 'icon-tip',
					handler : function() {
						rSubSend();
					}
				}],
				onClickRow:function(index, row){
					if(row.state=='1'){
						$('#rSubSend').linkbutton("enable");
						$('#rSubUpdate').linkbutton("enable");
						$('#rSubDel').linkbutton("enable");
					}else{
						$('#rSubSend').linkbutton("disable");
						$('#rSubUpdate').linkbutton("disable");
						$('#rSubDel').linkbutton("disable");
					}
				},
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
								releaseSubDg.datagrid('unselectAll');
								datailSub.dialog("destroy");
							}
						} ],
						onClose : function() {
							releaseSubDg.datagrid('unselectAll');
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
function releaseSubSearchs() {
	var searchForm = $('#releaseSub_searchForm').form();
	releaseSubDg.datagrid('load', serializeObject(searchForm));
}
function releaseSubCleanSearch() {
	releaseSubDg.datagrid('load', {});
	$('#releaseSub_searchForm').form('clear');
}
function rSubDel() {
	var row = releaseSubDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要删除的数据！');
		return;
	}
	$.messager.confirm('请确认','确认删除选中的数据？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subject/del',
					data :"id="+row.subId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						releaseSubDg.datagrid('load');
						releaseSubDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function rSubAdd(){
	$('#releaseSub_Dialog').dialog('open');
	$('#releaseSub_updateForm').form('clear');
	$("#releaseSub_Dialog").dialog('setTitle','新增课题');
}
function rSubUpdate(){
	var row = releaseSubDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请选择要修改的数据！');
		return;
	}
	$('#releaseSub_Dialog').dialog('open');
	$('#releaseSub_updateForm').form('clear');
	$("#releaseSub_Dialog").dialog('setTitle','修改课题');
	$('#releaseSub_updateForm').form('load',{
		subId:row.subId,
    	title:row.title,
    	require:row.require,
    	intro:row.intro
    });
}
function releaseSubSave(){
	var subId=$("#releaseSub_updateForm input[name=subId]").val();
	var url;
	if(subId){
		url='${pageContext.request.contextPath}/subject/update';
	}else{
		url='${pageContext.request.contextPath}/subject/add';
	}
	$('#releaseSub_updateForm').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$('#releaseSub_updateForm').form('clear');
            	$("#releaseSub_Dialog").dialog("close");
            	releaseSubDg.datagrid("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
function rSubSend(){
	var row = releaseSubDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要提交的数据！');
		return;
	}
	$.messager.confirm('请确认','提交后将不可修改，确认提交？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/subject/subSend',
					data :"subId="+row.subId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						releaseSubDg.datagrid('load');
						releaseSubDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="releaseSub_toolbar">
			<form id="releaseSub_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>标题</th>
						<td><input name="title"/></td>
						<th>提交时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<th>状态</th>
						<td><select name="state" style="width:150px;" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="releaseSubSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="releaseSubCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="releaseSub_datagrid" ></table>
	</div>
</div>

<div id="releaseSub_Dialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			releaseSubSave();
	   }
	}]">
	<form id="releaseSub_updateForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="subId"/>
		<table>
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="title" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>要求：</th>
				<td>
					<textarea name="require" rows="4" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<th>简介</th>
				<td>
					<textarea name="intro" rows="5" cols="60"></textarea>
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
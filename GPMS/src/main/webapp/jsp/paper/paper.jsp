<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var paperDg = $('#paper_datagrid');
var paperFlag=true;
$(function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/paper/getAllowPaper',
		type:'post',
		dataType : 'json',
		success : function(d) {
			if(d && d.paperId){
				$('#paperSend').linkbutton("disable");
				paperFlag=false;
			}
		}
	});
	paperDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/paper/myPaperList',
				idField : 'paperId',
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
					field : 'pfileUrl',
					title : '附件',
					width : 250,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ,{
					field : 'abs',
					title : '摘要',
					width : 250
				}] ],
				toolbar : [{
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						paperAdd();
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					id:'paperUpdate',
					handler : function() {
						paperUpdate();
					}
				}, '-', {
					text : '删除',
					id:'paperDel',
					iconCls : 'icon-remove',
					handler : function() {
						paperDel();
					}
				}, '-',{
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						paperDg.datagrid('unselectAll');
					}
				},'-', {
					text : '提交审批',
					id:'paperSend',
					iconCls : 'icon-tip',
					handler : function() {
						paperSend();
					}
				}],
				onClickRow:function(index, row){
					if(row.state=='1'){
						if(paperFlag){
							$('#paperSend').linkbutton("enable");							
						}
						$('#paperUpdate').linkbutton("enable");
						$('#paperDel').linkbutton("enable");
					}else{
						$('#paperSend').linkbutton("disable");
						$('#paperUpdate').linkbutton("disable");
						$('#paperDel').linkbutton("disable");
					}
				},
				onDblClickRow:function(index,row){
					var datailPaper=$('<div/>').dialog({
						title : '查看论文',
						href : '${pageContext.request.contextPath}/jsp/paper/datailPaper.jsp',
						width : 600,
						height : 400,
						modal : true,
						resizable :true,
						buttons : [ {
							text : '关闭',
							handler : function() {
								paperDg.datagrid('unselectAll');
								datailPaper.dialog("destroy");
							}
						} ],
						onClose : function() {
							paperDg.datagrid('unselectAll');
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#datailPaperPfileUrl').html(row.pfileUrl);
							$('#datailPaperPfileUrl').attr('href',"file/download?fileName="+row.pfileUrl);
							if(row.state=="3" ||row.state=="4" ){
								$('#datailPaperExamFileUrl').html(row.examFileUrl);
								$('#datailPaperExamFileUrl').attr('href',"file/download?fileName="+row.examFileUrl);
							}
			                $('#detailPaper_Form').form('load',{
			                	title:row.title,
			                	abs:row.abs,
			                	state:row.state,
								createAt:timeToString(row.createAt),
								createNm:row.createNm,
								sendAt:timeToString(row.sendAt),
								examorNm:row.examorNm,
								examAt:timeToString(row.examAt),
								examRemark:row.examRemark,
			                });
						}
		            });
				}
			});
});
function paperAdd(){
	$('#paper_Dialog').dialog('open');
	$('#paper_updateForm').form('clear');
	$("#paper_Dialog").dialog('setTitle','新增论文');
}
function paperDel() {
	var row = paperDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要删除的数据！');
		return;
	}
	$.messager.confirm('请确认','确认删除选中的数据？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/paper/del',
					data :"id="+row.paperId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						paperDg.datagrid('load');
						paperDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function paperUpdate(){
	var row = paperDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请选择要修改的数据！');
		return;
	}
	$('#paper_Dialog').dialog('open');
	$('#paper_updateForm').form('clear');
	$("#paper_Dialog").dialog('setTitle','修改论文');
	$('#paper_updateForm').form('load',{
		paperId:row.paperId,
    	title:row.title,
    	abs:row.abs
    });
}
function paperSave(){
	var paperId=$("#paper_updateForm input[name=paperId]").val();
	var url;
	if(paperId){
		url='${pageContext.request.contextPath}/paper/update';
	}else{
		url='${pageContext.request.contextPath}/paper/add';
	}
	$('#paper_updateForm').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$('#paper_updateForm').form('clear');
            	$("#paper_Dialog").dialog("close");
            	paperDg.datagrid("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
function paperSend(){
	var row = paperDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要提交的数据！');
		return;
	}
	$.messager.confirm('请确认','提交后将不可修改，确认提交？',
		function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/paper/paperSend',
					data :"paperId="+row.paperId,
					type:'post',
					dataType : 'json',
					success : function(d) {
						paperDg.datagrid('load');
						paperDg.datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
}
function paperSearchs() {
	var searchForm = $('#paper_searchForm').form();
	paperDg.datagrid('load', serializeObject(searchForm));
}
function paperCleanSearch() {
	paperDg.datagrid('load', {});
	$('#paper_searchForm').form('clear');
}

</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="paper_toolbar">
			<form id="paper_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>状态</th>
						<td><select name="state" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=GPMS&keyCd=applyState" valueField="keyValue" textField="caption">
							</select>
						</td>
						<th>标题</th>
						<td><input name="title"/></td>
						<td colspan="2">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="paperSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="paperCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="paper_datagrid" ></table>
	</div>
</div>

<div id="paper_Dialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '保存',
		iconCls : 'icon-save',
		handler :  function() {
			paperSave();
	   }
	}]">
	<form id="paper_updateForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="paperId"/>
		<table>
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="title" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>摘要：</th>
				<td>
					<textarea name="abs" rows="4" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<th>附件</th>
				<td>
					<input type="file" name="pfile" class="easyui-filebox" />
				</td>
			</tr>
		</table>
	</form>
</div>
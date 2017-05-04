<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<script type="text/javascript">
var examPaperDg = $('#examPaper_datagrid');
$(function() {
	examPaperDg.datagrid({
				fit : true,
				pageSize : 10,
				rownumbers:true,
				singleSelect:true,
				pageList : [ 10, 20, 30, 40 ],
				url : '${pageContext.request.contextPath}/paper/examList',
				idField : 'paperId',
				pagePosition : 'bottom',
				pagination : true,
				loadMsg : '正在加载数据当中....',
				frozenColumns : [ [ {
					field : 'createNm',
					title : '提交人',
					width : 250
				} ] ],
				columns : [ [  {
					field : 'title',
					title : '标题',
					width : 250
				} ,{
					field : 'sendAt',
					title : '提交时间',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return timeToString(value);
					}
				} ,{
					field : 'pfileUrl',
					title : '附件',
					width : 220,
					formatter : function(value, rowData, rowIndex) {
						return downHref(value);
					}
				} ] ],
				toolbar : [{
					text : '审批',
					iconCls : 'icon-remove',
					handler : function() {
						examPaper();
					}
				}, '-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						examPaperDg.datagrid('unselectAll');
					}
				}],
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
								examPaperDg.datagrid('unselectAll');
								datailPaper.dialog("destroy");
							}
						} ],
						onClose : function() {
							examPaperDg.datagrid('unselectAll');
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
function examPaperSearchs() {
	var searchForm = $('#examPaper_searchForm').form();
	examPaperDg.datagrid('load', serializeObject(searchForm));
}
function examPaperCleanSearch() {
	examPaperDg.datagrid('load', {});
	$('#examPaper_searchForm').form('clear');
}
function examPaper(){
	var row = examPaperDg.datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', '请勾选要审批的数据！');
		return;
	}
	$('#examPaper_dialog').dialog('open');
	$('#examPaper_form').form('clear');
}
function examPaperAllow() {
	$.messager.confirm('请确认','通过后将进入答辩阶段，确认？',
		function(r) {
			if (r) {
				var row = examPaperDg.datagrid('getSelected');
				$('#examPaper_form input[name=paperId]').val(row.paperId);
				$('#examPaper_form').form('submit',{
					onSubmit:function(){
						return $(this).form('validate');
					},
					url:'${pageContext.request.contextPath}/paper/examPaperAllow',
			        success:function(r){
			        	var obj=$.parseJSON(r);
			            if(obj.success){
			            	$('#examPaper_form').form('clear');
			            	$("#examPaper_dialog").dialog("close");
			            	examPaperDg.datagrid("reload");
			            }
			            $.messager.alert('提示', obj.msg);
				    }
				});
			}
		});
}
function examPaperReject(){
	$.messager.confirm('请确认','审批后不可修改，确认？',
		function(r) {
			if (r) {
				var row = examPaperDg.datagrid('getSelected');
				$('#examPaper_form input[name=paperId]').val(row.paperId);
				$('#examPaper_form').form('submit',{
					onSubmit:function(){
						return $(this).form('validate');
					},
					url:'${pageContext.request.contextPath}/subRep/examPaperReject',
			        success:function(r){
			        	var obj=$.parseJSON(r);
			            if(obj.success){
			            	$('#examPaper_form').form('clear');
			            	$("#examPaper_dialog").dialog("close");
			            	examPaperDg.datagrid("reload");
			            }
			            $.messager.alert('提示', obj.msg);
				    }
				});
			}
		});
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询条件'"style="height: 60px;overflow: hidden;" align="left">
		<div id="examPaper_toolbar">
			<form id="examPaper_searchForm">
				<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
					<tr>
						<th>提交人</th>
						<td><input name="createNm"/></td>
						<th>标题</th>
						<td><input name="title"/></td>
						<th>创建时间</th>
						<td><input name="createAtStart" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
							至<input name="createAtEnd" class="easyui-datetimebox" data-options="editable:false" style="width: 155px;" />
						</td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examPaperSearchs();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="examPaperCleanSearch();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="examPaper_datagrid" ></table>
	</div>
</div>

<div id="examPaper_dialog" class="easyui-dialog" style="width:600px;height:300px;" align="center"
	data-options="closed:true,modal:true,title:'添加',resizable :true,
	buttons:[{
		text : '同意',
		iconCls : 'icon-ok',
		handler :  function() {
			examPaperAllow();
	   }
	},{
		text : '拒绝',
		iconCls : 'icon-no',
		handler :  function() {
			examPaperReject();
	   }
	}]">
	<form id="examPaper_form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="paperId"/>
		<table>
			<tr>
				<th>审批意见</th>
				<td>
					<textarea name="examRemark" rows="7" cols="60"></textarea>
				</td>
			</tr>
			<tr>
				<th>附件</th>
				<td>
					<input type="file" name="examFile" class="easyui-filebox" />
				</td>
			</tr>
		</table>
	</form>
</div>
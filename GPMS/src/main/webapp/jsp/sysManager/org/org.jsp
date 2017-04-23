<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var orgToolbar = $('#org_toolbar');
$(function() {
	orgToolbar.datagrid({
				toolbar : [ {
					text : '增加同级',
					iconCls : 'icon-add',
					handler : function() {
						orgAdd();
					}
				}, '-', {
					text : '增加子级',
					iconCls : 'icon-edit',
					handler : function() {
						orgAddChild();
					}
				}, '-', {
					text : '删除机构',
					iconCls : 'icon-remove',
					handler : function() {
						orgDel();
					}
				}]
			});
});
function orgSave(){
	var flag=$("#org_isAdd").val();
	var url;
	if(flag==1||flag=='1'){
		url='${pageContext.request.contextPath}/org/add';
	}
	if(!url || url==''){
		url='${pageContext.request.contextPath}/org/update';
	}
	$('#org_orgFrom').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$('#org_orgFrom').form('clear');
            	$('#org_orgTree').tree("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
function orgDel(){
	var node = $('#org_orgTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择要删除的机构');
		return ;
	}
	if(node.id=='new'){
		$('#org_orgTree').tree('remove',node.target);
		return;
	}
	if(!$('#org_orgTree').tree('isLeaf',node.target)){
		$.messager.alert('提示', '请先删除子菜单');
	}else{
		$.messager.confirm('请确认',"确认删除？",function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/org/del',
					data :'id='+node.id,
					type:'post',
					dataType : 'json',
					success : function(d) {
						if(d.success){
							$('#org_orgTree').form('clear');
			            	$('#org_orgTree').tree("reload");
						}
						$.messager.show({
							title : '提示',
							msg : d.msg
						});
					}
				});
			}
		});
	}
}

function orgAdd(){
	var node = $('#org_orgTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择目标机构');
		return ;
	}
	$('#org_orgTree').tree('insert', {
		after:node.target,
		data:{
			text:'name',
			id:'new',
		}
	});
	$('#org_porgId').val(node.attributes.porgId);
	$('#org_isAdd').val('1');
}

function orgAddChild(){
	var node = $('#org_orgTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择目标机构');
		return ;
	}
	$('#org_orgTree').tree('append', {
		parent:node.target,
		data:[{
			text:'name',
			id:'new',
		}]
	});
	$('#org_porgId').val(node.id);
	$('#org_isAdd').val('1');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div region="north" style="height:36px" overflow="hidden" border="false">
		<div id="org_toolbar" overflow="hidden" border="false"></div>
	</div>
	<div data-options="region:'west'" style="width:200px;">
		<ul class="easyui-tree" id="org_orgTree"
					data-options="
							url:'${pageContext.request.contextPath}/org/getAllOrgTree',
							parentField:'parentId',
							lines:true,
							onClick:function(node){
								if(node.id){
									var porgId=$('#org_porgId').val();
									$('#org_orgFrom').form('clear');
									$('#org_porgId').val(porgId);
									if(node.id !='new'){
										 $('#org_isAdd').val('0');
										 $('#org_orgFrom').form('load',{
						    				orgId:node.id,
						    				orgNm:node.text,
						    				address:node.attributes.address,
						    				orgType:node.attributes.orgType,
						    				isUse:node.attributes.isUse,
						    				orgDesc:node.attributes.orgDesc
						                });
									}else{
										$('#org_isAdd').val('1');
									}
								}
							}">
				</ul>
	</div>
	<div region="center" border="false">
		<form id="org_orgFrom" method="post" >
			<input type="hidden" name="porgId" id="org_porgId"/>
			<input type="hidden" id="org_isAdd" />
			<table>
				<tr>
					<th>机构类型：</th>
					<td><select name="orgType" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=orgType" valueField="keyValue" textField="caption">
						</select>
					</td>
				</tr>
				<tr>
					<th>机构名：</th>
					<td><input name="orgNm" type="text" /></td>
				</tr>
				<tr>
					<th>地址：</th>
					<td><input name="address" type="text" /></td>
				</tr>
				<tr>
					<th>是否启用 ：</th>
					<td><select name="isUse">
						<option value="1">是</option>
						<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<th>机构描述：</th>
					<td><textarea name=orgDesc rows="4" cols="50"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="button" value="保存" onclick="orgSave();" />
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>
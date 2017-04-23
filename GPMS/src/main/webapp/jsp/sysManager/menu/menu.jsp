<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var menuToolbar = $('#menu_toolbar');
$(function() {
	menuToolbar.datagrid({
				toolbar : [ {
					text : '增加同级',
					iconCls : 'icon-add',
					handler : function() {
						menuAdd();
					}
				}, '-', {
					text : '增加子级',
					iconCls : 'icon-edit',
					handler : function() {
						menuAddChild();
					}
				}, '-', {
					text : '删除菜单',
					iconCls : 'icon-remove',
					handler : function() {
						menuDel();
					}
				}]
			});
});
function menuSave(){
	var flag=$("#menu_isAdd").val();
	var url;
	if(flag==1||flag=='1'){
		url='${pageContext.request.contextPath}/menu/add';
	}
	if(!url || url==''){
		url='${pageContext.request.contextPath}/menu/update';
	}
	$('#menu_menuFrom').form('submit',{
		onSubmit:function(){
			return $(this).form('validate');
		},
		url:url,
        success:function(r){
        	var obj=$.parseJSON(r);
            if(obj.success){
            	$('#menu_menuFrom').form('clear');
            	$('#menu_menuTree').tree("reload");
            }
            $.messager.alert('提示', obj.msg);
	    }
	});
}
function menuDel(){
	var node = $('#menu_menuTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择要删除的菜单');
		return ;
	}
	if(node.id=='new'){
		$('#menu_menuTree').tree('remove',node.target);
		return;
	}
	if(!$('#menu_menuTree').tree('isLeaf',node.target)){
		$.messager.alert('提示', '请先删除子菜单');
	}else{
		$.messager.confirm('请确认',"确认删除？",function(r) {
			if (r) {
				$.ajax({
					url : '${pageContext.request.contextPath}/menu/del',
					data :'id='+node.id,
					type:'post',
					dataType : 'json',
					success : function(d) {
						if(d.success){
							$('#menu_menuFrom').form('clear');
			            	$('#menu_menuTree').tree("reload");
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

function menuAdd(){
	var node = $('#menu_menuTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择目标菜单');
		return ;
	}
	$('#menu_menuTree').tree('insert', {
		after:node.target,
		data:{
			text:'name',
			id:'new',
		}
	});
	$('#menu_pmenuCd').val(node.attributes.pmenuCd);
	$('#menu_isAdd').val('1');
}

function menuAddChild(){
	var node = $('#menu_menuTree').tree('getSelected');
	if(!node){
		$.messager.alert('提示', '请选择目标菜单');
		return ;
	}
	$('#menu_menuTree').tree('append', {
		parent:node.target,
		data:[{
			text:'name',
			id:'new',
		}]
	});
	$('#menu_pmenuCd').val(node.id);
	$('#menu_isAdd').val('1');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div region="north" style="height:36px" overflow="hidden" border="false">
		<div id="menu_toolbar" overflow="hidden" border="false"></div>
	</div>
	<div data-options="region:'west'" style="width:200px;">
		<ul class="easyui-tree" id="menu_menuTree"
					data-options="
							url:'${pageContext.request.contextPath}/menu/getAllMenuTree',
							parentField:'parentId',
							lines:true,
							onClick:function(node){
								if(node.id){
									var pmenuCd=$('#menu_pmenuCd').val();
									$('#menu_menuFrom').form('clear');
									$('#menu_pmenuCd').val(pmenuCd);
									if(node.id !='new'){
										 $('#menu_isAdd').val('0');
										 $('#menu_menuFrom').form('load',{
							                sysNm:node.attributes.sysNm,
						    				menuCd:node.id,
						    				menuNm:node.text,
						    				menuType:node.attributes.menuType,
						    				iconFile:node.iconCls,
						    				orderBy:node.attributes.orderBy,
						    				jspUrl:node.attributes.jspUrl,
						    				isUse:node.attributes.isUse,
						    				remark:node.attributes.remark,
						                });
									}else{
										$('#menu_isAdd').val('1');
									}
								}
							}">
				</ul>
	</div>
	<div region="center" border="false">
		<form id="menu_menuFrom" method="post" >
			<input type="hidden" name="pmenuCd" id="menu_pmenuCd"/>
			<input type="hidden" id="menu_isAdd" />
			<table>
				<tr>
					<th>系统名称：</th>
					<td><select name="sysNm" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=system" valueField="keyValue" textField="caption">
						</select>
					</td>
				</tr>
				<tr>
					<th>菜单编号：</th>
					<td><input name="menuCd" type="text" /></td>
				</tr>
				<tr>
					<th>菜单名称：</th>
					<td><input name="menuNm" type="text" /></td>
				</tr>
				<tr>
					<th>菜单类型：</th>
					<td><select name="menuType" class="easyui-combobox" url="dictionary/getDictionaryByKey?sysNm=SYS&keyCd=menuType" valueField="keyValue" textField="caption">
						</select>
					</td>
				</tr>
				<tr>
					<th>菜单图标：</th>
					<td><input name="iconFile" type="text" /></td>
				</tr>
				<tr>
					<th>排序：</th>
					<td><input name="orderBy" type="text" /></td>
				</tr>
				<tr>
					<th>菜单URL：</th>
					<td><input name="jspUrl" type="text" /></td>
				</tr>
				<tr>
					<th>是否启用 ：</th>
					<td><select name="isUse">
						<option value="1">是</option>
						<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<th>菜单描述：</th>
					<td><textarea name=remark rows="4" cols="50"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="button" value="保存" onclick="menuSave();" />
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</div>

</div>
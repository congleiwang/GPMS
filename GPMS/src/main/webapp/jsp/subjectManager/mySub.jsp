<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/subject/getMySub',
		type:'post',
		dataType : 'json',
		success : function(data) {
			if(data && data.doer){
				$('#subFileUrl').html(data.fileUrl);
				$('#subFileUrl').attr('href',"file/download?fileName="+data.fileUrl);
				$('#mySub_Form').form('load',{
					title:data.title,
					createAt:timeToString(data.createAt),
					createNm:data.createNm,
					doerNm:data.doerNm,
					doAt:timeToString(data.doAt),
					finishAT:timeToString(data.finishAT),
					moderNm:data.moderNm,
					modAt:timeToString(data.modAt),
					require:data.require,
					intro:data.intro,
					applySbjt:data.applySbjt,
					applyCont:data.applyCont
				});
			}
		}
	});
});
</script>
<form id="mySub_Form" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="subId" />
	<table>
		<tr>
			<th>标题：</th>
			<td colspan="3"><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>创建时间：</th>
			<td><input type="text" name="createAt"/></td>
			<th>创建人：</th>
			<td><input type="text" name="createNm"/></td>
		</tr>
		<tr>
			<th>审批时间：</th>
			<td><input type="text" name="modAt"/></td>
			<th>审批人：</th>
			<td><input type="text" name="moderNm"/></td>
		</tr>
		<tr>
			<th>选择时间：</th>
			<td><input type="text" name="doAt"/></td>
			<th>完成人：</th>
			<td><input type="text" name="doerNm"/></td>
		</tr>
		<tr>
			<th>要求：</th>
			<td colspan="3"><textarea name="require" rows="4" cols="50"></textarea></td>
		</tr>
		<tr>
			<th>简介</th>
			<td colspan="3"><textarea name="intro" rows="5" cols="60"></textarea></td>
		</tr>
		<tr>
			<th>附件</th>
			<td colspan="3"><a id='subFileUrl'></a></td>
		</tr>
	</table>
</form>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="detailSub_Form" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="subId" />
	<table>
		<tr>
			<th>标题：</th>
			<td colspan="3"><input type="text" name="title" style="width: 300px"/></td>
		</tr>
		<tr>
			<th>提交时间：</th>
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
			<th>申请时间：</th>
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
			<td colspan="3"><a id='detailSubFileUrl'></a></td>
		</tr>
	</table>
</form>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="detailSubRep_Form" >
	<input type="hidden" name="subId" />
	<table>
		<tr>
			<th>标题：</th>
			<td colspan="3"><input type="text" name="title" style="width: 300px" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>状态：</th>
			<td><input type="text" name="state" readonly="readonly"/></td>
			<th>创建时间：</th>
			<td><input type="text" name="createAt" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>提交时间：</th>
			<td><input type="text" name="sendAt" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><textarea name="remark" rows="5" cols="60" readonly="readonly"></textarea></td>
		</tr>
		<tr>
			<th>附件</th>
			<td colspan="3"><a id='detailSubRepFileUrl'></a></td>
		</tr>
	</table>
</form>

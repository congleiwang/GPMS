<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="detailPaper_Form" >
	<table>
		<tr>
			<th>标题：</th>
			<td colspan="3"><input type="text" name="title" style="width: 300px" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>创建人：</th>
			<td><input type="text" name="createNm" readonly="readonly"/></td>
			<th>创建时间：</th>
			<td><input type="text" name="createAt" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>状态：</th>
			<td><input type="text" name="state" readonly="readonly"/></td>
			<th>提交时间：</th>
			<td><input type="text" name="sendAt" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>摘要：</th>
			<td colspan="3"><textarea name="abs" rows="5" cols="60" readonly="readonly"></textarea></td>
		</tr>
		<tr>
			<th>开题报告附件：</th>
			<td colspan="3"><a id='datailPaperPfileUrl'></a></td>
		</tr>
		<tr>
			<th>审批人</th>
			<td><input type="text" name="examorNm" readonly="readonly"/></td>
			<th>审批时间：</th>
			<td><input type="text" name="examAt" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>审批回复</th>
			<td colspan="3"><textarea name="examRemark" rows="5" cols="60" readonly="readonly"></textarea></td>
		</tr>
		<tr>
			<th>审批附件</th>
			<td colspan="3"><a id='datailPaperExamFileUrl'></a></td>
		</tr>
	</table>
</form>

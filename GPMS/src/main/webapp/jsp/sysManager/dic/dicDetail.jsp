<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="dic_detailForm" method="post">
		<table>
			<tr>
				<th>键值码</th>
				<td>
					<input type="text" name="keyCd" readonly="readonly" />
				</td>
				<th>系统</th>
				<td>
					<input type="text" name="sysNm" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>键值</th>
				<td>
					<input type="text" name="keyValue" readonly="readonly" />
				</td>
				<th>中文名称</th>
				<td>
					<input type="text" name="caption" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>可否更改</th>
				<td>
					<input type="text" name="isMod" readonly="readonly" />
				</td>
				<th>英文名称</th>
				<td>
					<input type="text" name="english" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>是否启用</th>
				<td>
					<input type="text" name="isUse" readonly="readonly" />
				</td>
				<th>排序</th>
				<td>
					<input type="text" name="orderBy" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="4"><textarea name='remark' rows="3" cols="50" readonly="readonly"></textarea></td>
			</tr>
		</table>
	</form>
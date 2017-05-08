<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="canApplyForm" method="post">
	<table>
		<tr>
			<c:if test="${sessionScope.user.canApply=='0'}">
				<td>
						<input id="canApply0" name='canApply' type="radio" value="0" checked="checked" /><label for="canApply0">不可申请</label> 
				</td>
				<td>
				</td>
				<td>
					<input id="canApply1" name='canApply' type="radio" value="1" /><label for="canApply1">可申请</label>
				</td>
			</c:if>
			<c:if test="${sessionScope.user.canApply!='0'}">
				<td>
						<input id="canApply0" name='canApply' type="radio" value="0" /><label for="canApply0">不可申请</label> 
				</td>
				<td>
				</td>
				<td>
					<input id="canApply1" name='canApply' type="radio" value="1" checked="checked" /><label for="canApply1">可申请</label>
				</td>
			</c:if>
		</tr>
	</table>
</form>

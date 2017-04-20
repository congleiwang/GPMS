<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/base.jsp"%>

<div class="easyui-panel"
	data-options="title:'功能导航',fit:true,border:false">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<c:forEach items="${sessionScope.user.menuTrees }" var="menuTrees">
			<div id="layout_west_tree" title="${menuTrees.text }" iconCls="">
				<ul class="easyui-tree"
					data-options="
							url:'${pageContext.request.contextPath}/menu/getSubMenuTreeById?id=${menuTrees.id }',
							parentField:'parentId',
							lines:true,
							onClick:function(node){
								if(node.attributes.jspUrl){
									var url='${pageContext.request.contextPath}'+node.attributes.jspUrl;
									addTabs({title:node.text,href:url,closable:true});
								}
							}">
				</ul>
			</div>
		</c:forEach>
	</div>
</div>

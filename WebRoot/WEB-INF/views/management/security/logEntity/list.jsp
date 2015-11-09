<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<gcs:paginationForm action="${contextPath}/management/security/logEntity/list" page="${page }">
	<input type="hidden" name="username" value="${logEntity.username }"/>
	<input type="hidden" name="ipAddress" value="${logEntity.ipAddress }"/>
	<input type="hidden" name="logLevel" value="${logEntity.logLevel }"/>
	<input type="hidden" name="startDate" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"/>
	<input type="hidden" name="endDate" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"/>
</gcs:paginationForm>

<form method="post" action="${contextPath}/management/security/logEntity/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">登录名称：</label>
					<input type="text" name="username" value="${logEntity.username }"/>
				</li>	
				<li>
					<label style="width: 100px;">登录IP：</label>
					<input type="text" name="ipAddress" value="${logEntity.ipAddress }"/>
				</li>
				<li>
					<label style="width: 100px;">日志等级：</label>
					<select name="logLevel">
						<option value="">所有</option>
						<c:forEach var="logLevel" items="${logLevels }"> 
							<option value="${logLevel}" ${logEntity.logLevel == logLevel ? 'selected="selected"':'' }>${logLevel}</option>
						</c:forEach>
					</select>
				</li>																
			</ul>
			<ul class="searchContent">	
				<li>
					<label style="width: 100px;">日志开始时间：</label>
					<input type="text" name="startDate" class="date" readonly="readonly" style="float:left;" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"/>
					<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
				</li>			
				<li>
					<label style="width: 100px;">日志结束时间：</label>
					<input type="text" name="endDate" class="date" readonly="readonly" style="float:left;" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"/>
					<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
				</li>							
			</ul>			
			<div class="subBar">
				<ul>						
					<li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="LogEntity:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath}/management/security/logEntity/delete" title="确认要删除?"><span>删除日志</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="163" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">登录名称</th>
				<th width="100">登录IP</th>
				<th width="100" orderField="logLevel" class="${page.orderField eq 'logLevel' ? page.orderDirection : ''}">日志等级</th>
				<th >日志内容</th>
				<th width="130" orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${logEntities}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.username}</td>
				<td>${item.ipAddress}</td>
				<td>${item.logLevel}</td>
				<td>${item.message}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<gcs:pagination page="${page}"/>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid auditoria">
		<jsp:include page="header-auditoria.jsp"/>
		<h2>Auditoria de Logs</h2>
		<table class="table">		
			<thead>
				<tr>
					<th>Id</th>
					<th>Fecha</th>
					<th>Hora</th>
					<th>IP</th>
					<th>Method</th>
					<th>Resource</th>
					<th>Status Code</th>
					<th>Content-Type</th>
					<th style="text-align:right;">Elapsed Time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="log" items="${logs}">
				<tr>
					<td>${log.id}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${log.timeStamp}"/></td>
					<td><fmt:formatDate pattern="HH:mm:ss" value="${log.timeStamp}"/></td>
					<td>${log.ip}</td>
					<td>${log.method}</td>
					<td>${log.resource}</td>
					<td>
						<c:if test="${log.statusCode >= 400 && log.statusCode < 500}"><span style="color:orange;">${log.statusCode}</span></c:if>
						<c:if test="${log.statusCode >= 500}"><span style="color:red;">${log.statusCode}</span></c:if>
						<c:if test="${log.statusCode < 400}"><span style="color:green;">${log.statusCode}</span></c:if>
					</td>
					<td>${log.contentType}</td>
					<td style="text-align: right;">${log.elapsedTime}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
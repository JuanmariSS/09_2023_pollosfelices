<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header.jsp"/>
		<h2>Lista de Pedidos</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Número</th>
					<th>Fecha</th>
					<th>Hora</th>
					<th>Establecimiento</th>
					<th>Camarero</th>
					<th>Cliente</th>
					<th>Número de Lineas</th>
					<th>Estado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pedido" items="${pedidos}">
				<tr>
					<td>${pedido.numero}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.fecha}"/></td>
					<td><fmt:formatDate pattern="HH:mm:ss" value="${pedido.fecha}"/></td>
					<td>${pedido.establecimiento}</td>
					<td>${pedido.nombreCamarero}</td>
					<td>
						<c:if test="${pedido.nombreCliente == null}">-</c:if>
						<c:if test="${pedido.nombreCliente != null}">${pedido.nombreCliente}</c:if>
					</td>
					<td>${pedido.numeroLineas}</td>
					<td>${pedido.estado}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
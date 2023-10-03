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
		<h2>Lista de Productos</h2>
		<table>
			<thead>
				<tr>
					<th>Código</th>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>Fecha de Alta</th>
					<th>Descatalogado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="producto" items="${productos}">
				<tr>
					<td><a href="productos?codigo=${producto.codigo}">${producto.codigo}</a></td>
					<td>${producto.nombre}</td>
					<td>${producto.descripcion}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${producto.fechaAlta}"/></td>
					<td style="text-align:center;">
						<c:if test="${producto.descatalogado}"><span style="color:red;">descatalogado</span></c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
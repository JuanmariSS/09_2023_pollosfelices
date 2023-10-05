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
		<h2>Ficha de Producto</h2>
		<table>
			<tr>
				<th>Código</th>
				<td>${producto.codigo}</td>
			</tr>
			<tr>
				<th>Nombre</th>
				<td>${producto.nombre}</td>
			</tr>
			<tr>
				<th>Precio</th>
				<td>${producto.precio}</td>
			</tr>
			<tr>
				<th>Categoría</th>
				<td>${producto.categoria.nombre}</td>
			</tr>
			<tr>
				<th>Descatalogado</th>
				<td>
					<c:if test="${producto.descatalogado}"><span style="color:red;">SI</span></c:if>
					<c:if test="${!producto.descatalogado}"><span style="color:green;">NO</span></c:if>
				</td>
			</tr>
			<tr>
				<th>Descripción</th>
				<td>${producto.descripcion}</td>
			</tr>
			<tr>
				<th>Fecha de Alta</th>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${producto.fechaAlta}"/></td>
			</tr>
		</table>
	</div>
</body>
</html>
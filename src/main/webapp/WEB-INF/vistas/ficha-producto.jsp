<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<td>${producto.descatalogado}</td>
			</tr>
			<tr>
				<th>Descripción</th>
				<td>${producto.descripcion}</td>
			</tr>
			<tr>
				<th>Fecha de Alta</th>
				<td>${producto.fechaAlta}</td>
			</tr>
		</table>
	</div>
</body>
</html>
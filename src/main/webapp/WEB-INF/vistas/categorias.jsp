<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header.jsp"/>
		<h2>Lista de Categorías</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Nombre</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="categoria" items="${categorias}">
				<tr>
					<td>${categoria.codigo}</td>
					<td>${categoria.nombre}</td>
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
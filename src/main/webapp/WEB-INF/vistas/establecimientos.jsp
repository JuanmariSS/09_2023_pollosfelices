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
		<h2>Lista de Establecimientos</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Nombre Comercial</th>
					<th>Direccion</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="establecimiento" items="${establecimientos}">
				<tr>
					<td>${establecimiento.codigo}</td>
					<td>${establecimiento.nombreComercial}</td>
					<td>${establecimiento.direccion.direccion} 
					    ${establecimiento.direccion.poblacion} - ${establecimiento.direccion.codigoPostal} 
					    ${establecimiento.direccion.provincia} (${establecimiento.direccion.pais})
					 </td>
					<td>${establecimiento.datosContacto.email}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
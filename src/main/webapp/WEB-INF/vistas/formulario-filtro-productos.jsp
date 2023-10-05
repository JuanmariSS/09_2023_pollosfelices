<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header.jsp"/>
		<h2>Formulario Solicitud Productos</h2>
		<form action="productos" method="GET">
		<table>
			<tr>
				<td>Filtrar por texto en nombre del producto (no poner nada para obtenerlos todos) </td>
				<td><input type="text" name="texto"/></td>
				<td><button type="submit">SOLICITAR</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>Prueba Productos...</title>
</head>
<body>
	<h2>Lista de Productos</h2>
	<ul>
		<c:forEach var="producto" items="${productos}">
			<li>${producto.nombre}</li>
		</c:forEach>
	</ul>
</body>
</html>
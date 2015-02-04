<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Listagem de produtos</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<a href="<c:url value='/produto/formulario'/>">
	    Adicionar mais produtos!
	</a>
	</br>
	<a href="<c:url value='/produto/listaXML'/>">
	    Listar em XML!
	</a>
	</br>
	<a href="<c:url value='/produto/listaJSON'/>">
	    Listar em JSON!
	</a>
	<h1>Listagem de produtos</h1>
	<table class="table table-stripped table-hover table-bordered">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Valor</th>
				<th>Quantidade</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtoList}" var="produto">
				<tr>
					<td>${produto.nome}</td>
					<td>${produto.valor}</td>
					<td>${produto.quantidade}</td>
					<td>
					  <a href="<c:url value='/produto/remove?produto.id=${produto.id}'/>">Remover</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	${mensagem}
</body>
</html>
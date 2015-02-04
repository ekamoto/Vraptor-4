<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Novo produto</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../base.css">
</head>
<body>
	<h1>Adicionar produto</h1>
	<form action="<c:url value='/produto/adiciona'/>" method="post">
		Nome:<input type="text" name="produto.nome"  class="form-control"/>
		Valor:<input type="text" name="produto.valor"  class="form-control"/>
		Quantidade:<input type="text" name="produto.quantidade"  class="form-control"/>
		<input type="submit" value="cadastrar"  class="btn btn-primary"/>
	</form>
	<c:forEach items="${errors}" var="erro">
		${erro.category} - ${erro.message}<br/>
	</c:forEach>
</body>
</html>
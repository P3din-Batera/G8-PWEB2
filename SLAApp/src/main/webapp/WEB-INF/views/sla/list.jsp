<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SLAApp</title>
</head>

<body>

<h2>Lista de SLAs</h2>
<a href="${pageContext.request.contextPath}/sla?action=createForm">Novo SLA</a>
<table border="1">
<tr><th>ID</th><th>Resposta (h)</th><th>Solucao (h)</th><th>Prioridade</th><th>Criticidade</th></tr>
<c:forEach var="s" items="${slas}">
  <tr>
    <td>${s.id}</td>
    <td>${s.tempoResposta}</td>
    <td>${s.tempoSolucao}</td>
    <td>${s.prioridade}</td>
    <td>${s.criticidade}</td>
  </tr>
</c:forEach>
</table>

</body>

</html>
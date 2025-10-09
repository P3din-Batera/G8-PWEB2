<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SLAApp</title>
</head>

<body>

<form action="${pageContext.request.contextPath}/sla" method="post">
  Tempo Resposta (h): <input name="tempoResposta" type="number"/><br/>
  Tempo Solucao (h): <input name="tempoSolucao" type="number"/><br/>
  Prioridade: <input name="prioridade"/><br/>
  Criticidade: <input name="criticidade"/><br/>
  <button type="submit">Salvar</button>
</form>

</body>
</html>
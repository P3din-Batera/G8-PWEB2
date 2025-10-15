<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp" />
<h1>Tipos de Serviço</h1>

<form method="post" action="${pageContext.request.contextPath}/tiposervico" class="row g-2 mb-3">
  <div class="col-md-5"><input name="nome" placeholder="Nome" class="form-control" required></div>
  <div class="col-md-5"><input name="descricao" placeholder="Descrição" class="form-control"></div>
  <div class="col-md-2"><button class="btn btn-primary w-100">Salvar</button></div>
</form>

<table class="table table-bordered">
  <thead><tr><th>ID</th><th>Nome</th><th>Descrição</th></tr></thead>
  <tbody>
    <c:forEach var="t" items="${tipos}">
      <tr><td>${t.id}</td><td>${t.nome}</td><td>${t.descricao}</td></tr>
    </c:forEach>
  </tbody>
</table>

<jsp:include page="../includes/footer.jsp" />

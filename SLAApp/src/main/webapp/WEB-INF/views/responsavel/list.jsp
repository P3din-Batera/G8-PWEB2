<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp" />
<h1>Responsáveis</h1>

<form method="post" action="${pageContext.request.contextPath}/responsavel" class="row g-2 mb-3">
  <div class="col-md-4"><input name="nome" placeholder="Nome" class="form-control" required></div>
  <div class="col-md-3"><input name="cargo" placeholder="Cargo" class="form-control"></div>
  <div class="col-md-3"><input name="contato" placeholder="Contato" class="form-control"></div>
  <div class="col-md-2"><button class="btn btn-primary w-100">Salvar</button></div>
</form>

<table class="table table-bordered">
  <thead><tr><th>ID</th><th>Nome</th><th>Cargo</th><th>Contato</th></tr></thead>
  <tbody>
    <c:forEach var="r" items="${responsaveis}">
      <tr><td>${r.id}</td><td>${r.nome}</td><td>${r.cargo}</td><td>${r.contato}</td></tr>
    </c:forEach>
  </tbody>
</table>

<jsp:include page="../includes/footer.jsp" />

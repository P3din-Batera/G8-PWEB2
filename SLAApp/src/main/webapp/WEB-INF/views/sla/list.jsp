<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp" />
<h1>SLAs</h1>

<div class="mb-3">
  <button class="btn btn-primary" onclick="openSLAForm()">Novo SLA</button>
</div>

<table class="table table-striped table-bordered">
  <thead><tr>
    <th>ID</th><th>Resposta (h)</th><th>Solução (h)</th><th>Prioridade</th><th>Criticidade</th><th>Responsável</th><th>Tipo</th><th>Ações</th>
  </tr></thead>
  <tbody>
    <c:forEach var="s" items="${slas}">
      <tr>
        <td>${s.id}</td>
        <td>${s.tempoResposta}</td>
        <td>${s.tempoSolucao}</td>
        <td>${s.prioridade}</td>
        <td>${s.criticidade}</td>
        <td><c:out value="${s.nomeResponsavel != null ? s.nomeResponsavel : '-'}"/></td>
        <td><c:out value="${s.nomeTipo != null ? s.nomeTipo : '-'}"/></td>
        <td class="table-actions">
          <button class="btn btn-sm btn-outline-secondary btn-edit" data-id="${s.id}"><i class="fa fa-edit"></i></button>
          <button class="btn btn-sm btn-outline-danger btn-delete" data-id="${s.id}"><i class="fa fa-trash"></i></button>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty slas}">
      <tr><td colspan="8" class="text-center">Nenhum SLA cadastrado.</td></tr>
    </c:if>
  </tbody>
</table>

<!-- Modal (form) -->
<div class="modal fade" id="slaModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="slaForm">
        <div class="modal-header"><h5 class="modal-title">SLA</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
        <div class="modal-body">
          <input type="hidden" name="id" id="sla_id">
          <div class="mb-3"><label>Tempo Resposta (h)</label><input type="number" name="tempoResposta" id="tempoResposta" class="form-control" required></div>
          <div class="mb-3"><label>Tempo Solução (h)</label><input type="number" name="tempoSolucao" id="tempoSolucao" class="form-control" required></div>
          <div class="mb-3"><label>Prioridade</label><input name="prioridade" id="prioridade" class="form-control"></div>
          <div class="mb-3"><label>Criticidade</label><input name="criticidade" id="criticidade" class="form-control"></div>

          <div class="mb-3"><label>Responsável</label>
            <select id="idResponsavel" name="idResponsavel" class="form-select">
              <option value="">-- nenhum --</option>
              <c:forEach var="r" items="${responsaveis}">
                <option value="${r.id}">${r.nome}</option>
              </c:forEach>
            </select>
          </div>
          <div class="mb-3"><label>Tipo de Serviço</label>
            <select id="idTipoServico" name="idTipoServico" class="form-select">
              <option value="">-- nenhum --</option>
              <c:forEach var="t" items="${tipos}">
                <option value="${t.id}">${t.nome}</option>
              </c:forEach>
            </select>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="submit" class="btn btn-primary">Salvar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="../includes/footer.jsp" />

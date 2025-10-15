</div> <!-- /container -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  const ctx = '${pageContext.request.contextPath}';
  // abrir modal (formulário SLA)
  function openSLAForm(data) {
    const modal = new bootstrap.Modal(document.getElementById('slaModal'));
    if (data) {
      document.getElementById('sla_id').value = data.id || '';
      document.getElementById('tempoResposta').value = data.tempoResposta || '';
      document.getElementById('tempoSolucao').value = data.tempoSolucao || '';
      document.getElementById('prioridade').value = data.prioridade || '';
      document.getElementById('criticidade').value = data.criticidade || '';
      // selects: select by value if exists
      if (data.idResponsavel) document.getElementById('idResponsavel').value = data.idResponsavel;
      if (data.idTipoServico) document.getElementById('idTipoServico').value = data.idTipoServico;
    } else {
      document.getElementById('slaForm').reset();
      document.getElementById('sla_id').value = '';
    }
    modal.show();
  }

  document.addEventListener('click', function(e) {
    if (e.target.matches('.btn-edit') || e.target.closest('.btn-edit')) {
      const id = e.target.closest('.btn-edit').dataset.id;
      fetch(ctx + '/sla?action=get&id=' + id).then(r => r.json()).then(j => {
        if (j.status === 'ok') {
          const s = j.sla;
          // if controller returned only names, we don't have idResponsavel - that's ok; editing will keep associations simple
          openSLAForm({
            id: s.id,
            tempoResposta: s.tempoResposta,
            tempoSolucao: s.tempoSolucao,
            prioridade: s.prioridade,
            criticidade: s.criticidade
          });
        } else alert('SLA não encontrado');
      });
    } else if (e.target.matches('.btn-delete') || e.target.closest('.btn-delete')) {
      const id = e.target.closest('.btn-delete').dataset.id;
      if (confirm('Excluir SLA #' + id + '?')) {
        fetch(ctx + '/sla', {
          method: 'POST',
          headers: {'Content-Type':'application/x-www-form-urlencoded'},
          body: new URLSearchParams({action:'delete', id: id})
        }).then(r => r.json()).then(j => { if (j.status === 'ok') location.reload(); else alert('Erro'); });
      }
    }
  });

  // form submit handler
  document.addEventListener('submit', function(e) {
    if (e.target && e.target.id === 'slaForm') {
      e.preventDefault();
      const data = new URLSearchParams(new FormData(e.target));
      fetch(ctx + '/sla', { method: 'POST', body: data })
        .then(()=> location.reload())
        .catch(err => alert('Erro: ' + err));
    }
  });
</script>
</body>
</html>

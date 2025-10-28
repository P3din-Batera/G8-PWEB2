package br.edu.ifal.SLAApp.controller;

import dao.SLARepository;
import dao.ResponsavelRepository;
import dao.TipoServicoRepository;
import br.edu.ifal.SLAApp.model.SLA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/sla")
public class SLAController extends HttpServlet {
    private final SLARepository repo = new SLARepository();
    private final ResponsavelRepository rr = new ResponsavelRepository();
    private final TipoServicoRepository tr = new TipoServicoRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if (action == null || action.equals("list")) {
                List<SLA> slas = repo.findAll();
                req.setAttribute("slas", slas);
                req.setAttribute("responsaveis", rr.findAll());
                req.setAttribute("tipos", tr.findAll());
                req.getRequestDispatcher("/WEB-INF/views/sla/list.jsp").forward(req, resp);
                return;
            }
            if ("get".equals(action)) { // retorna JSON para edição via modal
                int id = Integer.parseInt(req.getParameter("id"));
                SLA s = repo.findById(id);
                resp.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    if (s != null) {
                        out.print("{\"status\":\"ok\",\"sla\":{");
                        out.printf("\"id\":%d,", s.getId());
                        out.printf("\"tempoResposta\":%d,", s.getTempoResposta());
                        out.printf("\"tempoSolucao\":%d,", s.getTempoSolucao());
                        out.printf("\"prioridade\":\"%s\",", s.getPrioridade());
                        out.printf("\"criticidade\":\"%s\",", s.getCriticidade());
                        out.printf("\"nomeResponsavel\":\"%s\",", s.getNomeResponsavel() == null ? "" : s.getNomeResponsavel());
                        out.printf("\"nomeTipo\":\"%s\"", s.getNomeTipo() == null ? "" : s.getNomeTipo());
                        out.print("}}");
                    } else out.print("{\"status\":\"not_found\"}");
                }
                return;
            }
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                repo.delete(id);
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().print("{\"status\":\"ok\"}");
                return;
            }

            // ===== INSERIR / ATUALIZAR =====
            String sid = req.getParameter("id");
            SLA s = new SLA();
            s.setTempoResposta(Integer.parseInt(req.getParameter("tempoResposta")));
            s.setTempoSolucao(Integer.parseInt(req.getParameter("tempoSolucao")));
            s.setPrioridade(req.getParameter("prioridade"));
            s.setCriticidade(req.getParameter("criticidade"));

            Integer idResp = null;
            Integer idTipo = null;

            try {
                idResp = Integer.parseInt(req.getParameter("idResponsavel"));
            } catch (Exception ex) {
                idResp = null;
            }

            try {
                idTipo = Integer.parseInt(req.getParameter("idTipoServico"));
            } catch (Exception ex) {
                idTipo = null;
            }

            if (sid == null || sid.isEmpty()) {
                repo.insert(s, idResp, idTipo);
            } else {
                s.setId(Integer.parseInt(sid));
                repo.update(s, idResp, idTipo);
            }

            // ✅ Após salvar, recarrega a lista e volta à tela principal
            List<SLA> slas = repo.findAll();
            req.setAttribute("slas", slas);
            req.setAttribute("responsaveis", rr.findAll());
            req.setAttribute("tipos", tr.findAll());

            req.getRequestDispatcher("/WEB-INF/views/sla/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    
    }
}

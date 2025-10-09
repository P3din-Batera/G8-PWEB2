package br.edu.ifal.SLAApp.controller;

import dao.SLARepository;
import br.edu.ifal.SLAApp.model.SLA;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sla")
public class SLAController extends HttpServlet {
    private SLARepository repo = new SLARepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("list".equals(action) || action == null) {
                List<SLA> slas = repo.findAll();
                req.setAttribute("slas", slas);
                req.getRequestDispatcher("/WEB-INF/views/sla/list.jsp").forward(req, resp);
            } else if ("createForm".equals(action)) {
                req.getRequestDispatcher("/WEB-INF/views/sla/form.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // exemplo de criação
        try {
            int tempoResp = Integer.parseInt(req.getParameter("tempoResposta"));
            int tempoSol = Integer.parseInt(req.getParameter("tempoSolucao"));
            String criticidade = req.getParameter("criticidade");
            String prioridade = req.getParameter("prioridade");

            SLA sla = new SLA(tempoResp, tempoSol, criticidade, prioridade);
            repo.insert(sla);
            resp.sendRedirect(req.getContextPath() + "/sla?action=list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
	}

}

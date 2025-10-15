package br.edu.ifal.SLAApp.controller;

import dao.TipoServicoRepository;
import br.edu.ifal.SLAApp.model.TipoServico;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/tiposervico")
public class TipoServicoController extends HttpServlet {
    private final TipoServicoRepository repo = new TipoServicoRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<TipoServico> lista = repo.findAll();
            req.setAttribute("tipos", lista);
            req.getRequestDispatcher("/WEB-INF/views/tipo_servico/list.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TipoServico t = new TipoServico();
            t.setNome(req.getParameter("nome"));
            t.setDescricao(req.getParameter("descricao"));
            repo.insert(t);
            resp.sendRedirect(req.getContextPath() + "/tiposervico");
        } catch (Exception e) { throw new ServletException(e); }
    }
}

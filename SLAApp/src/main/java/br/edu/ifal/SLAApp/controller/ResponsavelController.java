package br.edu.ifal.SLAApp.controller;

import dao.ResponsavelRepository;
import br.edu.ifal.SLAApp.model.Responsavel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/responsavel")
public class ResponsavelController extends HttpServlet {
    private final ResponsavelRepository repo = new ResponsavelRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if (action == null || action.equals("list")) {
                List<Responsavel> lista = repo.findAll();
                req.setAttribute("responsaveis", lista);
                req.getRequestDispatcher("/WEB-INF/views/responsavel/list.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");
            String cargo = req.getParameter("cargo");
            String contato = req.getParameter("contato");
            Responsavel r = new Responsavel();
            r.setNome(nome); r.setCargo(cargo); r.setContato(contato);
            repo.insert(r);
            resp.sendRedirect(req.getContextPath() + "/responsavel?action=list");
        } catch (Exception e) { throw new ServletException(e); }
    }
}

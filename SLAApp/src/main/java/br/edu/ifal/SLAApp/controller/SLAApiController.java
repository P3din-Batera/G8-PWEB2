package br.edu.ifal.SLAApp.controller;

import dao.SLARepository;
import br.edu.ifal.SLAApp.model.SLA;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/slas/*")
public class SLAApiController extends HttpServlet {

    private final SLARepository repo = new SLARepository();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        try {
            String path = req.getPathInfo();

            // GET /api/sla → lista tudo
            if (path == null || path.equals("/")) {
                List<SLA> slas = repo.findAll();
                resp.getWriter().print(gson.toJson(slas));
                return;
            }

            // GET /api/sla/{id}
            int id = Integer.parseInt(path.substring(1));
            SLA s = repo.findById(id);

            if (s == null) {
                resp.setStatus(404);
                resp.getWriter().print("{\"erro\":\"SLA não encontrado\"}");
                return;
            }

            resp.getWriter().print(gson.toJson(s));

        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().print("{\"erro\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        try {
            BufferedReader reader = req.getReader();
            SLA s = gson.fromJson(reader, SLA.class);
            repo.insert(s, null, null);

            resp.setStatus(201);
            resp.getWriter().print("{\"status\":\"criado\"}");

        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().print("{\"erro\":\"" + e.getMessage() + "\"}");
        }
        
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        try {
            String path = req.getPathInfo();
            int id = Integer.parseInt(path.substring(1));

            BufferedReader reader = req.getReader();
            SLA s = gson.fromJson(reader, SLA.class);
            s.setId(id);

            repo.update(s, null, null);

            resp.getWriter().print("{\"status\":\"atualizado\"}");

        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().print("{\"erro\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        try {
            String path = req.getPathInfo();
            int id = Integer.parseInt(path.substring(1));
            repo.delete(id);

            resp.getWriter().print("{\"status\":\"removido\"}");

        } catch (Exception e) {
            resp.setStatus(400);
            resp.getWriter().print("{\"erro\":\"" + e.getMessage() + "\"}");
        }
        
        
    }
}

package dao;
import br.edu.ifal.SLAApp.model.SLA;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class SLARepository {
    public SLA insert(SLA sla) throws SQLException {
        String sql = "INSERT INTO sla (tempo_resposta, tempo_solucao, criticidade, prioridade) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, sla.getTempoResposta());
            ps.setInt(2, sla.getTempoSolucao());
            ps.setString(3, sla.getCriticidade());
            ps.setString(4, sla.getPrioridade());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) sla.setId(rs.getInt(1));
            }
        }
        return sla;
    }

    public List<SLA> findAll() throws SQLException {
        List<SLA> list = new ArrayList<>();
        String sql = "SELECT id_sla, tempo_resposta, tempo_solucao, criticidade, prioridade FROM sla";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SLA s = new SLA();
                s.setId(rs.getInt("id_sla"));
                s.setTempoResposta(rs.getInt("tempo_resposta"));
                s.setTempoSolucao(rs.getInt("tempo_solucao"));
                s.setCriticidade(rs.getString("criticidade"));
                s.setPrioridade(rs.getString("prioridade"));
                list.add(s);
            }
        }
        return list;
    }

}
package dao;

import br.edu.ifal.SLAApp.model.SLA;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SLARepository {

    public SLA insert(SLA s, Integer idResponsavel, Integer idTipo) throws SQLException {
        String sql = "INSERT INTO sla (tempo_resposta, tempo_solucao, criticidade, prioridade) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getTempoResposta());
            ps.setInt(2, s.getTempoSolucao());
            ps.setString(3, s.getCriticidade());
            ps.setString(4, s.getPrioridade());
            ps.executeUpdate();
            int newId;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                newId = rs.getInt(1);
            }
            // inserir associacoes
            if (idResponsavel != null && idResponsavel > 0) {
                try (PreparedStatement p2 = c.prepareStatement("INSERT INTO sla_responsavel (id_sla, id_responsavel) VALUES (?,?)")) {
                    p2.setInt(1, newId); p2.setInt(2, idResponsavel); p2.executeUpdate();
                }
            }
            if (idTipo != null && idTipo > 0) {
                try (PreparedStatement p3 = c.prepareStatement("INSERT INTO sla_tipo_servico (id_sla, id_servico) VALUES (?,?)")) {
                    p3.setInt(1, newId); p3.setInt(2, idTipo); p3.executeUpdate();
                }
            }
            s.setId(newId);
            return s;
        }
    }

    public void update(SLA s, Integer idResponsavel, Integer idTipo) throws SQLException {
        String sql = "UPDATE sla SET tempo_resposta=?, tempo_solucao=?, criticidade=?, prioridade=? WHERE id_sla=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getTempoResposta());
            ps.setInt(2, s.getTempoSolucao());
            ps.setString(3, s.getCriticidade());
            ps.setString(4, s.getPrioridade());
            ps.setInt(5, s.getId());
            ps.executeUpdate();
            // atualizar associacoes: excluir e inserir (simples)
            try (PreparedStatement pdel1 = c.prepareStatement("DELETE FROM sla_responsavel WHERE id_sla=?")) {
                pdel1.setInt(1, s.getId()); pdel1.executeUpdate();
            }
            try (PreparedStatement pdel2 = c.prepareStatement("DELETE FROM sla_tipo_servico WHERE id_sla=?")) {
                pdel2.setInt(1, s.getId()); pdel2.executeUpdate();
            }
            if (idResponsavel != null && idResponsavel > 0) {
                try (PreparedStatement p2 = c.prepareStatement("INSERT INTO sla_responsavel (id_sla, id_responsavel) VALUES (?,?)")) {
                    p2.setInt(1, s.getId()); p2.setInt(2, idResponsavel); p2.executeUpdate();
                }
            }
            if (idTipo != null && idTipo > 0) {
                try (PreparedStatement p3 = c.prepareStatement("INSERT INTO sla_tipo_servico (id_sla, id_servico) VALUES (?,?)")) {
                    p3.setInt(1, s.getId()); p3.setInt(2, idTipo); p3.executeUpdate();
                }
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM sla WHERE id_sla = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        }
    }

    public SLA findById(int id) throws SQLException {
        String sql = """
            SELECT s.id_sla, s.tempo_resposta, s.tempo_solucao, s.criticidade, s.prioridade,
                   r.nome AS nome_responsavel, t.nome AS nome_tipo
            FROM sla s
            LEFT JOIN sla_responsavel sr ON s.id_sla = sr.id_sla
            LEFT JOIN responsavel r ON sr.id_responsavel = r.id_responsavel
            LEFT JOIN sla_tipo_servico st ON s.id_sla = st.id_sla
            LEFT JOIN tipo_servico t ON st.id_servico = t.id_servico
            WHERE s.id_sla = ?
        """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SLA s = new SLA();
                    s.setId(rs.getInt("id_sla"));
                    s.setTempoResposta(rs.getInt("tempo_resposta"));
                    s.setTempoSolucao(rs.getInt("tempo_solucao"));
                    s.setCriticidade(rs.getString("criticidade"));
                    s.setPrioridade(rs.getString("prioridade"));
                    s.setNomeResponsavel(rs.getString("nome_responsavel"));
                    s.setNomeTipo(rs.getString("nome_tipo"));
                    return s;
                }
            }
        }
        return null;
    }

    public List<SLA> findAll() throws SQLException {
        List<SLA> list = new ArrayList<>();
        String sql = """
            SELECT s.id_sla, s.tempo_resposta, s.tempo_solucao, s.criticidade, s.prioridade,
                   r.nome AS nome_responsavel, t.nome AS nome_tipo
            FROM sla s
            LEFT JOIN sla_responsavel sr ON s.id_sla = sr.id_sla
            LEFT JOIN responsavel r ON sr.id_responsavel = r.id_responsavel
            LEFT JOIN sla_tipo_servico st ON s.id_sla = st.id_sla
            LEFT JOIN tipo_servico t ON st.id_servico = t.id_servico
            ORDER BY s.id_sla DESC
        """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SLA s = new SLA();
                s.setId(rs.getInt("id_sla"));
                s.setTempoResposta(rs.getInt("tempo_resposta"));
                s.setTempoSolucao(rs.getInt("tempo_solucao"));
                s.setCriticidade(rs.getString("criticidade"));
                s.setPrioridade(rs.getString("prioridade"));
                s.setNomeResponsavel(rs.getString("nome_responsavel"));
                s.setNomeTipo(rs.getString("nome_tipo"));
                list.add(s);
            }
        }
        return list;
    }
}
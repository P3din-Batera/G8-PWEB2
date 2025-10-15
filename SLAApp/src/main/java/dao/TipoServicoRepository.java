package dao;

import br.edu.ifal.SLAApp.model.TipoServico;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoServicoRepository {
    public List<TipoServico> findAll() throws SQLException {
        List<TipoServico> list = new ArrayList<>();
        String sql = "SELECT * FROM tipo_servico ORDER BY id_servico DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TipoServico t = new TipoServico();
                t.setId(rs.getInt("id_servico"));
                t.setNome(rs.getString("nome"));
                t.setDescricao(rs.getString("descricao"));
                list.add(t);
            }
        }
        return list;
    }

    public int insert(TipoServico t) throws SQLException {
        String sql = "INSERT INTO tipo_servico (nome, descricao) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getNome());
            ps.setString(2, t.getDescricao());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}

package dao;

import br.edu.ifal.SLAApp.model.Responsavel;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelRepository {
    public List<Responsavel> findAll() throws SQLException {
        List<Responsavel> list = new ArrayList<>();
        String sql = "SELECT * FROM responsavel ORDER BY id_responsavel DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Responsavel r = new Responsavel();
                r.setId(rs.getInt("id_responsavel"));
                r.setNome(rs.getString("nome"));
                r.setCargo(rs.getString("cargo"));
                r.setContato(rs.getString("contato"));
                list.add(r);
            }
        }
        return list;
    }

    public int insert(Responsavel r) throws SQLException {
        String sql = "INSERT INTO responsavel (nome, cargo, contato) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getNome());
            ps.setString(2, r.getCargo());
            ps.setString(3, r.getContato());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}

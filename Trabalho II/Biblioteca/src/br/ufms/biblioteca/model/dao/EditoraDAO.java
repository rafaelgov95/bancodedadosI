/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Editora;
import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.Municipio;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.enumerate.TipoNacionalidade;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class EditoraDAO extends ReadWriteDAO<Editora, Integer> {

    public EditoraDAO() {
        super(Editora.class);
    }

    @Override
    protected void insert(Connection conn, Editora bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.editoras (nome, cidade) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getCidade());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }

        }
    }

    @Override
    protected void update(Connection conn, Editora bean) throws SQLException {
        final String sql = "UPDATE Biblioteca.editoras SET nome = ?, cidade = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getCidade());
            ps.setInt(3, bean.getCodigo());

            ps.executeUpdate();

        }
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        final String sql = "DELETE FROM Biblioteca.autores WHERE id = ?";
        Autor autor = new Autor();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.execute();
        }
        conn.commit();
    }

    @Override
    protected Editora get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.editoras WHERE id = ?";
        Editora editora = new Editora();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(editora, rs.getInt("id"));
                    editora.setNome(rs.getString("nome"));
                    editora.setCidade(rs.getString("cidade"));
                }
            }
        }
        return editora;
    }
      protected Editora getFindName(Connection conn, String codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.autores WHERE name = ?";
        Editora editora = new Editora();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(editora, rs.getInt("id"));
                    editora.setNome(rs.getString("nome"));
                    editora.setCidade(rs.getString("cidade"));
                }
            }
        }
        return editora;
    }

    @Override
    protected List<Editora> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.editoras ";
        List<Editora> editoras = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Editora editora = new Editora();
                    setGeneratedKey(editora, rs.getInt("id"));
                    editora.setNome(rs.getString("nome"));
                    editora.setCidade(rs.getString("cidade"));
                    editoras.add(editora);
                }
            }
        }
        return editoras;
    }

}

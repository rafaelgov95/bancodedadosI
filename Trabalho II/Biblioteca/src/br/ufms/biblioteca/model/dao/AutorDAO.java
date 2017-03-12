/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
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
public class AutorDAO extends ReadWriteDAO<Autor, Integer> {

    public AutorDAO() {
        super(Autor.class);
    }

    @Override
    protected void insert(Connection conn, Autor bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.autores (nome, nacionalidade) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getNacionalidade().toString());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }

        }
    }

    @Override
    protected void update(Connection conn, Autor bean) throws SQLException {
        final String sql = "UPDATE Biblioteca.autores SET nome = ?, nacionalidade = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getNacionalidade().toString());
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

    }

    @Override
    protected Autor get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.autores WHERE id = ?";
        Autor autor = new Autor();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(autor, rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    autor.setNacionalidade(TipoNacionalidade.valueOf(rs.getString("nacionalidade").toUpperCase()));
                }
            }
        }
        return autor;
    }

    @Override
    protected List<Autor> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.autores ";
        List<Autor> autores = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Autor autor = new Autor();
                    setGeneratedKey(autor, rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    autor.setNacionalidade(TipoNacionalidade.valueOf(rs.getString("nacionalidade").toUpperCase()));
                    autores.add(autor);
                }
            }
        }
        return autores;
    }

    @Override
    protected Autor get(Connection conn, String codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.autores WHERE nome = ?";
        Autor autor = new Autor();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(autor, rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    autor.setNacionalidade(TipoNacionalidade.valueOf(rs.getString("nacionalidade").toUpperCase()));
                }
            }
        }
        return autor;
    }

    protected List<Autor> getAllLivro(Connection conn, int codigo) throws SQLException {
        final String sql = "SELECT * FROM livros_has_autores where id_livro=?";
        List<Autor> autores = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    autores.add(DAOFactory.getInstance().getAutorDAO().get(rs.getInt("id_autor")));
                }
            }
        }
        return autores;
    }
}

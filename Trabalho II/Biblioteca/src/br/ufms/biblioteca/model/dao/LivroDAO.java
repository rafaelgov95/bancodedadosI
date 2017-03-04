/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Editora;
import br.ufms.biblioteca.model.bean.Emprestimo;
import br.ufms.biblioteca.model.bean.Endereco;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoIdioma;
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
public class LivroDAO extends ReadWriteDAO<Livro, Integer> {

    public LivroDAO() {
        super(Livro.class);
    }

    @Override
    protected void insert(Connection conn, Livro bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.livros(nome,isbn, edicao,classificacao,idioma,ano_publi,id_editora) VALUES (?, ?,?, ?, ?, ?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, bean.getNome());
            ps.setInt(2, bean.getIsbn());
            ps.setShort(3, bean.getEdicao());
            ps.setString(4, bean.getClassificacao() != null ? bean.getClassificacao().toString() : null);
            ps.setObject(5, bean.getIdioma() != null ? bean.getIdioma().toString() : null);
            ps.setDate(6, Date.valueOf(bean.getAno_publicacao()));
            ps.setInt(7, 1);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }

            for (Autor autor : bean.getAutores()) {
                addHasAutor(conn, autor, bean.getCodigo(), 1);
            }
            conn.commit();
        }
    }

    protected void addHasAutor(Connection conn, Autor bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.livros_has_autores(id_livro,id_autor,id_livro_editora) VALUES (?,?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.NO_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setObject(1, dependencies[0]);
            ps.setObject(2, bean.getCodigo());
            System.out.println(bean.getCodigo());
            ps.setObject(3, dependencies[1]);
            ps.executeUpdate();
        }
    }

    @Override
    protected void update(Connection conn, Livro bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        final String sql = "DELETE FROM Biblioteca.livros WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.execute();
        }
    }

    @Override
    protected Livro get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.livros WHERE id = ?";
        Livro livro = new Livro();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(livro, rs.getInt("id"));
                    livro.setNome(rs.getString("nome"));
                    livro.setIsbn(rs.getInt("isbn"));
                    livro.setEdicao(rs.getShort("edicao"));
                    System.out.println(rs.getString("idioma"));
                    System.out.println(TipoIdioma.setIdioma(rs.getString("idioma")));
                    livro.setIdioma(TipoIdioma.setIdioma(rs.getString("idioma")));
                    livro.setClassificacao(TipoClassificacao.valueOf(rs.getString("classificacao")));
                    livro.setAno_publicacao(rs.getDate("ano_publi").toLocalDate());
                    livro.setEditora((Editora) DAOFactory.getInstance().getEditoraDAO().get(rs.getInt("id_editora")));
                }
            }
        }
        return livro;
    }

    @Override
    protected List<Livro> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.editoras ";
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro();
                    setGeneratedKey(livro, rs.getInt("id"));
                    livro.setNome(rs.getString("nome"));
                    livro.setIsbn(rs.getInt("isbn"));
                    livro.setEdicao(rs.getShort("edicao"));
                    livro.setIdioma(TipoIdioma.valueOf(rs.getString("idioma")));
                    livro.setClassificacao(TipoClassificacao.valueOf(rs.getString("classificacao")));
                    livro.setAno_publicacao(rs.getDate("ano_publi").toLocalDate());
                    livro.setEditora((Editora) DAOFactory.getInstance().getEditoraDAO().get(rs.getInt("id_editora")));
                    livros.add(livro);
                }

            }
        }

        return livros;
    }

}

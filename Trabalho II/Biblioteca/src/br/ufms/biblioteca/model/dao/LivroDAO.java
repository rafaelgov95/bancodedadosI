/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Emprestimo;
import br.ufms.biblioteca.model.bean.Endereco;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    protected void AddLivro(Connection conn, Emprestimo bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.emprestimos (id_usuario,id_livro,data_empretimo,is_ativo) VALUES (?, ?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getCodigo());
            ps.setInt(2, (int) dependencies[0]);
            ps.setInt(3, (int) dependencies[1]);
//            ps.setData(4,bean)
            ps.executeUpdate();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Livro get(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Livro> getAll(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

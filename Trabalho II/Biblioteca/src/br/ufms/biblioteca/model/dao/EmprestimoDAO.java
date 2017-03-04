/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Emprestimo;
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
import java.util.List;

/**
 *
 * @author rafael
 */
public class EmprestimoDAO extends ReadWriteDAO<Emprestimo, Integer> {

    public EmprestimoDAO() {
        super(Emprestimo.class);
    }

    @Override
    protected void insert(Connection conn, Emprestimo bean, Serializable... dependencies) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.emprestimos (is_ativo,id_usuario,id_livro) VALUES ( ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, bean.isAtiva());
            ps.setInt(2, bean.getUsuario().getCodigo());
            ps.setInt(3, bean.getLivro().getCodigo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }
        }

    }

    @Override
    protected void update(Connection conn, Emprestimo bean) throws SQLException {
//        final String sql = "update Biblioteca.telefones set is_ativa = ?,numero = ?,tipo=?,principal = ? where id = ?";
//        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            ps.setBoolean(1, true);
//            ps.setInt(2, bean.getUsuario().getCodigo());
//            ps.setInt(3, bean.getLivro().getCodigo());
//            ps.executeUpdate();
//
//        }
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Emprestimo get(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Emprestimo> getAll(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

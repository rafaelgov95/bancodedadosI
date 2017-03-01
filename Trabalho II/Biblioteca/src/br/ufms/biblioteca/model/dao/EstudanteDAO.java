/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Endereco;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.daolib.Bean;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class EstudanteDAO extends UsuarioDAO<Estudante> {

    public EstudanteDAO() {
        super(Estudante.class);
    }

    @Override
    protected void insertAbst(Connection conn, Estudante bean) throws SQLException {
        System.out.println("Chego aqui");
        final String sql = "INSERT INTO Biblioteca.estudantes (id, rga) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getCodigo());
            ps.setString(2, bean.getRga());
            ps.executeUpdate();
        }
        System.out.println("Nome" + bean.getNome());

    }

    

    @Override
    protected void updateAbst(Connection conn, Estudante bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Estudante resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Estudante get(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String sqlToGet(Long codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String sqlToGetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

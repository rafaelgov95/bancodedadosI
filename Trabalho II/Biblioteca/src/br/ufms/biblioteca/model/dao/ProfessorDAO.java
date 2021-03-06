/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ProfessorDAO extends UsuarioDAO<Professor> {

    public ProfessorDAO() {
        super(Professor.class);
    }

    @Override
    protected void insertAbst(Connection conn, Professor bean) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.professores (id, siap,is_substituto) VALUES ( ?,?,?)";
        System.out.println("sechego aqui fudeu");
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bean.getCodigo());
            ps.setInt(2, bean.getSiap());
            ps.setBoolean(3, bean.isIs_substituto());

            ps.executeUpdate();
        }
    }

    @Override
    protected void updateAbst(Connection conn, Professor bean) throws SQLException {
        final String sql = "UPDATE Biblioteca.professores SET siap = ?,is_substituto=? WHERE id = ?";
        
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bean.getSiap());
            ps.setBoolean(2, bean.isIs_substituto());

            ps.setLong(3, bean.getCodigo());
            ps.executeUpdate();

        }
        System.out.println("Update Professor");
    }

    @Override
    protected Professor resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        Professor p = new Professor();
        populateBean(p, conn, rs);
        setGeneratedKey(p, rs.getInt("id"));
        return p;
    }

 

}

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
import br.ufms.biblioteca.model.bean.enumerate.TipoCurso;
import br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao;
import br.ufms.biblioteca.model.daolib.Bean;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     final String sql = "UPDATE Biblioteca.estudantes SET rga = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bean.getRga());
            ps.setLong(2, bean.getCodigo());
            ps.executeUpdate();

        }
    }

//    @Override
//    protected Estudante resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
//        Estudante estudante = new Estudante();
//
//        setGeneratedKey(estudante, rs.getInt("id"));
//        
////        estudante.setNome(DAOFactory.getInstance().getUsuarioDAO().get(conn, Integer.SIZE));
////        estudante.setRga(rs.getString("rga"));
////        estudante.setCurso(TipoCurso.setCurso(rs.getString("curso")));
////        estudante.setRga(rs.getString("rga"));
////        estudante.setTitulacao(TipoTitulacao.setTitulacao(rs.getString("titulacao")));
//        return estudante;
//    }
//    @Override
//    protected String sqlToGet() {
//            return "SELECT * FROM Biblioteca.estudantes where id = ?";
//
//    }


    @Override
    protected Estudante resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

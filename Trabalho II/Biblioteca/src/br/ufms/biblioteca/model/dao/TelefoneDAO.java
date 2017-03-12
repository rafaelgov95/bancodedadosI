/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.enumerate.TipoTelefone;

/**
 *
 * @author rafael
 */
public class TelefoneDAO extends ReadWriteDAO<Telefone, Integer> {

    public TelefoneDAO() {
        super(Telefone.class);
    }

    @Override
    protected void insert(Connection conn, Telefone bean, Serializable... dependencies) throws SQLException {
        final String sql = "insert into Biblioteca.telefones (ddd,numero,tipo,is_principal,id_usuario) values (?,?,?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
  
            ps.setString(1, bean.getDDD());
            ps.setString(2, bean.getNumero());
            ps.setString(3, bean.getTipo().toString());
            ps.setBoolean(4, bean.getPrincipal());
            ps.setInt(5, (int) dependencies[0]);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }
        }
    }

    @Override
    protected void update(Connection conn, Telefone bean) throws SQLException {
        final String sql = "update Biblioteca.telefones set ddd = ?,numero = ?,tipo=?,principal = ? where id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bean.getDDD());
            ps.setString(2, bean.getNumero());
            ps.setString(3, bean.getTipo().toString());
            ps.setBoolean(4, bean.getPrincipal());
            ps.setInt(5, bean.getCodigo());
            ps.executeUpdate();

        }

    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        final String sql = "DELETE FROM Biblioteca.telefones WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    protected Telefone get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.telefones WHERE id = ?";
        Telefone telefone = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    telefone = resultSetToBean(conn, rs);
                }
            }
        }
        return telefone;
    }

    @Override
    protected List<Telefone> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.telefones";
        List<Telefone> telefones = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    telefones.add(resultSetToBean(conn, rs));
                }
            }
        }
        return telefones;
    }

    public List<Telefone> findByID(Connection conn, Integer contatoID) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.telefones WHERE id = ?";
        List<Telefone> telefones = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, contatoID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    telefones.add(resultSetToBean(conn, rs));
                }
            }
        }
        return telefones;
    }

    private Telefone resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        Telefone telefone = new Telefone();
        setGeneratedKey(telefone, rs.getInt(1));
        telefone.setDDD(rs.getString("ddd"));
        telefone.setNumero(rs.getString("numero"));
        telefone.setTipo(TipoTelefone.valueOf(rs.getString("tipo")));
        telefone.setPrincipal(rs.getBoolean("principal"));
        return telefone;
    }

    @Override
    protected Telefone get(Connection conn, String codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

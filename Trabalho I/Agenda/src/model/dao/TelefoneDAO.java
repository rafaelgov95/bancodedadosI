/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Contato;
import model.bean.Telefone;
import model.bean.TipoTelefone;

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
        final String sql = "insert into Agenda.Telefones (ddd,numero,tipo,principal,contato_codigo) values (?,?,?,?,?)";

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
        final String sql = "update Agenda.Telefones set ddd = ?,numero = ?,tipo=?,principal = ? where codigo = ?";

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
        final String sql = "DELETE FROM Agenda.Telefones WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    protected Telefone get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Telefones WHERE codigo = ?";
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
        final String sql = "SELECT * FROM Agenda.Telefones";
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
        final String sql = "SELECT * FROM Agenda.Telefones WHERE contato_codigo = ?";
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
}

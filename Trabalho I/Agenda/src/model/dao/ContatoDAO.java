/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.bean.Contato;
import model.bean.Telefone;
import model.bean.TipoTelefone;

/**
 *
 * @author rafael
 */
public class ContatoDAO extends ReadWriteDAO<Contato, Integer> {

    public ContatoDAO() {
        super(Contato.class);
    }

    @Override
    protected void insert(Connection conn, Contato bean, Serializable... dependencies) throws SQLException {
        final String sql = "insert into Agenda.Contatos (nome,data_nascimento) values (?,?)";
        conn.setAutoCommit(false);
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bean.getNome());
            ps.setObject(2, bean.getData_nascimento() != null ? Date.valueOf(bean.getData_nascimento()) : null);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                    TelefoneDAO dao = DAOFactory.getInstance().getTelefoneDAO();
                    for (Telefone tel : bean.getListaTelefones()) {
                        dao.insert(conn, tel, bean.getCodigo());
                    }
                    conn.commit();
                }
            }
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
    }

    @Override
    protected void update(Connection conn, Contato bean) throws SQLException {
        final String sql = "update Agenda.Contatos set nome = ? , data_nascimento = ? where codigo = ?";
        conn.setAutoCommit(false);
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bean.getNome());
            ps.setObject(2, bean.getData_nascimento() != null ? Date.valueOf(bean.getData_nascimento()) : null);
            ps.setInt(3, bean.getCodigo());
            ps.executeUpdate();
            TelefoneDAO dao = DAOFactory.getInstance().getTelefoneDAO();
            for (Telefone t : dao.getAll(conn)) {
                dao.save(t, bean.getCodigo());
            }
            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        final String sql = "DELETE FROM Agenda.Contatos WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        }
    }

    @Override
    protected Contato get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos WHERE codigo = ?";
        Contato contato = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    contato = resultSetToBean(conn, rs);
                }
            }
        }
        return contato;
    }

    @Override
    protected List<Contato> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos";
        List<Contato> contatos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    contatos.add(resultSetToBean(conn, rs));
                }
            }
        }
        return contatos;
    }

    private Contato resultSetToBean(Connection conn, ResultSet rs) throws SQLException {

        TelefoneDAO dao = DAOFactory.getInstance().getTelefoneDAO();
        Contato contato = new Contato();

        setGeneratedKey(contato, rs.getInt(1));
        contato.setNome(rs.getString("nome"));
        contato.setData_nascimento(rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null);
        List<Telefone> tst = dao.findByContatoID(conn, contato.getCodigo());
        for (int i = 0; i < tst.size(); i++) {
            contato.getListaTelefones().add(tst.get(i));
            tst.remove(i);
        }

        return contato;
    }
}

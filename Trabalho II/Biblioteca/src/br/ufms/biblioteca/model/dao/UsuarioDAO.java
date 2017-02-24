/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.TipoTelefone;

/**
 *
 * @author rafael
 */
public class UsuarioDAO extends ReadWriteDAO<Usuario, Integer> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    protected void insert(Connection conn, Usuario bean, Serializable... dependencies) throws SQLException {
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
                    for (Telefone tel : bean.getTelefones()) {
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
    protected void update(Connection conn, Usuario bean) throws SQLException {
        final String sql = "update Agenda.Contatos set nome = ? , data_nascimento = ? where codigo = ?";
        conn.setAutoCommit(false);
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bean.getNome());
            ps.setObject(2, bean.getData_nascimento() != null ? Date.valueOf(bean.getData_nascimento()) : null);
            ps.setInt(3, bean.getCodigo());
            ps.executeUpdate();
            TelefoneDAO dao = DAOFactory.getInstance().getTelefoneDAO();
            for (Telefone t : bean.getTelefones()) {
                dao.save(conn,t, bean.getCodigo());
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
    protected Usuario get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos WHERE codigo = ?";
        Usuario contato = null;
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
    protected List<Usuario> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos";
        List<Usuario> contatos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    contatos.add(resultSetToBean(conn, rs));
                }
            }
        }
        return contatos;
    }

    public Usuario findByID(Connection conn, Integer contatoCodigo) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos WHERE contato_codigo = ?";
        Usuario c = new Usuario();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, contatoCodigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.first()) {
                    c = resultSetToBean(conn, rs);
                }
            }
        }
        return c;
    }

    public List<Usuario> findByNumberTelefone(Connection conn, Integer number) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Telefones WHERE numero = ?";
        List<Usuario> contatos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuarioDAO cDao = DAOFactory.getInstance().getContatoDAO();
                    contatos.add(findByID(conn, rs.getInt("contatos_codigo")));
                }
            }
        }
        return contatos;
    }

    public List<Usuario> findByName(Connection conn, String nome) throws SQLException {
        final String sql = "SELECT * FROM Agenda.Contatos WHERE nome = ?";
        List<Usuario> ListaContato = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ListaContato.add(resultSetToBean(conn, rs));
                }
            }
        }
        return ListaContato;
    }

    private Usuario resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
        TelefoneDAO dao = DAOFactory.getInstance().getTelefoneDAO();
        Usuario contato = new Usuario();
        setGeneratedKey(contato, rs.getInt(1));
        contato.setNome(rs.getString("nome"));
        contato.setData_nascimento(rs.getDate("data_nascimento") != null ? rs.getDate("data_nascimento").toLocalDate() : null);
        contato.setData_at(rs.getDate("data_at").toLocalDate());
        contato.getTelefones().addAll(dao.findByID(conn, contato.getCodigo()));
        return contato;
    }

}

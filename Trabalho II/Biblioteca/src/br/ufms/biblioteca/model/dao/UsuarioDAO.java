/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Endereco;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.daolib.Bean;
import java.time.LocalDate;

/**
 *
 * @author rafael
 * @param <B>
 */
public abstract class UsuarioDAO<B extends Usuario> extends ReadWriteDAO<B, Integer> {

    protected UsuarioDAO(Class<B> clazz) {
        super(clazz);
    }

    public void insertUsuario(Connection conn, B bean) throws SQLException {
        final String sql = "INSERT INTO Biblioteca.usuarios (nome, curso, cpf,titulacao,data_fim_contrato,data_nascimento) VALUES (?, ?,?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, bean.getNome());
            ps.setString(2, String.valueOf(bean.getCurso()));
            ps.setString(3, bean.getCpf());
            ps.setObject(4, bean.getTitulacao() != null ? bean.getTitulacao().toString() : null);
            ps.setDate(5, Date.valueOf(bean.getFim_contrato()));
            ps.setDate(6, Date.valueOf(bean.getData_nascimento()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.first()) {
                    setGeneratedKey(bean, rs.getInt(1));
                }
            }
        }
    }

    /**
     * Atualiza os dados do elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    private void updateUsuario(Connection conn, B bean) throws SQLException {
        final String sql = "UPDATE Biblioteca.usuarios SET nome = ?, curso = ?, cpf = ?, titulacao = ?, "
                + "data_fim_contrato=?,data_nascimento=? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getCurso().toString());
            ps.setString(3, bean.getCpf());
            ps.setString(4, bean.getTitulacao().toString());
            ps.setDate(5, Date.valueOf(bean.getFim_contrato()));
            ps.setDate(6, Date.valueOf(bean.getData_nascimento()));
            ps.setLong(7, bean.getCodigo());
            ps.executeUpdate();
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveUsuario(Connection conn, B bean) throws SQLException {
        if (bean.getCodigo() == null) {
            insertUsuario(conn, bean);
        } else {
            updateUsuario(conn, bean);
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveEndereco(Connection conn, B bean) throws SQLException {

        if (bean.getTelefones().size() > 0) {
            for (Endereco endereco : bean.getEnderecos()) {
                DAOFactory.getInstance().getEnderecoDAO().save(conn, endereco, bean.getCodigo());
            }
        }
    }

    /**
     * Salva (insere ou atualiza) o elemento na tabela usuario.
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    protected void saveTelefones(Connection conn, B bean) throws SQLException {
        TelefoneDAO telefoneDAO = getDAOFactory().getTelefoneDAO();
        if (bean.getTelefones().size() > 0) {
            for (Telefone telefone : bean.getTelefones()) {
                telefoneDAO.save(conn, telefone, bean.getCodigo());
            }
        }
    }

    protected void updateEstudante(Connection conn, B bean) throws SQLException {
        DAOFactory.getInstance().getEstudanteDAO().updateAbst(conn, (Estudante) bean);

    }

    protected void updateProfessor(Connection conn, B bean) throws SQLException {
        DAOFactory.getInstance().getProfessorDAO().updateAbst(conn, (Professor) bean);

    }

    protected void saveEstudante(Connection conn, B bean) throws SQLException {
        DAOFactory.getInstance().getEstudanteDAO().insertAbst(conn, (Estudante) bean);

    }

    protected void saveProfessor(Connection conn, B bean) throws SQLException {
        DAOFactory.getInstance().getProfessorDAO().insertAbst(conn, (Professor) bean);

    }

    /**
     * Insere o objeto bean na tabela "usuario" do banco de dados. Caso os
     * campos endereço e telefones tenham algum valor, insere-os também em suas
     * respectivas tabelas.
     *
     * @param conn
     * @param bean
     * @param dependencies
     * @throws SQLException
     */
    @Override
    protected void insert(Connection conn, B bean, Serializable... dependencies) throws SQLException {
        conn.setAutoCommit(false);
        try {
            insertUsuario(conn, bean);
            saveEndereco(conn, bean);
            saveTelefones(conn, bean);
            if (bean instanceof Estudante) {
                saveEstudante(conn, bean);
            } else if (bean instanceof Professor) {
                saveProfessor(conn, bean);
            }

            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     *
     * @param conn
     * @param bean
     * @throws SQLException
     */
    @Override
    protected void update(Connection conn, B bean) throws SQLException {
        conn.setAutoCommit(false);
        try {
            updateUsuario(conn, bean);
            saveEndereco(conn, bean);
            saveTelefones(conn, bean);
            if (bean instanceof Estudante) {
                updateEstudante(conn, bean);
            } else if (bean instanceof Professor) {
                updateProfessor(conn, bean);
            }

//            updateAbst(conn, bean);
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

//    @Override
    protected void delete(Connection conn, Long codigo) throws SQLException {
        final String sql = "DELETE FROM desafio.usuario WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, codigo);
            ps.executeUpdate();
        }
    }

//    @Override
    protected B get(Connection conn, Long codigo) throws SQLException {
        B bean = null;
        try (PreparedStatement ps = conn.prepareStatement(sqlToGet(codigo))) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    bean = resultSetToBean(conn, rs);
                }
            }
        }
        return bean;
    }

    @Override
    protected List<B> getAll(Connection conn) throws SQLException {
        List<B> beans = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sqlToGetAll())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    beans.add(resultSetToBean(conn, rs));
                }
            }
        }
        return beans;
    }

    protected B populateBean(B bean, Connection conn, ResultSet rs) throws SQLException {
//        final Endereco endereco = getDAOFactory().getEnderecoDAO().findByUsuario(conn, rs.getLong("codigo"));
//        final List<Telefone> telefones = getDAOFactory().getTelefoneDAO().findByUsuario(conn, rs.getLong("codigo"));
//        final Date criacao = rs.getDate("data_criacao");
//        bean.setCodigo(rs.getLong("codigo"));
//        bean.setNome(rs.getString("nome"));
//        bean.setUsuario(rs.getString("usuario"));
//        bean.setSenha(rs.getString("senha"));
//        bean.setEmail(rs.getString("email"));
//        bean.setTelefones(telefones != null ? telefones : new ArrayList<>());
//        bean.setEndereco(endereco != null ? endereco : new Endereco());
//        bean.setCriacao(criacao != null ? criacao.toLocalDate() : null);
        return bean;
    }

    protected abstract void insertAbst(Connection conn, B bean) throws SQLException;

    protected abstract void updateAbst(Connection conn, B bean) throws SQLException;

    protected abstract B resultSetToBean(Connection conn, ResultSet rs) throws SQLException;

    protected abstract String sqlToGet(Long codigo);

    protected abstract String sqlToGetAll();

//    /**
//     * -------------------------------------------------------------------------------------------
//     * Este método usa reflections para instanciar um objeto (derivado de Usuario) B. Como
//     * reflections gera um custo adicional no desempenho, vou deixar este método comentado e usar
//     * outra abordagem.
//     * -------------------------------------------------------------------------------------------
//     */
//    protected B resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
//        B jogador = null;
//        try {
//            return populateBean(getBeanClass().newInstance(), conn, rs);
//        } catch (InstantiationException | IllegalAccessException ex) {
//            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jogador;
//    }
}

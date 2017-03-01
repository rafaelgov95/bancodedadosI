/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Autor;
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
public class AutorDAO extends ReadWriteDAO<Autor, Integer> {

    public AutorDAO() {
        super(Autor.class);
    }

    @Override
    protected void insert(Connection conn, Autor bean, Serializable... dependencies) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void addLivro(Connection conn, Autor bean, Serializable... dependencies) throws SQLException {

    }

    @Override
    protected void update(Connection conn, Autor bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Autor get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.autores WHERE id = ?";
        Autor autor = new Autor();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    setGeneratedKey(autor, rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    autor.setNacionalidade(TipoNacionalidade.BRASILEIRO);
                }
            }
        }
        return autor;
    }

    @Override
    protected List<Autor> getAll(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

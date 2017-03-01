/*
 * Copyright (C) 2016 Kleber Kruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Municipio;
import br.ufms.biblioteca.model.bean.enumerate.UF;
import br.ufms.biblioteca.model.daolib.ReadOnlyDAO;
import br.ufms.biblioteca.model.daolib.ReadWriteDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de leitura na tabela municipio.
 *
 * @author Kleber Kruger
 */
public class MunicipioDAO extends ReadWriteDAO<Municipio, Integer> {

    protected MunicipioDAO() {
        super(Municipio.class);
    }

    /**
     *
     * @param conn
     * @param codigo
     * @return
     * @throws SQLException
     */
    @Override
    protected Municipio get(Connection conn, Integer codigo) throws SQLException {
        final String sql = "SELECT * FROM Biblioteca.municipios WHERE codigo_ibge = ?";
        Municipio municipio = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    municipio = resultSetToBean(rs);
                }
            }
        }
        return municipio;
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    @Override
    protected List<Municipio> getAll(Connection conn) throws SQLException {
        final String sql = "SELECT * FROM desafio.municipio";
        List<Municipio> municipios = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    municipios.add(resultSetToBean(rs));
                }
            }
        }
        return municipios;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Municipio resultSetToBean(ResultSet rs) throws SQLException {
        Municipio municipio = new Municipio();
        setGeneratedKey(municipio, rs.getInt("ibge"));
        municipio.setNome(rs.getString("nome"));
        municipio.setUf(UF.valueOf(rs.getString("uf")));

        return municipio;
    }

    @Override
    protected void insert(Connection conn, Municipio bean, Serializable... dependencies) throws SQLException {
//        final String sql = "INSERT INTO Biblioteca.enderecos (id, rua,numero,complemento, bairro, cep,id_usuario,id_municipio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            final Municipio municipio = bean.getMunicipio();
//            ps.setObject(1, bean.getRua());
//            ps.setObject(2, bean.getNumero());
//            ps.setObject(3, bean.getSemNumero());
//            ps.setObject(4, bean.getComplemento());
//            ps.setObject(5, bean.getBairro());
//            ps.setObject(6, bean.getCEP());
//            ps.setInt(7, (int) dependencies[0]);
//            ps.setObject(8, municipio != null ? municipio.getCodigo() : null);
//            ps.executeUpdate();
//
//            try (ResultSet rs = ps.getGeneratedKeys()) {
//                if (rs.first()) {
//                    setGeneratedKey(bean, rs.getInt(1));
//                }
//            }
//        }
    }

    @Override
    protected void update(Connection conn, Municipio bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Connection conn, Integer codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.bean.Contato;
import model.dao.ContatoDAO;
import model.dao.DAOFactory;
import view.Alertas;
import view.Menus;

/**
 *
 * @author rafael
 */
public class ControllerDaos {

    public void SaveContato(Contato c) throws IOException, SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        cDao.save(c);
    }

    public List<Contato> FindContato(String procurado, Connection conn) throws SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        if (procurado.contains("^[a-Z]")) {
            return cDao.findByNumberTelefone(conn, Integer.parseInt(procurado));
        } else {
            return cDao.findByName(conn, procurado);
        }
    }

    public void DeleteContato(Contato c) throws SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        cDao.delete(c);
    }

    public void PrintContato(Contato c, int index) {
        Menus.ModelContato(c, index + 1);
        for (int i = 0; i < c.getListaTelefones().size(); i++) {
            Menus.ModelTelefone(c.getListaTelefones().get(i), i + 1);
        }

    }

    public void DelContato(Contato c) throws SQLException {
        DeleteContato(c);
        Alertas.ContaRemovidaSucesso();
    }
}

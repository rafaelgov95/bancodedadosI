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

    public List<Contato> ByContato(String procurado, Connection conn) throws SQLException {
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

    public void imprimirAgenda() throws SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        List<Contato> listaContatos = cDao.getAll();

        for (Contato c : listaContatos) {
            imprimirContato(c);
        }
    }

    public void imprimirContato(Contato c) {
        Menus.ModelContato(c);
        for (int i = 0; i < c.getListaTelefones().size(); i++) {
            Menus.ModelTelefone(c.getListaTelefones().get(i));
        }
    }

}

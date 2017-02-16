/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.ufms.agenda.model.bean.Contato;
import br.ufms.agenda.model.dao.ContatoDAO;
import br.ufms.agenda.model.dao.DAOFactory;

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

   

    public void DelContato(Contato c) throws SQLException {
        DeleteContato(c);
        
    }
}

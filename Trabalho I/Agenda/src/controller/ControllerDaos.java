/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.bean.Contato;
import model.bean.Telefone;
import model.dao.ContatoDAO;
import model.dao.DAOFactory;

/**
 *
 * @author rafael
 */
public class ControllerDaos {

    public void CreateContato(Contato c) throws IOException, SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        cDao.save(c);
    }

    public void UpdateContato(Contato c) throws IOException, SQLException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        cDao.update(c);
    }

    public Contato ByContato(String procurado, boolean isNumero) throws SQLException {

        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        List<Contato> listaContatos = cDao.getAll();

        for (Contato c : listaContatos) {
            if (isNumero) {
                for (Telefone t : c.getListaTelefones()) {
                    if (t.getNumero().equals(procurado)) {
                        return c;
                    }
                }
            } else if (c.getNome().equals(procurado)) {
                return c;
            }
        }
        return null;
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
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
        System.out.println("Nome: " + c.getNome());
        System.out.print("Aniversário: ");
        System.out.println(c.getData_nascimento() == null ? "Não cadastrado" : c.getData_nascimento());
        List<Telefone> listaTel = c.getListaTelefones();
        System.out.print("Telefone(s): ");
        System.out.println(listaTel.isEmpty() ? "Não há telefones cadastrados" : "");
        for (int i = 0; i < listaTel.size(); i++) {
            System.out.println(i);
            String check = "✖";
            if (listaTel.get(i).getPrincipal()) {
                check = "✔";
            }
            System.out.println("    Principal: " + check);
            System.out.println("    Número: " + listaTel.get(i).getNumero());
            System.out.println("    Tipo: " + listaTel.get(i).getTipo());
        }
    }

}

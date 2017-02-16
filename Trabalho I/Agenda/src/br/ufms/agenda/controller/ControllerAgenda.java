/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.ufms.agenda.model.bean.Contato;
import br.ufms.agenda.model.dao.ContatoDAO;
import br.ufms.agenda.model.dao.DAOFactory;
import br.ufms.agenda.util.DatabaseManager;
import br.ufms.agenda.view.Alertas;
import br.ufms.agenda.view.Menus;

/**
 *
 * @author rafael
 */
public class ControllerAgenda extends ControllerDaos {

    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
    Connection conn = DatabaseManager.getInstance().getConnection();
    Menus menu = new Menus();

    public ControllerAgenda() throws SQLException, IOException {
        MenuPrincipal();
    }

    private void MenuPrincipal() throws SQLException, IOException {

        while (true) {
            Menus.MenuPrincipal();
            String opcao = ler.readLine();
            switch (opcao) {
                case "1":
                    Contato c = new Contato();
                    SaveContato(menu.CriarContato(c));
                    break;
                case "2":
                    Alertas.InformeNomeNumero();
                    MenuDeAcessado(FindContato(ler.readLine(), conn));

                    break;
                case "3":
                    PrintAgenda();
                    break;
                case "0":
                    System.exit(0);
                default:
                    Alertas.DefaultCase();
                    break;
            }
        }
    }

    private void MenuInterno(Contato c, int numero) throws SQLException, IOException {
        boolean flag = true;
        while (flag) {
            Menus.MenuInterno(numero);
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    SaveContato(menu.CriarContato(c));
                    break;
                case 2:
                    DelContato(c);
                    Alertas.ContaRemovidaSucesso();
                    flag = false;
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }

    private void MenuDeAcessado(List<Contato> c) throws SQLException, IOException {
        Alertas.Espaco();
        if (c.isEmpty()) {
            Alertas.ContatoNaoExiste();
        } else {
            for (int i = 0; i < c.size(); i++) {
                PrintContato(c.get(i), i);
                Alertas.Espaco();
            }
            if (c.size() == 1) {
                MenuInterno(c.get(1-1), 1);
            } else {
                Alertas.InformeNumeroContato();
                int n = Integer.parseInt(ler.readLine());
                if ((n < 1) || ((n) > c.size())) {
                    MenuDeAcessado(c);
                } else {
                    MenuInterno(c.get(n-1), n);
                }
            }
        }
    }

    public void PrintAgenda() throws SQLException, IOException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        List<Contato> listaContatos = cDao.getAll();
        MenuDeAcessado(listaContatos);
    }

    public void PrintContato(Contato c, int index) {
        Menus.ModelContato(c, index + 1);
        for (int i = 0; i < c.getListaTelefones().size(); i++) {
            Menus.ModelTelefone(c.getListaTelefones().get(i), i + 1);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import controller.ControllerDaos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.bean.Contato;
import util.DatabaseManager;
import view.Alertas;
import view.Menus;

/**
 *
 * @author rafael
 */
public class Agenda extends ControllerDaos {

    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
    Connection conn = DatabaseManager.getInstance().getConnection();
    Menus menu = new Menus();

    public Agenda() throws SQLException, IOException {
        inicirAgenda();
    }

    private void inicirAgenda() throws SQLException, IOException {

        while (true) {
            Menus.MenuPrincipal();
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    Contato c = new Contato();
                    SaveContato(menu.CriarContato(c));
                    break;
                case 2:
                    Alertas.InformeNomeNumero();
                    menuAcessado(ByContato(ler.readLine(), conn));
                    break;
                case 3:
                    imprimirAgenda();
                    break;
                case 0:
                    System.exit(0);
                default:
                    Alertas.DefaultCase();
                    break;
            }
        }
    }

    private void menuAcessado(List<Contato> c) throws SQLException, IOException {
        if (c.isEmpty()) {
            Alertas.ContatoNaoExiste();
        } else {
            for (Contato contato : c) {
                imprimirContato(contato);
            }
            System.out.println("Informe o id para abrir Menu");
            menuInterno(c.get(Integer.parseInt(ler.readLine())));
        }
    }

    private void menuInterno(Contato c) throws SQLException, IOException {
        boolean flag = true;
        while (flag) {
            Menus.MenuInterno();
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    SaveContato(menu.CriarContato(c));
                    break;
                case 2:
                    DelContato(c);
                    flag = false;
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }

    private void DelContato(Contato c) throws SQLException {
        DeleteContato(c);
        Alertas.ContaRemovidaSucesso();
    }
}

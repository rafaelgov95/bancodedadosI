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
import br.ufms.agenda.model.bean.Telefone;
import br.ufms.agenda.model.bean.TipoTelefone;
import br.ufms.agenda.model.dao.ContatoDAO;
import br.ufms.agenda.model.dao.DAOFactory;
import br.ufms.agenda.util.DatabaseManager;
import br.ufms.agenda.view.Alertas;
import br.ufms.agenda.view.Menus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                    SaveContato(CriarContato(c));
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
                    MenuUpdate(c);
                    flag = false;
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

    public void SetData(Contato c) throws IOException, IOException {
        String data = ler.readLine();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (!data.equals("")) {
            LocalDate dt = LocalDate.parse(data, f);
            c.setData_nascimento(dt);
        } else {
            c.setData_nascimento(null);
        }
    }

    private void MenuUpdate(Contato c) throws IOException, SQLException {
        boolean flag = true;
        while (flag) {
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    c.setNome(ler.readLine());
                    break;
                case 2:
                    SetData(c);
                    break;
                case 3:
                    //editar data niver

                    break;
                case 0:
                    SaveContato(c);
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
                MenuInterno(c.get(1 - 1), 1);
            } else {
                Alertas.InformeNumeroContato();
                int n = Integer.parseInt(ler.readLine());
                if ((n < 1) || ((n) > c.size())) {
                    MenuDeAcessado(c);
                } else {
                    MenuInterno(c.get(n - 1), n);
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
     public Contato CriarContato(Contato c) throws IOException {
        System.out.print("Nome do Contato: ");
        String nome = ler.readLine();
        System.out.print("Data de nascimento Exemplo:(22-07-1995): ");
        String data = ler.readLine();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (!data.equals("")) {
            LocalDate dt = LocalDate.parse(data, f);
            c.setData_nascimento(dt);
        } else {
            c.setData_nascimento(null);
        }
        c.setNome(nome);
        boolean menut = true;
        while (menut) {
            System.out.println(" ---------- Menu Telefone ---------- \n"
                    + " ---- 1 - Adicionar Telefone -------  \n"
                    + " ---- 0 - Finalizar Contato -------- ");
            String op = ler.readLine();
            switch (op) {
                case "1":
                    c.getListaTelefones().add(criarTelefone());
                    break;
                case "0":
                    menut = false;
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        }
        Alertas.ContaAdicionadoSucesso();
        return c;

    }
     private Telefone criarTelefone() throws IOException {
        Telefone t = new Telefone();
        System.out.println("Digite o DDD do telefone: ");
        t.setDDD(ler.readLine());
        System.out.println("Digite um número de telefone: ");
        t.setNumero(ler.readLine());
        System.out.println("Defina o tipo (Celular, Residencial, Comercial): ");
        t.setTipo(TipoTelefone.valueOf(ler.readLine().toUpperCase()));
        System.out.println("Principal? (s/n) ");
        t.setPrincipal(ler.readLine().equals("s"));;
        return t;
    }

}

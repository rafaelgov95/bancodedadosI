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
                    CriarContato(c);
                    SaveContato(c);
                    break;
                case "2":
                    Alertas.InformeNomeNumero();
                    MenuDeAcessado(FindContato(ler.readLine(), conn));
                    break;
                case "3":
                    MenuDeAcessado(PrintAgenda());
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
                    SaveContato(criarTelefone(c));
                    MenuPrincipal();
                    break;
                case 2:
                    MenuUpdate(c);
                    flag = false;
                    break;
                case 3:
                    DelContato(c);
                    Alertas.ContaRemovidaSucesso();
                    flag = false;
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    Alertas.DefaultCase();
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
        Menus.MenuUpdateContato();
        while (flag) {
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    Alertas.NovoNome();
                    c.setNome(ler.readLine());
                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 2:
                    Alertas.NovaDataN();
                    SetData(c);
                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 3:
                    SelecionarEditarTelefone(c);
                    break;
                case 0:

                    MenuPrincipal();
                    break;
                default:
                    Alertas.DefaultCase();

                    break;
            }
        }
    }

    private void SelecionarEditarTelefone(Contato c) throws IOException, SQLException {
        Alertas.Espaco();
        if (c.getTelefones().isEmpty()) {
            criarTelefone(c);
            SaveContato(c);
        } else if (c.getTelefones().size() == 1) {
            TelEditador(c, 1);
        } else {
            ListarTelefones(c);
            Alertas.NovoNumero();
            int n = Integer.parseInt(ler.readLine());
            if ((n < 1) || ((n) > c.getTelefones().size())) {
                Alertas.NumeroInvalido();
                SelecionarEditarTelefone(c);
            } else {
                TelEditador(c, n);
            }
        }

    }

    private void TelEditador(Contato c, int n) throws IOException, SQLException {
        boolean flag = true;
        Alertas.Espaco();
        Menus.ModelTelefone(c.getTelefones().get(n - 1), n);
        Menus.MenuUpdateTelefone(n);
        while (flag) {
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    Alertas.NovoDDD();
                    c.getTelefones().get(n - 1).setDDD(ler.readLine());
                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 2:

                    Alertas.NovoNome();
                    c.getTelefones().get(n - 1).setNumero(ler.readLine());
                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 3:
                    Alertas.NovoTipo();
                    c.getTelefones().get(n - 1).setTipo(TipoTelefone.valueOf(ler.readLine().toUpperCase()));
                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 4:
                    Alertas.NovoPrincipal();
                    if (ler.readLine().equals("s")) {
                        c.getTelefones().get(n - 1).setPrincipal(true);
                        for (Telefone telefone : c.getTelefones()) {
                            if (!(c.getTelefones().get(n - 1).getCodigo() == telefone.getCodigo())) {
                                telefone.setPrincipal(false);
                            }
                        }
                        c.getTelefones().get(n - 1).setPrincipal(true);
                    } else {
                        c.getTelefones().get(n - 1).setPrincipal(false);
                    }

                    SaveContato(c);
                    MenuPrincipal();
                    break;
                case 0:
                    MenuPrincipal();
                    break;
                default:
                    Alertas.DefaultCase();
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

    public List<Contato> PrintAgenda() throws SQLException, IOException {
        ContatoDAO cDao = DAOFactory.getInstance().getContatoDAO();
        List<Contato> listaContatos = cDao.getAll();
        return listaContatos;
    }

    public void ListarTelefones(Contato c) {

        for (int i = 0; i < c.getTelefones().size(); i++) {
            Menus.ModelTelefone(c.getTelefones().get(i), i + 1);
        }

    }

    public void PrintContato(Contato c, int index) {
        Menus.ModelContato(c, index + 1);
        ListarTelefones(c);

    }

    public Contato CriarContato(Contato c) throws IOException {
        Alertas.NomeContato();
        String nome = ler.readLine();
        Alertas.Data();
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
            Menus.MenuAddTel();
            String op = ler.readLine();
            switch (op) {
                case "1":
                    criarTelefone(c);
                    break;
                case "0":
                    menut = false;
                    break;
                default:
                    Alertas.DefaultCase();
            }

        }
        Alertas.ContaAdicionadoSucesso();
        return c;

    }

    private Contato criarTelefone(Contato c) throws IOException {
        Telefone t = new Telefone();
        Alertas.DDD();
        t.setDDD(ler.readLine());
        Alertas.NumeroTel();
        t.setNumero(ler.readLine());
        Alertas.Tipo();
        t.setTipo(TipoTelefone.valueOf(ler.readLine().toUpperCase()));
        Alertas.Principal();
        if (ler.readLine().equals("s")) {
            for (Telefone telefone : c.getTelefones()) {
                telefone.setPrincipal(false);
            }
            t.setPrincipal(true);

        }
        c.getTelefones().add(t);
        return c;
    }

}

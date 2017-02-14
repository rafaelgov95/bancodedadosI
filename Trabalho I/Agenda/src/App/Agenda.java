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
import java.time.LocalDate;
import model.bean.Contato;
import model.bean.Telefone;
import model.bean.TipoTelefone;
import util.DatabaseManager;

/**
 *
 * @author rafael
 */
public class Agenda extends ControllerDaos {

    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
    Connection conn = DatabaseManager.getInstance().getConnection();

    public Agenda() throws SQLException, IOException {
        inicirAgenda();
    }

    private void inicirAgenda() throws SQLException, IOException {

        while (true) {
            System.out.println("\n------------ AGENDA--------------------\n"
                    + "------------Menu Principal-------------\n"
                    + "---1 - Inserir um novo contato---------\n"
                    + "---2 - Buscar um contato pelo nome ----\n"
                    + "---3 - Buscar um contato pelo número --\n"
                    + "---4 - Vizualizar minha agenda completa\n"
                    + "---0 - Sair da Agenda -----------------");

            System.out.print("\nDigite a opção desejada: ");
            int opcao = Integer.parseInt(ler.readLine());
            Contato c = new Contato();;
            switch (opcao) {
                case 1:
                    CreateContato(CriarContato(c));
                    System.out.println("*******************************\n"
                            + "Contato adicionado com sucesso!\n"
                            + "*******************************\n");
                    break;
                case 2:
                    System.out.println("Digite um nome para buscar em sua agenda: ");
                    menuAcessado(ByContato(ler.readLine(), false));
                    break;
                case 3:
                    System.out.println("Digite um número para buscar em sua agenda: ");
                    menuAcessado(ByContato(ler.readLine(), true));
                    break;
                case 4:
                    imprimirAgenda();
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("Esta opção não existe! Tente novamente.");
                    break;
            }
        }
    }

    private void menuAcessado(Contato c) throws SQLException, IOException {
        if (c == null) {
            System.out.println("*******************************\n"
                    + "Não consta em sua agenda um contato com este número!\n"
                    + "*******************************\n");

        } else {
            System.out.println("*******************************\n"
                    + "CONTATO ENCONTRADO\n"
                    + "*******************************\n");
            imprimirContato(c);
            menuInterno(c);
        }
    }

    private void menuInterno(Contato c) throws SQLException, IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("\nMenu Logado: ");
            System.out.println("1 - Update Contato");
            System.out.println("2 - Delete Contato");
            System.out.println("0 - Voltar para o Menu principal");
            int opcao = Integer.parseInt(ler.readLine());
            switch (opcao) {
                case 1:
                    UpdateContato(CriarContato(c));
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
        System.out.println("\n*****************************");
        System.out.println("CONTATO DELETADO COM SUCESSO!");
        System.out.println("*****************************");
    }

    private Contato CriarContato(Contato c) throws IOException {
        System.out.print("Digite o nome do contato: ");
        String nome = ler.readLine();
        System.out.print("\nDigite a data de nascimento, separada por '/': ");

        String dataCriacao[] = ler.readLine().split("/");
        if (dataCriacao.length != 1) {
            c.setData_nascimento(LocalDate.of(Integer.parseInt(dataCriacao[2]), Integer.parseInt(dataCriacao[1]), Integer.parseInt(dataCriacao[0])));
        } else {
            c.setData_nascimento(null);
        }

        c.setNome(nome);

        while (true) {
            System.out.println("Para adicionar um novo telefone digite '1'. Digite '0' para sair.");
            if (ler.readLine().equals("0")) {
                break;
            }
            c.getListaTelefones().add(criarTelefone());
        }
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

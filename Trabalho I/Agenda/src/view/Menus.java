/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import model.bean.Contato;
import model.bean.Telefone;
import model.bean.TipoTelefone;

/**
 *
 * @author rafael
 */
public class Menus {

    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

    public static void MenuPrincipal() {
        System.out.println("\n------------ AGENDA--------------------\n"
                + "------------Menu Principal-------------\n"
                + "---1 - Inserir um novo contato---------\n"
                + "---2 - Buscar um contato pelo nome ou numero ----\n"
                + "---3 - Vizualizar minha agenda completa\n"
                + "---0 - Sair da Agenda -----------------");

        System.out.print("\nDigite a opção desejada: ");

    }

    public static void MenuInterno() {
        System.out.println("\nMenu Logado: \n"
                + "1 - Update Contato\n"
                + "2 - Delete Contato\n"
                + "0 - Voltar para o Menu principal");
    }

    public static void ModelTelefone(Telefone t) {

        System.out.println("    DDD: " + t.getDDD());
        System.out.println("    Número: " + t.getNumero());
        System.out.println("    Tipo: " + t.getTipo());
        System.out.println(t.getPrincipal() == true ? "Principal ✔" : "Principal ✖");

    }

    public static void ModelContato(Contato c) {
        System.out.println("\n----------------------------\n");
        System.out.println("Nome: " + c.getNome());
        System.out.print("Aniversário: ");
        System.out.println(c.getData_nascimento() == null ? "Não cadastrado" : c.getData_nascimento());
        System.out.print("Telefone(s): ");
        System.out.println(c.getListaTelefones().isEmpty() ? "Não há telefones cadastrados" : "");
    }

    public Contato CriarContato(Contato c) throws IOException {
        System.out.print("Digite o nome do contato: ");
        String nome = ler.readLine();
        System.out.print("\nDigite a data de nascimento, separada por '-': ");
        String dataCriacao[] = ler.readLine().split("-");
        if (dataCriacao.length != 0) {
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

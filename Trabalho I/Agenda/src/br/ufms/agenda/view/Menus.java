/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import br.ufms.agenda.model.bean.Contato;
import br.ufms.agenda.model.bean.Telefone;
import br.ufms.agenda.model.bean.TipoTelefone;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rafael
 */
public class Menus {

    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

    public static void MenuPrincipal() {
        System.out.print("\n---------------- AGENDA 0.4 BETA --------------\n"
                + "----------------- Menu Principal --------------\n"
                + "--- 1 - Adicionar Contato ---------------------\n"
                + "--- 2 - Buscar contato por Nome/Numero --------\n"
                + "--- 3 - Listar Agenda Completa ----------------\n"
                + "--- 0 - Sair ----------------------------------\n"
                + "INFORME UMA OPÇÃO:");

    }

    public static void MenuInterno(int contato) {
        System.out.print("\n----------------- MENU DE OPÇÕES DO CONTATO Nº: " + contato + " ----------------- \n"
                + "-------------------- 1 - Update Contato ---------------------------\n"
                + "-------------------- 2 - Delete Contato ---------------------------\n"
                + "-------------------- 0 - Voltar para Menu Principal ---------------\n"
                + "INFORME UMA OPÇÃO:");
    }

    public static void ModelTelefone(Telefone t, int index) {
        System.out.println("              Telefone Nº: " + index);
        System.out.println("                        DDD: " + t.getDDD());
        System.out.println("                        Número: " + t.getNumero());
        System.out.println("                        Tipo: " + t.getTipo());
        System.out.println(t.getPrincipal() == true ? "                        Principal ✔\n" : "                        Principal ✖\n");

    }

    public static void ModelContato(Contato c, int index) {

        System.out.println("Contato Nº: " + index);
        System.out.println("-------------------------------------------------------");
        System.out.println("          Nome: " + c.getNome());
        System.out.print("          Aniversário: ");
        System.out.println(c.getData_nascimento() == null ? "Não cadastrado" : c.getData_nascimento());
        System.out.println("          Data de Criação da Conta: " + c.getData_criacao());
        System.out.print("          Telefone(s): ");
        System.out.println(c.getListaTelefones().isEmpty() ? "Não há telefones cadastrados" : "");
    }
}

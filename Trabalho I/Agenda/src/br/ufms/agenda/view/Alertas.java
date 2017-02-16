/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.view;

/**
 *
 * @author rafael
 */
public class Alertas {

    public static void ContaAdicionadoSucesso() {
        System.out.println("+++++++++++++++++++++++++++++++\n"
                + "CONTATO ADICIONADO COM SUCESSO!\n"
                + "+++++++++++++++++++++++++++++++\n");
    }

    public static void ContaRemovidaSucesso() {
        System.out.println("+++++++++++++++++++++++++++++\n"
                + "CONTATO DELETADO COM SUCESSO!\n"
                + "+++++++++++++++++++++++++++++\n");
    }

    public static void InformeNomeNumero() {
        System.out.print("INFORME UM NOME/TELEFONE: ");
    }

    public static void ContatoNaoExiste() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++\n"
                + "NÃO ENCONTRADO NOME/TELEFONE NA AGENDA\n"
                + "+++++++++++++++++++++++++++++++++++++++\n");
    }

    public static void DefaultCase() {
        System.out.println("OPÇÂO INVALIDA!.");
    }

    public static void InformeNumeroContato() {
        System.out.print("INFORME O Nº DO CONTATO PARA MAIS OPÇOẼS: ");
    }

    public static void Espaco() {
        System.out.println("-------------------------------------------------------");
    }
}

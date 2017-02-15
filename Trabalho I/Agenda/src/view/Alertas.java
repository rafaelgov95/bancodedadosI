/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author rafael
 */
public class Alertas {

    public static void ContaAdicionadoSucesso() {
        System.out.println("*******************************\n"
                + "Contato adicionado com sucesso!\n"
                + "*******************************\n");
    }

    public static void ContaRemovidaSucesso() {
        System.out.println("*******************************\n"
                + "CONTATO DELETADO COM SUCESSO!\n"
                + "*******************************\n");
    }

    public static void InformeNomeNumero() {
        System.out.println("Digite um nome ou um numero para buscar em sua agenda: ");
    }

    public static void ContatoNaoExiste() {
        System.out.println("*******************************\n"
                + "Não consta em sua agenda um contato com este número ou nome!\n"
                + "*******************************\n");
    }

    public static void DefaultCase() {
        System.out.println("Esta opção não existe! Tente novamente.");
    }

    public static void InformeNumeroContato() {
        System.out.println("\n Informe o Index para abrir MenuInterno do Contato");
    }

    public static void Espaco() {
        System.out.println("-------------------------------------------------------");
    }
}

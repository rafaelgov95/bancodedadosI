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

    public static void NovaDataN() {
        System.out.print("Nova data nascimento Exemplo: dd-MM-yyyy : ");

    }

    public static void NovoNome() {
        System.out.print("Novo Nome: ");

    }

    public static void NumeroInvalido() {
        System.out.println("NUMERO INVALIDO!");

    }

    public static void NovoDDD() {
        System.out.print("INFOME NOVO DDD: ");

    }

    public static void NovoNumero() {
        System.out.print("INFOME NOVO NUMERO: ");

    }

    public static void NovoTipo() {
        System.out.print("TIPO(Celular, Residencial, Comercial): ");

    }

    public static void NovoPrincipal() {
        System.out.print("Principal? (s/n): ");

    }

    public static void DDD() {
        System.out.println("DDD do telefone: ");

    }

    public static void NumeroTel() {
        System.out.println("Número de telefone: ");

    }

    public static void Tipo() {
        System.out.println("Tipo (Celular, Residencial, Comercial): ");

    }

    public static void Principal() {
        System.out.println("Principal? (s/n) ");

    }

    public static void NomeContato() {
        System.out.print("Nome do Contato: ");

    }

    public static void Data() {
        System.out.print("Data de nascimento Exemplo:(22-07-1995): ");

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

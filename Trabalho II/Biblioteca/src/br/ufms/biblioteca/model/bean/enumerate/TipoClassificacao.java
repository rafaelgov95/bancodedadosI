package br.ufms.biblioteca.model.bean.enumerate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rafael
 */
public enum TipoClassificacao {
    CIENTIFICO, COLECAO, MATEMATICA, HISTORIA, FILOSOFIA, ECONOMIA, ADMINISTRACAO_E_NEGOCIOS, ENGENHARIA, SOCIOLOGIA, LITERATURA, ARTES, PERIODICOS_CIENTIFICOS, PERIODICOS_INFORMATIVOS, ENTRETENIMENTO;

    @Override
    public String toString() {
        switch (this) {
            case CIENTIFICO:
                return "Cientifico";
            case COLECAO:
                return "COLECAO";
            case MATEMATICA:
                return "Matematica";
            case HISTORIA:
                return "Historia";
            case FILOSOFIA:
                return "Filosofia";
            case ECONOMIA:
                return "Economia";
            case ADMINISTRACAO_E_NEGOCIOS:
                return "Administracao e Negocios";
            case ENGENHARIA:
                return "Engenharia";
            case SOCIOLOGIA:
                return "Sociologia";
            case LITERATURA:
                return "Literatura";
            case ARTES:
                return "Artes";
            case PERIODICOS_CIENTIFICOS:
                return "Periodicos Cientificos";
            case PERIODICOS_INFORMATIVOS:
                return "Periodicos Informativos";
            case ENTRETENIMENTO:
                return "Entretenimento";
            default:
                return "";
        }
    }
}
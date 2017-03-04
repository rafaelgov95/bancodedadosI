package br.ufms.biblioteca.model.bean.enumerate;

import static br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao.DOUTOR;
import static br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao.GRADUADO;
import static br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao.GRADUANDO;
import static br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao.MESTRE;
import static br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao.POS_DOUTOR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public enum TipoIdioma {
    BRASILEIRO, INGLES, MEXICANO, ITALIANO;

    @Override
    public String toString() {
        switch (this) {
            case BRASILEIRO:
                return "BRASILEIRO";
            case INGLES:
                return "INGLES";
            case MEXICANO:
                return "MEXICANO";
            case ITALIANO:
                return "ITALIANO";
            default:
                return "";
        }
    }

    public static TipoIdioma setIdioma(String idioma) {
        switch (idioma) {
            case "Brasileiro":
                return BRASILEIRO;
            case "Mexicano":
                return MEXICANO;
            case "Ingles":
                return INGLES;
            case "Italiano":
                return ITALIANO;
            default:
                return null;
        }
    }
}

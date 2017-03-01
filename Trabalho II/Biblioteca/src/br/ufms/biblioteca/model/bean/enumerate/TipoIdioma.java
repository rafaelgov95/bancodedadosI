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
public enum TipoIdioma {
    BRASILEIRO, INGLES, MEXICANO, ITALIANO;

    @Override
    public String toString() {
        switch (this) {
            case BRASILEIRO:
                return "Cientifico";
            case INGLES:
                return "Colecao";
            case MEXICANO:
                return "Matematica";
            case ITALIANO:
                return "Historia";

            default:
                return "";
        }
    }
}

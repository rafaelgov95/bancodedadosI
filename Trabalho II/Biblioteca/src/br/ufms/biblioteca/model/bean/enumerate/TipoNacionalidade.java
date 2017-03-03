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
public enum TipoNacionalidade {
    BRASILEIRO, INGLES, MEXICANO, ITALIANO;

    public TipoNacionalidade setEnum(String arg) {
        switch (arg) {
            case "Brasileiro":
                return BRASILEIRO;
            case "Ingles":
                return INGLES;
            case "Mexicano":
                return MEXICANO;
            case "Italiano":
                return ITALIANO;
            default:
                return null;
        }

    }

    @Override
    public String toString() {
        switch (this) {
            case BRASILEIRO:
                return "Brasileiro";
            case INGLES:
                return "Ingles";
            case MEXICANO:
                return "Mexicano";
            case ITALIANO:
                return "Italiano";

            default:
                return "";
        }
    }
}

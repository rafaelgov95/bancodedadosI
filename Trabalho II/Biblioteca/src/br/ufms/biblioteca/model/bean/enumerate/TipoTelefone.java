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
public enum TipoTelefone {
    RESIDENCIAL, COMERCIAL, CELULAR;

    @Override
    public String toString() {
        switch (this) {
            case CELULAR:
                return "Celular";
            case RESIDENCIAL:
                return "Residencial";
            case COMERCIAL:
                return "Comercial";
            default:
                return "";
        }
    }
}

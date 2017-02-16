/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.model.bean;

import br.ufms.agenda.model.daolib.Bean;

/**
 *
 * @author rafael
 */
public class Telefone extends Bean<Integer> {

    private String numero;
    private String ddd;
    private TipoTelefone tipo;
    private boolean principal;

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    /**
     * @return the tipo
     */
    public TipoTelefone getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the principal
     */
    public boolean getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

}

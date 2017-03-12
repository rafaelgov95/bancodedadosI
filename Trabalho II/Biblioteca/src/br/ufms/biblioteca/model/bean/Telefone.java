/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.enumerate.TipoTelefone;
import br.ufms.biblioteca.model.daolib.Bean;

/**
 *
 * @author rafael
 */
public class Telefone extends Bean<Integer> {

    private String numero;
    private String ddd;
    private TipoTelefone tipo;
    private boolean principal;

    public Telefone() {
        principal = false;
    }

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

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Telefone) {
            String numero_is = ((Telefone) obj).getNumero();
            String ddd_is = ((Telefone) obj).getDDD();
            if (numero_is.equals(numero) && ddd_is.equals(ddd)) {
                return true;
            }
        }
        return false;
    }

}

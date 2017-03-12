/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class Estudante extends Usuario implements Serializable {

    private String rga;

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append(super.toString()).append("\n");
        saida.append("RGA: ").append(rga).append("\n");
        return saida.toString();
    }

}

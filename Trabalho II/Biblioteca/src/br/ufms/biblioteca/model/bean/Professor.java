/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import java.time.LocalDate;

/**
 *
 * @author rafael
 */
public class Professor extends Usuario {

    private int siap;
    private boolean is_substituto;

    public int getSiap() {
        return siap;
    }

    public void setSiap(int siap) {
        this.siap = siap;
    }

    public boolean isIs_substituto() {
        return is_substituto;
    }

    public void setIs_substituto(boolean is_substituto) {
        this.is_substituto = is_substituto;
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append(super.toString()).append("\n");
        saida.append("SIAP: ").append(siap).append("\n");
        saida.append("Substituto: ").append(String.valueOf(is_substituto)).append("\n");
        return saida.toString();
    }

}

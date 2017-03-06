/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author rafael
 */
public class Estudante extends Usuario {

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

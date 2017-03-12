/*
 * Copyright (C) 2016 Kleber Kruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.enumerate.UF;
import br.ufms.biblioteca.model.daolib.Bean;
import java.io.Serializable;

/**
 *
 *
 * @author rafael
 */
public class Municipio extends Bean<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private UF uf;
    private int ibge;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public int getIbge() {
        return ibge;
    }

    public void setIbge(int ibge) {
        this.ibge = ibge;
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append("     Municipio:").append("\n");
        saida.append("           Ibge: ").append(ibge).append("\n");
        saida.append("           Nome: ").append(nome).append("\n");
        saida.append("           UF: ").append(uf.toString());
        return saida.toString();
    }

}

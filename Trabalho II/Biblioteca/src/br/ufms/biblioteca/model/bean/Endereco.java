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

import br.ufms.biblioteca.model.daolib.Bean;
import java.io.Serializable;

/**
 * Classe que mapeia a tabela Endereco do banco de dados.
 *
 * @author Kleber Kruger
 */
public class Endereco extends Bean<Integer> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String rua;
    private Short numero;
    private Boolean sn;
    private String complemento;
    private String bairro;
    private String cep;
    private Municipio municipio;

    /**
     * Cria um novo objeto Endereco com todos os atributos nulos inicialmente.
     */
    public Endereco() {
        super();
    }
    
    public String getRua() {
        return rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public Short getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Short numero) {
        this.numero = numero;
    }

    /**
     * @return the sn
     */
    public Boolean getSemNumero() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSemNumero(Boolean sn) {
        this.sn = sn;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cep
     */
    public String getCEP() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCEP(String cep) {
        this.cep = cep;
    }

    /**
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append("     Rua: ").append(rua).append("\n");
        
        saida.append("     Numero: ").append(numero).append("\n");
        
        saida.append("     Bairro: ").append(bairro).append("\n");
        
        saida.append("     Compemento: ").append(complemento).append("\n");
        
        saida.append("     CEP: ").append(cep).append("\n");
        
        saida.append("     Sem Numero: ").append(sn.toString()).append("\n");
        saida.append(municipio).append("\n");
        return saida.toString();
    }
    
}

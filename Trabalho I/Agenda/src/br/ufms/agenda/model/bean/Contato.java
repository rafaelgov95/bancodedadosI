package br.ufms.agenda.model.bean;

import br.ufms.agenda.model.daolib.Bean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public class Contato extends Bean<Integer> {

    private String nome;
    private LocalDate data_nascimento;
    private LocalDate data_at;
    private final List<Telefone> Telefones;

    public Contato() {
        this.Telefones = new ArrayList<>();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the data_nascimento
     */
    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    /**
     * @return the data_criacao
     */
    public void setData_at(LocalDate date) {
        data_at = date;
    }

    public LocalDate getData_at() {
        return data_at;
    }

    /**
     * @return the listaTelefones
     */
    public List<Telefone> getTelefones() {
        return Telefones;
    }
}

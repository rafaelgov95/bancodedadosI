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
    private LocalDate data_criacao;
    private final List<Telefone> listaTelefones;

    public Contato() {
        this(LocalDate.now());
    }

    public Contato(LocalDate criacao) {
        this.listaTelefones = new ArrayList<>();
        this.data_criacao = criacao;
    }
    
    public void copiarContato(Contato c){
        this.nome = c.getNome();
        this.data_nascimento = c.getData_nascimento();
        this.data_criacao = c.data_criacao;
        this.getListaTelefones().clear();
        this.getListaTelefones().addAll(c.getListaTelefones());
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
    public LocalDate getData_criacao() {
        return data_criacao;
    }

    /**
     * @return the listaTelefones
     */
    public List<Telefone> getListaTelefones() {
        return listaTelefones;
    }
}

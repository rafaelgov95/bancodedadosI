/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoIdioma;
import br.ufms.biblioteca.model.daolib.Bean;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Livro extends Bean<Integer> {

    private String nome;
    private int isbn;
    private Short edicao;
    private TipoClassificacao classificacao;
    private TipoIdioma idioma;
    private LocalDate ano_publicacao;
    private Editora editora;
    private List<Autor> autores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Short getEdicao() {
        return edicao;
    }

    public void setEdicao(Short edicao) {
        this.edicao = edicao;
    }

    public TipoClassificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(TipoClassificacao classificacao) {
        this.classificacao = classificacao;
    }

    public TipoIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(TipoIdioma idioma) {
        this.idioma = idioma;
    }

    public LocalDate getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(LocalDate ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

}

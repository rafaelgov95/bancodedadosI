/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoIdioma;
import br.ufms.biblioteca.model.dao.DAOFactory;
import br.ufms.biblioteca.model.daolib.Bean;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Livro() {
        this.autores = new ArrayList<>();
    }
    
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
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(this.getCodigo()).append("\n");
        output.append("Nome: ").append(this.getNome()).append("\n");
        output.append("Isbn: ").append(this.getIsbn()).append("\n");
        output.append("Idioma: ").append(this.getIdioma()).append("\n");
        output.append("Edicao: ").append(this.getEdicao()).append("\n");
        output.append("Editora: ").append(this.getEditora().getNome()).append("\n");
        output.append("Ano Publicação: ").append(this.getAno_publicacao()).append("\n");
        output.append("Autores: ").append("\n");
        for (Autor autor : autores) {
            output.append(autor);
        }
        return output.toString();
    }
    
}

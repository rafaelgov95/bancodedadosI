package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoNacionalidade;
import br.ufms.biblioteca.model.daolib.Bean;
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
public class Autor extends Bean<Integer> {

    private String nome;
    private TipoNacionalidade nacionalidade;

//    private List<TipoClassificacao> Generos;
//    private List<Livro> livros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoNacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(TipoNacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}

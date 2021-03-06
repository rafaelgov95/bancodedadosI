/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.daolib.Bean;
import java.time.LocalTime;

/**
 *
 * @author rafael
 */
public class Emprestimo extends Bean<Integer> {

    private Usuario usuario;
    private Livro livro;
    private LocalTime data_emprestimo;
    private boolean ativa;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalTime getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalTime data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append("Id Emprestimo: ").append(this.getCodigo()).append("\n");
        saida.append("Usuario:\n").append(this.getUsuario()).append("\n");
        saida.append("Livro:\n ").append(this.getLivro()).append("\n");
        return saida.toString();
    }

}

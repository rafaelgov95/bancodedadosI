/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.daolib.Bean;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Emprestimo extends Bean<Integer> {

    private List<Livro> livro;
    private LocalDate data_emprestimo;

}

package br.ufms.biblioteca.model.bean;

import br.ufms.biblioteca.model.bean.enumerate.TipoCurso;
import br.ufms.biblioteca.model.bean.enumerate.Titulacao;
import br.ufms.biblioteca.model.daolib.Bean;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public abstract class Usuario extends Bean<Integer> implements Serializable {

    private String nome;
    private TipoCurso curso;
    private String cpf;
    private Titulacao titulacao;
    private LocalDate fim_contrato;
    private LocalDate data_nascimento;
    private LocalDate data_at;
    private final List<Telefone> telefones;
    private final List<Endereco> enderecos;
    private final List<Livro> emprestimos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> Enderecos) {
        this.enderecos.addAll(enderecos);
    }

    public Usuario() {
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    
    public List<Livro> getEmprestimos() {
        return emprestimos;
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

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public LocalDate getData_at() {
        return data_at;
    }

    /**
     * @return the listaTelefones
     */
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public TipoCurso getCurso() {
        return curso;
    }

    public void setCurso(TipoCurso curso) {
        this.curso = curso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getFim_contrato() {
        return fim_contrato;
    }

    public void setFim_contrato(LocalDate fim_contrato) {
        this.fim_contrato = fim_contrato;
    }
}

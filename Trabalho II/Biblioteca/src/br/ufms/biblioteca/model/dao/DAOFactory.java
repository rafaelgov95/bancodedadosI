/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.model.dao;

import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.Usuario;

/**
 *
 * @author rafael
 */
public class DAOFactory {

    private final TelefoneDAO telefone;
    private final EstudanteDAO estudante;
    private final ProfessorDAO professor;
    private final MunicipioDAO municipio;
    private final EnderecoDAO endereco;
    private final UsuarioDAO<Estudante> usuario;
    private final LivroDAO livro;
    private final AutorDAO autor;
    private final EmprestimoDAO emprestimo;
    private final EditoraDAO editora;

    private DAOFactory() {
        this.telefone = new TelefoneDAO();
        this.estudante = new EstudanteDAO();
        this.professor = new ProfessorDAO();
        this.endereco = new EnderecoDAO();
        this.municipio = new MunicipioDAO();
        this.usuario = new EstudanteDAO();
        this.livro = new LivroDAO();
        this.autor = new AutorDAO();
        this.emprestimo = new EmprestimoDAO();
        this.editora = new EditoraDAO();

    }

    /**
     * Gets the single instance of DAOFactory class.
     *
     * @return the singleton instance
     */
    public static DAOFactory getInstance() {
        return DAOFactoryHolder.INSTANCE;
    }

    /**
     * Classe privada que armazena a única instância de DAOFactory.
     */
    private static class DAOFactoryHolder {

        private static final DAOFactory INSTANCE = new DAOFactory();
    }

    /**
     * @return the telefoneDAO
     */
    public TelefoneDAO getTelefoneDAO() {
        return telefone;
    }

    public EditoraDAO getEditoraDAO() {
        return editora;
    }
    public EmprestimoDAO getEmprestimoDAO() {
        return emprestimo;
    }

    public AutorDAO getAutorDAO() {
        return autor;
    }

    public LivroDAO getLivro() {
        return livro;
    }

    public EstudanteDAO getEstudanteDAO() {
        return estudante;
    }

    public ProfessorDAO getProfessorDAO() {
        return professor;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuario;
    }

    public MunicipioDAO getMunicipioDAO() {
        return municipio;
    }

    public EnderecoDAO getEnderecoDAO() {
        return endereco;
    }

}

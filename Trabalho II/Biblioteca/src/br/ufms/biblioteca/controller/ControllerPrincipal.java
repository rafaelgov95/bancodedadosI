/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.controller;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Editora;
import br.ufms.biblioteca.model.bean.Emprestimo;
import br.ufms.biblioteca.model.bean.Endereco;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Livro;
import br.ufms.biblioteca.model.bean.Municipio;
import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoCurso;
import br.ufms.biblioteca.model.bean.enumerate.TipoIdioma;
import br.ufms.biblioteca.model.bean.enumerate.TipoNacionalidade;
import br.ufms.biblioteca.model.bean.enumerate.TipoTelefone;
import br.ufms.biblioteca.model.bean.enumerate.UF;
import br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao;
import br.ufms.biblioteca.model.dao.DAOFactory;
import br.ufms.biblioteca.model.dao.EnderecoDAO;
import br.ufms.biblioteca.model.dao.EstudanteDAO;
import br.ufms.biblioteca.model.daolib.Bean;
import com.sun.jndi.ldap.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ControllerPrincipal {

    BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));

    public ControllerPrincipal() throws IOException, SQLException {
        MenuPrincipal();
    }

    public void MenuPrincipal() throws IOException, SQLException {
        while (true) {
            System.out.println("Bem Vindo a Biblioteca UFMS-CPCX\n"
                    + " 1 - Menu TipoUsuario\n"
                    + " 2 - Menu Telefone\n"
                    + " 3 - Menu Endereco\n"
                    + " 4 - Menu Editora \n"
                    + " 5 - Menu Livros \n"
                    + " 6 - Menu Autores \n"
                    + " 7 - Menu Emprestimos");

            String opcao = leia.readLine();
            switch (opcao) {
                case "1":
                    MenuEscolhaTipoUsuario();
                    break;
                case "2":
                    MenuTelefone();
                    break;
                case "3":
                    MenuEndereco();
                    break;
                case "4":
                    MenuEditora();
                    break;
                case "5":
                    MenuLivros();
                    break;
                case "6":
                    MenuAutor();
                    break;
                case "7":
                    MenuEmprestimo();
                default:
                    System.out.println("Escolha Opcao Certa");
                    break;
            }

        }
    }

    private void MenuEscolhaTipoUsuario() throws IOException, SQLException {
        System.out.println("Menu Tipo Usuario\n"
                + "1 - Estudante\n"
                + "2 - Professor");
        String tipo = leia.readLine();
        switch (tipo) {
            case "1":
                MenuEstudante();
                break;
            case "2":
                MenuProfessor();
                break;
            default:
                System.out.println("Tipo Invalido!");
        }

    }

    private void MenuEditora() throws IOException, SQLException {
        System.out.println("MENU EDITORA");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL");
        String opcaoEditora = leia.readLine();
        Editora editora = new Editora();
        switch (opcaoEditora) {
            case "1":
                System.out.print("Nome: ");
                editora.setNome(leia.readLine());
                System.out.println("Cidade: ");
                editora.setCidade(leia.readLine());
                DAOFactory.getInstance().getEditoraDAO().save(editora);
                break;
            case "2":
                System.out.println("INFORME ID EDITORA UPDATE");
                editora = DAOFactory.getInstance().getEditoraDAO().get(Integer.parseInt(leia.readLine()));
                System.out.print("Nome: ");
                editora.setNome(leia.readLine());
                System.out.println("Cidade: ");
                editora.setCidade(leia.readLine());

                DAOFactory.getInstance().getEditoraDAO().save(editora);
                break;
            case "3":
                System.out.println("DELETAR ESTUDANTE");
                DAOFactory.getInstance().getEditoraDAO().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println("GET ESTUDANTE");
                System.out.println(DAOFactory.getInstance().getEditoraDAO().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                System.out.println("GET ALL");

                for (Editora ed : DAOFactory.getInstance().getEditoraDAO().getAll()) {
                    System.out.println(ed);
                }
                break;
        }
    }

    private Telefone CriaTelefone() throws IOException {
        Telefone t = new Telefone();
        System.out.print("DDD: ");
        t.setDDD(leia.readLine());
        System.out.println("Numero: ");
        t.setNumero(leia.readLine());
        System.out.println("PRINCIAL? TRUE OU FALSE: ");
        t.setPrincipal(Boolean.parseBoolean(leia.readLine()));
        System.out.println("TPO TELEFONE [RESIDENCIAL, CELULAR, COMERCIAL]: ");
        t.setTipo(TipoTelefone.valueOf(leia.readLine()));
        return t;
    }

    private List<Telefone> MenuTelefone() throws IOException, SQLException {
        boolean flag = true;
        List<Telefone> listT = new ArrayList<>();

        while (flag) {
            System.out.println("Adicionar Telefone\n"
                    + " 1 - Adicionar Telefone\n"
                    + " 2 - Voltar");

            String opcaoTel = leia.readLine();
            switch (opcaoTel) {
                case "1":
                    listT.add(CriaTelefone());
                    break;
                case "2":
                    flag = false;
                    break;
            }
        }
        return listT;
    }

    private void MenuEstudante() throws IOException, SQLException {
        System.out.println("MENU Estudate");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL");
        String opcaoEstudante = leia.readLine();
        Estudante user = new Estudante();
        switch (opcaoEstudante) {
            case "1":
                System.out.println("Tipo Estudante");
                System.out.print("Nome: ");
                user.setNome(leia.readLine());
                System.out.println("Titulacao: ");
                user.setTitulacao(TipoTitulacao.valueOf(leia.readLine()));
                System.out.println("DATA INICIO CONTRATO");
                user.setInicio_contrato(SetDate());
                System.out.println("DATA FIM CONTRATO");
                user.setFim_contrato(SetDate());
                System.out.println("DATA NASCIMENTO");
                user.setData_nascimento(SetDate());
                System.out.println("CPF");
                user.setCpf(leia.readLine());
                System.out.println("CUSO");
                user.setCurso(TipoCurso.valueOf(leia.readLine()));
                System.out.println("RGA");
                user.setRga(leia.readLine());
                System.out.println("TELEFONES: ");
                user.getTelefones().addAll(MenuTelefone());
                user.getEnderecos().addAll(MenuEndereco());
                DAOFactory.getInstance().getUsuarioDAO().save(user);

                break;
            case "2":
                user = DAOFactory.getInstance().getEstudanteDAO().get(Integer.parseInt(leia.readLine()));
                System.out.println("Tipo Estudante");
                System.out.print("Nome: ");
                user.setNome(leia.readLine());
                System.out.println("Titulacao: ");
                user.setTitulacao(TipoTitulacao.valueOf(leia.readLine()));
                System.out.println("DATA INICIO CONTRATO");
                user.setInicio_contrato(SetDate());
                System.out.println("DATA FIM CONTRATO");
                user.setFim_contrato(SetDate());
                System.out.println("DATA NASCIMENTO");
                user.setData_nascimento(SetDate());
                System.out.println("CPF");
                user.setCpf(leia.readLine());
                System.out.println("CUSO");
                user.setCurso(TipoCurso.valueOf(leia.readLine()));
                System.out.println("RGA");
                user.setRga(leia.readLine());
//              user.getTelefones().addAll(MenuTelefone());
//              user.getEnderecos().addAll(MenuEndereco());
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "3":
                System.out.println("DELETAR ESTUDANTE");
                DAOFactory.getInstance().getEstudanteDAO().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println("GET ESTUDANTE");
                System.out.println(DAOFactory.getInstance().getEstudanteDAO().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                System.out.println("GET ALL");

                for (Estudante est : DAOFactory.getInstance().getEstudanteDAO().getAll()) {
                    System.out.println(est);
                }
                break;
        }
    }

    private void MenuProfessor() throws IOException, SQLException {
        System.out.println("MENU PROFESSOR");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL");
        String opcaoProfessor = leia.readLine();
        Professor user = new Professor();
        switch (opcaoProfessor) {
            case "1":

                System.out.println("Tipo Professor");
                System.out.print("Nome: ");
                user.setNome(leia.readLine());
                System.out.println("Titulacao: ");
                user.setTitulacao(TipoTitulacao.valueOf(leia.readLine()));
                System.out.println("DATA INICIO CONTRATO");
                user.setInicio_contrato(SetDate());
                System.out.println("DATA FIM CONTRATO");
                user.setFim_contrato(SetDate());
                System.out.println("DATA NASCIMENTO");
                user.setData_nascimento(SetDate());
                System.out.println("CPF");
                user.setCpf(leia.readLine());
                System.out.println("CUSO");
                user.setCurso(TipoCurso.valueOf(leia.readLine()));
                System.out.println("SIAP");
                user.setSiap(Integer.parseInt(leia.readLine()));
                System.out.println("YES / NOT Substituto");
                user.setIs_substituto(Boolean.parseBoolean(leia.readLine()));

                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "2":
                System.out.println("INFORME O ID DO PROFESSOR: ");
                user = DAOFactory.getInstance().getProfessorDAO().get(Integer.parseInt(leia.readLine()));
                System.out.println("Tipo Estudante");
                System.out.print("Nome: ");
                user.setNome(leia.readLine());
                System.out.println("Titulacao: ");
                user.setTitulacao(TipoTitulacao.valueOf(leia.readLine()));
                System.out.println("DATA INICIO CONTRATO");
                user.setInicio_contrato(SetDate());
                System.out.println("DATA FIM CONTRATO");
                user.setFim_contrato(SetDate());
                System.out.println("DATA NASCIMENTO");
                user.setData_nascimento(SetDate());
                System.out.println("CPF");
                user.setCpf(leia.readLine());
                System.out.println("CUSO");
                user.setCurso(TipoCurso.valueOf(leia.readLine()));
                System.out.println("RGA");
                user.setSiap(Integer.parseInt(leia.readLine()));
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "3":
                System.out.println("DELETAR ESTUDANTE");
                DAOFactory.getInstance().getProfessorDAO().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println("GET ESTUDANTE");
                System.out.println(DAOFactory.getInstance().getProfessorDAO().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                System.out.println("GET ALL");

                for (Professor p : DAOFactory.getInstance().getProfessorDAO().getAll()) {
                    System.out.println(p);
                }
                break;
        }
    }

    private List<Endereco> MenuEndereco() throws IOException, SQLException {
        boolean flag = true;
        List<Endereco> listE = new ArrayList<>();

        System.out.println("Adicionar Endereco\n"
                + " 1 - Adicionar Endereco\n"
                + " 2 - Voltar");
        String opcaoTel = leia.readLine();
        while (flag) {
            switch (opcaoTel) {
                case "1":
                    listE.add(CriarEndereco());
                    break;
                case "2":
                    flag = false;
                    break;
            }
        }
        return listE;

//        Municipio m = new Municipio();
//        m.setIbge(1);
//        m.setNome("Coxim");
//        m.setUf(UF.MS);
    }

    private Endereco CriarEndereco() throws IOException {

        Endereco endereco = new Endereco();
        System.out.print("RUA: ");
        endereco.setRua(leia.readLine());
        System.out.print("Bairo: ");
        endereco.setBairro(leia.readLine());
        System.out.print("Complemento: ");
        endereco.setComplemento(leia.readLine());
        System.out.print("Numero: ");
        endereco.setNumero(Short.parseShort(leia.readLine()));
        System.out.print("CEP: ");
        endereco.setCEP(leia.readLine());
        System.out.print("Possui Numero?");
        endereco.setSemNumero(false);

        return endereco;
    }

    private void MenuEmprestimo() throws IOException, SQLException {
        System.out.println("MENU EMPRESTIMOS");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL");
        String opcaoEmprestimo = leia.readLine();

        Emprestimo emprestimo = new Emprestimo();
        switch (opcaoEmprestimo) {
            case "1":
                System.out.print("Id Livro: ");
                emprestimo.setLivro((Livro) DAOFactory.getInstance().getLivro().get(Integer.parseInt(leia.readLine())));
                System.out.print("Usuario que esta emprestando");
                emprestimo.setUsuario((Usuario) DAOFactory.getInstance().getUsuarioDAO().get(Integer.parseInt(leia.readLine())));
                System.out.print("Ativa ou Desativa");
                emprestimo.setAtiva(Boolean.parseBoolean(leia.readLine()));
                DAOFactory.getInstance().getEmprestimoDAO().save(emprestimo);
                break;
            case "2":
                System.out.println("Informe a id do livro buscado para update");
                emprestimo = DAOFactory.getInstance().getEmprestimoDAO().get(Integer.parseInt(leia.readLine()));
                System.out.print("Id Livro: ");
                emprestimo.setLivro((Livro) DAOFactory.getInstance().getLivro().get(Integer.parseInt(leia.readLine())));
                System.out.print("Usuario que esta emprestando");
                emprestimo.setUsuario((Usuario) DAOFactory.getInstance().getUsuarioDAO().get(Integer.parseInt(leia.readLine())));
                System.out.print("Ativa ou Desativa");
                emprestimo.setAtiva(Boolean.parseBoolean(leia.readLine()));
                DAOFactory.getInstance().getEmprestimoDAO().save(emprestimo);
                break;
            case "3":
                System.out.println("DELETAR EMPRESTIMO");
                DAOFactory.getInstance().getAutorDAO().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println("GET EMPRESTIMO");
                System.out.println(DAOFactory.getInstance().getEmprestimoDAO().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                System.out.println("GET ALL");

                for (Emprestimo emp : DAOFactory.getInstance().getEmprestimoDAO().getAll()) {

                    System.out.println(emp);
                }
                break;

        }

    }

    private void MenuAutor() throws IOException, SQLException {
        System.out.println("MENU AUTORES");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL");
        String opcaoAutores = leia.readLine();
        Autor a = new Autor();
        switch (opcaoAutores) {
            case "1":

                System.out.print("Nome: ");
                a.setNome(leia.readLine());
                System.out.print("Nacionalidade: ");
                a.setNacionalidade(TipoNacionalidade.valueOf(leia.readLine()));
                DAOFactory.getInstance().getAutorDAO().save(a);
                break;
            case "2":
                a = DAOFactory.getInstance().getAutorDAO().get(1);
                System.out.print("Novo Nome");
                a.setNome(leia.readLine());
                System.out.print("Nova Nascionalidade");
                a.setNacionalidade(TipoNacionalidade.valueOf(leia.readLine()));
                break;
            case "3":
                DAOFactory.getInstance().getAutorDAO().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println(DAOFactory.getInstance().getAutorDAO().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                for (Autor autor : DAOFactory.getInstance().getAutorDAO().getAll()) {
                    System.out.println(autor);
                }
                break;

        }
    }

    private void MenuLivros() throws IOException, SQLException {
        System.out.println("MENU LIVROS");
        System.out.println("1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Get\n"
                + "5 - GetALL\n");
        String opcaoLivros = leia.readLine();

        Livro l = new Livro();
        switch (opcaoLivros) {
            case "1":
                System.out.print("Nome: ");
                l.setNome(leia.readLine());
                System.out.print("Idioma: ");
                l.setIdioma(TipoIdioma.valueOf(leia.readLine()));
                System.out.print("ISNB: ");
                l.setIsbn(Integer.parseInt(leia.readLine()));
                System.out.print("Classificacao: ");
                l.setClassificacao(TipoClassificacao.valueOf(leia.readLine()));
                System.out.print("Edicao: ");
                l.setEdicao(Short.parseShort(leia.readLine()));
                System.out.println("Editora");
                l.setEditora(DAOFactory.getInstance().getEditoraDAO().get(1));
                System.out.println(l.getEditora().getNome());
                SubMenuAddAutor(l);
                System.out.println("Informe a data de publicacao do livro");
                l.setAno_publicacao(SetDate());
                DAOFactory.getInstance().getLivro().save(l);
                break;
            case "2":
                l = DAOFactory.getInstance().getLivro().get(Integer.parseInt(leia.readLine()));
                System.out.print("Novo Nome: ");
                l.setNome(leia.readLine());
                System.out.print("Idioma: ");
                l.setIdioma(TipoIdioma.valueOf(leia.readLine()));
                System.out.print("ISNB: ");
                l.setIsbn(Integer.parseInt(leia.readLine()));
                System.out.print("Classificacao: ");
                l.setClassificacao(TipoClassificacao.valueOf(leia.readLine()));
                System.out.print("Edicao: ");
                l.setEdicao(Short.parseShort(leia.readLine()));
                System.out.println("Editora");
                l.setEditora(DAOFactory.getInstance().getEditoraDAO().get(Integer.parseInt(leia.readLine())));
                System.out.println(l.getEditora().getNome());
                SubMenuAddAutor(l);
                System.out.println("Informe a data de publicacao do livro");
                l.setAno_publicacao(SetDate());
                DAOFactory.getInstance().getLivro().save(l);
                break;
            case "3":
                System.out.println("Informe um numero do id do autor para excluir");
                DAOFactory.getInstance().getLivro().delete(Integer.parseInt(leia.readLine()));
                break;
            case "4":
                System.out.println("Informe um id para get");
                System.out.println(DAOFactory.getInstance().getLivro().get(Integer.parseInt(leia.readLine())));
                break;
            case "5":
                System.out.println("Get all");
                for (Livro livro : DAOFactory.getInstance().getLivro().getAll()) {
                    System.out.println(livro);
                }
                break;

        }
    }

    private LocalDate SetDate() throws IOException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = leia.readLine();
        if (!data.equals("")) {
            LocalDate dt = LocalDate.parse(data, f);
            return dt;
        } else {
            return null;
        }

    }

    private void SubMenuAddAutor(Livro l) throws IOException, SQLException {

        boolean flag = true;
        while (flag) {
            System.out.println("Menu Adicionar Autores\n"
                    + " 1- Adicionar Autor Pelo id \n"
                    + " 2 - Voltar");

            String opcaoAdicionarAutor = leia.readLine();
            switch (opcaoAdicionarAutor) {
                case "1":
                    l.getAutores().add(DAOFactory.getInstance().getAutorDAO().get(Integer.parseInt(leia.readLine())));
                    break;
                case "2":
                    flag = false;
                    break;

            }
        }
    }

}

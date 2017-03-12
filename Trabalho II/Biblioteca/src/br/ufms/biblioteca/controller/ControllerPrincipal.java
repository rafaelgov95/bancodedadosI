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

import br.ufms.biblioteca.model.bean.Professor;
import br.ufms.biblioteca.model.bean.Telefone;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.bean.enumerate.TipoClassificacao;
import br.ufms.biblioteca.model.bean.enumerate.TipoCurso;
import br.ufms.biblioteca.model.bean.enumerate.TipoIdioma;
import br.ufms.biblioteca.model.bean.enumerate.TipoNacionalidade;
import br.ufms.biblioteca.model.bean.enumerate.TipoTelefone;

import br.ufms.biblioteca.model.bean.enumerate.TipoTitulacao;
import br.ufms.biblioteca.model.dao.DAOFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                    + " 2 - Menu Endereco\n"
                    + " 3 - Menu Editora \n"
                    + " 4 - Menu Livros \n"
                    + " 5 - Menu Autores \n"
                    + " 6 - Menu Emprestimos");

            String opcao = leia.readLine();
            switch (opcao) {
                case "1":
                    MenuEscolhaTipoUsuario();
                    break;
                case "2":
                    MenuEnderecoUsuario();
                    break;
                case "3":
                    MenuEditora();
                    break;
                case "4":
                    MenuLivros();
                    break;
                case "5":
                    MenuAutor();
                    break;
                case "6":
                    MenuEmprestimo();
                    break;

                default:
                    System.out.println("Escolha Opcao Certa");
                    break;
            }

        }
    }

    private void MenuEscolhaTipoUsuario() throws IOException, SQLException {
        System.out.println("Menu Tipo Usuario\n"
                + " 1 - Estudante\n"
                + " 2 - Professor");
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
        System.out.println(" 1 - Insert\n"
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL");
        String opcaoEditora = leia.readLine();
        Editora editora = new Editora();
        switch (opcaoEditora) {
            case "1":
                System.out.print("Nome: ");
                editora.setNome(leia.readLine());
                System.out.print("Cidade: ");
                editora.setCidade(leia.readLine());
                DAOFactory.getInstance().getEditoraDAO().save(editora);
                break;
            case "2":

                System.out.println("Informe um nome De Editora:");
                editora = DAOFactory.getInstance().getEditoraDAO().get(leia.readLine());

                if (editora.getNome() == null) {
                    System.out.println("Nome Invalido !");
                } else {

                    System.out.print("Novo Nome: ");
                    editora.setNome(leia.readLine());
                    System.out.println("Nova Cidade: ");
                    editora.setCidade(leia.readLine());
                    DAOFactory.getInstance().getEditoraDAO().save(editora);
                }
                break;
            case "3":
                System.out.println("Informe um nome de editora para deletar:");
                editora = DAOFactory.getInstance().getEditoraDAO().get(leia.readLine());
                if (editora.getNome() == null) {
                    System.out.println("Nome Invalido !");
                }
                try {
                    DAOFactory.getInstance().getEditoraDAO().delete(editora.getCodigo());
                } catch (SQLException e) {
                    System.out.println("Nao pode apagar um autor que ja esta registrado em livos" + e);
                }
                break;
            case "4":
                System.out.println("NOME DA EDITORA");
                editora = DAOFactory.getInstance().getEditoraDAO().get(leia.readLine());
                if (editora.getNome() == null) {
                    System.out.println("Nome Invalido !");
                } else {
                    System.out.println(editora);
                }
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
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL");
        String opcaoEstudante = leia.readLine();
        Estudante user = new Estudante();
        switch (opcaoEstudante) {
            case "1":
                user = (Estudante) digitaUsuario(user);
                System.out.println("RGA:");
                user.setRga(leia.readLine());
                user.getEnderecos().addAll(InsertEndereco());
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "2":

                System.out.println("Informe nome de um Usuario");
                user = DAOFactory.getInstance().getEstudanteDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                user = (Estudante) digitaUsuario(user);
                System.out.println("RGA");
                user.setRga(leia.readLine());
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "3":
                System.out.println("Informe um nome para Deletar");
                user = DAOFactory.getInstance().getEstudanteDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                DAOFactory.getInstance().getEstudanteDAO().delete(user.getCodigo());
                break;
            case "4":
                System.out.println("Informe nome Estudante");
                user = DAOFactory.getInstance().getEstudanteDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                System.out.println(user);
                break;
            case "5":
                System.out.println("GET ALL");

                for (Object est : DAOFactory.getInstance().getEstudanteDAO().getAll()) {
                    if (est instanceof Estudante) {
                        System.out.println((Estudante) est);
                    }

                }

                break;

        }
    }

    private Usuario digitaUsuario(Usuario user) throws IOException {

        System.out.print("Nome: ");
        user.setNome(leia.readLine());
        System.out.println("Titulacao: [MESTRE,DOUTOR,POS_DOUTOR,GRADUANDO,GRADUADO]");
        user.setTitulacao(TipoTitulacao.valueOf(leia.readLine()));
        System.out.println("DATA INICIO CONTRATO - EXEMPLO '22-09-2000':");
        user.setInicio_contrato(SetDate());
        System.out.println("DATA FIM CONTRATO - EXEMPLO '22-09-2000':");
        user.setFim_contrato(SetDate());
        System.out.println("DATA NASCIMENTO - EXEMPLO '22-09-2000':");
        user.setData_nascimento(SetDate());
        System.out.println("CPF:");
        user.setCpf(leia.readLine());
        System.out.println("CURSO:[SISTEMAS_DE_INFORMACAO, DIREITO, HISTORIA, LETRAS, QUIMICA, MATEMATICA, FISICA, ANALISE_DE_SISTEMAS: ]");
        user.setCurso(TipoCurso.valueOf(leia.readLine()));

        return user;
    }

    private void MenuProfessor() throws IOException, SQLException {
        System.out.println("MENU PROFESSOR");
        System.out.println(" 1 - Insert\n"
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL");
        String opcaoProfessor = leia.readLine();
        Professor user = new Professor();
        switch (opcaoProfessor) {
            case "1":
                user = (Professor) digitaUsuario(user);
                System.out.println("SIAP");
                user.setSiap(Integer.parseInt(leia.readLine()));
                System.out.println("Subistituto? TRUE/FALSE");
                user.setIs_substituto(Boolean.parseBoolean(leia.readLine()));
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "2":
                System.out.println("INFORME O ID DO PROFESSOR: ");
                user = DAOFactory.getInstance().getProfessorDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                user = (Professor) digitaUsuario(user);
                System.out.println("SIAP");
                user.setSiap(Integer.parseInt(leia.readLine()));
                System.out.println("Subistituto? TRUE/FALSE");
                user.setIs_substituto(Boolean.parseBoolean(leia.readLine()));
                DAOFactory.getInstance().getUsuarioDAO().save(user);
                break;
            case "3":
                System.out.println("Informe um nome professor pra deletar");
                user = DAOFactory.getInstance().getProfessorDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                DAOFactory.getInstance().getProfessorDAO().delete(user.getCodigo());
                break;
            case "4":
                System.out.println("Informe um nome Get");
                user = DAOFactory.getInstance().getProfessorDAO().get(leia.readLine());
                if (user == null) {
                    System.out.println("Nome Invalido!");
                    break;
                }
                System.out.println(user);
                break;
            case "5":
                System.out.println("GET ALL");
                for (Object est : DAOFactory.getInstance().getEstudanteDAO().getAll()) {
                    if (est instanceof Professor) {
                        System.out.println((Professor) est);
                    }
                }
                break;
        }
    }

    private void MenuEnderecoUsuario() throws IOException, SQLException {
        System.out.println("Informe um usuario:");

        Usuario e = (Usuario) DAOFactory.getInstance().getUsuarioDAO().get(leia.readLine());

        if (e != null) {
            System.out.println("MEU ENDERECO");
            System.out.println(" 1 - Insert\n"
                    + " 2 - Update\n"
                    + " 3 - Delete\n"
                    + " 4 - GetALL");
            String opcao = leia.readLine();
            List<Endereco> listE = DAOFactory.getInstance().getEnderecoDAO().getAllID(e.getCodigo());
            switch (opcao) {
                case "1":
                    e.getEnderecos().addAll(InsertEndereco());
                    DAOFactory.getInstance().getUsuarioDAO().save(e);
                    break;
                case "2":
                    if (listE.isEmpty()) {
                        System.out.println("Não possui Endereço Cadastrado");
                    } else {
                        for (int i = 0; i < listE.size(); i++) {
                            System.out.println("Endereco Nº:" + (i + 1));
                            System.out.println(listE.get(i));
                        }
                        System.out.println("Numero do Endereco para Editar:");
                        Endereco end = listE.get(Integer.parseInt(leia.readLine()) - 1);

                        DAOFactory.getInstance().getEnderecoDAO().save(UpdateEndereco(end));
                    }
                    break;

                case "3":
                    if (listE.isEmpty()) {
                        System.out.println("Não possui Endereço Cadastrado");
                    } else {
                        for (int i = 0; i < listE.size(); i++) {
                            System.out.println("Endereco Nº:" + (i + 1));
                            System.out.println(listE.get(i));
                        }
                        System.out.println("Numero do Endereco para Delete:");
                        DAOFactory.getInstance().getEnderecoDAO().delete(listE.get(Integer.parseInt(leia.readLine()) - 1).getCodigo());
                    }
                    break;
                case "4":
                    System.out.println("---------- Lista de Enderecos do " + e.getNome() + "----------");
                    listE.forEach((endereco) -> {
                        System.out.println(endereco);
                    });
                    break;
            }

        } else {
            System.out.println("Nome Invalido!");
        }

    }

    private List<Endereco> InsertEndereco() throws IOException, SQLException {
        boolean flag = true;
        List<Endereco> listE = new ArrayList<>();

        while (flag) {
            System.out.println("Adicionar Endereco\n"
                    + "  1 - Adicionar Endereco\n"
                    + "  2 - Voltar");
            String opcaoTel = leia.readLine();

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
        endereco.setSemNumero(Boolean.parseBoolean(leia.readLine()));

        return endereco;
    }

    private Endereco UpdateEndereco(Endereco endereco) throws IOException {

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
        endereco.setSemNumero(Boolean.parseBoolean(leia.readLine()));

        return endereco;
    }

    private void MenuEmprestimo() throws IOException, SQLException {
        System.out.println("MENU EMPRESTIMOS");
        System.out.println(" 1 - Insert\n"
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL");
        String opcaoEmprestimo = leia.readLine();

        Emprestimo emprestimo = new Emprestimo();
        Usuario usuario = new Estudante();
        Livro l = new Livro();
        switch (opcaoEmprestimo) {
            case "1":
                System.out.print("Nome Do Livro: ");

                l = DAOFactory.getInstance().getLivro().get(leia.readLine());

                if (l.getNome() != null) {
                    emprestimo.setLivro(l);
                } else {
                    System.out.println("Livro nao encontrado");
                    break;
                }
                System.out.print("Usuario que esta emprestando: ");
                usuario = (Usuario) DAOFactory.getInstance().getUsuarioDAO().get(leia.readLine());
                if (usuario.getNome() != null) {
                    emprestimo.setUsuario(usuario);
                    emprestimo.setAtiva(true);
                    DAOFactory.getInstance().getEmprestimoDAO().save(emprestimo);
                } else {
                    System.out.println("Livro nao encontrado");

                }
                break;
            case "2":

                System.out.println("Informe a id do livro buscado para update");
                emprestimo = DAOFactory.getInstance().getEmprestimoDAO().get(Integer.parseInt(leia.readLine()));
                if (emprestimo.getUsuario() != null) {

                } else {
                    System.out.println("Emprestimo encontrado");
                    break;
                }
                System.out.print("Nome Do Livro: ");

                l = DAOFactory.getInstance().getLivro().get(leia.readLine());

                if (l.getNome() != null) {
                    emprestimo.setLivro(l);
                } else {
                    System.out.println("Livro nao encontrado");
                    break;
                }
                System.out.print("Usuario que esta emprestando: ");
                usuario = (Usuario) DAOFactory.getInstance().getUsuarioDAO().get(leia.readLine());
                if (usuario.getNome() != null) {
                    emprestimo.setUsuario(usuario);
                    emprestimo.setAtiva(true);
                    DAOFactory.getInstance().getEmprestimoDAO().save(emprestimo);
                } else {
                    System.out.println("Livro nao encontrado");

                }
                break;
            case "3":
                System.out.println("Emprestimos não sao excluidos");
                break;
            case "4":
                System.out.println("GET EMPRESTIMO POR ID");
                emprestimo = DAOFactory.getInstance().getEmprestimoDAO().get(Integer.parseInt(leia.readLine()));

                if (emprestimo.getUsuario() != null) {
                    System.out.println(emprestimo);
                } else {
                    System.out.println("Emprestimo encontrado");
                }
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
        System.out.println(" 1 - Insert\n"
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL");
        String opcaoAutores = leia.readLine();
        Autor a = new Autor();
        switch (opcaoAutores) {
            case "1":
                System.out.print("Nome: ");
                a.setNome(leia.readLine());
                System.out.println("Nacionalidade [BRASILEIRO, INGLES, MEXICANO, ITALIANO]: ");
                a.setNacionalidade(TipoNacionalidade.valueOf(leia.readLine()));
                DAOFactory.getInstance().getAutorDAO().save(a);
                break;
            case "2":
                System.out.println("Informe o nome do autor que deseja fazer update:");

                a = DAOFactory.getInstance().getAutorDAO().get(leia.readLine());
                if (a.getNome() == null) {
                    System.out.println("Nome Invalido !");

                } else {

                    System.out.print("Novo Nome: ");
                    a.setNome(leia.readLine());
                    System.out.println("Nova Nascionalidade [BRASILEIRO, INGLES, MEXICANO, ITALIANO]: ");
                    a.setNacionalidade(TipoNacionalidade.valueOf(leia.readLine()));
                    DAOFactory.getInstance().getAutorDAO().save(a);
                }
                break;

            case "3":
                System.out.println("Informe um nome para deletar:");
                a = DAOFactory.getInstance().getAutorDAO().get(leia.readLine());
                if (a.getNome() == null) {
                    System.out.println("Nome Invalido !");
                } else {
                    try {
                        DAOFactory.getInstance().getAutorDAO().delete(a.getCodigo());
                    } catch (SQLException e) {
                        System.out.println("Nao pode apagar um autor que ja esta registrado em livos" + e);
                    }
                }

                break;

            case "4":
                System.out.println("Informe nome de autor para get ");

                a = DAOFactory.getInstance().getAutorDAO().get(leia.readLine());
                if (a.getNome() == null) {
                    System.out.println("Nome Invalido !");
                } else {
                    System.out.println(a);
                }

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
        System.out.println(" 1 - Insert\n"
                + " 2 - Update\n"
                + " 3 - Delete\n"
                + " 4 - Get\n"
                + " 5 - GetALL\n");
        String opcaoLivros = leia.readLine();

        Livro l = new Livro();
        switch (opcaoLivros) {
            case "1":
                l = adicionarLivro(l);
                DAOFactory.getInstance().getLivro().save(l);
                break;
            case "2":
                System.out.println("Nome do Livro");
                l = DAOFactory.getInstance().getLivro().get(leia.readLine());
                if (l.getNome() == null) {
                    System.out.println("Livro Invalido");
                } else {
                    l = adicionarLivro(l);
                    DAOFactory.getInstance().getLivro().save(l);
                }
                break;
            case "3":
                System.out.println("Informe um numero do id do autor para excluir");
                l = DAOFactory.getInstance().getLivro().get(leia.readLine());
                if (l.getNome() == null) {
                    System.out.println("Livro Invalido");
                } else {
                    DAOFactory.getInstance().getLivro().delete(l.getCodigo());
                }

                break;
            case "4":
                System.out.println("Informe um Nome para get Livro");
                l = DAOFactory.getInstance().getLivro().get(leia.readLine());
                if (l.getNome() == null) {
                    System.out.println("Livro Invalido");
                } else {
                    System.out.println(l);
                }
                break;
            case "5":
                System.out.println("Get all");
                for (Livro livro : DAOFactory.getInstance().getLivro().getAll()) {
                    System.out.println(livro);
                }
                break;

        }
    }

    private Livro adicionarLivro(Livro l) throws IOException, SQLException {
        System.out.print("Nome: ");
        l.setNome(leia.readLine());
        System.out.println("Idiomas [BRASILEIRO, INGLES, MEXICANO, ITALIANO]:");
        l.setIdioma(TipoIdioma.valueOf(leia.readLine()));
        System.out.print("ISNB: ");
        l.setIsbn(Integer.parseInt(leia.readLine()));
        System.out.print("EDICAO:");
        l.setEdicao(Short.parseShort(leia.readLine()));
        System.out.println("Classificacao [ CIENTIFICO, COLECAO, MATEMATICA, HISTORIA, FILOSOFIA, ECONOMIA, ADMINISTRACAO_E_NEGOCIOS, ENGENHARIA, SOCIOLOGIA, LITERATURA, ARTES, PERIODICOS_CIENTIFICOS, PERIODICOS_INFORMATIVOS, ENTRETENIMENTO]:");
        l.setClassificacao(TipoClassificacao.valueOf(leia.readLine()));
        SubMenuAddEditora(l);
        SubMenuAddAutor(l);
        System.out.println("Informe a data de publicacao do livro");
        l.setAno_publicacao(SetDate());
        return l;
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

    private void SubMenuAddEditora(Livro l) throws IOException, SQLException {
        boolean flag = true;
        while (flag) {
            System.out.println("Editora:");
            Editora ed = DAOFactory.getInstance().getEditoraDAO().get(leia.readLine());
            if (ed.getNome() != null) {
                l.setEditora(ed);

                break;
            } else {
                System.out.println("Informe uma Editora Valida!");
            }
        }
    }

    private void SubMenuAddAutor(Livro l) throws IOException, SQLException {

        boolean flag = true;
        while (flag) {
            System.out.println("Menu Adicionar Autores\n"
                    + " 1- Adicionar Autor Pelo Nome \n"
                    + " 2 - Voltar");

            String opcaoAdicionarAutor = leia.readLine();
            switch (opcaoAdicionarAutor) {
                case "1":
                    boolean flagg = true;
                    while (flagg) {
                        System.out.print("Autor: ");
                        Autor aut = DAOFactory.getInstance().getAutorDAO().get(leia.readLine());
                        if (aut.getNome() != null) {
                            l.getAutores().add(aut);
                            System.out.println("Adicionou com sucesso");
                            flagg = false;
                        } else {
                            if (l.getAutores().isEmpty()) {
                                System.out.println("Informe pelo menos um Autor Valido!");
                            } else {
                                System.out.println("Autor Invalido");
                                break;
                            }
                        }
                    }

                    break;
                case "2":
                    flag = false;
                    break;

            }
        }
    }

}

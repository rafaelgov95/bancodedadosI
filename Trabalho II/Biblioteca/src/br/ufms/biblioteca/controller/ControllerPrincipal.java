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
                    + " 1 - Cadastrar Usuario\n"
                    + " 2 - Cadastrar Professor\n"
                    + " 3 - Cadastar Livro\n"
                    + " 4 - Cadastrar Municipio\n"
                    + " 5 - Livros Menu \n"
                    + " 6 - Autores Menu\n"
                    + " 7 - Emprestimos");

            String opcao = leia.readLine();
            switch (opcao) {
                case "1":
                    Estudante user = new Estudante();
                    System.out.print("Nome: ");
                    user.setNome("Rafael");
                    user.setTitulacao(TipoTitulacao.POS_DOUTOR);
                    user.setData_nascimento(LocalDate.now());
                    user.setFim_contrato(LocalDate.now());
                    System.out.println("CPF");
                    user.setCpf("31231321");
                    user.setCurso(TipoCurso.SISTEMA_DE_INFORMACAO);
                    System.out.println("RGA");
                    user.setRga("3122342");

                    Municipio m = new Municipio();
                    m.setIbge(1);
                    m.setNome("Coxim");
                    m.setUf(UF.MS);

                    Endereco endereco = new Endereco();
                    endereco.setRua("Salvina Maria Do Carmo");
                    endereco.setBairro("Flavio Garcia");
                    endereco.setComplemento("Ao Lado da Publisom");
                    endereco.setNumero((short) 12);
                    endereco.setCEP("79400-000");
                    endereco.setSemNumero(false);
                    endereco.setMunicipio(m);

                    user.getEnderecos().add(endereco);
                    Telefone t = new Telefone();
                    t.setDDD("24");
                    t.setNumero("12312311");
                    t.setPrincipal(true);
                    t.setTipo(TipoTelefone.CELULAR);

                    user.getTelefones().add(t);
                    System.out.println(user.getEnderecos().get(0).getMunicipio().getCodigo());
                    DAOFactory.getInstance().getUsuarioDAO().save(user);
//                    user.getTelefones().get(1).setDDD("66");
//                    user.setNome("Rafael Do Verde Bom");
//                    DAOFactory.getInstance().getUsuarioDAO().save(user);
                    break;

                case "2":
                    Endereco en = new Endereco();
                    en.setRua("Salvina Maria Do Carmo");
                    en.setBairro("Flavio Garcia");
                    en.setComplemento("Ao Lado da Publisom");
                    en.setNumero((short) 12);
                    en.setCEP("79400-000");
                    en.setSemNumero(false);
//                    en.setMunicipio(m);

                    ;

                    Livro livro = new Livro();
                    livro.setNome("Rafael e o Ladrao de Codigo");
                    livro.setIsbn(23123123);
                    livro.setIdioma(TipoIdioma.INGLES);
                    livro.setAno_publicacao(LocalDate.now());
                    livro.setClassificacao(TipoClassificacao.CIENTIFICO);
                    livro.setEdicao(Short.parseShort("5"));

                    Autor autor = DAOFactory.getInstance().getAutorDAO().get(1);
                    System.out.println(autor.getNome());
                    List<Autor> autores = new ArrayList<>();
                    autores.add(autor);
                    livro.setAutores(autores);
                    System.out.println(livro.getAutores().get(0).getNome());

                    DAOFactory.getInstance().getLivro().insert(livro);

                    Professor p = new Professor();
                    p.setNome("Kleber");
                    p.setData_nascimento(LocalDate.now());
                    p.setFim_contrato(LocalDate.now());
                    p.setCpf("31231231");
                    p.setTitulacao(TipoTitulacao.GRADUANDO);
                    p.setCurso(TipoCurso.SISTEMA_DE_INFORMACAO);
                    p.setSiap(31231321);
                    p.setAdmissao(LocalDate.now());
                    p.setIs_substituto(false);
                    DAOFactory.getInstance().getUsuarioDAO().save(p);
                    p.setCpf("888888888");
                    p.setNome("Kleber Kruger"
                    );
                    p.setCpf("3123132");
                    Emprestimo empr = new Emprestimo();
                    empr.setAtiva(true);
                    empr.setUsuario(p);
                    empr.setLivro(livro);
                    p.getEmprestimos().add(empr);
                    DAOFactory.getInstance().getUsuarioDAO().save(p);

                    break;
                case "3":
//                    Endereco en = new Endereco();
//                    en.setRua("Salvina Maria Do Carmo");
//                    en.setBairro("Flavio Garcia");
//                    en.setComplemento("Ao Lado da Publisom");
//                    en.setNumero((short) 12);
//                    en.setCEP("79400-000");
//                    en.setSemNumero(false);
////                    en.setMunicipio(m);
//
//                    Editora editora = new Editora();
//                    editora.setNome("Rafael Producoes");
//                    editora.setEndereco(en);
//
//                    Livro livro = new Livro();
//                    livro.setNome("Rafael e o Ladrao de Codigo");
//                    livro.setIsbn(23123123);
//                    livro.setIdioma(TipoIdioma.INGLES);
//                    livro.setAno_publicacao(LocalDate.now());
//                    livro.setClassificacao(TipoClassificacao.CIENTIFICO);
//                    livro.setEdicao(Short.parseShort("5"));
//                    livro.setEditora(editora);
//                    
//                    Autor autor = DAOFactory.getInstance().getAutorDAO().get(1);
//                    System.out.println(autor.getNome());
//                    List<Autor> autores = new ArrayList<>();
//                    autores.add(autor);
//                    livro.setAutores(autores);
//                    System.out.println(livro.getAutores().get(0).getNome());
//                    
//                    DAOFactory.getInstance().getLivro().insert(livro);
//                  
                    break;
                case "4":
                    Usuario bean = (Usuario) DAOFactory.getInstance().getUsuarioDAO().get(2);

                    if (bean == null) {
                        System.out.println("Bean e Nullo");
                    } else if (bean instanceof Estudante) {
                        System.out.println("Estudante");
                    } else if (bean instanceof Professor) {
                        System.out.println("Professor");
                        System.out.println(((Professor) bean).getNome());
                        System.out.println(((Professor) bean).getCodigo());
                        System.out.println(((Professor) bean).getSiap());
                        System.out.println(((Professor) bean).getAdmissao());
                    }
                    break;
                case "5":
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
                            System.out.println("Todos Altores Ate o Momento Fez esse livro e estao sendo adicionados");
//                            for (Autor autore : DAOFactory.getInstance().getAutorDAO().getAll()) {
//                                if (autore != null) {
//                                    l.getAutores().add(autore);
//                                }
//                            }
                            l.getAutores().addAll(DAOFactory.getInstance().getAutorDAO().getAll());
                            System.out.println("Informe a data de publicacao do livro");

                            String data = leia.readLine();
                            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            if (!data.equals("")) {
                                LocalDate dt = LocalDate.parse(data, f);
                                l.setAno_publicacao(dt);
                            } else {
                                l.setAno_publicacao(null);
                            }

                            DAOFactory.getInstance().getLivro().save(l);
                            break;
                        case "2":
//                            a = DAOFactory.getInstance().getAutorDAO().get(1);
//                            System.out.print("Novo Nome");
//                            a.setNome(leia.readLine());
//                            System.out.print("Nova Nascionalidade");
//                            a.setNacionalidade(TipoNacionalidade.valueOf(leia.readLine()));
                            break;
                        case "3":
                            DAOFactory.getInstance().getLivro().delete(3);
                            break;
                        case "4":
//                            a = DAOFactory.getInstance().getAutorDAO().get(1);
//                            System.out.println(a.getNome());
//                            System.out.println(a.getNacionalidade().toString());
                            break;
                        case "5":
                            break;

                    }
                    break;
                case "6":
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
                            a = DAOFactory.getInstance().getAutorDAO().get(1);
                            System.out.println(a.getNome());
                            System.out.println(a.getNacionalidade().toString());
                            break;
                        case "5":
                            break;

                    }
                    break;
                case "7":
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
                            emprestimo = DAOFactory.getInstance().getEmprestimoDAO().get(Integer.parInt(leia.readLine()));
                            System.out.print("Id Livro: ");
                            emprestimo.setLivro((Livro) DAOFactory.getInstance().getLivro().get(Integer.parseInt(leia.readLine())));
                            System.out.print("Usuario que esta emprestando");
                            emprestimo.setUsuario((Usuario) DAOFactory.getInstance().getUsuarioDAO().get(Integer.parseInt(leia.readLine())));
                            System.out.print("Ativa ou Desativa");
                            emprestimo.setAtiva(Boolean.parseBoolean(leia.readLine()));
                            DAOFactory.getInstance().getEmprestimoDAO().save(emprestimo);
                            break;
                        case "3":
                            DAOFactory.getInstance().getAutorDAO().delete(Integer.parseInt(leia.readLine()));
                            break;
                        case "4":
//                            emprestimo = DAOFactory.getInstance().getAutorDAO().get(1);
//                            System.out.println(emprestimo.getNome());
//                            System.out.println(emprestimo.getNacionalidade().toString());
                            break;
                        case "5":
                            break;

                    }

                default:
                    System.out.println("Escolha Opcao Certa");
                    break;
            }

        }
    }

    private void MenuCadastroEndereco() throws SQLException {

    }
}

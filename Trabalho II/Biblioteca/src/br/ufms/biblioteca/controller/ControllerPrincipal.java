/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.biblioteca.controller;

import br.ufms.biblioteca.model.bean.Autor;
import br.ufms.biblioteca.model.bean.Editora;
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
import br.ufms.biblioteca.model.bean.enumerate.Titulacao;
import br.ufms.biblioteca.model.dao.DAOFactory;
import br.ufms.biblioteca.model.dao.EnderecoDAO;
import br.ufms.biblioteca.model.dao.EstudanteDAO;
import com.sun.jndi.ldap.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
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
                    + " 1 - Cadastrar Usuario\n"
                    + " 2 - Cadastrar Professor\n"
                    + " 3 - Cadastar Livro\n"
                    + " 4 - Cadastrar Municipio\n"
                    + " 5 - Cadastrar Telefone\n");

            String opcao = leia.readLine();
            switch (opcao) {
                case "1":
                    Estudante user = new Estudante();
                    System.out.print("Nome: ");
                    user.setNome("Rafael");
                    user.setTitulacao(Titulacao.POS_DOUTOR);
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
                    Professor p = new Professor();
                    p.setNome("Kleber");
                    p.setData_nascimento(LocalDate.now());
                    p.setFim_contrato(LocalDate.now());
                    p.setCpf("31231231");
                    p.setTitulacao(Titulacao.GRADUANDO);
                    p.setCurso(TipoCurso.SISTEMA_DE_INFORMACAO);
                    p.setSiap(31231321);
                    p.setAdmissao(LocalDate.now());
                    p.setIs_substituto(false);
                    DAOFactory.getInstance().getUsuarioDAO().save(p);
                    p.setCpf("888888888");
                    p.setNome("LUCAS LUCO"
                    );
                    p.setCpf("3123132");
                    DAOFactory.getInstance().getUsuarioDAO().save(p);
                    break;
                case "3":
                    Endereco en = new Endereco();
                    en.setRua("Salvina Maria Do Carmo");
                    en.setBairro("Flavio Garcia");
                    en.setComplemento("Ao Lado da Publisom");
                    en.setNumero((short) 12);
                    en.setCEP("79400-000");
                    en.setSemNumero(false);
//                    en.setMunicipio(m);

                    Editora editora = new Editora();
                    editora.setNome("Rafael Producoes");
                    editora.setEndereco(en);

                    Livro livro = new Livro();
                    livro.setNome("Rafael e o Ladrao de Codigo");
                    livro.setIsbn(23123123);
                    livro.setIdioma(TipoIdioma.INGLES);
                    livro.setAno_publicacao(LocalDate.now());
                    livro.setClassificacao(TipoClassificacao.CIENTIFICO);
                    livro.setEdicao(Short.parseShort("5"));
                    livro.setEditora(editora);
                    Autor autor = DAOFactory.getInstance().getAutorDAO().get(1);
                    System.out.println(autor.getNome());
                    List<Autor> autores = new ArrayList<>();
                    autores.add(autor);
                    livro.setAutores(autores);
                    System.out.println(livro.getAutores().get(0).getNome());
                    
                    
                    DAOFactory.getInstance().getLivro().insert(livro);
                    break;
                default:
                    System.out.println("Escolha Opcao Certa");
                    break;
            }

        }
    }

    private void MenuCadastroEndereco() throws SQLException {

//        return ;//        DAOFactory.getInstance().getEnderecoDAO().insert(endereco, n);
    }
}

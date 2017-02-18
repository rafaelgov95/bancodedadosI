/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.teste;

import br.ufms.agenda.model.bean.Contato;
import br.ufms.agenda.model.bean.Telefone;
import br.ufms.agenda.model.bean.TipoTelefone;
import br.ufms.agenda.model.dao.DAOFactory;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class ContatoTester extends DAOTester<Contato, Integer> {

    public ContatoTester() {
        super(DAOFactory.getInstance().getContatoDAO());
    }
    TelefoneTester testeTelefone = new TelefoneTester();

    @Override
    protected void printBean(Contato bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("Nome: ").append(bean.getNome()).append("\n");
        output.append("Nacimento: ").append(bean.getData_nascimento()).append("\n");
        output.append("Criação: ").append(bean.getData_at()).append("\n");
        output.append("Telefone: ").append(bean.getTelefones()).append("\n");
        System.out.println(output);
        System.out.print("Lista de Telefones:\n");
        bean.getTelefones().stream().forEach((t) -> {
            testeTelefone.printBean(t);
        });;
    }

    @Override
    protected Contato createBean() {
        Contato contato = new Contato();
        contato.setNome("KLEBER");
        contato.setData_nascimento(LocalDate.of(1985, Month.MARCH, 24));

        List<Telefone> lista = new ArrayList<>();
        Telefone t1 = new Telefone();
        t1.setNumero("98984949");
        t1.setPrincipal(true);
        t1.setTipo(TipoTelefone.CELULAR);
        lista.add(t1);

        return contato;
    }

    @Override
    protected void updateBean(Contato bean) {
        bean.setNome("Irineu");
    }

    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(1);
    }

}

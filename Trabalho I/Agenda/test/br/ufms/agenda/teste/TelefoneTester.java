/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.teste;

import br.ufms.agenda.model.bean.Telefone;
import br.ufms.agenda.model.bean.TipoTelefone;
import br.ufms.agenda.model.dao.DAOFactory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class TelefoneTester extends DAOTester<Telefone, Integer> {

    public TelefoneTester() {
        super(DAOFactory.getInstance().getTelefoneDAO());
    }

    @Override
    protected void printBean(Telefone bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("Número: ").append("(").append(bean.getDDD()).append(") ").append(bean.getNumero()).append("\n");
        output.append("Tipo: ").append(bean.getTipo()).append("\n");
        output.append("Principal: ").append(bean.getPrincipal()).append("\n");
        System.out.println(output);
    }

    @Override
    protected Telefone createBean() {
        Telefone tel = new Telefone();
        tel.setDDD("66");
        tel.setNumero("6799992222");
        tel.setTipo(TipoTelefone.CELULAR);
        tel.setPrincipal(true);
        return tel;
    }

    @Override
    protected void updateBean(Telefone bean) {
        bean.setDDD("66");
        bean.setNumero("999505050");
        bean.setTipo(TipoTelefone.RESIDENCIAL);
        bean.setPrincipal(true);
    }

    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(2);
    }
}

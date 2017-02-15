
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.bean.Contato;
import model.bean.Telefone;
import model.bean.TipoTelefone;
import model.dao.DAOFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public class ContatoTester extends DAOTester<Contato, Integer> {

    public ContatoTester() {
        super(DAOFactory.getInstance().getContatoDAO());
    }

    @Override
    protected void printBean(Contato bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("Nome: ").append(bean.getNome()).append("\n");
        output.append("Data criação: ").append(bean.getData_criacao()).append("\n");
        output.append("Data nascimento: ").append(bean.getData_nascimento()).append("\n");
//        output.append("Telefones: ").append(bean.getListaTelefones()).append("\n"); 
//        System.out.println("======Lista de Telefones======");
//        if(bean.getListaTelefones().isEmpty()){
//            System.out.println("Sem Telefone Registrado");
//        }else{
//        bean.getListaTelefones().forEach((t) -> {
//            System.out.println(t.getNumero());
//        });
//        }
    }

    @Override
    protected Contato createBean() {
        Contato c = new Contato();
        c.setNome("Rafael Viana");
        c.setData_nascimento(LocalDate.of(1995, Month.JUNE, 22));
//        List<Telefone> lista;
//        lista = new ArrayList<>();
//        Telefone t = new Telefone();
//        t.setDDD("67");
//        t.setNumero("999-507-979");
//        t.setPrincipal(true);
//        t.setTipo(TipoTelefone.CELULAR);
//        lista.add(t);
        return c;
    }

    @Override
    protected void updateBean(Contato bean) {
        bean.setNome("Dr.prof. Rafael Viana");
    }

    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(0);
    }

}

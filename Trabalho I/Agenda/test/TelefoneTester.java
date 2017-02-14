
import java.io.Serializable;
import java.util.List;
import model.bean.Telefone;
import model.bean.TipoTelefone;
import model.dao.DAOFactory;

/**
 *
 * @author rafael
 */
public class TelefoneTester extends DAOTester<Telefone, Integer> {
    
    public TelefoneTester() {
        super(DAOFactory.getInstance().getTelefoneDAO());
    }
    
    @Override
    protected void printBean(Telefone bean) {
        StringBuilder output = new StringBuilder();
        output.append("Código: ").append(bean.getCodigo()).append("\n");
        output.append("DDD: ").append(bean.getDDD()).append("\n");
        output.append("Número: ").append(bean.getNumero()).append("\n");
        output.append("Tipo: ").append(bean.getTipo()).append("\n");
        output.append("Principal: ").append(bean.getPrincipal()).append("\n");
        System.out.println(output);
    }
    
    @Override
    protected Telefone createBean() {
        Telefone t = new Telefone();
        t.setDDD("67");
        t.setNumero("999-507-979");
        t.setTipo(TipoTelefone.CELULAR);
        t.setPrincipal(true);
        return t;
    }
    
    @Override
    protected void updateBean(Telefone bean) {
        bean.setNumero("999-111-172");
        bean.setTipo(TipoTelefone.COMERCIAL);
        bean.setPrincipal(true);
    }
    
    @Override
    protected void insertDependencyList(List<Serializable> dependencies) {
        dependencies.add(1);
    }
}

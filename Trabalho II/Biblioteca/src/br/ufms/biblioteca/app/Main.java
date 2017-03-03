package br.ufms.biblioteca.app;

import br.ufms.biblioteca.controller.ControllerPrincipal;
import br.ufms.biblioteca.model.bean.Estudante;
import br.ufms.biblioteca.model.bean.Usuario;
import br.ufms.biblioteca.model.dao.EstudanteDAO;
import br.ufms.biblioteca.model.dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        
            
            new ControllerPrincipal();
            
//            EstudanteDAO dao = new EstudanteDAO();
//            dao.get(6);
//            
//            UsuarioDAO uDAO = new UsuarioDAO(Usuario.class) {
//                @Override
//                protected void insertAbst(Connection conn, Usuario bean) throws SQLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//                @Override
//                protected void updateAbst(Connection conn, Usuario bean) throws SQLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//                @Override
//                protected Usuario resultSetToBean(Connection conn, ResultSet rs) throws SQLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//                @Override
//                protected String sqlToGet() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//                @Override
//                protected String sqlToGetAll() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//                
//                @Override
//                protected void delete(Connection conn, Serializable codigo) throws SQLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            };
//            
//            Usuario u = (Usuario) uDAO.get(6);        
//            if (u instanceof Estudante) {
//                Estudante e = (Estudante) u;
//            }
//            
//            
        

    }

}

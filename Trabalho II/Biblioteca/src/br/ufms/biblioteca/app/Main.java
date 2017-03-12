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

    }

}

package br.ufms.agenda.App;

import br.ufms.agenda.controller.ControllerAgenda;
import java.io.IOException;
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
        new ControllerAgenda();        
    }

}

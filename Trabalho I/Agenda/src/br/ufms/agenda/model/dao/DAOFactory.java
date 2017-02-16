/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.agenda.model.dao;

/**
 *
 * @author rafael
 */
public class DAOFactory {

    private final TelefoneDAO telefoneDAO;
    private final ContatoDAO contatoDAO;

    private DAOFactory() {
        this.telefoneDAO = new TelefoneDAO();
        this.contatoDAO = new ContatoDAO();
    }
    
    
    /**
     * Gets the single instance of DAOFactory class.
     *
     * @return the singleton instance
     */
    public static DAOFactory getInstance() {
        return DAOFactoryHolder.INSTANCE;
    }

    /**
     * @return the telefoneDAO
     */
    public TelefoneDAO getTelefoneDAO() {
        return telefoneDAO;
    }

    /**
     * @return the contatoDAO
     */
    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    /**
     * Classe privada que armazena a única instância de DAOFactory.
     */
    private static class DAOFactoryHolder {

        private static final DAOFactory INSTANCE = new DAOFactory();
    }

}

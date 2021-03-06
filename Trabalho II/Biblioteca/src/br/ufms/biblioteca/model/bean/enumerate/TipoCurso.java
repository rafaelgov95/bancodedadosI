package br.ufms.biblioteca.model.bean.enumerate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public enum TipoCurso {
    SISTEMAS_DE_INFORMACAO, DIREITO, HISTORIA, LETRAS, QUIMICA, MATEMATICA, FISICA, ANALISE_DE_SISTEMAS;

    @Override
    public String toString() {
        switch (this) {
            case SISTEMAS_DE_INFORMACAO:
                return "SISTEMAS DE INFORMAÇÂO";
            case DIREITO:
                return "DIREITO";
            case HISTORIA:
                return "HISTORIA";
            case LETRAS:
                return "LETRAS";
            case QUIMICA:
                return "QUIMICA";
            case MATEMATICA:
                return "MATEMATICA";
            case FISICA:
                return "FISICA";
            case ANALISE_DE_SISTEMAS:
                return "ANALISE DE SISTEMAS";
            default:
                return "";
        }
    }

    public static TipoCurso setCurso(String curso) {
        switch (curso) {
            case "SISTEMAS DE INFORMAÇÂO":
                return SISTEMAS_DE_INFORMACAO;
            case "DIREITO":
                return DIREITO;
            case "HISTORIA":
                return HISTORIA;
            case "LETRAS":
                return LETRAS;
            case "QUIMICA":
                return QUIMICA;
            case "MATEMATICA":
                return MATEMATICA;
            case "FISICA":
                return FISICA;
            case "ANALISE DE SISTEMAS":
                return ANALISE_DE_SISTEMAS;
            default:
                return null;
        }
    }
}

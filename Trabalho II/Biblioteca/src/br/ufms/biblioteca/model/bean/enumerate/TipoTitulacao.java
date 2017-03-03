/*
 * Copyright (C) 2016 kleberkruger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ufms.biblioteca.model.bean.enumerate;


/**
 *
 * @author kleberkruger
 */
public enum TipoTitulacao {

    GRADUANDO, GRADUADO, ESPECIALISTA, MESTRE, DOUTOR, POS_DOUTOR;

    @Override
    public String toString() {
        switch (this) {
            case GRADUADO:
                return "Graduado";
            case GRADUANDO:
                return "Graduando";
            case ESPECIALISTA:
                return "Especialista";
            case MESTRE:
                return "Mestre";
            case DOUTOR:
                return "Doutor";
            case POS_DOUTOR:
                return "Pos Doutor";

            default:
                return "";
        }
    }

    public static TipoTitulacao setTitulacao(String titulacao) {
        switch (titulacao) {
            case "Mestre":
                return MESTRE;
            case "Graduando":
                return GRADUANDO;
            case "Graduado":
                return GRADUADO;
            case "Doutor":
                return DOUTOR;
            case "Pos Doutor":
                return POS_DOUTOR;
            default:
                return null;
        }
    }
}

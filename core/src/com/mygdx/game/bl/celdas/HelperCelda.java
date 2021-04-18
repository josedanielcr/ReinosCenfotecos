package com.mygdx.game.bl.celdas;

import java.util.Random;

public class HelperCelda {

    /**
     * Método que permite obtener la coordenada X de cualquier celda del tablero según su ID.
     * @param id El ID de la celda del tablero.
     * @param ancho El ancho preestablecido para cada celda.
     * @param posInicial La posición inicial en X de la primera celda.
     * @return La coordenada X de la celda consultada.
     */
    public static int getX(int id, int ancho, int posInicial) {
        int xPos;
        if (id<=20) {
            xPos = posInicial + ((id-1) * ancho);
        }
        else if (id<=400) {
            xPos = posInicial + ((id-1) % 20 * ancho);
        }
        else {
            xPos = posInicial + (id % 20 * ancho);
        }
        return xPos;
    }

    /**
     * Método que permite obtener la coordenada Y de cualquier celda del tablero según su ID.
     * @param id El ID de la celda del tablero.
     * @param alto El ancho preestablecido para cada celda.
     * @param posInicial La posición inicial en Y de la primera celda.
     * @return La coordenada Y de la celda consultada.
     */
    public static int getY(int id, int alto, int posInicial, int idInicialTablero, int idInicialCastillo) {
        int yPos;
        if (id <= idInicialTablero + 399) {
            if (id < 21) {
                yPos = posInicial;
            }
            else {
                yPos = posInicial + ((id-1) / 20 * alto);
            }
        }
        else if (id < idInicialCastillo+20) {
            yPos = posInicial - alto;
        }
        else {
            yPos = posInicial + (alto*20);
        }

        return yPos;
    }

    /**
     * Método que obtiene una celda al azar del Jugador 1 para colocar el Castillo.
     * @param idInicialCastillo El ID de la primera celda de zona de castillo.
     * @return El ID de zona de castillo seleccionada al azar para el Jugador 1.
     */
    public static int randomCastilloBlue(int idInicialCastillo) {
        int seleccion;
        Random rand = new Random();
        seleccion = rand.nextInt(20);
        return idInicialCastillo+seleccion;
    }

    /**
     * Método que obtiene una celda al azar del Jugador 2 para colocar el Castillo.
     * @param idInicialCastillo El ID de la primera celda de zona de castillo.
     * @return El ID de zona de castillo seleccionada al azar para el Jugador 2.
     */
    public static int randomCastilloRed (int idInicialCastillo) {
        int seleccion;
        Random rand = new Random();
        seleccion = rand.nextInt(20);
        return idInicialCastillo+20+seleccion;
    }


    /**
     * Método que notifica si se ha llegado a la celda final del tablero de juego.
     * @param id El ID de celda actualmente generado.
     * @param idInicialAreaCastillo El ID de la primera celda de zona de castillo.
     * @return Retorna true si se ha generado la última celda del tablero.
     */
    public static boolean checkFinalTable(int id, int idInicialAreaCastillo) {
        boolean celdaFinal = false;
        if (id == idInicialAreaCastillo+40) {
            celdaFinal = true;
        }
        return celdaFinal;

    }
}

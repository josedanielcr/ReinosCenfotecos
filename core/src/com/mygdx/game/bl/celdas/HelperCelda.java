package com.mygdx.game.bl.celdas;

import java.util.Random;

public class HelperCelda {

    public static int getX(int id, int ancho, int posInicial) {
        int xPos;
        if (id > 19) {
            xPos = posInicial + (id % 20 * ancho);
        }
        else {
            xPos = posInicial + (id * ancho);
        }
        return xPos;
    }

    public static int getY(int id, int alto, int posInicial, int idInicialTablero, int idInicialCastillo) {
        int yPos;
        if (id <= idInicialTablero + 399) {
            if (id > 19) {
                yPos = posInicial + (id / 20 * alto);
            }
            else {
                yPos = posInicial + (id * alto);
            }
        }
        else if (id <= idInicialCastillo+20) {
            yPos = posInicial - alto;
        }
        else {
            yPos = posInicial + (alto*20);
        }

        return yPos;
    }

    public static int randomCastilloRed(int idInicialCastillo) {
        int seleccion;
        Random rand = new Random();
        seleccion = rand.nextInt(21);
        return idInicialCastillo+seleccion-1;
    }

    public static int randomCastilloBlue (int idInicialCastillo) {
        int seleccion;
        Random rand = new Random();
        seleccion = rand.nextInt(20);
        return idInicialCastillo+20+seleccion-1;
    }


    public static boolean checkFinalTable(int id, int idInicialAreaCastillo) {
        return id == idInicialAreaCastillo+40;
    }
}

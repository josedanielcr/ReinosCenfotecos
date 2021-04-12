package com.mygdx.game.tl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.bl.celdas.HelperCelda;
import com.mygdx.game.bl.celdas.Prototipos.Celda_Castillo;
import com.mygdx.game.bl.celdas.Prototipos.Celda_Tablero;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

import java.util.ArrayList;

public class ControllerCelda {
    private final ArrayList<Celda> arrCeldas = new ArrayList<>();
    private int idCeldaTablero;
    private int idCeldaCastillo;
    private final int idInicialTablero;
    private final int idInicialCastillo;
    private final Celda prototipoCeldaCastillo;
    private final Celda prototipoCeldaTablero;
    public static final TextureAtlas cellAtlas = new TextureAtlas("cells.atlas");


    //***********************************************************************************************
    // CONSTRUCTOR
    //***********************************************************************************************

    public ControllerCelda(int pId1, int pId2, int pLifePoints){
        idCeldaTablero = pId1;
        idCeldaCastillo = pId2;
        idInicialTablero = pId1;
        idInicialCastillo = pId2;
        prototipoCeldaCastillo = new Celda_Castillo(pId1, pLifePoints);
        prototipoCeldaTablero = new Celda_Tablero(pId2);
        createCellArray();
    }

    //***********************************************************************************************
    // MÉTODOS DEL CONTROLADOR
    //***********************************************************************************************

    /**
     * Método que clona prototipos de celdas.
     * @param pNum La posición que el clon de celda tendrá en el arreglo.
     */
    public void nuevaCelda(int pNum) {
        int numCelda;
        if (pNum < 400) {
            arrCeldas.add(prototipoCeldaTablero.clone());
            numCelda = idCeldaTablero;
            idCeldaTablero ++;

        }else {
            arrCeldas.add(prototipoCeldaCastillo.clone());
            numCelda = idCeldaCastillo;
            idCeldaCastillo ++;
        }

        updateCloneCelda(pNum, numCelda);
        crearCastilloBlue(idCeldaCastillo, idInicialCastillo);
        crearCastilloRed(idCeldaCastillo, idInicialCastillo);
    }

    /**
     * Método que modifica los atributos seleccionados de los clones para generar nuevas celdas.
     * @param pId La posición de la celda en el arreglo.
     * @param numCelda El ID de la nueva celda.
     */
    private void updateCloneCelda(int pId, int numCelda) {
        Celda c = arrCeldas.get(pId);

        //Cambio de atributos. Generación al azar de castillos
        c.setId(numCelda);
        c.setBoundingBoxX(HelperCelda.getX(c.getId(), (int) c.getBoundingBox().width, (int) c.getBoundingBox().x));
        c.setBoundingBoxY(HelperCelda.getY(c.getId(), (int) c.getBoundingBox().height, (int) c.getBoundingBox().y, idInicialTablero, idInicialCastillo));



    }

    /**
     * Método que crea el castillo del Jugador Azul en una celda apropiada al azar si se ha construido el tablero.
     * @param pIdCeldaCastilloActual El ID de la última celda construida.
     * @param pIdCeldaInicial El ID de la primera celda construida.
     */
    public void crearCastilloBlue(int pIdCeldaCastilloActual, int pIdCeldaInicial) {
        if (HelperCelda.checkFinalTable(pIdCeldaCastilloActual, pIdCeldaInicial)) {
            int num = HelperCelda.randomCastilloBlue(pIdCeldaInicial);
            for (Celda c : arrCeldas) {
                if (c.getId()==num) {
                    c.setObjectCell("blue");
                    break;
                }
            }
        }
    }

    /**
     * Método que crea el castillo del Jugador Rojo en una celda apropiada al azar si se ha construido el tablero.
     * @param pIdCeldaCastilloActual El ID de la última celda construida.
     * @param pIdCeldaInicial El ID de la primera celda construida.
     */
    public void crearCastilloRed(int pIdCeldaCastilloActual, int pIdCeldaInicial) {
        if (HelperCelda.checkFinalTable(pIdCeldaCastilloActual, pIdCeldaInicial)) {
            int num = HelperCelda.randomCastilloRed(pIdCeldaInicial);
            for (Celda c : arrCeldas) {
                if (c.getId()==num) {
                    c.setObjectCell("red");
                }
            }
        }
    }

    /**
     * Método que llena el arreglo de las celdas del tablero.
     */
    public void createCellArray() {
        for(int i = 0; i<440;i++){
            nuevaCelda(i);
        }
    }

    /**
     * Método que permite obtener el arreglo de celdas del tablero de juego.
     * @return El arreglo con las celdas del tablero.
     */
    public ArrayList<Celda> getCellArray() {
        return this.arrCeldas;
    }

    public void variarCelda(int id) {
        arrCeldas.get(id).setCellTextureRegion(cellAtlas.findRegion("redCastleCell"));
    }

}

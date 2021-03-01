package com.mygdx.game.tl;

import com.mygdx.game.bl.celdas.HelperCelda;
import com.mygdx.game.bl.celdas.Prototipos.Celda_Castillo;
import com.mygdx.game.bl.celdas.Prototipos.Celda_Tablero;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;

import java.util.ArrayList;

public class ControllerCelda {
    private static ArrayList<Celda> arrCeldas = new ArrayList<>();
    private int idCeldaTablero;
    private int idCeldaCastillo;
    private Celda prototipoCeldaCastillo;
    private Celda prototipoCeldaTablero;

    public ControllerCelda(int pId1, int pId2, int pLifePoints){
        idCeldaTablero = pId1;
        idCeldaCastillo = pId2;
        prototipoCeldaCastillo = new Celda_Castillo(pId1, pLifePoints);
        prototipoCeldaTablero = new Celda_Tablero(pId2);
        createCellArray();
    }

    public void nuevaCelda(int pNum) {
        int numCelda = 0;
        if (pNum <= 400) {
            arrCeldas.add(prototipoCeldaTablero.clone());
            numCelda = idCeldaTablero;
            idCeldaTablero ++;

        }else {
            arrCeldas.add(prototipoCeldaCastillo.clone());
            numCelda = idCeldaCastillo;
            idCeldaCastillo ++;
        }

        updateCloneCelda(pNum, numCelda);
        crearCastilloBlue(pNum, idCeldaCastillo);
        crearCastilloRed(pNum, idCeldaCastillo);
    }

        private void updateCloneCelda(int pId, int numCelda) {
        Celda c = arrCeldas.get(pId);

        //Cambio de atributos. Generación al azar de castillos
        c.setId(numCelda);
        c.setBoundingBoxX(HelperCelda.getX(c.getId(), (int) c.getBoundingBox().width, (int) c.getBoundingBox().x));
        c.setBoundingBoxX(HelperCelda.getY(c.getId(), (int) c.getBoundingBox().height, (int) c.getBoundingBox().y, idCeldaTablero, idCeldaCastillo));



    }

    public void crearCastilloBlue(int pNum, int pIdCeldaInicial) {
        for (Celda c : arrCeldas) {
            if (HelperCelda.checkFinalTable(pNum, pIdCeldaInicial)) {
                if (c.getId()==HelperCelda.randomCastilloBlue(pIdCeldaInicial)) {
                    c.setTextureCastleCell("blue");
                }
            }
        }
    }

    public void crearCastilloRed(int pNum, int pIdCeldaInicial) {
        for (Celda c : arrCeldas) {
            if (HelperCelda.checkFinalTable(pNum, pIdCeldaInicial)) {
                if (c.getId()==HelperCelda.randomCastilloRed(pIdCeldaInicial)) {
                    c.setTextureCastleCell("red");
                }
            }
        }
    }

    public void createCellArray() {
        for(int i = 1; i<440;i++){
            nuevaCelda(i);
        }
    }


    public ArrayList<Celda> getCellArray() {
        return arrCeldas;
    }

}

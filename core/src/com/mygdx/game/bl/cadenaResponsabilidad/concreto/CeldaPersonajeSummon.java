package com.mygdx.game.bl.cadenaResponsabilidad.concreto;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

import java.util.Random;

public class CeldaPersonajeSummon extends HandlerSummon {
    public CeldaPersonajeSummon(ControllerCelda gCell, ControllerPersonaje gPer) {
        super(gCell, gPer);
    }

    @Override
    public boolean executeTask(Task pTask) {
        boolean resultado;
        int id = gPer.obtenerId();

        if (pTask.getType().equals("summon")) {
            int random = getRandomCell();
            Rectangle summonArea = pTask.getCellsPattern().get(random).getBoundingBox();
            if (pTask.getColorJugador().equals("blue")) {
                gPer.crearPersonaje(id, pTask.getTypeSummon(), 1, pTask.getColorJugador(), summonArea);
                gCell.getCell(pTask.getCellsPattern().get(random).getId()).setIdPersonaje(id);
            }
            if (pTask.getColorJugador().equals("red")) {
                gPer.crearPersonajeEnemigo(id, summonArea);
                gCell.getCell(pTask.getCellsPattern().get(random).getId()).setIdPersonaje(id);
                gPer.setLastEnemySummonId(id);
            }

            resultado = true;
        }
        else if (successor!= null) {
            resultado = successor.executeTask(pTask);
        }
        else {
            resultado = false;
        }
        return resultado;
    }

    private int getRandomCell() {
        Random rand = new Random();
        return rand.nextInt(5);
    }



}

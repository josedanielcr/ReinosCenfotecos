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

        if (pTask.getType().equals("summon")) {
            Rectangle summonArea = pTask.getCellsPattern().get(getRandomCell()).getBoundingBox();
            if (pTask.getColorJugador().equals("blue")) {
                gPer.crearPersonaje(1, 1, pTask.getColorJugador(), summonArea);
            }
            if (pTask.getColorJugador().equals("red")) {
                gPer.crearPersonajeEnemigo(summonArea);
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

    public int getRandomCell() {
        Random rand = new Random();
        return rand.nextInt(6);
    }


}

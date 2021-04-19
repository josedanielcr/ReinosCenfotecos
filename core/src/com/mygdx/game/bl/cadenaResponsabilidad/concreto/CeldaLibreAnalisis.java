package com.mygdx.game.bl.cadenaResponsabilidad.concreto;

import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class CeldaLibreAnalisis extends HandlerSummon {
    public CeldaLibreAnalisis(ControllerCelda gCell, ControllerPersonaje gPer) {
        super(gCell, gPer);
    }

    @Override
    public boolean executeTask(Task pTask) {
        boolean resultado = true;

        if (pTask.getType().equals("free")) {
            for (int i=0; i<pTask.getCellsPattern().size(); i++) {
                if (!pTask.getCellsPattern().get(i).getCellColor().getColor().equals("free")) {
                    resultado = false;
                    break;
                }
            }
        }
        else {
            if (successor!= null) {
                resultado = successor.executeTask(pTask);
            }
        }
        return resultado;
    }

}

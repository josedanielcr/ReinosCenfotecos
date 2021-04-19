package com.mygdx.game.bl.cadenaResponsabilidad.concreto;

import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class CeldaBordeAnalisis extends HandlerSummon {
    public CeldaBordeAnalisis(ControllerCelda gCell, ControllerPersonaje gPer) {
        super(gCell, gPer);
    }

    public boolean executeTask(Task pTask) {
        boolean resultado = true;

        if (pTask.getType().equals("border")) {
            int tam = pTask.getCellsPattern().size();
            for (int i=0; i<tam;i++) {
                if (i<tam-1) {
                    if (pTask.getCellsPattern().get(i).getId()%20==1) {
                        if (pTask.getCellsPattern().get(i + 1).getId() % 20 == 0) {
                            resultado = false;
                            break;
                        }
                    }
                    if (pTask.getCellsPattern().get(i).getId()%20==0) {
                        if (pTask.getCellsPattern().get(i + 1).getId() % 20 == 1) {
                            resultado = false;
                            break;
                        }
                    }

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

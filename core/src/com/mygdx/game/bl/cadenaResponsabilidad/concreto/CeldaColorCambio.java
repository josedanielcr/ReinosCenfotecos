package com.mygdx.game.bl.cadenaResponsabilidad.concreto;

import com.mygdx.game.bl.cadenaResponsabilidad.abstracto.HandlerSummon;
import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class CeldaColorCambio extends HandlerSummon {
    public CeldaColorCambio(ControllerCelda gCell, ControllerPersonaje gPer) {
        super(gCell, gPer);
    }

    public boolean executeTask(Task pTask) {
        boolean resultado = false;

        if (pTask.getType().equals("color")) {
            for (Celda c : pTask.getCellsPattern()) {
                this.gCell.changeColor(c.getId(), pTask.getColorJugador());
                resultado = true;
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

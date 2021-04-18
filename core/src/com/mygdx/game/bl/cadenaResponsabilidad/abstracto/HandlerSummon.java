package com.mygdx.game.bl.cadenaResponsabilidad.abstracto;

import com.mygdx.game.bl.cadenaResponsabilidad.objetos.Task;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public abstract class HandlerSummon {
    protected HandlerSummon successor;
    protected ControllerCelda gCell;
    protected ControllerPersonaje gPer;

    public HandlerSummon(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
    }

    public void setSuccessor(HandlerSummon pSuccessor) {
        this.successor = pSuccessor;
    }

    public abstract boolean executeTask(Task t);
}

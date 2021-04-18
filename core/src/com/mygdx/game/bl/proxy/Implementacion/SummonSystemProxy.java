package com.mygdx.game.bl.proxy.Implementacion;

import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.componente.Personaje;
import com.mygdx.game.bl.proxy.Interface.ISummonSystem;
import com.mygdx.game.tl.ControllerCelda;
import com.mygdx.game.tl.ControllerPersonaje;

public class SummonSystemProxy implements ISummonSystem {
    private final SummonSystem system;
    private ControllerCelda gCell;
    private ControllerPersonaje gPer;


    public SummonSystemProxy(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
        system = new SummonSystem(gCell, gPer);
    }

    public boolean validateCell(int pIdCelda, String pJugador) {
        boolean validation=false;
        Celda validatedCell = gCell.getCell(pIdCelda);
        if (validatedCell.getCellColor().getColor().equals(pJugador)) {
            validation = true;
        }
        return true; //TODO  como va a tener el color del jugador si no ha puesto la ficha? revertir esto a validation
    }

    @Override
    public String summon(int pIdCelda, String pIdPattern, String pJugador) {
        if (validateCell(pIdCelda, pJugador)) {
            return system.summon(pIdCelda, pIdPattern, pJugador);
        }
        else {
            return "Error en la convocación: La celda inicial no es de su color.";
        }
    }

    @Override
    public Personaje displayStats(int pIdPersonaje, String pJugador) {
        return null;
    }
}

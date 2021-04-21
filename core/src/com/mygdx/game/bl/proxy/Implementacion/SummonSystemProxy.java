package com.mygdx.game.bl.proxy.Implementacion;

import com.mygdx.game.bl.celdas.iPrototipo.Celda;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
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
        return validation;
    }

    @Override
    public String summon(int pIdCelda, String pIdPattern, String pJugador, int pType) {
        if (validateCell(pIdCelda, pJugador)) {
            return system.summon(pIdCelda, pIdPattern, pJugador, pType);
        }
        else {
            return "Error: The summoning starting cell is not under your control.";
        }
    }

    @Override
    public PersonajeAbstracto displayStats(int pIdPersonaje, String pJugador) {
        PersonajeAbstracto pTemp = null;
        if (pJugador.equals("blue")) {
            pTemp = gPer.retornarPersonajeDecorador(pIdPersonaje);
            if (pTemp!=null) {
                if (pTemp.getDuenno().equals(pJugador)) {
                    return system.displayStats(pIdPersonaje, pJugador);
                }
            }


        }
        if (pJugador.equals("red")) {
            pTemp = gPer.retornarPersonajeDecoradorEnemigue(pIdPersonaje);
            if (pTemp!=null) {
                if (pTemp.getDuenno().equals(pJugador)) {
                    return system.displayStats(pIdPersonaje, pJugador);
                }
            }

        }
        return pTemp;
    }

    public String moveUnit(int pIdPersonaje, int pIdCelda, String pIdJugador, String pMovimiento) {
        String report="Select a battle unit first.";
        PersonajeAbstracto pTemp;
        if (pIdJugador.equals("blue")) {
            pTemp = gPer.retornarPersonajeDecorador(pIdPersonaje);
            if (pTemp!=null) {
                if (pTemp.getDuenno().equals(pIdJugador)) {
                    report = system.moveUnit(pIdPersonaje,pIdCelda,pIdJugador,pMovimiento);
                }
                else {
                    report = "Unable to move a battle unit that is not under your control.";
                }
            }
        }
        if (pIdJugador.equals("red")) {
            pTemp = gPer.retornarPersonajeDecoradorEnemigue(pIdPersonaje);
            if (pTemp!=null) {
                if (pTemp.getDuenno().equals(pIdJugador)) {
                    report = system.moveUnit(pIdPersonaje,pIdCelda,pIdJugador,pMovimiento);
                }
                else {
                    report = "Unable to move a battle unit that is not under your control.";
                }
            }
        }
        return report;
    }
}

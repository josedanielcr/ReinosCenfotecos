package com.mygdx.game.tl;

import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.proxy.Implementacion.SummonSystemProxy;
import com.mygdx.game.bl.proxy.Interface.ISummonSystem;

public class ControllerProxy {
    private final ISummonSystem iSystem;
    private final ControllerCelda gCell;
    private final ControllerPersonaje gPer;

    public ControllerProxy(ControllerCelda gCell, ControllerPersonaje gPer) {
        this.gCell = gCell;
        this.gPer = gPer;
        iSystem = new SummonSystemProxy(gCell, gPer);

    }

    public String startSummon(int pIdCelda, String pIdPattern, String pJugador, int pType) {
        return iSystem.summon(pIdCelda, pIdPattern, pJugador, pType);
    }

    public PersonajeAbstracto getInfoPersonaje(int pIdPersonaje, String pJugador) {
        return iSystem.displayStats(pIdPersonaje, pJugador);
    }

    public String moveUnit(int pIdPersonaje, int pIdCelda, String pIdJugador, String pMovimiento) {
        return iSystem.moveUnit(pIdPersonaje,pIdCelda,pIdJugador,pMovimiento);
    }
}

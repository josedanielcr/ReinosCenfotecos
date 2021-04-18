package com.mygdx.game.bl.proxy.Interface;

import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.componente.Personaje;

public interface ISummonSystem {
    String summon(int pIdCelda, String pIdPattern, String pJugador, int pType);
    PersonajeAbstracto displayStats(int pIdPersonaje, String pJugador);
}

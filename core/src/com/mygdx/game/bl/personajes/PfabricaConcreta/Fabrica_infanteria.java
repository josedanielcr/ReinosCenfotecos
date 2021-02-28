package com.mygdx.game.bl.personajes.PfabricaConcreta;

import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;

public class Fabrica_infanteria implements PersonajeFA {
    @Override
    public PersonajeAbstracto crearPersonaje(int movimiento) {
        Infanteria infanteria = new Infanteria(movimiento);
        return infanteria;
    }
}
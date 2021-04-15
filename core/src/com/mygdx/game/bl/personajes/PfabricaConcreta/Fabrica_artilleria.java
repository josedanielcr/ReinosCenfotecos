package com.mygdx.game.bl.personajes.PfabricaConcreta;

import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Artilleria;

public class Fabrica_artilleria implements PersonajeFA {
    @Override
    public PersonajeAbstracto crearPersonaje(int movimiento, int id, String ataqueEspecial) {
        Artilleria artilleria = new Artilleria(movimiento,id, ataqueEspecial);
        return artilleria;
    }
}

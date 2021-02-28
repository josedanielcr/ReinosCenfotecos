package com.mygdx.game.bl.personajes.PfabricaConcreta;

import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Tanque;

public class Fabrica_tanque implements PersonajeFA {

    @Override
    public PersonajeAbstracto crearPersonaje(int movimiento) {
        Tanque tanque = new Tanque(movimiento);
        return tanque;
    }
}
package com.mygdx.game.bl.personajes.PfabricaConcreta;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Tanque;

public class Fabrica_tanque implements PersonajeFA {

    @Override
    public PersonajeAbstracto crearPersonaje( int id, String ataqueEspecial, String personajeActivo, Rectangle pBoundingBox) {
        Tanque tanque = new Tanque(id, ataqueEspecial, personajeActivo, pBoundingBox);
        return tanque;
    }
}

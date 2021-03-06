package com.mygdx.game.bl.personajes.PfabricaConcreta;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PfabricaAbstracta.PersonajeFA;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;
import com.mygdx.game.bl.personajes.PproductoConcreto.Infanteria;

public class Fabrica_infanteria implements PersonajeFA {
    @Override
    public PersonajeAbstracto crearPersonaje( int id, String ataqueEspecial, String personajeActivo, Rectangle pBoundingBox) {
        Infanteria infanteria = new Infanteria(id, ataqueEspecial,personajeActivo, pBoundingBox);
        return infanteria;
    }
}

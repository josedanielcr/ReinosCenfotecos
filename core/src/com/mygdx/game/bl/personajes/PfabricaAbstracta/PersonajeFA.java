package com.mygdx.game.bl.personajes.PfabricaAbstracta;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;


public interface PersonajeFA {
    PersonajeAbstracto crearPersonaje(int idP, String ataqueEspecial, String personajeActivo, Rectangle pBoundingBox);
}

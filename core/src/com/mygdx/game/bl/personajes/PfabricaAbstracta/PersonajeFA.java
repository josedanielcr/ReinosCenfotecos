package com.mygdx.game.bl.personajes.PfabricaAbstracta;

import com.mygdx.game.bl.personajes.PproductoAbstracto.PersonajeAbstracto;

public interface PersonajeFA {
    PersonajeAbstracto crearPersonaje( int idP, String ataqueEspecial, String personajeActivo);
}

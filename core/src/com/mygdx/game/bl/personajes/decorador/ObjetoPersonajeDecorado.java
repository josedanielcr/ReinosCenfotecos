package com.mygdx.game.bl.personajes.decorador;

import com.mygdx.game.bl.personajes.PproductoConcreto.Personaje;

public abstract class ObjetoPersonajeDecorado extends Personaje {

    protected Personaje cpersonaje;

    public Personaje getCpersonaje() {
        return cpersonaje;
    }
}

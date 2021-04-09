package com.mygdx.game.bl.personajes.decorador;

import com.mygdx.game.bl.personajes.PproductoConcreto.Personaje;

public abstract class ObjetoDecorado extends Personaje {
    protected Personaje cPersonaje;

    public Personaje getcPersonaje() {
        return cPersonaje;
    }
}

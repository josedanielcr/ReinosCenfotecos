package com.mygdx.game.bl.personajes.decoradoresConcretos;

import com.mygdx.game.bl.personajes.decorador.ObjetoPersonajeDecorado;

public class PersonajeDoblePoderDefensa extends ObjetoPersonajeDecorado {
    private int nuevaDefensa= this.cpersonaje.getDefensa()*2;

    public PersonajeDoblePoderDefensa (ObjetoPersonajeDecorado pPersonaje){
        this.cpersonaje = pPersonaje;
    }

    @Override
    public int getVida() {
        return this.cpersonaje.getVida();
    }

    @Override
    public int getAtaque() {
        return this.cpersonaje.getAtaque();
    }

    @Override
    public int getDefensa() {
        return nuevaDefensa;
    }

    @Override
    public int getMovimiento() {
        return this.cpersonaje.getMovimiento();
    }
}

package com.mygdx.game.bl.personajes.decoradoresConcretos;

import com.mygdx.game.bl.personajes.decorador.ObjetoPersonajeDecorado;

public class PersonajeDoblePoderAtaque extends ObjetoPersonajeDecorado {

    private int nuevoAtaque= this.cpersonaje.getAtaque()*2;

    public PersonajeDoblePoderAtaque (ObjetoPersonajeDecorado pPersonaje){
        this.cpersonaje = pPersonaje;
    }

    @Override
    public int getVida() {
        return this.cpersonaje.getVida();
    }

    @Override
    public int getAtaque() {
        return nuevoAtaque;
    }

    @Override
    public int getDefensa() {
        return this.cpersonaje.getDefensa();
    }
    @Override
    public int getMovimiento() {
        return this.cpersonaje.getMovimiento();
    }
}

package com.mygdx.game.bl.personajes.decoradoresConcretos;

import com.mygdx.game.bl.personajes.decorador.ObjetoPersonajeDecorado;

public class PersonajeAtaqueMas3 extends ObjetoPersonajeDecorado {

    private int nuevoAtaque= this.cpersonaje.getAtaque()+3;


    public PersonajeAtaqueMas3 (ObjetoPersonajeDecorado pPersonaje){
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

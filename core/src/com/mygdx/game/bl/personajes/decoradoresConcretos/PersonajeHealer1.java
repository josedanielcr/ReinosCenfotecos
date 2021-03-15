package com.mygdx.game.bl.personajes.decoradoresConcretos;

import com.mygdx.game.bl.personajes.decorador.ObjetoPersonajeDecorado;

public class PersonajeHealer1 extends ObjetoPersonajeDecorado {

    public PersonajeHealer1 (ObjetoPersonajeDecorado personajeObjetivo){// recibe el personaje al cual se le va a dar mas vida
        this.cpersonaje = personajeObjetivo;
    }


    @Override
    public int getVida() {
        return 0;
    }

    @Override
    public int getAtaque() {
        return 0;
    }

    @Override
    public int getDefensa() {
        return 0;
    }
}

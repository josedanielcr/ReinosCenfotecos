package com.mygdx.game.bl.personajes.decoradoresConcretos;

import com.mygdx.game.bl.personajes.decorador.ObjetoPersonajeDecorado;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

public class PersonajeVidaPor2Movimientos extends ObjetoPersonajeDecorado {
    private int nuevoMovimiento= this.cpersonaje.getMovimiento()*2;
    private int nuevaVida= this.cpersonaje.getVida()-1;

    public PersonajeVidaPor2Movimientos (ObjetoPersonajeDecorado pPersonaje){
        this.cpersonaje = pPersonaje;
    }

    @Override
    public int getVida() {
        return  nuevaVida;
    }

    @Override
    public int getAtaque() {
        return this.cpersonaje.getAtaque();
    }

    @Override
    public int getDefensa() {
        return this.cpersonaje.getDefensa();
    }

    @Override
    public int getMovimiento() {
        return nuevoMovimiento;
    }
}
